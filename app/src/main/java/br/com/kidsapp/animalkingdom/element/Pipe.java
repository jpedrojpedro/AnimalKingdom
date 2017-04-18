package br.com.kidsapp.animalkingdom.element;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import java.util.Random;

public abstract class Pipe {
    // constants
    private final int BASE = 200;
    // attributes
    private Rect rectangle = new Rect();
    private Paint color = new Paint();
    private Random random = new Random();
    private PhysicsControl control;
    private DeviceScreen screen;

    //              - Rectangle Reference
    //
    //          L = (x,y)o-------o
    //                   |       |
    //                   |       |
    //                   |       |       => (Xl, Yl, Xr, Yr)
    //                   |       |
    //                   |       |
    //                   |       |
    //                   o-------o(x,y) = R
    //

    public Pipe(PhysicsControl control, DeviceScreen screen) {
        this.control = control;
        this.screen = screen;
        // ARGB => opacity, red, green, blue
        this.color.setColor(0xFF00FF00);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(rectangle, color);
    }

    public boolean isVisible() {
        return rectangle.left + BASE >= 0;
    }

    public void setHeight() {
        this.rectangle.bottom = randomizeHeight();
    }

    private int randomizeHeight() {
        return screen.getHeight()/4 + random.nextInt(300);
    }

    // abstract methods
    public abstract void slide();

    public abstract void setInitialPosition();

    // getters and setters
    public PhysicsControl getControl() {
        return control;
    }

    public DeviceScreen getScreen() {
        return screen;
    }

    public int getBase() {
        return BASE;
    }

    public Random getRandom() {
        return random;
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rect r) {
        this.rectangle.set(r);
    }
}
