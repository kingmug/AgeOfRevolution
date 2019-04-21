package com.arthurvlug.ageofrevolution.game;

import com.arthurvlug.ageofrevolution.game.fieldmaterial.RenderableUnit;
import com.arthurvlug.ageofrevolution.gui.renderer.AbsoluteLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Move {
     private final RenderableUnit unit;
     private final AbsoluteLocation targetLocation;
}
