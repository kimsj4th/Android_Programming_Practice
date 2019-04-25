package com.example.customclassbasic_03;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class CustomToast extends Toast {
    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public CustomToast(Context context) {
        super(context);
    }

    public static Toast makeText(Context context, String message, int duration) {
        Toast toast = new Toast(context);

        View customView = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        TextView textView = customView.findViewById(R.id.message);
        textView.setText(message);

        toast.setView(customView);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 200);
        return  toast;
    }
}
