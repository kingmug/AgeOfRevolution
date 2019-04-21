package com.arthurvlug.ageofrevolution.gui.location;

import java.awt.Point;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class PixelLocation {
     public final int x;
     public final int y;

     public PixelLocation(final Point point) {
          this(point.x, point.y);
     }
}
