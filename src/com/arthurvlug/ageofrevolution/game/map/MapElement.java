package com.arthurvlug.ageofrevolution.game.map;

import com.arthurvlug.ageofrevolution.game.renderable.resource.RenderableResource;
import com.arthurvlug.ageofrevolution.game.renderable.RenderableUnit;
import com.arthurvlug.ageofrevolution.game.renderable.building.RenderableBuilding;
import com.arthurvlug.ageofrevolution.gui.location.AbsoluteLocation;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MapElement {
    private final Optional<RenderableResource> resource;
    private final Optional<RenderableUnit> unit;
    private final Optional<RenderableBuilding> building;
    private AbsoluteLocation centerLocation;

    public void setCenterLocation(final AbsoluteLocation centerLocation) {
          this.centerLocation = centerLocation;
     }
}
