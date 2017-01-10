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
import br.com.kidsapp.animalkingdom.element.PhysicsControl;

public class CrazyBird extends SurfaceView implements Runnable, View.OnTouchListener {
    // constants
    private final SurfaceHolder holder = getHolder();
    // attributes
    private boolean isPaused = false;
    private DeviceScreen screen;
    private Bitmap background;
    private PhysicsControl control;
    private Bird bird;
    private ArrayList<Pipe> topPipes = new ArrayList<>();
    private ArrayList<Pipe> bottomPipes = new ArrayList<>();

    public CrazyBird(Context context) {
        super(context);
        this.control = new PhysicsControl();
        this.screen = DeviceScreen.getInstance(getContext());
        this.bird = new Bird(control);
        // six topPipes top
        this.topPipes.add(new Pipe(new Point(1080, 0)));
        this.topPipes.add(new Pipe(new Point(864, 0)));
        this.topPipes.add(new Pipe(new Point(648, 0)));
        this.topPipes.add(new Pipe(new Point(432, 0)));
        this.topPipes.add(new Pipe(new Point(216, 0)));
        this.topPipes.add(new Pipe(new Point(0, 0)));
        // six topPipes bottom
        this.bottomPipes.add(new Pipe(new Point(1080, screen.getHeight())));
        this.bottomPipes.add(new Pipe(new Point(864, screen.getHeight())));
        this.bottomPipes.add(new Pipe(new Point(648, screen.getHeight())));
        this.bottomPipes.add(new Pipe(new Point(432, screen.getHeight())));
        this.bottomPipes.add(new Pipe(new Point(216, screen.getHeight())));
        this.bottomPipes.add(new Pipe(new Point(0, screen.getHeight())));
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
            for(Pipe pipe : topPipes) {
                if(pipe.isVisible()) {
                    pipe.slide(0);
                } else {
                    pipe.setPosition(new Point(screen.getWidth(), 0));
                    pipe.setRandomHeight();
                }
                pipe.draw(canvas);
            }
            for(Pipe pipe : bottomPipes) {
                if(pipe.isVisible()) {
                    pipe.slide(screen.getHeight());
                } else {
                    pipe.setPosition(new Point(screen.getWidth(), screen.getHeight()));
                    pipe.setRandomHeight();
                }
                pipe.draw(canvas);
            }
            this.bird.fly();
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
