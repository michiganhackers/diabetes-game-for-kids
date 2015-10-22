package com.michiganhackers.diabeticons;

import android.graphics.drawable.Drawable;

/**
 * Created by jawad on 22/10/15.
 */
public class Icon {
    private String mTitle;
    private String mPath;
    private Drawable mImage;
    private boolean mIsFavorite;

    public Icon() {
        this.mTitle = null;
        this.mPath = null;
        this.mImage = null;
        this.mIsFavorite = false;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        this.mPath = path;
    }

    public Drawable getImage() {
        return mImage;
    }

    public void setImage(Drawable image) {
        this.mImage = image;
    }

    public boolean getIsFavorite() { return mIsFavorite; }

    public void setIsFavorite(boolean val) { this.mIsFavorite = val; }
}
