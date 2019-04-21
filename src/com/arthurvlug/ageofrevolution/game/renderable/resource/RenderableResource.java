package com.arthurvlug.ageofrevolution.game.renderable.resource;

import com.arthurvlug.ageofrevolution.game.renderable.Renderable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RenderableResource implements Renderable {
    private final ResourceType resourceType;
    private final int capacity;
    private final int variant;
}
