package com.arthurvlug.ageofrevolution.game;

import com.arthurvlug.ageofrevolution.game.fieldmaterial.Activity;
import com.arthurvlug.ageofrevolution.game.fieldmaterial.CarryingResource;
import com.arthurvlug.ageofrevolution.game.fieldmaterial.RenderableResource;
import com.arthurvlug.ageofrevolution.game.fieldmaterial.RenderableUnit;
import com.arthurvlug.ageofrevolution.game.fieldmaterial.Status;
import com.arthurvlug.ageofrevolution.gui.Selection;
import com.arthurvlug.ageofrevolution.gui.renderer.AbsoluteLocation;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.arthurvlug.ageofrevolution.game.fieldmaterial.Activity.*;
import static com.arthurvlug.ageofrevolution.game.fieldmaterial.Status.*;

@AllArgsConstructor
@Getter
public class Game {
    private final GameMap gameMap;
    private final List<PlayerStats> players;
    private final HashMap<RenderableUnit, Move> moves;

    public void moveUnits(final Selection selection,
                               final AbsoluteLocation newLocation,
                               final Optional<RenderableResource> targetResource) {
         if(selection.getSelectables().isEmpty()) {
              return;
         }

         final boolean isUnitType = RenderableUnit.class.isAssignableFrom(selection.getSelectables().iterator().next().getClass());
         if(!isUnitType) {
              return;
         }

         selection.getSelectables().forEach(selectable -> {
              // TODO: Follow path
              final RenderableUnit unit = modifyUnit(targetResource, selectable);
              moves.put(unit, new Move(unit, newLocation));
         });
    }

    private RenderableUnit modifyUnit(final Optional<RenderableResource> targetResource, final Selectable selectable) {
         final RenderableUnit unit = (RenderableUnit) selectable;
         final Activity activity = targetResource.map(resource -> getActivity(resource))
                             .orElse(STANDARD);
         Status status = WALK;
         unit.setActivity(activity);
         unit.setStatus(status);
         System.out.println(selectable.getClass());
         if(selectable instanceof RenderableVillager) {
              final RenderableVillager villager = (RenderableVillager) unit;
              System.out.println(activity);
              System.out.println(status);
              if(status != WALK) {
                   villager.setCarryingResource(Optional.empty());
              } else if(activity == MINE) {
                   villager.setCarryingResource(getCarryingMine(villager));
              } else if(activity == FISH) {
                   villager.setCarryingResource(getCarryingFish(villager));
              } else if(activity == HUNT) {
                   villager.setCarryingResource(getCarryingHunt(villager));
              } else if(activity == SHEPHEARD) {
                   villager.setCarryingResource(getCarryingShepheard(villager));
              } else if(activity == WOODCUTTER) {
                   villager.setCarryingResource(getCarryingWood(villager));
              } else {
                   villager.setCarryingResource(Optional.empty());
              }

         }
         System.out.println(unit);
         return unit;
    }

    private Optional<CarryingResource> getCarryingHunt(final RenderableVillager villager) {
         // TODO: If Villager is already carrying, me might need to change this
         return Optional.of(CarryingResource.CARRY_NO_MEAT);
    }

    private Optional<CarryingResource> getCarryingFish(final RenderableVillager villager) {
         // TODO: If Villager is already carrying, me might need to change this
         return Optional.of(CarryingResource.CARRYING_NO_FISH);
    }

    private Optional<CarryingResource> getCarryingShepheard(final RenderableVillager villager) {
         // TODO: If Villager is already carrying, me might need to change this
         return Optional.of(CarryingResource.CARRYING_NO_MEAT);
    }

    private Optional<CarryingResource> getCarryingWood(final RenderableVillager villager) {
         // TODO: If Villager is already carrying, me might need to change this
         return Optional.of(CarryingResource.CARRYING_NO_WOOD);
    }

    private Optional<CarryingResource> getCarryingMine(final RenderableVillager villager) {
        // TODO: If Villager is already carrying, me might need to change this
        return Optional.of(CarryingResource.CARRYING_NOTHING);
    }

    private Activity getActivity(final RenderableResource targetResource) {
        switch(targetResource.getResourceType()) {
            case TREE: return WOODCUTTER;
            case BUSH: return FORAGE;
            case STONE:
            case GOLD: return MINE;
            default: throw new RuntimeException("Could not find activity for resource " + targetResource);
        }
    }

    public MapElement getMapElement(final RenderableUnit renderableUnit) {
         return gameMap.getFields().values().stream()
                 .filter(field -> field.getUnit().map(unit -> unit == renderableUnit).orElse(false))
                 .findFirst()
                 .get();
    }
}
