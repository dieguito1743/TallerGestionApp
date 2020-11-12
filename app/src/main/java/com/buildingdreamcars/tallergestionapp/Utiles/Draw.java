package com.buildingdreamcars.tallergestionapp.Utiles;


import android.graphics.Path;

public class Draw {
    public int color;
    public int strokeWith;
    public Path path;

    public Draw(int color, int strokeWith, Path path) {
        this.color = color;
        this.strokeWith = strokeWith;
        this.path = path;
    }
}
