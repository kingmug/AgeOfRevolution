package com.arthurvlug.ageofrevolution.game;

import com.arthurvlug.ageofrevolution.game.fieldmaterial.RenderableResource;
import com.arthurvlug.ageofrevolution.game.fieldmaterial.RenderableUnit;
import com.arthurvlug.ageofrevolution.gui.renderer.AbsoluteLocation;
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
