package com.arthurvlug.ageofrevolution;

import com.arthurvlug.ageofrevolution.game.RenderableBuilding;
import com.arthurvlug.ageofrevolution.game.RenderableTownCenter;
import com.arthurvlug.ageofrevolution.game.RenderableVillager;
import com.arthurvlug.ageofrevolution.game.Selectable;
import com.arthurvlug.ageofrevolution.gui.Selection;
import com.google.common.collect.Sets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameKeyboardListener implements KeyListener {
    private SelectionHolder selectionHolder;

    @Override
    public void keyPressed(final KeyEvent e) {
        selectionHolder.getSelection()
                .getSelectables()
                .stream()
                .filter(x -> x instanceof RenderableVillager)
                .findFirst()
                .ifPresent(x -> {
                    switch(e.getKeyChar()) {
                        case 'z': buildTownCenter(); break;
                        case 'a': buildStoneWall(); break;
                    }
                });
    }

    private void buildTownCenter() {
        System.out.println("Building TC..");
        final RenderableTownCenter renderableTownCenter = new RenderableTownCenter();
        final Selection selection = createSelectionForBuilding(renderableTownCenter);
        selectionHolder.setSelection(selection);
    }

    private void buildStoneWall() {
        System.out.println("Building Stone wall..");
        final RenderableStoneWall farm = new RenderableStoneWall();
        final Selection selection = createSelectionForBuilding(farm);
        selectionHolder.setSelection(selection);
    }

    private Selection createSelectionForBuilding(final RenderableBuilding building) {
        final Set<Selectable> selectables = Sets.newHashSet();
        selectables.add(building);
        return new Selection(selectables);
    }

    @Override
    public void keyTyped(final KeyEvent e) {}

    @Override
    public void keyReleased(final KeyEvent e) { }
}
