package com.example.paintboard;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    PaintBoard board;
    Button mButton1;
    Button mButton2;
    LinearLayout layout;
    LinearLayout buttonLayout;
    LinearLayout.LayoutParams layoutParams;
    int mColor = 0xff000000;
    int mSize = 10;

    int oldColor;
    int oldSize;

    boolean eraserSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
        setView();

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ColorDialog.class);
                startActivityForResult(intent, 101);
            }
        });


        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eraserSelected = !eraserSelected;

                if (eraserSelected) {
                    mButton1.setEnabled(false);
                    mButton2.setBackgroundColor(Color.MAGENTA);
                    oldColor = mColor;
                    oldSize = mSize;
                    mColor = Color.WHITE;
                    mSize = 60;
                    board.updatePaintProperty(mColor, mSize);

                } else {
                    mButton1.setEnabled(true);
                    mButton2.setBackgroundColor(Color.LTGRAY);
                    mColor = oldColor;
                    mSize = oldSize;
                    board.updatePaintProperty(mColor, mSize);
            }
            }
        });

        setContentView(layout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            if (data != null) {
                int color = data.getIntExtra("color", 0);
                board.setColor(color);
            }
        }
    }

    public void setLayout() {
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 0);

        buttonLayout = new LinearLayout(this);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);


    }

    public void setView() {
        mButton1 = new Button(this);
        mButton2 = new Button(this);
        board = new PaintBoard(this);
        mButton1.setText("색상선택");
        mButton2.setText("지우개");
        mButton1.setBackgroundColor(Color.LTGRAY);
        mButton2.setBackgroundColor(Color.LTGRAY);
        buttonLayout.addView(mButton1, layoutParams);
        buttonLayout.addView(mButton2, layoutParams);
        layout.addView(buttonLayout,layoutParams);
        layout.addView(board, layoutParams);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }


}
