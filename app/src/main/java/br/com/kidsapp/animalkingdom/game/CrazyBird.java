package br.com.kidsapp.animalkingdom.game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import br.com.kidsapp.animalkingdom.element.Bird;
import br.com.kidsapp.animalkingdom.element.Pipe;

public class CrazyBird extends SurfaceView implements Runnable {
    private boolean isPaused = false;
    private final SurfaceHolder holder = getHolder();
    private Bird bird;
    private Pipe pipe;

    public CrazyBird(Context context) {
        super(context);
        this.bird = new Bird();
        this.pipe = new Pipe();
    }

    @Override
    public void run() {
        while(!isPaused) {
            Canvas canvas = holder.lockCanvas();
            // let the magic begins
            if(!holder.getSurface().isValid()) {
                continue;
            }
            this.bird.draw(canvas);
            this.pipe.draw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        this.isPaused = true;
    }

    public void resume() {
        this.isPaused = false;
    }
}
