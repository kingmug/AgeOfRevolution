package com.arthurvlug.ageofrevolution.game;

import com.arthurvlug.ageofrevolution.game.fieldmaterial.Activity;
import com.arthurvlug.ageofrevolution.game.fieldmaterial.CarryingResource;
import com.arthurvlug.ageofrevolution.game.fieldmaterial.Direction;
import com.arthurvlug.ageofrevolution.game.fieldmaterial.PlayerId;
import com.arthurvlug.ageofrevolution.game.fieldmaterial.RenderableUnit;
import com.arthurvlug.ageofrevolution.game.fieldmaterial.Status;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.arthurvlug.ageofrevolution.game.fieldmaterial.UnitType.VILLAGER;

@Getter
@Setter
@ToString
public class RenderableVillager extends RenderableUnit implements Renderable {
     private final VillagerGender gender;
     private Optional<CarryingResource> carryingResource;

     RenderableVillager(final PlayerId playerColor, final VillagerGender gender, final Direction direction, final Activity activity, final Status status, final Optional<CarryingResource> carryingResource) {
          super(playerColor, VILLAGER, direction, activity, status, 0.8);
          this.gender = gender;
          this.carryingResource = carryingResource;
     }
}
