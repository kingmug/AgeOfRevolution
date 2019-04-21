package com.arthurvlug.ageofrevolution.utils;

import com.google.common.base.Functions;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.function.Function;
import javax.imageio.ImageIO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ImageFileLoader {
     private static final String basePath = "/Users/arthur-prive/workspace/AgeOfRevolution/assets";

     private final ImageCache imageCache;

     private File getFile(final String file) {
          return new File(basePath, file);
     }

     private BufferedImage loadImage(final String pathname) {
          final File file = getFile(pathname);
          try {
               return ImageIO.read(file);
          } catch (IOException e) {
               throw new RuntimeException("Could not read image " + file.getAbsolutePath(), e);
          }
     }

     public BufferedImage getImage(final String pathName,
                                   final Function<BufferedImage, BufferedImage> imageTransformation,
                                   final String id) {
          final Callable<? extends BufferedImage> c = (Callable<BufferedImage>) () -> {
               final BufferedImage image = loadImage(pathName);
               return imageTransformation.apply(image);
          };
          return imageCache.get(id, c);
     }

     public Image getImage(final String pathName) {
          return getImage(pathName, Functions.identity(), pathName);
     }
}
