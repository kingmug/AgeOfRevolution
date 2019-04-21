package com.arthurvlug.ageofrevolution.gui.component;

import com.arthurvlug.ageofrevolution.game.selection.Selection;
import com.arthurvlug.ageofrevolution.game.selection.SelectionHolder;
import com.arthurvlug.ageofrevolution.game.map.MapCoordinate;
import com.arthurvlug.ageofrevolution.game.renderable.building.RenderableBuilding;
import com.arthurvlug.ageofrevolution.game.selection.Selectable;
import com.arthurvlug.ageofrevolution.game.GameHolder;
import com.arthurvlug.ageofrevolution.gui.interaction.GameMouseListener;
import com.arthurvlug.ageofrevolution.gui.location.PixelLocation;
import com.arthurvlug.ageofrevolution.gui.location.AbsoluteLocation;
import com.arthurvlug.ageofrevolution.gui.renderer.CustomGraphics;
import com.arthurvlug.ageofrevolution.utils.Debug;
import com.arthurvlug.ageofrevolution.game.GameLoop;
import com.arthurvlug.ageofrevolution.gui.location.LocationCalculator;
import com.arthurvlug.ageofrevolution.gui.renderer.MapRenderer;
import com.arthurvlug.ageofrevolution.gui.renderer.Painter;
import com.arthurvlug.ageofrevolution.gui.renderer.RenderFactory;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JPanel;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class GamePanel extends JPanel {
    private final GameHolder gameHolder;
    private final MapRenderer mapRenderer;
    private final Painter painter;
    private final BufferedImage gameImage;
    private final BufferedImage tmpImage;
    private final SelectionHolder selectionHolder;
    private final LocationCalculator locationCalculator;
    private final RenderFactory renderFactory;

    private Optional<Rectangle> selectionRectangle = Optional.empty();
    private AbsoluteLocation mousePosition;

    private static long renderCount = 0;

    private static final ExecutorService gameRefreshThread = Executors.newFixedThreadPool(1);
    private Set<? extends Selectable> currentlySelected = new HashSet<>();
    
    public GamePanel(final GameHolder gameHolder, final MapRenderer mapRenderer, final Painter painter, final SelectionHolder selectionHolder, final LocationCalculator locationCalculator,
                     final int width, final int height, final RenderFactory renderFactory) {
        this.gameHolder = gameHolder;
        this.mapRenderer = mapRenderer;
        this.painter = painter;
        this.selectionHolder = selectionHolder;
        this.locationCalculator = locationCalculator;
        this.renderFactory = renderFactory;
    
        gameImage = new BufferedImage(width, height, TYPE_INT_RGB);
        tmpImage = new BufferedImage(width, height, TYPE_INT_RGB);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        final CustomGraphics g2 = CustomGraphics.fromGraphics(g, this.getSize());
        Debug.customGraphics = g2;
        if(renderCount % GameLoop.gameRefreshSampleRate == 0) {
            gameRefreshThread.submit(() -> this.replaceGameImage(g2));
        }
        drawScreen(g2);
        renderCount++;
    }

    private void replaceGameImage(final CustomGraphics g) {
        try {
            if(Debug.redraw) {
                System.out.println("Redrawing..");
            }
            final CustomGraphics newGraphics = new CustomGraphics((Graphics2D) tmpImage.getGraphics(), tmpImage.getRaster().getBounds().getSize());

            newGraphics.clear();

            gameHolder.getGame().ifPresent(game -> mapRenderer.render(game, painter, newGraphics));

            final Graphics2D gameGraphics = (Graphics2D) gameImage.getGraphics();
            gameGraphics.drawImage(tmpImage, 0, 0, null);
            drawScreen(g);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void drawScreen(final CustomGraphics g) {
        drawFields(g);
        drawSelectionRectangle(g);
        drawSelectionBuilding(g);
    }

    private void drawSelectionBuilding(final CustomGraphics g) {
        Set<Selectable> selectables = selectionHolder.getSelection().getSelectables();
        if(selectables.size() == 1) {
            final Selectable selectable = selectables.iterator().next();
            if(selectable instanceof RenderableBuilding) {
                final MapCoordinate mapCoordinate = locationCalculator.mapCoordinate(mousePosition);
                final PixelLocation pixelLocation = locationCalculator.pixelLocation(mapCoordinate);
                drawBuilding(selectable, pixelLocation, g);
            }
        }
    }

    private void drawBuilding(final Selectable selectable, final PixelLocation pixelLocation, final CustomGraphics g) {
        final AbsoluteLocation absoluteLocation = locationCalculator.absoluteLocation(pixelLocation);
        if(Debug.logDrawField) {
            System.out.printf("Building @ %s //// %s%n", locationCalculator.mapCoordinate(pixelLocation), pixelLocation);
        }
        renderFactory.getRenderer(selectable)
                     .render(selectable, absoluteLocation, g);
    }

    private void drawSelectionRectangle(final CustomGraphics g) {
        selectionRectangle.ifPresent(selection -> {
            g.setColor(Color.GRAY);
            g.drawRect(new PixelLocation(selection.x, selection.y), selection.width, selection.height);
        });
    }

    private void drawFields(final CustomGraphics g) {
        g.drawImage(gameImage, new PixelLocation(0, 0));
    }

    public void mouseDragged(final PixelLocation clickLocation, final PixelLocation dragLocation) {
        int minX = Math.min(clickLocation.x, dragLocation.x);
        int minY = Math.min(clickLocation.y, dragLocation.y);
        int maxX = Math.max(clickLocation.x, dragLocation.x);
        int maxY = Math.max(clickLocation.y, dragLocation.y);
        int width = maxX - minX;
        int height = maxY - minY;
        this.selectionRectangle = Optional.of(new Rectangle(minX, minY, width, height));
    }

    public void mouseReleased() {
        final Selection selection = selectionHolder.getSelection();
        toggleSelectedObjects(selection);
        this.currentlySelected = selection.getSelectables();

        selectionRectangle = Optional.empty();
    }

    private void toggleSelectedObjects(final Selection selection) {
        currentlySelected.forEach(s -> s.setSelected(false));
        selection.getSelectables().forEach(s -> s.setSelected(true));
    }

    public void initialise(final GameMouseListener gameMouseListener) {
        addMouseListener(gameMouseListener);
        addMouseMotionListener(gameMouseListener);
    }

    public void setMousePosition(final AbsoluteLocation mousePosition) {
        this.mousePosition = mousePosition;
    }
}
