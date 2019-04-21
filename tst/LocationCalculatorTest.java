import com.arthurvlug.ageofrevolution.game.MapCoordinate;
import com.arthurvlug.ageofrevolution.gui.PixelLocation;
import com.arthurvlug.ageofrevolution.gui.renderer.AbsoluteLocation;
import com.arthurvlug.ageofrevolution.gui.renderer.LocationCalculator;
import com.arthurvlug.ageofrevolution.gui.renderer.PainterConfig;
import com.arthurvlug.ageofrevolution.gui.renderer.ViewPort;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Assumption: Map is 2x2 fields
 */
public class LocationCalculatorTest {
    private static final int FIELD_WIDTH = 20;
    private static final int FIELD_HEIGHT = 10;

    private PainterConfig painterConfig;
    private ViewPort viewPort;
    private LocationCalculator locationCalculator;

    @Before
    public void before() {
        painterConfig = mock(PainterConfig.class);
        when(painterConfig.getFieldWidth()).thenReturn(FIELD_WIDTH);
        when(painterConfig.getFieldHeight()).thenReturn(FIELD_HEIGHT);

        viewPort = mock(ViewPort.class);
    }

    /*
     *             x
     *            / \
     *           /   \
     *          x     x
     *         / \   / \
     *        /   \ /   \
     *       x     x     x
     *        \   / \   /
     *         \ /   \ /
     *          x     x
     *           \   /
     *            \ /
     *             x
     */
    /*** 2 ***/

    @Test
    public void testAbsoluteLocationFromMapCoordinate2() {
        locationCalculator = new LocationCalculator(painterConfig, viewPort, 2);

        final MapCoordinate mapCoordinate = new MapCoordinate(0, 0);
        final AbsoluteLocation absoluteLocation = locationCalculator.absoluteLocation(mapCoordinate);

        final AbsoluteLocation expectedAbsoluteLocation = new AbsoluteLocation(-FIELD_WIDTH /2, 0);
        assertEquals(absoluteLocation, expectedAbsoluteLocation);
    }

    @Test
    public void testMapCoordinateFromAbsoluteLocation2() {
        locationCalculator = new LocationCalculator(painterConfig, viewPort, 2);

        final AbsoluteLocation absoluteLocation = new AbsoluteLocation(-FIELD_WIDTH /2, 0);
        final MapCoordinate mapCoordinate = locationCalculator.mapCoordinate(absoluteLocation);

        final MapCoordinate expectedMapCoordinate = new MapCoordinate(0, 0);
        assertEquals(expectedMapCoordinate, mapCoordinate);
    }

    @Test
    public void testMapCoordinateFromAbsoluteLocation2_left() {
        locationCalculator = new LocationCalculator(painterConfig, viewPort, 2);

        final AbsoluteLocation absoluteLocation = new AbsoluteLocation(-FIELD_WIDTH /2 - 3, 0);
        final MapCoordinate mapCoordinate = locationCalculator.mapCoordinate(absoluteLocation);

        final MapCoordinate expectedMapCoordinate = new MapCoordinate(0, 0);
        assertEquals(expectedMapCoordinate, mapCoordinate);
    }

    @Test
    public void testMapCoordinateFromAbsoluteLocation2_right() {
        locationCalculator = new LocationCalculator(painterConfig, viewPort, 2);

        final AbsoluteLocation absoluteLocation = new AbsoluteLocation(-FIELD_WIDTH /2 + 3, 0);
        final MapCoordinate mapCoordinate = locationCalculator.mapCoordinate(absoluteLocation);

        final MapCoordinate expectedMapCoordinate = new MapCoordinate(0, 0);
        assertEquals(expectedMapCoordinate, mapCoordinate);
    }

    @Test
    public void testAbsoluteLocationFromPixelLocation2() {
        when(viewPort.getWidth()).thenReturn(FIELD_WIDTH * 2);
        when(viewPort.getHeight()).thenReturn(FIELD_HEIGHT * 2);
        locationCalculator = new LocationCalculator(painterConfig, viewPort, 2);

        final PixelLocation pixelLocation = new PixelLocation(FIELD_WIDTH, FIELD_HEIGHT);
        final AbsoluteLocation absoluteLocation = locationCalculator.absoluteLocation(pixelLocation);

        final AbsoluteLocation expectedAbsoluteLocation = new AbsoluteLocation(0, 0);
        assertEquals(expectedAbsoluteLocation, absoluteLocation);
    }

    @Test
    public void testPixelLocationFromAbsoluteLocation2() {
        when(viewPort.getWidth()).thenReturn(FIELD_WIDTH * 2);
        when(viewPort.getHeight()).thenReturn(FIELD_HEIGHT * 2);
        locationCalculator = new LocationCalculator(painterConfig, viewPort, 2);

        final AbsoluteLocation absoluteLocation = new AbsoluteLocation(0, 0);
        final PixelLocation pixelLocation = locationCalculator.pixelLocation(absoluteLocation);

        final PixelLocation expectedPixelLocation = new PixelLocation(FIELD_WIDTH, FIELD_HEIGHT);
        assertEquals(pixelLocation, expectedPixelLocation);
    }

    /*
     *          (3,0)
     *       (2,0)  (3,1)
     *    (1,0)  (2,1)  (3,2)
     * (0,0)  (1,1)  (2,2)  (3,3)
     *    (0,1)   (1,2)  (2,3)
     *        (0,2)  (1,3)
     *          (0,3)
     *
     *             x
     *            / \
     *           /   \
     *          x     x
     *         / \   / \
     *        /   \ /   \
     *       x     x     x
     *      / \   / \   / \
     *     /   \ /   \ /   \
     *    x     x     x     x
     *   / \   / \   / \   / \
     *  /   \ /   \ /   \ /   \
     * x     x     x     x     x
     *  \   / \   / \   / \   /
     *   \ /   \ /   \ /   \ /
     *    x     x     x     x
     *     \   / \   / \   /
     *      \ /   \ /   \ /
     *       x     x     x
     *        \   / \   /
     *         \ /   \ /
     *          x     x
     *           \   /
     *            \ /
     *             x
     */

    /**** 4 ****/

    @Test
    public void testAbsoluteLocationFromMapCoordinate4() {
        locationCalculator = new LocationCalculator(painterConfig, viewPort, 4);

        final MapCoordinate mapCoordinate = new MapCoordinate(0, 0);
        final AbsoluteLocation absoluteLocation = locationCalculator.absoluteLocation(mapCoordinate);

        final AbsoluteLocation expectedAbsoluteLocation = new AbsoluteLocation(-1.5 * FIELD_WIDTH, 0);
        assertEquals(absoluteLocation, expectedAbsoluteLocation);
    }

    @Test
    public void testMapCoordinateFromAbsoluteLocation4() {
        locationCalculator = new LocationCalculator(painterConfig, viewPort, 4);

        final AbsoluteLocation absoluteLocation = new AbsoluteLocation(-1.5 * FIELD_WIDTH, 0);
        final MapCoordinate mapCoordinate = locationCalculator.mapCoordinate(absoluteLocation);

        final MapCoordinate expectedMapCoordinate = new MapCoordinate(0, 0);
        assertEquals(expectedMapCoordinate, mapCoordinate);
    }

    @Test
    public void testAbsoluteLocationFromPixelLocation4() {
        when(viewPort.getWidth()).thenReturn(FIELD_WIDTH);
        when(viewPort.getHeight()).thenReturn(FIELD_HEIGHT);
        locationCalculator = new LocationCalculator(painterConfig, viewPort, 4);

        final PixelLocation pixelLocation = new PixelLocation(2 * FIELD_WIDTH, 2 * FIELD_HEIGHT);
        final AbsoluteLocation absoluteLocation = locationCalculator.absoluteLocation(pixelLocation);

        final AbsoluteLocation expectedAbsoluteLocation = new AbsoluteLocation(0, 0);
        assertEquals(expectedAbsoluteLocation, absoluteLocation);
    }

    @Test
    public void testPixelLocationFromAbsoluteLocation4() {
        when(viewPort.getWidth()).thenReturn(FIELD_WIDTH);
        when(viewPort.getHeight()).thenReturn(FIELD_HEIGHT);
        locationCalculator = new LocationCalculator(painterConfig, viewPort, 4);

        final AbsoluteLocation absoluteLocation = new AbsoluteLocation(0, 0);
        final PixelLocation pixelLocation = locationCalculator.pixelLocation(absoluteLocation);

        final PixelLocation expectedPixelLocation = new PixelLocation(2*FIELD_WIDTH, 2*FIELD_HEIGHT);
        assertEquals(pixelLocation, expectedPixelLocation);
    }
}
