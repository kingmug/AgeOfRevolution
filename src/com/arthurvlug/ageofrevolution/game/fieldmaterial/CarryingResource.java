package com.arthurvlug.ageofrevolution.game.fieldmaterial;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CarryingResource {
     CARRYING_FISH("Carrying Fish"),
     CARRYING_NO_FISH("Carrying no Fish"),
     CARRY_MEAT("Carry Meat"),
     CARRY_NO_MEAT("Carry no Meat"), // For Hunt
     CARRYING_GOLD("Carrying Gold"),
     CARRYING_NOTHING("Carrying Nothing"),
     CARRYING_STONE("Carrying Stone"),
     CARRYING_NO_MEAT("Carrying no Meat"), // For Shepheard
     CARRYING_NO_WOOD("Carrying no Wood"),
     CARRYING_WOOD("Carrying Wood");

     private final String folderName;
}
