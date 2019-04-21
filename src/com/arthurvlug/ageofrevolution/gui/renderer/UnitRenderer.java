package com.arthurvlug.ageofrevolution.gui.renderer;

import com.arthurvlug.ageofrevolution.game.Renderable;
import com.arthurvlug.ageofrevolution.game.fieldmaterial.Activity;
import com.arthurvlug.ageofrevolution.game.fieldmaterial.Status;
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
