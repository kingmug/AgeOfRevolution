package com.arthurvlug.ageofrevolution.game.renderable.unit.villager;

import com.arthurvlug.ageofrevolution.game.renderable.Renderable;
import com.arthurvlug.ageofrevolution.game.renderable.Direction;
import com.arthurvlug.ageofrevolution.game.player.PlayerId;
import com.arthurvlug.ageofrevolution.game.renderable.RenderableUnit;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.arthurvlug.ageofrevolution.game.renderable.unit.villager.UnitType.VILLAGER;

@Getter
@Setter
@ToString
public class RenderableVillager extends RenderableUnit implements Renderable {
     private final Gender gender;
     private Optional<CarryingResource> carryingResource;

     public RenderableVillager(final PlayerId playerColor, final Gender gender, final Direction direction, final Activity activity, final Status status, final Optional<CarryingResource> carryingResource) {
          super(playerColor, VILLAGER, direction, activity, status, 0.8);
          this.gender = gender;
          this.carryingResource = carryingResource;
     }
}
