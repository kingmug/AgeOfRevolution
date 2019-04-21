package com.arthurvlug.ageofrevolution.gui.renderer;

import com.arthurvlug.ageofrevolution.game.Renderable;
import com.arthurvlug.ageofrevolution.gui.Locations;
import com.arthurvlug.ageofrevolution.gui.PixelLocation;
import com.arthurvlug.ageofrevolution.utils.ImageFileLoader;
import com.arthurvlug.ageofrevolution.utils.ImageTransformer;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.function.Function;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class FieldComponentRenderer<T extends Renderable> {
     private static final int HEALTH_BAR_WIDTH = 18;
     private static final int HEALTH_BAR_HEIGHT = 5;
     private static final int HEALTH_BAR_PADDING = 5;

     private static final Function<BufferedImage, BufferedImage> imageTransformation = ImageTransformer::flip;

     protected final ImageFileLoader imageLoader;
     protected final LocationCalculator locationCalculator;
     protected final PainterConfig painterConfig;

     String pad(final int idx, final int pad) {
          return String.format("%0" + pad + "d", idx);
     }

     BufferedImage getImage(final String pathName, final boolean flip) {
          return imageLoader.getImage(pathName, imageTransformation, pathName + "#" + flip);
     }

     protected Image scaleImage(final Image image, final double newWidth) {
          final double ratio = image.getWidth(null) / newWidth;
          final double height = image.getHeight(null) / ratio;
          return image.getScaledInstance((int) newWidth, (int) height, Image.SCALE_DEFAULT);
     }

     protected void drawImage(final Image image, final double width, final AbsoluteLocation centerLocation, final CustomGraphics g) {
          final Image assetImage = scaleImage(image, width);
          final PixelLocation pixelLocation = locationCalculator.pixelLocation(centerLocation);
          g.drawImageFromBottomMiddle(assetImage, pixelLocation);
     }

     protected void drawHealth(final AbsoluteLocation centerLocation, final BufferedImage image, final CustomGraphics g) {
          final PixelLocation pixelLocation = locationCalculator.pixelLocation(centerLocation);
          g.drawCircleAround(pixelLocation);

          final AbsoluteLocation healthBarPosition = Locations.move(centerLocation,
                  -HEALTH_BAR_WIDTH / 2,
                  -image.getHeight() / 2 - HEALTH_BAR_PADDING - HEALTH_BAR_HEIGHT - 4);

          // Draw box
//          g.setColor(Color.BLACK);
//          g.drawRect(healthBarXPos, healthBarYPos, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);

          // Draw health bar
          g.setColor(Color.GREEN);
          g.fillRect(locationCalculator.pixelLocation(healthBarPosition), HEALTH_BAR_WIDTH-2, HEALTH_BAR_HEIGHT-2);
     }

     protected void drawCircle(final AbsoluteLocation centerLocation, final BufferedImage bufferedImage, final CustomGraphics g) {
          g.setColor(Color.LIGHT_GRAY);
          final double circleWidth = bufferedImage.getWidth();
          final double circleHeight = bufferedImage.getHeight() * 0.2;
          final AbsoluteLocation circleCenterLocation = Locations.move(centerLocation,
                  (int) (-circleWidth / 2),
                  (int) ( circleHeight / 2));
          final PixelLocation topLeftLocation = locationCalculator.pixelLocation(circleCenterLocation);
          g.drawOval(topLeftLocation,
                     (int) circleWidth,
                     (int) circleHeight);
     }

     public abstract void render(final T selectable, final AbsoluteLocation absoluteLocation, final CustomGraphics g);
}
