package com.arthurvlug.ageofrevolution;

import com.arthurvlug.ageofrevolution.gui.Locations;
import com.arthurvlug.ageofrevolution.gui.renderer.AbsoluteLocation;
import com.arthurvlug.ageofrevolution.gui.renderer.CustomGraphics;
import com.arthurvlug.ageofrevolution.gui.renderer.FieldComponentRenderer;
import com.arthurvlug.ageofrevolution.gui.renderer.LocationCalculator;
import com.arthurvlug.ageofrevolution.gui.renderer.PainterConfig;
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
