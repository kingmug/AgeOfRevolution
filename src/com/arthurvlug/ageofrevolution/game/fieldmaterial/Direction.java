package com.arthurvlug.ageofrevolution.game.fieldmaterial;

import lombok.Getter;

@Getter
public enum Direction {
     EAST,  NORTH_EAST,
     NORTH, NORTH_WEST,
     WEST, SOUTH_WEST,
     SOUTH, SOUTH_EAST;

     private final double angle;
     private final double x;
     private final double y;


     Direction() {
          angle = ordinal() * diag();
          x = Math.cos(angle);
          y = Math.sin(angle);
     }

     public static double diag() {
          return 0.25 * Math.PI;
     }
}
