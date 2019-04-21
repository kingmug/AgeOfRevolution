package com.arthurvlug.ageofrevolution.game.renderable.unit.villager;

import com.sun.xml.internal.ws.util.StringUtils;
import java.util.Optional;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum Activity {
     BUILD_REPAIR("Build & Repair"), FARM, FISH, FORAGE, HUNT, MINE, SHEPHEARD, STANDARD, WOODCUTTER;

     private Optional<String> folderName = Optional.empty();

     Activity(final String folderName) {
          this.folderName = Optional.of(folderName);
     }

     public String getFolderName() {
          if(!folderName.isPresent()) {
               this.folderName = Optional.of(StringUtils.capitalize(name().toLowerCase()));
          }
          return folderName.get();
     }
}
