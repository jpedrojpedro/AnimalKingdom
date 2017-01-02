package br.com.kidsapp.animalkingdom.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import java.util.ArrayList;
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
    private ArrayList<Pipe> pipes = new ArrayList<>();

    public CrazyBird(Context context) {
        super(context);
        this.bird = new Bird();
        // five pipes
        this.pipes.add(new Pipe(new Point(1200, 0)));
        this.pipes.add(new Pipe(new Point(960, 0)));
        this.pipes.add(new Pipe(new Point(720, 0)));
        this.pipes.add(new Pipe(new Point(480, 0)));
        this.pipes.add(new Pipe(new Point(240, 0)));
        this.screen = DeviceScreen.getInstance(getContext());
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
            for(Pipe pipe : pipes) {
                if(pipe.isVisible()) {
                    pipe.slide();
                } else {
                    pipe.setInitialPosition();
                }
                pipe.draw(canvas);
            }
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
