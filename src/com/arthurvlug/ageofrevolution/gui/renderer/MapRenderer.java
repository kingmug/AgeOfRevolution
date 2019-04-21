package com.arthurvlug.ageofrevolution.gui.renderer;

import com.arthurvlug.ageofrevolution.game.Game;

public interface MapRenderer {
    void render(final Game game, final Painter painter, final CustomGraphics g);
}
