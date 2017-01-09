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
import br.com.kidsapp.animalkingdom.element.TimeControl;

public class CrazyBird extends SurfaceView implements Runnable, View.OnTouchListener {
    // constants
    private final SurfaceHolder holder = getHolder();
    // attributes
    private boolean isPaused = false;
    private DeviceScreen screen;
    private Bitmap background;
    private TimeControl time;
    private Bird bird;
    private ArrayList<Pipe> pipes = new ArrayList<>();

    public CrazyBird(Context context) {
        super(context);
        this.time = new TimeControl();
        this.bird = new Bird(time);
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
            this.bird.fly();
            this.bird.draw(canvas);
            holder.unlockCanvasAndPost(canvas);
            this.time.increment();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.bird.setBoosted(true);
        return false;
    }

    public void pause() {
        this.isPaused = true;
    }

    public void resume() {
        this.isPaused = false;
    }
}
