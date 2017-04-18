package br.com.kidsapp.animalkingdom.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import java.util.ArrayList;
import br.com.kidsapp.animalkingdom.R;
import br.com.kidsapp.animalkingdom.element.Bird;
import br.com.kidsapp.animalkingdom.element.BottomPipe;
import br.com.kidsapp.animalkingdom.element.DeviceScreen;
import br.com.kidsapp.animalkingdom.element.PhysicsControl;
import br.com.kidsapp.animalkingdom.element.TopPipe;

public class CrazyBird extends SurfaceView implements Runnable, View.OnTouchListener {
    // constants
    private final SurfaceHolder holder = getHolder();
    // attributes
    private boolean isPaused = false;
    private DeviceScreen screen;
    private Bitmap background;
    private PhysicsControl control;
    private Bird bird;
    private ArrayList<TopPipe> topPipes = new ArrayList<>();
    private ArrayList<BottomPipe> bottomPipes = new ArrayList<>();

    public CrazyBird(Context context) {
        super(context);
        this.control = new PhysicsControl();
        this.screen = DeviceScreen.getInstance(getContext());
        this.bird = new Bird(control);
        // five top Pipes
        this.topPipes.add(new TopPipe(control, screen, 0));
        this.topPipes.add(new TopPipe(control, screen, 220));
        this.topPipes.add(new TopPipe(control, screen, 440));
        this.topPipes.add(new TopPipe(control, screen, 660));
        this.topPipes.add(new TopPipe(control, screen, 880));
        this.topPipes.add(new TopPipe(control, screen, 1100));
        // five bottom Pipes
        this.bottomPipes.add(new BottomPipe(control, screen, 0));
        this.bottomPipes.add(new BottomPipe(control, screen, 216));
        this.bottomPipes.add(new BottomPipe(control, screen, 432));
        this.bottomPipes.add(new BottomPipe(control, screen, 648));
        this.bottomPipes.add(new BottomPipe(control, screen, 864));
        this.bottomPipes.add(new BottomPipe(control, screen, 1080));
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
            for(TopPipe pipe : topPipes) {
                if (pipe.isVisible()) {
                    pipe.slide();
                } else {
                    pipe.setInitialPosition();
                    pipe.setHeight();
                }
                pipe.draw(canvas);
            }
            for(BottomPipe pipe : bottomPipes) {
                if(pipe.isVisible()) {
                    pipe.slide();
                } else {
                    pipe.setInitialPosition();
                    pipe.setHeight();
                }
                pipe.draw(canvas);
            }
            //this.bird.fly();
            this.bird.draw(canvas);
            holder.unlockCanvasAndPost(canvas);
            this.control.increment();
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
