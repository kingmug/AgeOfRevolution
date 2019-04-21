package com.arthurvlug.ageofrevolution.game;

import lombok.Getter;
import lombok.Setter;

public abstract class Selectable implements Renderable {
     @Setter@Getter
     boolean selected = false;
}
