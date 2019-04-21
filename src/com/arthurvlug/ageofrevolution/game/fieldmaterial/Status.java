package com.arthurvlug.ageofrevolution.game.fieldmaterial;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
     ACT("Act", 15),
     DIE("Die", 15),
     ROT("Rot", 5),
     STAND_GROUND("Stand Ground", 15),
     WALK("Walk", 15),
     ATTACK("Attack", 15), ATTACK2("Attack2", 15);

     private final String folderName;
     private final int images;
}
