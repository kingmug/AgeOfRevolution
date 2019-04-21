package com.arthurvlug.ageofrevolution.gui.renderer;

import com.arthurvlug.ageofrevolution.game.renderable.Renderable;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RenderFactory {
    private final Map<Class<? extends Renderable>, ? extends FieldComponentRenderer> renderers;

    public <T extends Renderable, R extends FieldComponentRenderer<T>> R getRenderer(final T selectable) {
        final Class<T> aClass = (Class<T>) selectable.getClass();
        return (R) renderers.get(aClass);
    }
}
