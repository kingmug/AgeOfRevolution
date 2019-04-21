package com.arthurvlug.ageofrevolution.gui;

import com.arthurvlug.ageofrevolution.SelectionHolder;
import com.arthurvlug.ageofrevolution.game.Game;
import com.arthurvlug.ageofrevolution.game.MapCoordinate;
import com.arthurvlug.ageofrevolution.game.MapElement;
import com.arthurvlug.ageofrevolution.game.RenderableBuilding;
import com.arthurvlug.ageofrevolution.game.Selectable;
import com.arthurvlug.ageofrevolution.gui.renderer.AbsoluteLocation;
import com.arthurvlug.ageofrevolution.gui.renderer.Debug;
import com.arthurvlug.ageofrevolution.gui.renderer.LocationCalculator;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.SwingUtilities;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameMouseListener implements MouseListener, MouseMotionListener {
    private final SelectionHolder selectionHolder;
    private final GamePanel gamePanel;
    private final GameHolder gameHolder;
    private final LocationCalculator locationCalculator;
    private Optional<PixelLocation> clickLocation;
    
    @Override
    public void mouseMoved(final MouseEvent e) {
        mouseMovedPosition(e.getPoint());
    }
    
    @Override
    public void mouseClicked(final MouseEvent e) {
//        gameHolder.getGame().ifPresent(game -> {
//            if (SwingUtilities.isLeftMouseButton(e)) {
//                leftClick(e.getPoint(), game);
//            }
//        });
    }
    
    @Override
    public void mousePressed(final MouseEvent e) {
        gameHolder.getGame().ifPresent(game -> {
            if (SwingUtilities.isLeftMouseButton(e)) {
                leftPress(e.getPoint());
            } else {
                rightPress(e.getPoint(), game);
            }
        });
    }
    
    @Override
    public void mouseReleased(final MouseEvent e) {
        gameHolder.getGame().ifPresent(game -> {
            //          System.out.println("Mouse released");
            if (SwingUtilities.isLeftMouseButton(e)) {
                leftClick(e.getPoint(), game);
            }
        });
    }
    
    @Override
    public void mouseDragged(final MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            leftDrag(e.getPoint());
        }
    }
    
    private void mouseMovedPosition(final Point point) {
        if (Debug.logMouseListener) {
            System.out.println();
            System.out.println("Mouse Listener: mouseMovedPosition");
        }
        
        final PixelLocation pixelLocation = new PixelLocation(point);
        final AbsoluteLocation absolutePosition = locationCalculator.absoluteLocation(pixelLocation);
        System.out.println(selectionHolder.getSelection().getSelectables());
        gamePanel.setMousePosition(absolutePosition);
    }
    
    /**
     * Draw selection rectangle
     *
     * @param point
     */
    private void leftDrag(final Point point) {
        if (Debug.logMouseListener) {
            System.out.println();
            System.out.println("Mouse Listener: leftDrag");
        }
    
        gamePanel.mouseDragged(clickLocation.get(), new PixelLocation(point));
    }
    
    /**
     * Store the position so we can use it when we drag
     *
     * @param point
     */
    private void leftPress(final Point point) {
        if (Debug.logMouseListener) {
            System.out.println();
            System.out.println("Mouse Listener: leftPress");
        }

        this.clickLocation = Optional.of(new PixelLocation(point));
    }
    
    /**
     * Move the units to the location
     */
    private void rightPress(final Point point, final Game game) {
        if (Debug.logMouseListener) {
            System.out.println();
            System.out.println("Mouse Listener: rightPress");
        }


//          System.out.println("Clicked right");
        PixelLocation pixelLocation = new PixelLocation(point);
        move(selectionHolder.getSelection(), pixelLocation, game);
//                         gamePanel.mouseReleased(Selection.EMPTY);
        
        // TODO: Add back
//                         this.selection = Optional.empty();
    }
    
    private void leftClick(final Point point, final Game game) {
        if (Debug.logMouseListener) {
            System.out.println();
            System.out.println("Mouse Listener: leftClick");
        }
    
        final Set<Selectable> selectables = selectionHolder.getSelection().getSelectables();
        final Optional<RenderableBuilding> buildingToBuild = buildingToBuild(selectables);
        if (buildingToBuild.isPresent()) {
            final RenderableBuilding renderableBuilding = buildingToBuild.get();
            putBuilding(renderableBuilding, point, game);
        } else {
            final PixelLocation clickLocation = this.clickLocation.get();
            final PixelLocation topLeftLocation = new PixelLocation(
                    Math.min(clickLocation.x, point.x),
                    Math.min(clickLocation.y, point.y));
            final PixelLocation bottomRightLocation = new PixelLocation(
                    Math.max(clickLocation.x, point.x),
                    Math.max(clickLocation.y, point.y));
            final Selection newSelection = selectInRectangle(topLeftLocation, bottomRightLocation, game);
            //               System.out.printf("Selection: %s%n", selection);
            this.selectionHolder.setSelection(newSelection);
            gamePanel.mouseReleased();
    
            if (Debug.logMouseListener) {
                System.out.printf("ClickLocation: %s%n", this.clickLocation);
                System.out.printf("topLeftLocation: %s%n", topLeftLocation);
                System.out.printf("bottomRightLocation: %s%n", bottomRightLocation);
                System.out.printf("selection: %s%n", newSelection);
                System.out.println();
            }
        }
        this.clickLocation = Optional.empty();
        
    }
    
    private Optional<RenderableBuilding> buildingToBuild(final Set<Selectable> selectables) {
        if(selectables.size() != 1) {
            return Optional.empty();
        }
        final Selectable firstSelectable = selectables.iterator().next();
        if(firstSelectable instanceof RenderableBuilding) {
            return Optional.of((RenderableBuilding) firstSelectable);
        }
        return Optional.empty();
    }
    
    private void putBuilding(final RenderableBuilding building, final Point point, final Game game) {
        if (Debug.logMouseListener) {
            System.out.println("Putting building");
        }
        final MapCoordinate coordinate = locationCalculator.mapCoordinate(new PixelLocation(point));
        game.getGameMap().putBuilding(coordinate, building);
    }
    
    private void move(final Selection selection, final PixelLocation pixelLocation, final Game game) {
        final AbsoluteLocation absoluteLocation = locationCalculator.absoluteLocation(pixelLocation);
//          System.out.println("Moving units " + selection + " to " + absoluteLocation);
        final MapCoordinate mapCoordinate = locationCalculator.mapCoordinate(absoluteLocation);
        MapElement mapElement = game.getGameMap().getMapElement(mapCoordinate);
        
        game.moveUnits(selection, absoluteLocation, mapElement.getResource());
    }
    
    private Selection selectInRectangle(final PixelLocation topLeftLocation, final PixelLocation bottomRightLocation, final Game game) {
        final Set<Selectable> units = selectUnits(topLeftLocation, bottomRightLocation, game);
        if (!units.isEmpty()) {
            return new Selection(units);
        }
        final Set<Selectable> buildings = selectBuildings(topLeftLocation, bottomRightLocation);
        return new Selection(buildings);
    }
    
    private Set<Selectable> selectUnits(final PixelLocation topLeftLocation, final PixelLocation bottomRightLocation, final Game game) {
        return select(element -> element.getUnit().isPresent() && isInRectangle(element, topLeftLocation, bottomRightLocation), game).stream()
                .flatMap(mapElement -> mapElement.getUnit().map(Stream::of)
                        .orElse(Stream.of()))
                .collect(Collectors.toSet());
    }
    
    private boolean isInRectangle(final MapElement element, final PixelLocation topLeftLocation, final PixelLocation bottomRightLocation) {
        final AbsoluteLocation location = element.getCenterLocation();
        // TODO: The unit is not one point but a rectangle.
        // Also will fail when clicking on a unit.
        final PixelLocation unitPixelLocation = locationCalculator.pixelLocation(location);
        boolean betweenX = between(unitPixelLocation.x, topLeftLocation.x, bottomRightLocation.x);
        boolean betweenY = between(unitPixelLocation.y, topLeftLocation.y, bottomRightLocation.y);
        return betweenX && betweenY;
    }
    
    private boolean between(final int v, final int min, final int max) {
        return v >= min && v <= max;
    }
    
    private Set<MapElement> select(final Predicate<MapElement> selector, final Game game) {
        final Collection<MapElement> fields = game.getGameMap().getFields().values();
        return fields.stream()
                .filter(selector)
                .collect(Collectors.toSet());
    }
    
    private Set<Selectable> selectBuildings(final PixelLocation topLeftLocation, final PixelLocation bottomRightLocation) {
        // TODO: Implement
        return new HashSet<>();
    }
    
    private Selection selectOnPixelLocation(final PixelLocation pixelLocation, final Game game) {
        return selectInRectangle(pixelLocation, pixelLocation, game);
    }
    
    
    @Override
    public void mouseEntered(final MouseEvent e) {
    }
    
    @Override
    public void mouseExited(final MouseEvent e) {
    }
}
