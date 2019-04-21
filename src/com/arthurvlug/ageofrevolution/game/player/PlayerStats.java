package com.arthurvlug.ageofrevolution.game.player;

import lombok.Builder;

@Builder
public class PlayerStats {
    private final int wood;
    private final int food;
    private final int gold;
    private final int stone;
    private final int population;
}
