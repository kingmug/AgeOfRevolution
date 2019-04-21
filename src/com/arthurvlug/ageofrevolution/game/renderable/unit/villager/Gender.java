package com.arthurvlug.ageofrevolution.game.renderable.unit.villager;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
     MALE("Male"), FEMALE("Female");

     private final String folderName;
}
