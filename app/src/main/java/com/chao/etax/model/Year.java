package com.chao.etax.model;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Year year = (Year) o;
        return value == year.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
