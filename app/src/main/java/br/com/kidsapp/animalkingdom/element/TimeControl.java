package br.com.kidsapp.animalkingdom.element;

public class TimeControl {
    // constants
    private final float TIME_RATE = 0.04f;
    // attributes
    private float timeSpent;

    public TimeControl() {
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
}
