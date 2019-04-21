package com.arthurvlug.ageofrevolution.game;

import com.arthurvlug.ageofrevolution.game.map.MapElement;
import com.arthurvlug.ageofrevolution.game.renderable.Direction;
import com.arthurvlug.ageofrevolution.game.renderable.RenderableUnit;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.Activity;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.RenderableVillager;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.Status;
import com.arthurvlug.ageofrevolution.gui.component.GraphicalFrame;
import com.arthurvlug.ageofrevolution.game.move.Move;
import com.arthurvlug.ageofrevolution.game.move.Mover;
import com.arthurvlug.ageofrevolution.utils.Debug;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameLoop {
    private static final int fps = 20;
    public static final int gameRefreshSampleRate = 1; // Lower is rendered more often
    private static final double DISTANCE_EPS = 1;

    private final GraphicalFrame frame;
    private final Game game;
    private final Mover mover;
    private final ExecutorService threadpool;


    public void start() {
        System.out.println("Starting game loop...");

        final double tick_time = 1000 / fps;
        double next_tick = System.currentTimeMillis() + tick_time;
        while(true) {
            tick();
            frame.rerenderGamePanel();
            try {
                final long now = System.currentTimeMillis();
                final long sleep_time = (long) Math.max(0, next_tick - now);
                Thread.sleep(sleep_time);
                next_tick += tick_time;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void tick() {
        Collection<Move> moves = game.getMoves().values();
        appyMoves(moves);
    }

    private void appyMoves(final Collection<Move> moves) {
        if(moves.isEmpty()) {
           return;
        }
        final BlockingQueue<RenderableUnit> removedUnits = new ArrayBlockingQueue<>(moves.size());
        final Collection<? extends Callable<Void>> xx = moves.stream()
                .map(move -> (Callable<Void>) () -> {
                    applyMove(move, removedUnits);
                    return null;
                })
                .collect(Collectors.toList());
        try {
            final List<Future<Void>> futures = threadpool.invokeAll(xx);
            futures.forEach(x -> {
                try {
                    x.get();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        removedUnits.forEach(unit -> game.getMoves().remove(unit));
    }

    private void applyMove(final Move move, final BlockingQueue<RenderableUnit> removedUnits) {
        final RenderableUnit unit = move.getUnit();
        unit.setStatus(Status.WALK);

        final Direction bestDirection = mover.getDirection(move, game);
        unit.setDirection(bestDirection);

        System.out.println("Move:");
        System.out.println(game.getMapElement(unit).getCenterLocation());
        System.out.println(move.getTargetLocation());
        Debug.customGraphics.drawCircleAround(Debug.locationCalculator.pixelLocation(game.getMapElement(unit).getCenterLocation()));
        Debug.customGraphics.drawRectAround(Debug.locationCalculator.pixelLocation(move.getTargetLocation()));

        mover.move(game, unit, bestDirection);

        final double dist = calculateDistance(move, unit);
        if(dist < DISTANCE_EPS) {
            unit.setStatus(Status.STAND_GROUND);
            unit.setActivity(Activity.STANDARD);
            ((RenderableVillager) unit).setCarryingResource(Optional.empty());
            removedUnits.add(unit);
        }
    }

    private double calculateDistance(final Move move, final RenderableUnit unit) {
        final MapElement mapElement = game.getMapElement(unit);
        final double diffX = mapElement.getCenterLocation().x - move.getTargetLocation().x;
        final double diffY = mapElement.getCenterLocation().y - move.getTargetLocation().y;
        return diffX * diffX + diffY * diffY;
    }
}
