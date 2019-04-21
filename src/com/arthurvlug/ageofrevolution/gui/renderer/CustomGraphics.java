package com.arthurvlug.ageofrevolution.gui.renderer;

import com.arthurvlug.ageofrevolution.gui.Locations;
import com.arthurvlug.ageofrevolution.gui.PixelLocation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import lombok.AllArgsConstructor;

import static java.awt.AlphaComposite.Clear;
import static java.awt.AlphaComposite.SrcOver;

@AllArgsConstructor
public class CustomGraphics {
     private final Graphics2D g;
     private final Dimension dimension;

     public static CustomGraphics fromGraphics(final Graphics g, final Dimension dimension) {
          return new CustomGraphics((Graphics2D) g, dimension);
     }

     public void clear() {
          g.setComposite(Clear);
          g.fillRect(0, 0, (int) dimension.getWidth(), (int) dimension.getHeight());
          g.setComposite(SrcOver);
     }
     public void setColor(final Color c) {
          g.setColor(c);
     }

     public void drawRect(final PixelLocation pixelLocation, final int width, final int height) {
          drawRect(pixelLocation.x, pixelLocation.y, width, height);
     }
     private void drawRect(final int x, final int y, final int width, final int height) {
          g.drawRect(x, y,  width, height);
     }

     public void drawImage(final Image img, final PixelLocation pixelLocation) {
          drawImage(img, pixelLocation.x, pixelLocation.y);
     }

     private void drawImage(final Image img, int xPixel, int yPixel) {
          if(Debug.drawBoundingBox) {
               g.setColor(Color.PINK);
               g.drawRect(xPixel, yPixel, img.getWidth(null), img.getHeight(null));
          }

          g.drawImage(img, xPixel, yPixel, null);
     }

     public void fillRect(final PixelLocation pixelLocation, final int width, final int height) {
          fillRect(pixelLocation.x, pixelLocation.y, width, height);
     }
     private void fillRect(final int x, final int y, final int width, final int height) {
          g.fillRect(x, y, width, height);
     }

     public void drawOval(final PixelLocation pixelLocation, final int width, final int height) {
          drawOval(pixelLocation.x, pixelLocation.y, width, height);
     }
     private void drawOval(final int x, final int y, final int width, final int height) {
          g.drawOval(x, y, width, height);
     }

     public void drawLine(final PixelLocation pixelLocation1, final PixelLocation pixelLocation2) {
          drawLine(pixelLocation1.x, pixelLocation1.y, pixelLocation2.x, pixelLocation2.y);
     }
     private void drawLine(final int x1, final int y1, final int x2, final int y2) {
          g.drawLine(x1, y1, x2, y2);
     }

     public void drawImageFromBottomMiddle(final Image assetImage, PixelLocation pixelLocation) {
          final double height = assetImage.getHeight(null);
          final int width = assetImage.getWidth(null);
          final PixelLocation topLeftLocationMovedUp = Locations.move(pixelLocation, -width/2, (int) (-height));
          drawImage(assetImage, topLeftLocationMovedUp);
     }

     public void drawCircleAround(final PixelLocation pixelLocation) {
          g.setColor(Color.RED);
          drawOval(new PixelLocation(pixelLocation.x - 3, pixelLocation.y - 3), 6, 6);
     }

     public void drawRectAround(final PixelLocation pixelLocation) {
          g.setColor(Color.CYAN);
          drawRect(new PixelLocation(pixelLocation.x - 3, pixelLocation.y - 3), 6, 6);
     }

//     protected void drawImageFromCenter(final Image image, final double width, final AbsoluteLocation centerLocation, final CustomGraphics g) {
//          final Image assetImage = scaleImage(image, width);
//          int w = assetImage.getWidth(null);
//          int h = assetImage.getHeight(null);
//          final AbsoluteLocation topLeftLocation = Locations.move(centerLocation, -w / 2, -h / 2);
//     }
}
