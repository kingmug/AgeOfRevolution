package com.arthurvlug.ageofrevolution.gui.location;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class Locations {
     public static PixelLocation move(PixelLocation pixelLocation, int dx, int dy) {
          return new PixelLocation(
                    pixelLocation.x + dx,
                    pixelLocation.y + dy
          );
     }

     public static AbsoluteLocation move(AbsoluteLocation location, int dx, int dy) {
          return new AbsoluteLocation(
                    location.x + dx,
                    location.y + dy
          );
     }
}
