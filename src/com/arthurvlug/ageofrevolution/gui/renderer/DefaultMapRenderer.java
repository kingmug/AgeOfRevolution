package com.arthurvlug.ageofrevolution.gui.renderer;

import com.arthurvlug.ageofrevolution.game.Game;
import com.arthurvlug.ageofrevolution.game.map.MapElement;
import com.arthurvlug.ageofrevolution.utils.Debug;
import java.util.Collection;

public class DefaultMapRenderer implements MapRenderer {
    @Override
    public void render(final Game game, final Painter painter, final CustomGraphics g) {
        if(Debug.drawMap) {
            painter.drawMap(g);
        }
        if(Debug.drawOrthogonalGrid) {
            painter.drawOrthogonalGrid(g);
        }
        if(Debug.drawDiagonalGrid) {
            painter.drawDiagonalGrid(g, game.getGameMap().getSize());
        }
        if(Debug.drawFields) {
            Collection<MapElement> values = game.getGameMap().getFields().values();
            painter.drawFields(values, g);
        }
    }
}
