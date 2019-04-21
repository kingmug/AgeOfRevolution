package com.arthurvlug.ageofrevolution.gui.renderer;

import com.arthurvlug.ageofrevolution.game.MapElement;
import java.util.Collection;

public interface Painter {
    void drawMap(final CustomGraphics g);
    
    void drawDiagonalGrid(CustomGraphics g, final int size);
    
    void drawOrthogonalGrid(CustomGraphics g);
    
    void drawFields(Collection<MapElement> fields, final CustomGraphics g);
}
