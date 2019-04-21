package com.arthurvlug.ageofrevolution.game.move;

import com.arthurvlug.ageofrevolution.game.renderable.Direction;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AngleDirection {
     private double angle;
     private Direction dir;
}
