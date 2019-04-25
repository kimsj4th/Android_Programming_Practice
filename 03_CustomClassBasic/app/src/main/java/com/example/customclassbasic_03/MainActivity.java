package com.example.customclassbasic_03;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 레이아웃 선언
    LinearLayout layout;
    LinearLayout.LayoutParams layoutParams;
    LinearLayout.LayoutParams layoutParmas2;
    // 위젯 선언
    BitmapButton mBitmapButton;
    Button mButton1;
    EditText mEditText1, mEditText2, mEditText3;
    // 리스너 선언
    ButtonListener1 listener1;

    // 뷰 배열 생성
    View[] viewArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    setLayout();
    setView();

    listener1 = new ButtonListener1();
    mButton1.setOnClickListener(listener1);

    setContentView(layout);
    setFocusEvent();


    viewArray = new View[]{mBitmapButton, mButton1, mEditText1, mEditText2, mEditText3};




    }

    private void changeColor() {

    }

    // 에디트 텍스트 선택시 색상 반전
    private void setFocusEvent() {
        View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    view.setBackgroundColor(Color.BLUE);
                } else {
                    view.setBackgroundColor(Color.GRAY);
                }
            }
        };

        mEditText1.setOnFocusChangeListener(focusChangeListener);
        mEditText2.setOnFocusChangeListener(focusChangeListener);
        mEditText3.setOnFocusChangeListener(focusChangeListener);
    }

    public void setLayout() {
        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 0);
        layoutParmas2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400);
    }

    public void setView() {
        mBitmapButton = new BitmapButton(this);
        mButton1 = new Button(this);

        mEditText1 = new EditText(this);
        mEditText2 = new EditText(this);
        mEditText3 = new EditText(this);
        mEditText1.setBackgroundColor(Color.GRAY);
        mEditText2.setBackgroundColor(Color.GRAY);
        mEditText3.setBackgroundColor(Color.GRAY);

        // 버튼 텍스트 설정
        mBitmapButton.setText("Hello!");
        mButton1.setText("Custom Toast Button");

        // 버튼 텍스트 소문자 지원
        mBitmapButton.setAllCaps(false);
        mButton1.setAllCaps(false);

        // 뷰 추가
        layout.addView(mBitmapButton, layoutParmas2);
        layout.addView(mButton1,layoutParams);
        layout.addView(mEditText1, layoutParams);
        layout.addView(mEditText2, layoutParams);
        layout.addView(mEditText3, layoutParams);

        //상태바 제거
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }




    // 버튼 클릭 리스너로 커스텀 토스트 메시지 출력
    class ButtonListener1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            CustomToast.makeText(MainActivity.this,"Custom Toast!!", Toast.LENGTH_SHORT).show();

            for (View view : viewArray) {
                view.setBackgroundColor(Color.RED);
            }

        }

    }

}

/*

for(int i=0; i<5; i++){
            viewArray[i].setBackgroundColor(Color.RED);
        }

for (View view : viewArray) {
            view.setBackgroundColor(Color.RED);
        }
*/