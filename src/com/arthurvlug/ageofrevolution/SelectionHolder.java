package com.arthurvlug.ageofrevolution;

import com.arthurvlug.ageofrevolution.gui.Selection;

public class SelectionHolder {
    private Selection selection = Selection.EMPTY;
    
    public void setSelection(final Selection selection) {
        this.selection = selection;
    }

    public Selection getSelection() {
        return selection;
    }
}
