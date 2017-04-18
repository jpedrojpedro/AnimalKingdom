package br.com.kidsapp.animalkingdom.element;

import android.graphics.Rect;

public class BottomPipe extends Pipe {

    public BottomPipe(PhysicsControl control, DeviceScreen screen, int position) {
        super(control, screen);
        setRectangle(
            new Rect(
                position, (screen.getHeight()/2) - randomizeHeight(),
                position + getBase(), screen.getHeight()
            )
        );
    }

    public void slide() {
        getRectangle().offsetTo(
            getRectangle().left - (int) (getControl().getSpeed() * getControl().getTimeRate()),
            getScreen().getHeight()
        );
    }

    public void setInitialPosition() {
        getRectangle().offsetTo(getScreen().getWidth(), getScreen().getHeight()/2);
    }

    private int randomizeHeight() {
        return getRandom().nextInt(300);
    }
}
