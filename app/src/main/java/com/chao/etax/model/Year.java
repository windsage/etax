package com.chao.etax.model;

public class Year {
    private int value;
    private boolean isSelected;

    public Year(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
