package br.com.kidsapp.animalkingdom.element;

public class PhysicsControl {
    // constants
    private final float TIME_RATE = 0.08f;
    private final int GRAVITY = 10;
    private final int METER_TO_PIXELS = 3780;
    private final int SPEED = 216; // pixels / seconds
    // attributes
    private float timeSpent;

    public PhysicsControl() {
        this.timeSpent = 0;
    }

    public void increment() {
        this.timeSpent += TIME_RATE;
    }

    public float getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(float time) {
        this.timeSpent = time;
    }

    public float getTimeRate() {
        return TIME_RATE;
    }

    public int getGravity() {
        return GRAVITY;
    }

    public int getSpeed() { return SPEED; }

    public int metersToPixels(float meters) {
        return (int) (meters * METER_TO_PIXELS);
    }

    public float pixelsToMeters(int pixels) {
        return pixels / METER_TO_PIXELS;
    }
}
