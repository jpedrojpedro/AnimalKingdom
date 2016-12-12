package br.com.kidsapp.animalkingdom.game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import br.com.kidsapp.animalkingdom.element.Bird;

public class CrazyBird extends SurfaceView implements Runnable {
    private boolean isPaused = false;
    private final SurfaceHolder holder = getHolder();
    private Bird bird;

    public CrazyBird(Context context) {
        super(context);
        this.bird = new Bird();
    }

    @Override
    public void run() {
        while(!this.isPaused) {
            Canvas canvas = this.holder.lockCanvas();
            // let the magic begins
            if(!this.holder.getSurface().isValid()) {
                continue;
            }
            this.bird.draw(canvas);
            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        this.isPaused = true;
    }

    public void resume() {
        this.isPaused = false;
    }
}
