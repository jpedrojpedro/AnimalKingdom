package br.com.kidsapp.animalkingdom.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import br.com.kidsapp.animalkingdom.R;
import br.com.kidsapp.animalkingdom.element.Bird;
import br.com.kidsapp.animalkingdom.element.DeviceScreen;
import br.com.kidsapp.animalkingdom.element.Pipe;
// import android.util.Log;

public class CrazyBird extends SurfaceView implements Runnable, View.OnTouchListener {
    private boolean isPaused = false;
    private final SurfaceHolder holder = getHolder();
    private DeviceScreen screen;
    private Bitmap background;
    private Bird bird;
    private Pipe pipe;

    public CrazyBird(Context context) {
        super(context);
        this.bird = new Bird();
        this.pipe = new Pipe();
        this.screen = new DeviceScreen(getContext());
        this.background = BitmapFactory.decodeResource(getResources(),
                                                       R.drawable.background);
        this.background = Bitmap.createScaledBitmap(background,
                                                    background.getWidth(),
                                                    screen.getHeight(),
                                                    false);
        setOnTouchListener(this);
    }

    @Override
    public void run() {
        while(!isPaused) {
            Canvas canvas = holder.lockCanvas();
            // let the magic begins
            if(!holder.getSurface().isValid()) {
                continue;
            }
            canvas.drawBitmap(this.background, 0, 0, null);
            if(pipe.isVisible()) {
                this.pipe.slide();
            } else {
                this.pipe.setInitialPosition();
            }
            this.pipe.draw(canvas);
            this.bird.fly(false);
            this.bird.draw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.bird.fly(true);
        // Log.d("ON_TOUCH", "Clicked");
        return false;
    }

    public void pause() {
        this.isPaused = true;
    }

    public void resume() {
        this.isPaused = false;
    }
}
