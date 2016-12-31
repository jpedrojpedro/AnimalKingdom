package br.com.kidsapp.animalkingdom.element;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Bird {
    // constants
    private final int RADIUS = 50;
    // attributes
    private Point position = new Point();
    private Paint color = new Paint();

    public Bird() {
        this.position.set(100, 100);
        // ARGB => opacity, red, green, blue
        this.color.setColor(0xFFFF0000);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(position.x, position.y,
                          RADIUS, color);
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
