package br.com.kidsapp.animalkingdom.element;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import java.util.Random;

public class Pipe {
    // constants
    private final int BASE = 200;
    private final int MIN_HEIGHT = 700;
    private final int SPEED = 10;
    private final int TIME_SPENT = 1;
    // attributes
    private Point position = new Point();
    private Paint color = new Paint();
    private Random random = new Random();
    private int height;

    public Pipe(Point initialPosition) {
        this.position.set(initialPosition.x, initialPosition.y);
        // ARGB => opacity, red, green, blue
        this.color.setColor(0xFF00FF00);
        this.setRandomHeight();
    }

    public void draw(Canvas canvas) {
        float x = (float) (position.x - (BASE/2.0));
        float y = (float) (position.y - (height /2.0));
        canvas.drawRect(x, y, x + (float) (BASE/2.0),
                        y + height, color);
    }

    public void slide(int height) {
        position = new Point(position.x - SPEED * TIME_SPENT, height);
    }

    public boolean isVisible() {
        return (float) (position.x + (BASE / 2.0)) >= 0;
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

    public void setRandomHeight() {
        height = MIN_HEIGHT + random.nextInt(400);
    }
}
