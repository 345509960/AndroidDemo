package com.lyc.indonesia.shareDemo;

import android.content.Intent;

public class SortIntent extends Intent {

    private int sortCode;

    public SortIntent(String action) {
        super(action);
    }

    public int getSortCode() {
        return sortCode;
    }

    public void setSortCode(int sortCode) {
        this.sortCode = sortCode;
    }
}
