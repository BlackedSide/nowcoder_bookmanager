package com.fannyuan.bookmanager.model.enums;

public enum BookStatus {
    NORMAL(0),
    DELETE(1),
    RECOMMENDED(2);

    private int value;

    BookStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
