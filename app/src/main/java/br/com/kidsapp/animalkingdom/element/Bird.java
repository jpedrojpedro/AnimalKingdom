package br.com.kidsapp.animalkingdom.element;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Bird {
    // constants
    private final int RADIUS = 50;
    private final int BOOST = 20;
    // attributes
    private Point currentPosition = new Point();
    private Paint color = new Paint();
    private PhysicsControl control;
    private boolean boosted = false;
    private float speed;

    public Bird(PhysicsControl control) {
        this.control = control;
        this.currentPosition.set(200, 100);
        // ARGB => opacity, red, green, blue
        this.color.setColor(0xFFFF0000);
        this.speed = 0;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(currentPosition.x, currentPosition.y,
                          RADIUS, color);
    }

    public void fly() {
        float acceleration = (control.getGravity() * control.getTimeSpent() * control.getTimeSpent()) / 2.0f;
        currentPosition = new Point(
            currentPosition.x,
            (int)(currentPosition.y - speed * control.getTimeSpent() + acceleration)
        );
        if(boosted) {
            this.speed = BOOST - control.getGravity() * control.getTimeSpent();
            this.control.setTimeSpent(0);
            this.boosted = false;
        }
    }

    public Point getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Point position) {
        this.currentPosition = position;
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

    public void setBoosted(boolean boosted) {
        this.boosted = boosted;
    }
}
