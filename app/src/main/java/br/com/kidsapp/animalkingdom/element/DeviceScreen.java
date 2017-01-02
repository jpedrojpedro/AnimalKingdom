package br.com.kidsapp.animalkingdom.element;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class DeviceScreen {
    private DisplayMetrics metrics;

    public DeviceScreen(Context context) {
        WindowManager wm =
                (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        this.metrics = new DisplayMetrics();
        display.getMetrics(metrics);
    }

    public int getHeight() {
        return this.metrics.heightPixels;
    }
}
