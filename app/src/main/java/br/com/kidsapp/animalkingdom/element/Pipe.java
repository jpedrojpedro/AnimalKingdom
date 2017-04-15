package br.com.kidsapp.animalkingdom.element;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import java.util.Random;

public class Pipe {
    // constants
    private final int BASE = 200;
    private final int MIN_HEIGHT = 500;
    private final int SPEED = 200; // pixels / seconds
    // attributes
    private Rect rectangle = new Rect();
    private Paint color = new Paint();
    private Random random = new Random();
    private PhysicsControl control;

    public Pipe(PhysicsControl control, Point upper_left) {
        this.control = control;
        this.rectangle.set(
            upper_left.x,
            upper_left.y,
            BASE,
            upper_left.y == 0 ? randomizeHeight() : upper_left.y - randomizeHeight()
        );
        // ARGB => opacity, red, green, blue
        this.color.setColor(0xFF00FF00);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(rectangle, color);
    }

    public void slide(int height) {
        this.rectangle.offsetTo(
            this.rectangle.left - (int) (SPEED * control.getTimeRate()), height
        );
    }

    public void setPosition(Point upper_left) {
        this.rectangle.offsetTo(upper_left.x, upper_left.y);
    }

    public Point getPosition() {
        return new Point(this.rectangle.left, this.rectangle.bottom);
    }

    public boolean isVisible() {
        return rectangle.left + BASE >= 0;
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

    public void setHeight() {
        this.rectangle.bottom = randomizeHeight();
    }

    private int randomizeHeight() {
        return MIN_HEIGHT + random.nextInt(400);
    }
}
