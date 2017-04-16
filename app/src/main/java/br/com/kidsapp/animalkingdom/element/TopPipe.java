package br.com.kidsapp.animalkingdom.element;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import java.util.Random;

public class TopPipe {
    // constants
    private final int BASE = 200;
    // attributes
    private Rect rectangle = new Rect();
    private Paint color = new Paint();
    private Random random = new Random();
    private PhysicsControl control;
    private DeviceScreen screen;

    public TopPipe(PhysicsControl control, DeviceScreen screen, int position) {
        this.control = control;
        this.screen = screen;
        this.rectangle.set(position, 0, BASE, randomizeHeight());
        // ARGB => opacity, red, green, blue
        this.color.setColor(0xFF00FF00);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(rectangle, color);
    }

    public void slide() {
        this.rectangle.offsetTo(
            this.rectangle.left - (int) (control.getSpeed() * control.getTimeRate()), 0
        );
    }

    public void setInitialPosition() {
        this.rectangle.offsetTo(screen.getWidth(), 0);
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
        return screen.getHeight()/4 + random.nextInt(300);
    }
}
