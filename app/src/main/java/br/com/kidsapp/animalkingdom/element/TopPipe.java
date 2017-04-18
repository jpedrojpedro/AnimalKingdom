package br.com.kidsapp.animalkingdom.element;

import android.graphics.Rect;

public class TopPipe extends Pipe {

    public TopPipe(PhysicsControl control, DeviceScreen screen, int position) {
        super(control, screen);
        setRectangle(new Rect(position, 0, position + getBase(), randomizeHeight()));
    }

    public void slide() {
        getRectangle().offsetTo(
            getRectangle().left - (int) (getControl().getSpeed() * getControl().getTimeRate()), 0
        );
    }

    public void setInitialPosition() {
        getRectangle().offsetTo(getScreen().getWidth(), 0);
    }

    private int randomizeHeight() {
        return getScreen().getHeight()/4 + getRandom().nextInt(300);
    }
}
