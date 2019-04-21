package com.arthurvlug.ageofrevolution.gui.renderer;

import com.arthurvlug.ageofrevolution.game.renderable.Renderable;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.Activity;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.Status;
import com.arthurvlug.ageofrevolution.gui.location.LocationCalculator;
import com.arthurvlug.ageofrevolution.utils.ImageFileLoader;

public abstract class UnitRenderer<T extends Renderable> extends FieldComponentRenderer<T> {
     public UnitRenderer(final ImageFileLoader imageLoader,
                         final LocationCalculator locationCalculator,
                         final PainterConfig painterConfig) {
          super(imageLoader, locationCalculator, painterConfig);
     }

     protected String getUnitName() {
          return getClass().getSimpleName().replace("Renderer", "");
     }

     protected String filePrefix(Activity activity, Status status) {
          return getUnitName() + status.getFolderName().split(" ")[0].toLowerCase();
     }

}
