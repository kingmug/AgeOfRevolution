package com.arthurvlug.ageofrevolution.gui.renderer;

import com.arthurvlug.ageofrevolution.game.MapCoordinate;
import com.arthurvlug.ageofrevolution.gui.PixelLocation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LocationCalculator {    
    private final PainterConfig painterConfig;
    
    // TODO: Use this    
    private final ViewPort viewPort;
    
    private final int size;
    
    public AbsoluteLocation absoluteLocation(final PixelLocation pixelLocation) {    
        // TODO: Fix this    
        return new AbsoluteLocation(    
               pixelLocation.x - size * fieldWidth() * 0.5,
               pixelLocation.y - size * fieldHeight() * 0.5);
    }
    
    public AbsoluteLocation absoluteLocation(final MapCoordinate mapCoordinate) {    
        final double width = fieldWidth();
        final double height = fieldHeight();
    
        final int x = mapCoordinate.getX();    
        final int y = mapCoordinate.getY();    
        final int xPos = (int) ((x+y) * width/2);    
        final int yPos = (int) ((y-x) * height/2);    
        return new AbsoluteLocation(    
               xPos + 0.5 * width - (size/2) * width,    
               yPos);
    
    }

//    public AbsoluteLocation absoluteLocation(final AbsoluteLocation absoluteLocation) {
//        return new AbsoluteLocation(
//               absoluteLocation.x + painterConfig.getFieldWidth() * 0.5,
//               absoluteLocation.y
//        );
//    }
    
    public MapCoordinate mapCoordinate(final AbsoluteLocation absoluteLocation) {    
        double width = (double) fieldWidth();
        double height = (double) fieldHeight();
    
        final double xPos = absoluteLocation.x;    
        final double yPos = absoluteLocation.y;
    
        double p = xPos / width;    
        double q = yPos / height;    
        final double x = p - q;    
        final double y = p + q;

// 4: (.5W,0) -> (2,2)

// 2: (-.5W,0) -> (0,0)
// 2: (-.6W,0) -> (0,0)
// 2: (.5W,0) -> (1,1)
        return new MapCoordinate(
                 (int) Math.floor(x) + size/2,
                 (int) Math.floor(y) + size/2);
    }

    public PixelLocation pixelLocation(final MapCoordinate mapCoordinate) {
        return pixelLocation(absoluteLocation(mapCoordinate));
    }
    
    public PixelLocation pixelLocation(final AbsoluteLocation absoluteLocation) {
        // 2: (0,0) -> (w,h)
        // 4: (0,0) -> (2w,2h)
        return new PixelLocation(
                (int) (absoluteLocation.x + size/2*fieldWidth()),
                (int) (absoluteLocation.y + size/2*fieldHeight()));
    }

    public MapCoordinate mapCoordinate(final PixelLocation pixelLocation) {
        return mapCoordinate(absoluteLocation(pixelLocation));
    }

    private int fieldWidth() {
        return painterConfig.getFieldWidth();
    }

    private int fieldHeight() {
        return painterConfig.getFieldHeight();
    }
}