package com.fannyuan.bookmanager.model.enums;

public enum BookStatus {
    NORMAL(0), // 正常
    DELETED(1); // 被删除

    private int value;

    BookStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
