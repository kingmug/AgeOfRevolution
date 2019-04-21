package com.arthurvlug.ageofrevolution.game.player;

import java.awt.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlayerId {
    PLAYER_1(Color.RED), PLAYER_2(Color.GREEN);
    
    private Color color;
}
