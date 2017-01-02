package br.com.kidsapp.animalkingdom.element;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
// import android.util.Log;

public class Bird {
    // constants
    private final int RADIUS = 50;
    private final int GRAVITY = 10;
    private final int TIME_SPENT = 1;
    private final int BOOST = 70;
    // attributes
    private Point position = new Point();
    private Paint color = new Paint();

    public Bird() {
        this.position.set(200, 100);
        // ARGB => opacity, red, green, blue
        this.color.setColor(0xFFFF0000);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(position.x, position.y,
                          RADIUS, color);
    }

    public void fly(boolean boosted) {
        int gravity = (int) ((GRAVITY * TIME_SPENT * TIME_SPENT)/2.0);
        position = new Point(
            position.x, (position.y - (boosted ? BOOST : 0)) + gravity
        );
        // Log.d("FLY->POSITION", position.toString());
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
