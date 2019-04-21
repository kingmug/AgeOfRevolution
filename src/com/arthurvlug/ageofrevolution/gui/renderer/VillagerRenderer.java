package com.arthurvlug.ageofrevolution.gui.renderer;

import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.RenderableVillager;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.Activity;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.CarryingResource;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.Status;
import com.arthurvlug.ageofrevolution.gui.location.AbsoluteLocation;
import com.arthurvlug.ageofrevolution.gui.location.LocationCalculator;
import com.arthurvlug.ageofrevolution.gui.location.Locations;
import com.arthurvlug.ageofrevolution.utils.Debug;
import com.arthurvlug.ageofrevolution.utils.ImageFileLoader;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Optional;

import static com.arthurvlug.ageofrevolution.game.renderable.unit.villager.Activity.FISH;
import static com.arthurvlug.ageofrevolution.game.renderable.unit.villager.Activity.WOODCUTTER;

public class VillagerRenderer extends UnitRenderer<RenderableVillager> {
     public VillagerRenderer(final ImageFileLoader imageLoader,
                             final LocationCalculator locationCalculator,
                             final PainterConfig painterConfig) {
          super(imageLoader, locationCalculator, painterConfig);
     }

     @Override
     public void render(final RenderableVillager villager, final AbsoluteLocation centerLocation, final CustomGraphics g) {
          final BufferedImage image = getVillagerImage(villager, g);
          if(villager.isSelected()) {
               drawCircle(centerLocation, image, g);
               drawHealth(centerLocation, image, g);
          }
          drawVillager(centerLocation, image, g);
     }

     private void drawVillager(final AbsoluteLocation centerLocation, final Image image, final CustomGraphics g) {
         if(Debug.logDrawField) {
             System.out.println(centerLocation);
         }
         drawImage(image, image.getWidth(null), Locations.move(centerLocation, 0, 5), g);
     }

     private BufferedImage getVillagerImage(final RenderableVillager unit, final CustomGraphics g) {
          g.setColor(unit.getPlayerColor().getColor());
          final ImageSetDefinition imageSetDefinition = ImageSetDefinition.valueOf(unit);
          final int images = unit.getStatus().getImages();
          final int t = unit.getTime() % images + images * imageSetDefinition.getDirection();
          return getImage(resourcePath(unit, t), imageSetDefinition.isFlipped());
     }

     private String resourcePath(final RenderableVillager unit, final int idx) {
          final String idxStr = pad(idx + 1, 3);

          return String.format("Units/%s/%s/%s/%s/%s%s%s.png",
                    getUnitName(),
                    unit.getGender().getFolderName(),
                    unit.getActivity().getFolderName(),
                    unit.getStatus().getFolderName(),
                    getCarryingResource(unit).map(x -> x.getFolderName() + "/").orElse(""),
                    filePrefix(unit.getActivity(), unit.getStatus()),
                    idxStr);
     }

     private Optional<CarryingResource> getCarryingResource(final RenderableVillager unit) {
          return unit.getCarryingResource();
     }

     /**
      * Overriding since the pattern has a bug in it (the 'r' is missing in 'Villager')
      *
      * @param activity
      * @param status
      * @return
      */
     @Override
     protected String filePrefix(final Activity activity, final Status status) {
          if(activity == FISH && status == Status.DIE) {
               return "Villagedie";
          }
          if(activity == WOODCUTTER && status == Status.ATTACK2) {
               return "Villagerattack";
          }
          return super.filePrefix(activity, status);
     }
}
