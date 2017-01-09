package br.com.kidsapp.animalkingdom.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import br.com.kidsapp.animalkingdom.R;
import br.com.kidsapp.animalkingdom.game.CrazyBird;

public class MainActivity extends Activity {
    private CrazyBird crazyBirdGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout container = (FrameLayout) findViewById(R.id.container);
        this.crazyBirdGame = new CrazyBird(this);
        container.addView(this.crazyBirdGame);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.crazyBirdGame.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.crazyBirdGame.resume();
        new Thread(crazyBirdGame).start();
    }
}
