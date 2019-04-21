package com.arthurvlug.ageofrevolution.game;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VillagerGender {
     MALE("Male"), FEMALE("Female");

     private final String folderName;
}
