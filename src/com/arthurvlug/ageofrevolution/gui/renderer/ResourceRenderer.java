package com.arthurvlug.ageofrevolution.gui.renderer;

import com.arthurvlug.ageofrevolution.game.renderable.resource.RenderableResource;
import com.arthurvlug.ageofrevolution.gui.location.LocationCalculator;
import com.arthurvlug.ageofrevolution.utils.ImageFileLoader;

public abstract class ResourceRenderer<T extends RenderableResource> extends FieldComponentRenderer<T> {
     public ResourceRenderer(final ImageFileLoader imageLoader,
                             final LocationCalculator locationCalculator,
                             final PainterConfig painterConfig) {
          super(imageLoader, locationCalculator, painterConfig);
     }

//     void drawStone(final AbsoluteLocation absoluteLocation, final int width, final RenderableResource resource, final CustomGraphics g) {
//          paintResource(resourcePath("assets/Buildings/stonemine", resource.getVariant()), absoluteLocation, width, g);
//     }
//
//     void drawTree(final AbsoluteLocation absoluteLocation, final int width, final RenderableResource resource, final CustomGraphics g) {
//          paintResource(resourcePath("assets/Buildings/Tree", resource.getVariant()), absoluteLocation, width, g);
//     }
//
//     void drawGold(final AbsoluteLocation absoluteLocation, final int width, final RenderableResource resource, final CustomGraphics g) {
////          AbsoluteLocation location1 = Locations.move(location, 0);
//          paintResource(resourcePath("assets/Buildings/Gold Mine", resource.getVariant()), absoluteLocation, width, g);
//     }
//
////     void drawBush(final AbsoluteLocation absoluteLocation, final int width, final int height, final RenderableResource resource, final CustomGraphics g) {
////          graphics.fillRect(position, width, height);
////     }
//
//     private void paintResource(final String pathName, final AbsoluteLocation absoluteLocation, final int width, final CustomGraphics g) {
//          final BufferedImage image = getImage(pathName, false);
//          PixelLocation pixelLocation = locationCalculator.pixelLocation(absoluteLocation);
//          g.drawImageFromBottomMiddle(assetImage, pixelLocation);
//          drawImage(image, width, absoluteLocation, g);
//     }
//
//     private String resourcePath(final String pathPrefix, final int idx) {
//          final String idxStr = pad(idx + 1, 3);
//
//          return pathPrefix + idxStr + ".png";
//     }
}
