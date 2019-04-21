package com.arthurvlug.ageofrevolution.utils;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ImageTransformer {
     public static BufferedImage flip(final BufferedImage image) {
          final AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
          tx.translate(-image.getWidth(null), 0);
          final AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
          return op.filter(image, null);
     }
}
