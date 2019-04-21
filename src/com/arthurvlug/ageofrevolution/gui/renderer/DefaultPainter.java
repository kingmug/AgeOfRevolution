package com.arthurvlug.ageofrevolution.gui.renderer;

import com.arthurvlug.ageofrevolution.game.map.MapCoordinate;
import com.arthurvlug.ageofrevolution.game.map.MapElement;
import com.arthurvlug.ageofrevolution.game.renderable.Renderable;
import com.arthurvlug.ageofrevolution.game.renderable.building.RenderableBuilding;
import com.arthurvlug.ageofrevolution.game.renderable.resource.RenderableResource;
import com.arthurvlug.ageofrevolution.game.renderable.RenderableUnit;
import com.arthurvlug.ageofrevolution.gui.location.AbsoluteLocation;
import com.arthurvlug.ageofrevolution.gui.location.LocationCalculator;
import com.arthurvlug.ageofrevolution.gui.location.Locations;
import com.arthurvlug.ageofrevolution.gui.location.PixelLocation;
import com.arthurvlug.ageofrevolution.utils.Debug;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Optional;
import lombok.AllArgsConstructor;

import static java.awt.Color.GRAY;

@AllArgsConstructor
public class DefaultPainter implements Painter {
    private static final Color BACKGROUND_COLOR = new Color(0.9F, 0.9F, 0.9F);
    static final double SQRT_2 = Math.sqrt(2);
    
    private final PainterConfig painterConfig;
    private final ResourceRenderer resourceRenderer;
    private final LocationCalculator locationCalculator;
    private final RenderFactory renderFactory;

    private static final String GROUND_IMAGE_PATH = "Terrains/g_ds3_00_color.png";
    private static Optional<BufferedImage> transformedGroundImage = Optional.empty();
    
    @Override
    public void drawFields(final Collection<MapElement> fields, final CustomGraphics g) {
        fields.forEach(field -> drawField(field, g));
        if (Debug.logDrawField) {
            System.out.println();
        }
    }
    
    public void drawField(final MapElement mapElement, final CustomGraphics g) {
//        AbsoluteLocation AbsoluteLocation = mapElement.getCenterLocation();
//        if(Math.random() < 0.5) {
        final AbsoluteLocation centerLocation = mapElement.getCenterLocation();
//        }
//        AbsoluteLocation centerOfFieldLocation = absoluteLocation;
//        if(Debug.logDrawField) {
//            System.out.println(centerOfFieldLocation);
//            centerOfFieldLocation = Locations.move(centerOfFieldLocation, 0, -squareSize);
//        }
        
        // TODO: Delete & replace with surface image
        if(Debug.logDrawField) {
            System.out.printf("Drawing mapElement %s%n", mapElement);
        }
//        g.setColor(Color.orange);
//        drawDiamond(absoluteLocation, g);
        if (mapElement.getResource().isPresent()) {
            final Renderable renderableResource = mapElement.getResource().get();
            renderFactory.getRenderer(renderableResource).render(renderableResource, centerLocation, g);
        } else if (mapElement.getUnit().isPresent()) {
            final RenderableUnit renderableUnit = mapElement.getUnit().get();
            drawUnit(g, centerLocation, renderableUnit);
        } else if (mapElement.getBuilding().isPresent()) {
            final Renderable renderableBuilding = mapElement.getBuilding().get();
            renderFactory.getRenderer(renderableBuilding).render(renderableBuilding, centerLocation, g);
        } else {
            drawEmpty(centerLocation, g);
        }
//        g.drawCircleAround(locationCalculator.pixelLocation(absoluteLocation));
    }
    
    private void drawBuilding(final CustomGraphics g, final AbsoluteLocation centerLocation, final RenderableBuilding renderableBuilding) {
    }
    
    private void drawDiamond(final AbsoluteLocation absoluteLocation, final CustomGraphics g) {
        double dx = 0.5 * painterConfig.getFieldWidth();
        double dy = 0.5 * painterConfig.getFieldHeight();
        
        final int px1 = (int) (absoluteLocation.x - dx);
        final int py1 = (int) (absoluteLocation.y);
        final int px2 = (int) (absoluteLocation.x);
        final int py2 = (int) (absoluteLocation.y - dy);
        final int px3 = (int) (absoluteLocation.x + dx);
        final int py3 = (int) (absoluteLocation.y);
        final int px4 = (int) (absoluteLocation.x);
        final int py4 = (int) (absoluteLocation.y + dy);
        
        final PixelLocation p1 = locationCalculator.pixelLocation(new AbsoluteLocation(px1, py1));
        final PixelLocation p2 = locationCalculator.pixelLocation(new AbsoluteLocation(px2, py2));
        final PixelLocation p3 = locationCalculator.pixelLocation(new AbsoluteLocation(px3, py3));
        final PixelLocation p4 = locationCalculator.pixelLocation(new AbsoluteLocation(px4, py4));
        
        g.drawLine(p1, p2);
        g.drawLine(p2, p3);
        g.drawLine(p3, p4);
        g.drawLine(p4, p1);
    }
    
    private void drawSquare(final AbsoluteLocation absoluteLocation, final int width, final int height, final CustomGraphics g) {
        g.setColor(GRAY);
        g.drawRect(locationCalculator.pixelLocation(absoluteLocation), width, height);
    }
    
    private AbsoluteLocation centerOfFieldLocation(final AbsoluteLocation absoluteLocation) {
        return new AbsoluteLocation(
                absoluteLocation.x + painterConfig.getSquareSize(),
                absoluteLocation.y - painterConfig.getSquareSize());
    }
    
    @Override
    public void drawMap(final CustomGraphics g) {
        final BufferedImage groundImage = resourceRenderer.getImage(GROUND_IMAGE_PATH, false);
        if (!transformedGroundImage.isPresent()) {
            transformedGroundImage = Optional.of(createIsometricImage(groundImage));
        }
        final BufferedImage image = transformedGroundImage.get();
        
        if (Debug.drawMapLogs) {
            System.out.println();
            System.out.println("Drawing map...");
        }
        final int fieldsInTexture = 7;
        final int renderedTextures = 3;
        for (int i = 0; i < renderedTextures; i++) {
            for (int j = 0; j < renderedTextures; j++) {
                final MapCoordinate mapCoordinate = new MapCoordinate(i * fieldsInTexture, j * fieldsInTexture);
                final AbsoluteLocation absoluteLocation = locationCalculator.absoluteLocation(mapCoordinate);
                final PixelLocation pixelLocation = locationCalculator.pixelLocation(absoluteLocation);
                final PixelLocation middleLeftPixelLocation = Locations.move(pixelLocation, 0, -image.getHeight() / 2);
    
                if (Debug.drawMapLogs) {
                    System.out.printf("%s: %s / %s%n", mapCoordinate, absoluteLocation, middleLeftPixelLocation);
                }
                g.drawImage(image, middleLeftPixelLocation);
            }
        }
    }
    
    @Override
    public void drawDiagonalGrid(final CustomGraphics g, final int size) {
        if (Debug.drawGridLogs) {
            System.out.println();
            System.out.println("Drawing grid...");
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final MapCoordinate mapCoordinate = new MapCoordinate(i, j);
                final AbsoluteLocation absoluteLocation = locationCalculator.absoluteLocation(mapCoordinate);
                if (Debug.drawGridLogs) {
                    System.out.println("Absolute location: " + absoluteLocation);
                }
                g.setColor(Color.green);
                drawDiamond(absoluteLocation, g);
            }
        }
    }
    
    @Override
    public void drawOrthogonalGrid(final CustomGraphics g) {
        g.setColor(Color.BLUE);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                final PixelLocation pixelLocation = new PixelLocation(i * 100, j * 100);
                g.drawRect(pixelLocation, 100, 100);
            }
        }
    }
    
    
    private BufferedImage createIsometricImage(final BufferedImage groundImage) {
        return stretch(rotate(groundImage));
    }
    
    private BufferedImage stretch(final BufferedImage image) {
        final int w = image.getWidth();
        final int h = image.getHeight();
        
        BufferedImage after = new BufferedImage(w, h / 2, BufferedImage.TYPE_INT_ARGB);
        final AffineTransform at = new AffineTransform();
        at.scale(1.0, 0.5);
        final AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = scaleOp.filter(image, after);
        
        return after;
    }
    
    private BufferedImage rotate(final BufferedImage image) {
        final int w = image.getWidth();
        final int h = image.getHeight();
        
        int newWidth = (int) (image.getWidth() * SQRT_2);
        int newHeight = (int) (image.getHeight() * SQRT_2);
        
        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);
        
        at.rotate(Math.toRadians(45), w / 2, h / 2);
        g2d.setTransform(at);
        g2d.drawImage(image, 0, 0, null);
        g2d.setColor(Color.RED);
        g2d.drawRect(0, 0, newWidth - 1, newHeight - 1);
        return rotated;
    }
    
    private void drawUnit(final CustomGraphics g, final AbsoluteLocation centerLocation, final RenderableUnit unit) {
        renderFactory.getRenderer(unit).render(unit, centerLocation, g);
        unit.increaseVariant();
    }
    
    private void drawResource(final CustomGraphics g, final AbsoluteLocation absoluteLocation, final RenderableResource resource) {
        switch (resource.getResourceType()) {
//            case BUSH: resourceRenderer.drawBush(xPos, yPos, width, height, resource, g); break;
//            case TREE: resourceRenderer.drawTree(absoluteLocation, width, resource, g); break;
//            case STONE: resourceRenderer.drawStone(absoluteLocation, width, resource, g); break;
//            case GOLD: resourceRenderer.drawGold(absoluteLocation, width, resource, g); break;
            default:
                throw new RuntimeException("Drawing resource type " + resource.getResourceType() + " is not supported");
        }
    }
    
    private void drawEmpty(final AbsoluteLocation location, final CustomGraphics g) {
//        g.setColor(BACKGROUND_COLOR);
//        g.fillRect((int) absoluteLocation.x, (int) absoluteLocation.y, width, height);
    }
    
}
