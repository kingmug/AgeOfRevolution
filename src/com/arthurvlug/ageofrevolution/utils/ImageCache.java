package com.arthurvlug.ageofrevolution.utils;

import com.google.common.cache.Cache;
import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ImageCache {
     private final Cache<String, BufferedImage> cache;

     public BufferedImage get(final String id, final Callable<? extends BufferedImage> callable) {
          try {
               return cache.get(id, callable);
          } catch (ExecutionException e) {
               throw new RuntimeException(e);
          }
     }
}
