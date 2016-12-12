package br.com.kidsapp.animalkingdom.element;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Bird {
    private final int X = 50;
    private final int RADIUS = 50;
    private final Paint RED = new Paint();
    private int height;

    public Bird() {
        this.height = 100;
        // ARGB => opacity, red, green, blue
        RED.setColor(0xFFFF0000);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(this.X, this.height, this.RADIUS, this.RED);
    }
}
