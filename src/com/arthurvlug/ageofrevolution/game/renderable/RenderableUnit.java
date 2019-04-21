package com.arthurvlug.ageofrevolution.game.renderable;

import com.arthurvlug.ageofrevolution.game.selection.Selectable;
import com.arthurvlug.ageofrevolution.game.player.PlayerId;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.Activity;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.Status;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.UnitType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class RenderableUnit extends Selectable implements Renderable {
    private PlayerId playerColor;
    private UnitType unitType;
    private Direction direction;
    private Activity activity;
    private Status status;
    private double movingSpeed;

    private int time = 0;

     public RenderableUnit(final PlayerId playerColor, final UnitType unitType, final Direction direction, final Activity activity, final Status status, final double movingSpeed) {
          this.playerColor = playerColor;
          this.unitType = unitType;
          this.direction = direction;
          this.activity = activity;
          this.status = status;
          this.movingSpeed = movingSpeed;
     }

     public void increaseVariant() {
        time++;
    }
}
