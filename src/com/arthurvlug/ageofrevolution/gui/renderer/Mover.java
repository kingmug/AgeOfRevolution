package com.arthurvlug.ageofrevolution.gui.renderer;

import com.arthurvlug.ageofrevolution.game.Game;
import com.arthurvlug.ageofrevolution.game.MapElement;
import com.arthurvlug.ageofrevolution.game.Move;
import com.arthurvlug.ageofrevolution.game.fieldmaterial.Direction;
import com.arthurvlug.ageofrevolution.game.fieldmaterial.RenderableUnit;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class Mover {
     private static final double MOVING_RATIO = 2;
     private static final double CIRCLE = Math.PI * 2;

     public Direction getDirection(final Move move, final Game game) {
          final RenderableUnit unit = move.getUnit();
          final MapElement mapElement = game.getMapElement(unit);


          final AbsoluteLocation currentUnitLocation = mapElement.getCenterLocation();
          final double x = currentUnitLocation.x;
          final double y = currentUnitLocation.y;

          final double newX = move.getTargetLocation().x;
          final double newY = move.getTargetLocation().y;

          final double angle = angle(x, y, newX, newY);
//          System.out.println("Angle (grad): " + angle / Math.PI * 180);

          return bestDirection(angle);
     }

     public void move(final Game game, final RenderableUnit unit, final Direction bestDirection) {
//          System.out.println("Direction: " + bestDirection);
          final MapElement mapElement = game.getMapElement(unit);
          final AbsoluteLocation currentUnitLocation = mapElement.getCenterLocation();
          final double movingSpeed = unit.getMovingSpeed();

          final AbsoluteLocation newLocation = newLocation(currentUnitLocation, bestDirection, movingSpeed);
//          System.out.println(Arrays.asList(newLocation, currentUnitLocation, bestDirection, movingSpeed, MOVING_RATIO));

          mapElement.setCenterLocation(newLocation);
     }

     private AbsoluteLocation newLocation(final AbsoluteLocation currentUnitLocation, final Direction bestDirection, final double movingSpeed) {
          return new AbsoluteLocation(
                    currentUnitLocation.x + bestDirection.getX() * movingSpeed * MOVING_RATIO,
                    currentUnitLocation.y + -bestDirection.getY() * movingSpeed * MOVING_RATIO
          );
     }

     private double angle(final double x, final double y, final double newX, final double newY) {
          final double xDiff = newX - x;
          final double yDiff = newY - y;

          if(yDiff == 0) {
               return xDiff > 0
                         ? Direction.EAST.getAngle()
                         : Direction.WEST.getAngle();
          }

          final double vectorLength = Math.sqrt((xDiff * xDiff) / (yDiff * yDiff));
//          new Vector
          final double xd = xDiff / vectorLength;
          final double yd = -yDiff / vectorLength;
          double angle = Math.atan2(yd, xd);
          return angle >= 0
                    ? angle
                    : angle + 2 * Math.PI;
     }

     private Direction bestDirection(final double angle) {
          return Arrays.stream(Direction.values())
                    .map(dir -> {
                         double v = angleDifference(angle, dir);
                         return new AngleDirection(v, dir);
                    })
                    .min((x, y) -> {
                         final double i = x.getAngle() - y.getAngle();
                         return (int) Math.signum(i);
                    })
                    .get()
                    .getDir();
     }

     private double angleDifference(final double angle, final Direction o1) {
          final Stream<Double> diffs = ImmutableList.of(-1, 0, 1).stream()
                    .map(x -> x * CIRCLE + o1.getAngle())
                    .map(y -> Math.abs(y - angle));
          final double minDiff = diffs.min(Comparator.naturalOrder()).get();
//          System.out.printf("%s [%s / %s]: %s%n", o1, angle, o1.getAngle(), minDiff);
          return minDiff;
     }
}
