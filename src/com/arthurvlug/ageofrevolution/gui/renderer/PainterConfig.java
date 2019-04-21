package com.arthurvlug.ageofrevolution.gui.renderer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PainterConfig {
    private static final int fieldRatio = 2;

    private final int diamondHeight;

    int getSquareSize() {
        return getFieldWidth();
    }

    public int getFieldWidth() {
        return diamondHeight * fieldRatio;
    }

    public int getFieldHeight() {
        return diamondHeight;
    }
}
