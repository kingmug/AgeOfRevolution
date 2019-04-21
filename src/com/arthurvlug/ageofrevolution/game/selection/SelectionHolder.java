package com.arthurvlug.ageofrevolution.game.selection;

public class SelectionHolder {
    private Selection selection = Selection.EMPTY;
    
    public void setSelection(final Selection selection) {
        this.selection = selection;
    }

    public Selection getSelection() {
        return selection;
    }
}
