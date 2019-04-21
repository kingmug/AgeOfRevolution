package com.arthurvlug.ageofrevolution.gui.renderer;

import com.arthurvlug.ageofrevolution.game.renderable.building.stonewall.RenderableStoneWall;
import com.arthurvlug.ageofrevolution.gui.location.AbsoluteLocation;
import com.arthurvlug.ageofrevolution.gui.location.LocationCalculator;
import com.arthurvlug.ageofrevolution.gui.location.Locations;
import com.arthurvlug.ageofrevolution.utils.ImageFileLoader;

public class StoneWallRenderer extends FieldComponentRenderer<RenderableStoneWall> {
    public StoneWallRenderer(final ImageFileLoader imageLoader,
                             final LocationCalculator locationCalculator,
                             final PainterConfig painterConfig) {
        super(imageLoader, locationCalculator, painterConfig);
    }

    @Override
    public void render(final RenderableStoneWall renderableFarm, final AbsoluteLocation absoluteLocation, final CustomGraphics g) {
        g.drawImageFromBottomMiddle(imageLoader.getImage("Buildings/Stonewall3.png"),
                Locations.move(locationCalculator.pixelLocation(absoluteLocation), 0, painterConfig.getFieldHeight()/2));
    }
}
