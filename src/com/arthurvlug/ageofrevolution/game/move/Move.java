package com.arthurvlug.ageofrevolution.game.move;

import com.arthurvlug.ageofrevolution.game.renderable.RenderableUnit;
import com.arthurvlug.ageofrevolution.gui.location.AbsoluteLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Move {
     private final RenderableUnit unit;
     private final AbsoluteLocation targetLocation;
}
