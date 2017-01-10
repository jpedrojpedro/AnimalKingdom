package br.com.kidsapp.animalkingdom.element;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class DeviceScreen {
    private DisplayMetrics metrics;
    // singleton
    private static DeviceScreen instance = null;

    private DeviceScreen(Context context) {
        WindowManager wm =
                (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        this.metrics = new DisplayMetrics();
        display.getMetrics(metrics);
    }

    public static DeviceScreen getInstance(Context context) {
        if(instance == null) {
            instance = new DeviceScreen(context);
        }
        return instance;
    }

    public int getHeight() {
        return metrics.heightPixels;
    }

    public int getWidth() {
        return metrics.widthPixels;
    }
}
