package br.com.kidsapp.animalkingdom.element;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
// import android.util.Log;

public class Pipe {
    // constants
    private final int BASE = 200;
    private final int HEIGHT = 400;
    private final int SPEED = 10;
    private final int TIME_SPENT = 1;
    // attributes
    private Point position = new Point();
    private Paint color = new Paint();

    public Pipe() {
        setInitialPosition();
        // ARGB => opacity, red, green, blue
        this.color.setColor(0xFF00FF00);
    }

    public void draw(Canvas canvas) {
        float x = (float) (position.x - (BASE/2.0));
        float y = (float) (position.y - (HEIGHT/2.0));
        canvas.drawRect(x, y, x + (float) (BASE/2.0),
                        y + HEIGHT, color);
    }

    public void slide() {
        position = new Point(position.x - SPEED * TIME_SPENT, 0);
        // Log.d("SLIDE->POSITION", position.toString());
    }

    public boolean isVisible() {
        return (float) (position.x + (BASE / 2.0)) >= 0;
    }

    public void setInitialPosition() {
        this.position.set(1200, 0);
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
