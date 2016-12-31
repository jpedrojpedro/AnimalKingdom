package br.com.kidsapp.animalkingdom.element;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Pipe {
    // constants
    private final int BASE = 50;
    private final int HEIGHT = 400;
    // attributes
    private Point position = new Point();
    private Paint color = new Paint();

    public Pipe() {
        this.position.set(600, 0);
        // ARGB => opacity, red, green, blue
        this.color.setColor(0xFF00FF00);
    }

    public void draw(Canvas canvas) {
        // left - top - right - bottom
        canvas.drawRect((float) (position.x - (BASE/2.0)),
                        0, (float) (BASE/2.0), HEIGHT, color);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }
}
