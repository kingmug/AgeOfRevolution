package com.arthurvlug.ageofrevolution;

import com.arthurvlug.ageofrevolution.game.Game;
import com.arthurvlug.ageofrevolution.game.map.GameMap;
import com.arthurvlug.ageofrevolution.game.player.PlayerStats;
import com.arthurvlug.ageofrevolution.game.selection.SelectionHolder;
import com.arthurvlug.ageofrevolution.game.renderable.Renderable;
import com.arthurvlug.ageofrevolution.game.renderable.building.stonewall.RenderableStoneWall;
import com.arthurvlug.ageofrevolution.game.renderable.unit.villager.RenderableVillager;
import com.arthurvlug.ageofrevolution.game.GameHolder;
import com.arthurvlug.ageofrevolution.gui.interaction.GameKeyboardListener;
import com.arthurvlug.ageofrevolution.gui.interaction.GameMouseListener;
import com.arthurvlug.ageofrevolution.gui.component.GamePanel;
import com.arthurvlug.ageofrevolution.gui.component.GraphicalFrame;
import com.arthurvlug.ageofrevolution.utils.ImageCache;
import com.arthurvlug.ageofrevolution.gui.renderer.RenderFactory;
import com.arthurvlug.ageofrevolution.utils.Debug;
import com.arthurvlug.ageofrevolution.gui.renderer.DefaultMapRenderer;
import com.arthurvlug.ageofrevolution.gui.renderer.DefaultPainter;
import com.arthurvlug.ageofrevolution.gui.renderer.FieldComponentRenderer;
import com.arthurvlug.ageofrevolution.game.GameLoop;
import com.arthurvlug.ageofrevolution.gui.location.LocationCalculator;
import com.arthurvlug.ageofrevolution.gui.renderer.MapRenderer;
import com.arthurvlug.ageofrevolution.game.move.Mover;
import com.arthurvlug.ageofrevolution.gui.renderer.Painter;
import com.arthurvlug.ageofrevolution.gui.renderer.PainterConfig;
import com.arthurvlug.ageofrevolution.gui.renderer.ResourceRenderer;
import com.arthurvlug.ageofrevolution.gui.renderer.StoneWallRenderer;
import com.arthurvlug.ageofrevolution.gui.component.ViewPort;
import com.arthurvlug.ageofrevolution.gui.renderer.VillagerRenderer;
import com.arthurvlug.ageofrevolution.utils.ImageFileLoader;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.SwingUtilities;

public class Main {
    private static final int SIZE = 2;
    private static final int DIAMOND_HEIGHT = 92/2;
    private int WIDTH = 400;
    private int HEIGHT = 400;

    private final SelectionHolder selectionHolder = new SelectionHolder();
    private final PainterConfig painterConfig = new PainterConfig(DIAMOND_HEIGHT);
    private final ImageCache imageCache = new ImageCache(CacheBuilder.newBuilder().build());
    private final ImageFileLoader imageLoader = new ImageFileLoader(imageCache);
    private final ViewPort viewPort = new ViewPort(0, 0, painterConfig.getFieldWidth() * SIZE, painterConfig.getFieldHeight() * SIZE);
    private final LocationCalculator locationCalculator = new LocationCalculator(painterConfig, viewPort, SIZE);
    private final ResourceRenderer resourceRenderer = null;
//    private final ResourceRenderer resourceRenderer = new ResourceRenderer(imageLoader, locationCalculator, painterConfig);
    private final VillagerRenderer villagerRenderer = new VillagerRenderer(imageLoader, locationCalculator, painterConfig);
    private final StoneWallRenderer stoneWallRenderer = new StoneWallRenderer(imageLoader, locationCalculator, painterConfig);

    private final MapRenderer mapRenderer = new DefaultMapRenderer();
    private final RenderFactory renderFactory = new RenderFactory(renderers());
    private final Painter painter = new DefaultPainter(painterConfig, resourceRenderer, locationCalculator, renderFactory);
    private final GameHolder gameHolder = new GameHolder();

    private Map<Class<? extends Renderable>, FieldComponentRenderer> renderers() {
        return ImmutableMap.<Class<? extends Renderable>, FieldComponentRenderer>builder()
                .put(renderableClass(RenderableVillager.class), villagerRenderer)
                .put(renderableClass(RenderableStoneWall.class), stoneWallRenderer)
                .build();
    }

    private Class<? extends Renderable> renderableClass(final Class<?> renderableClass) {
        return (Class<? extends Renderable>) renderableClass;
    }

    private final GamePanel gamePanel = new GamePanel(gameHolder, mapRenderer, painter, selectionHolder, locationCalculator, WIDTH, HEIGHT, renderFactory);
    private final GameKeyboardListener gameKeyboardListener = new GameKeyboardListener(selectionHolder);
    private final GameMouseListener gameMouseListener = new GameMouseListener(selectionHolder, gamePanel, gameHolder, locationCalculator, Optional.empty());
    private final Mover mover = new Mover();
    private final ExecutorService tickThreadpool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        Debug.locationCalculator = locationCalculator;
        final Runnable r = () -> {
            final GraphicalFrame frame = new GraphicalFrame(gamePanel, gameKeyboardListener, WIDTH, HEIGHT);
            gamePanel.initialise(gameMouseListener);

            final Game game = setupGame();
            gameHolder.setGame(game);
            new Thread(() -> new GameLoop(frame, game, mover, tickThreadpool).start()).start();
        };
        SwingUtilities.invokeLater(r);
    }

    private Game setupGame() {
        final PlayerStats player1Stat = PlayerStats.builder()
               .food(1000).wood(900).gold(800).stone(700).population(600).build();
        final PlayerStats player2Stat = PlayerStats.builder()
               .food(1000).wood(900).gold(800).stone(700).population(600).build();
        final List<PlayerStats> playerStats = ImmutableList.of(player1Stat, player2Stat);
        final GameMap gameMap = new GameMap(SIZE, locationCalculator);
        return new Game(gameMap, playerStats, new HashMap<>());
    }
}
