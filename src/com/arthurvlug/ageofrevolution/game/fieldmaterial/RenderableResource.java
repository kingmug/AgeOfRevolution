package com.arthurvlug.ageofrevolution.game.fieldmaterial;

import com.arthurvlug.ageofrevolution.game.Renderable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RenderableResource implements Renderable {
    private final ResourceType resourceType;
    private final int capacity;
    private final int variant;
}
