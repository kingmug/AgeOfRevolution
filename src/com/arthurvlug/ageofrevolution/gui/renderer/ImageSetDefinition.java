package com.arthurvlug.ageofrevolution.gui.renderer;

import com.arthurvlug.ageofrevolution.game.renderable.Direction;
import com.arthurvlug.ageofrevolution.game.renderable.RenderableUnit;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.arthurvlug.ageofrevolution.game.renderable.Direction.*;

@Getter
@AllArgsConstructor
public class ImageSetDefinition {
     private int direction;
     private boolean flipped;

     public static Map<Direction, ImageSetDefinition> mapping = createMapping();

     private static Map<Direction, ImageSetDefinition> createMapping() {
          final Map<Direction, ImageSetDefinition> map = new HashMap<>();
          map.put(SOUTH,      new ImageSetDefinition(0, false));
          map.put(SOUTH_WEST, new ImageSetDefinition(1, false));
          map.put(WEST,       new ImageSetDefinition(2, false));
          map.put(NORTH_WEST, new ImageSetDefinition(3, false));
          map.put(NORTH,      new ImageSetDefinition(4, false));
          map.put(NORTH_EAST, new ImageSetDefinition(3, true));
          map.put(EAST,       new ImageSetDefinition(2, true));
          map.put(SOUTH_EAST, new ImageSetDefinition(1, true));
          return map;
     }

     static ImageSetDefinition valueOf(RenderableUnit unit) {
          return mapping.get(unit.getDirection());
     }
}
