package com.arthurvlug.ageofrevolution.game.map;

import com.arthurvlug.ageofrevolution.game.player.PlayerId;
import com.arthurvlug.ageofrevolution.game.renderable.Direction;
import com.arthurvlug.ageofrevolution.game.renderable.building.RenderableBuilding;
import com.arthurvlug.ageofrevolution.game.renderable.resource.RenderableResource;
import com.arthurvlug.ageofrevolution.game.renderable.resource.ResourceType;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.Activity;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.CarryingResource;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.Gender;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.RenderableVillager;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.Status;
import com.arthurvlug.ageofrevolution.gui.location.AbsoluteLocation;
import com.arthurvlug.ageofrevolution.gui.location.LocationCalculator;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;

@Getter
public class GameMap {
    private final int size;

    private Map<MapCoordinate, MapElement> fields;
    private LocationCalculator locationCalculator;

    public GameMap(final int size, final LocationCalculator locationCalculator) {
        this.size = size;
        this.locationCalculator = locationCalculator;
        fields = Maps.newHashMap();
//        for (int i = 0; i < size*size; i++) {
//            putField(i, landscape(i);
//        }
//        putResource(0, 0, ResourceType.TREE, 100, 0);
//        putResource(1, 1, ResourceType.STONE, 100, 0);
//        putResource(2, ResourceType.GOLD, 100, 0);
//        putResource(3, GOLD, 100, 0);
//        putResource(1, 0, ResourceType.GOLD, 100, 0);
//
        putVillager(0, 0, PlayerId.PLAYER_1, Gender.MALE, Direction.NORTH_WEST, Activity.STANDARD, Status.STAND_GROUND, Optional.empty());
//
//        putResource(size, GOLD, 100, 0);
//        putResource(size+1, GOLD, 100, 1);
//        putResource(size+2, GOLD, 100, 2);
//        putResource(size+3, GOLD, 100, 3);
//        putResource(size+4, GOLD, 100, 4);
//        putResource(size+5, GOLD, 100, 5);
//        putResource(size+6, GOLD, 100, 6);
//
//        putVillager(0, 1, PlayerId.PLAYER_1, Gender.MALE, Direction.SOUTH, Activity.BUILD_REPAIR, Status.ACT, Optional.empty());
//        putVillager(2*size+1, PLAYER_1, MALE, SOUTH_WEST, BUILD_REPAIR, DIE, Optional.empty());
//        putVillager(2*size+2, PLAYER_1, MALE, WEST, BUILD_REPAIR, ROT, Optional.empty());
//        putVillager(0, 2, PlayerId.PLAYER_1, Gender.MALE, Direction.NORTH_WEST, Activity.BUILD_REPAIR, Status.STAND_GROUND, Optional.empty());
//        putVillager(2*size+4, PLAYER_1, MALE, NORTH, BUILD_REPAIR, WALK, Optional.empty());
//        putVillager(2*size+5, PLAYER_1, MALE, NORTH_EAST, BUILD_REPAIR, ACT, Optional.empty());
//        putVillager(2*size+6, PLAYER_1, MALE, EAST, BUILD_REPAIR, ACT, Optional.empty());
//        putVillager(2*size+7, PLAYER_1, MALE, SOUTH_EAST, BUILD_REPAIR, ACT, Optional.empty());
//
//
//        putVillager(3*size, PLAYER_1, MALE, SOUTH, FARM, ACT, Optional.empty());
//        putVillager(3*size+2, PLAYER_1, MALE, SOUTH_WEST, FARM, DIE, Optional.empty());
//        putVillager(3*size+3, PLAYER_1, MALE, WEST, FARM, ROT, Optional.empty());
//        putVillager(3*size+4, PLAYER_1, MALE, NORTH_WEST, FARM, STAND_GROUND, Optional.empty());
//        putVillager(3*size+5, PLAYER_1, MALE, NORTH, FARM, WALK, Optional.empty());
//
//        putVillager(4*size, PLAYER_1, MALE, SOUTH_WEST, FISH, ACT, Optional.empty());
//        putVillager(4*size+2, PLAYER_1, MALE, SOUTH_WEST, FISH, DIE, Optional.empty());
//        putVillager(4*size+4, PLAYER_1, MALE, NORTH_WEST, FISH, STAND_GROUND, Optional.empty());
//        putVillager(4*size+5, PLAYER_1, MALE, NORTH, FISH, WALK, Optional.of(CARRYING_NO_FISH));
//        putVillager(4*size+6, PLAYER_1, MALE, NORTH, FISH, WALK, Optional.of(CARRYING_FISH));
//
//        putVillager(5*size, PLAYER_1, MALE, SOUTH_WEST, FORAGE, ACT, Optional.empty());
//        putVillager(5*size+2, PLAYER_1, MALE, SOUTH_WEST, FORAGE, DIE, Optional.empty());
//        putVillager(5*size+3, PLAYER_1, MALE, SOUTH_WEST, FORAGE, ROT, Optional.empty());
//        putVillager(5*size+4, PLAYER_1, MALE, NORTH_WEST, FORAGE, STAND_GROUND, Optional.empty());
//        putVillager(5*size+5, PLAYER_1, MALE, NORTH, FORAGE, WALK, Optional.empty());
//
//        putVillager(6*size, PLAYER_1, MALE, SOUTH_WEST, HUNT, ACT, Optional.empty());
//        putVillager(6*size+2, PLAYER_1, MALE, SOUTH_WEST, HUNT, DIE, Optional.empty());
//        putVillager(6*size+3, PLAYER_1, MALE, NORTH_WEST, HUNT, STAND_GROUND, Optional.empty());
//        putVillager(6*size+4, PLAYER_1, MALE, NORTH, HUNT, WALK, Optional.of(CARRY_MEAT));
//        putVillager(6*size+5, PLAYER_1, MALE, NORTH, HUNT, WALK, Optional.of(CARRY_NO_MEAT));
//
//        putVillager(7*size+1, PLAYER_1, MALE, SOUTH_WEST, MINE, ATTACK, Optional.empty());
//        putVillager(7*size+2, PLAYER_1, MALE, SOUTH_WEST, MINE, DIE, Optional.empty());
//        putVillager(7*size+3, PLAYER_1, MALE, SOUTH_WEST, MINE, ROT, Optional.empty());
//        putVillager(7*size+4, PLAYER_1, MALE, NORTH_WEST, MINE, STAND_GROUND, Optional.empty());
//        putVillager(7*size+5, PLAYER_1, MALE, NORTH, MINE, WALK, Optional.of(CARRYING_GOLD));
//        putVillager(7*size+6, PLAYER_1, MALE, NORTH, MINE, WALK, Optional.of(CARRYING_NOTHING));
//        putVillager(7*size+7, PLAYER_1, MALE, NORTH, MINE, WALK, Optional.of(CARRYING_STONE));
//
//        putVillager(8*size, PLAYER_1, MALE, SOUTH_WEST, SHEPHEARD, ACT, Optional.empty());
//        putVillager(8*size+1, PLAYER_1, MALE, SOUTH_WEST, SHEPHEARD, ATTACK, Optional.empty());
//        putVillager(8*size+2, PLAYER_1, MALE, SOUTH_WEST, SHEPHEARD, DIE, Optional.empty());
//        putVillager(8*size+3, PLAYER_1, MALE, SOUTH_WEST, SHEPHEARD, ROT, Optional.empty());
//        putVillager(8*size+4, PLAYER_1, MALE, NORTH_WEST, SHEPHEARD, STAND_GROUND, Optional.empty());
//        putVillager(8*size+5, PLAYER_1, MALE, NORTH, SHEPHEARD, WALK, Optional.of(CARRYING_NO_MEAT));
////
//        putVillager(9*size+1, PLAYER_1, MALE, SOUTH_WEST, STANDARD, ATTACK, Optional.empty());
//        putVillager(9*size+2, PLAYER_1, MALE, SOUTH_WEST, STANDARD, DIE, Optional.empty());
//        putVillager(9*size+3, PLAYER_1, MALE, SOUTH_WEST, STANDARD, ROT, Optional.empty());
//        putVillager(9*size+5, PLAYER_1, MALE, NORTH, STANDARD, WALK, Optional.empty());
////
//        putVillager(10*size, PLAYER_1, MALE, SOUTH_WEST, WOODCUTTER, ATTACK, Optional.empty());
//        putVillager(10*size+1, PLAYER_1, MALE, SOUTH_WEST, WOODCUTTER, ATTACK2, Optional.empty());
//        putVillager(10*size+2, PLAYER_1, MALE, SOUTH_WEST, WOODCUTTER, DIE, Optional.empty());
//        putVillager(10*size+3, PLAYER_1, MALE, SOUTH_WEST, WOODCUTTER, ROT, Optional.empty());
//        putVillager(10*size+4, PLAYER_1, MALE, NORTH_WEST, WOODCUTTER, STAND_GROUND, Optional.empty());
//        putVillager(10*size+5, PLAYER_1, MALE, NORTH, WOODCUTTER, WALK, Optional.of(CARRYING_NO_WOOD));
//        putVillager(10*size+6, PLAYER_1, MALE, NORTH, WOODCUTTER, WALK, Optional.of(CARRYING_WOOD));
    }

    private void putVillager(final int x, final int y, final PlayerId playerId, final Gender villagerGender, final Direction direction, final Activity activity, final Status status, final Optional<CarryingResource> carryingResource) {
        final MapElement villager = villager(x, y, playerId, villagerGender, direction, activity, status, carryingResource);
        putField(x, y, villager);
    }

    private void putResource(final int x, final int y, final ResourceType resourceType, final int capacity, final int variant) {
        final AbsoluteLocation coordinate = coordinate(x, y);
        final MapElement resource = new MapElement(
                Optional.of(new RenderableResource(resourceType, capacity, variant)),
                Optional.empty(),
                Optional.empty(),
                coordinate);
        putField(x, y, resource);
    }

    private void putField(final int x, final int y, final MapElement mapElement) {
        final MapCoordinate coordinate = new MapCoordinate(x, y);
        fields.put(coordinate, mapElement);
    }

    private MapElement landscape(final int x, final int y) {
        final AbsoluteLocation coordinate = coordinate(x, y);
        return new MapElement(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                coordinate(x, y));
    }

    private MapElement villager(final int x, final int y, final PlayerId playerId, final Gender villagerGender, final Direction direction, final Activity activity, final Status status, final Optional<CarryingResource> carryingResource) {
        final AbsoluteLocation coordinate = coordinate(x, y);
        return new MapElement(
                Optional.empty(),
                Optional.of(new RenderableVillager(playerId, villagerGender, direction, activity, status, carryingResource)),
                Optional.empty(),
                coordinate(x, y));
    }

    private AbsoluteLocation coordinate(final int x, final int y) {
        return locationCalculator.absoluteLocation(new MapCoordinate(x, y));
    }

    public MapElement getMapElement(final MapCoordinate mapCoordinate) {
        return getMapElement(mapCoordinate.getX(), mapCoordinate.getY());
    }

    private MapElement getMapElement(final int x, final int y) {
        final MapCoordinate coordinate = new MapCoordinate(x, y);
        return fields.get(coordinate);
    }
    
    public void putBuilding(final MapCoordinate coordinate, final RenderableBuilding building) {
        putField(coordinate, new MapElement(
                Optional.empty(),
                Optional.empty(),
                Optional.of(building),
                locationCalculator.absoluteLocation(coordinate)
        ));
    }
    
    private void putField(final MapCoordinate coordinate, final MapElement mapElement) {
        putField(coordinate.getX(), coordinate.getY(), mapElement);
    }
}
