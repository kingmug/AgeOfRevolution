package com.arthurvlug.ageofrevolution.game.selection;

import com.arthurvlug.ageofrevolution.game.renderable.Renderable;
import lombok.Getter;
import lombok.Setter;

public abstract class Selectable implements Renderable {
     @Setter@Getter
     boolean selected = false;
}
