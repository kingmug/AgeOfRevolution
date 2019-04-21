package com.arthurvlug.ageofrevolution.game;

import com.arthurvlug.ageofrevolution.game.Game;
import java.util.Optional;
import lombok.Getter;

@Getter
public class GameHolder {
     private Optional<Game> game = Optional.empty();

     public void setGame(final Game game) {
          this.game = Optional.ofNullable(game);
     }
}
