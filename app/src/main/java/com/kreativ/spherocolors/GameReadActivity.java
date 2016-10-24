package com.kreativ.spherocolors;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.Button;

public class GameReadActivity extends Activity {

    private Button btnTextRed, btnTextBlue, btnTextGreen, btnTextPink, btnTextPurple, btnTextYellow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_read);

        btnTextRed = (Button) findViewById(R.id.btnTextRed);
        btnTextBlue = (Button) findViewById(R.id.btnTextBlue);
        btnTextGreen = (Button) findViewById(R.id.btnTextGreen);
        btnTextPink = (Button) findViewById(R.id.btnTextPink);
        btnTextPurple = (Button) findViewById(R.id.btnTextPurple);
        btnTextYellow = (Button) findViewById(R.id.btnTextYellow);

        btnTextRed.getBackground().setColorFilter(getResources().getColor(R.color.btn_default), PorterDuff.Mode.MULTIPLY);
        btnTextBlue.getBackground().setColorFilter(getResources().getColor(R.color.btn_default), PorterDuff.Mode.MULTIPLY);
        btnTextGreen.getBackground().setColorFilter(getResources().getColor(R.color.btn_default), PorterDuff.Mode.MULTIPLY);
        btnTextPink.getBackground().setColorFilter(getResources().getColor(R.color.btn_default), PorterDuff.Mode.MULTIPLY);
        btnTextPurple.getBackground().setColorFilter(getResources().getColor(R.color.btn_default), PorterDuff.Mode.MULTIPLY);
        btnTextYellow.getBackground().setColorFilter(getResources().getColor(R.color.btn_default), PorterDuff.Mode.MULTIPLY);
    }
}
