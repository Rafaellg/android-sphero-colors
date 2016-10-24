package com.kreativ.spherocolors;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.Button;

public class GameNoReadActivity extends Activity {

    private Button btnRed, btnBlue, btnGreen, btnPink, btnPurple, btnYellow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_no_read);

        btnRed = (Button) findViewById(R.id.btnColorRed);
        btnBlue = (Button) findViewById(R.id.btnColorBlue);
        btnGreen = (Button) findViewById(R.id.btnColorGreen);
        btnPink = (Button) findViewById(R.id.btnColorPink);
        btnPurple = (Button) findViewById(R.id.btnColorPurple);
        btnYellow = (Button) findViewById(R.id.btnColorYellow);

        btnRed.getBackground().setColorFilter(getResources().getColor(R.color.flat_red), PorterDuff.Mode.MULTIPLY);
        btnBlue.getBackground().setColorFilter(getResources().getColor(R.color.flat_blue), PorterDuff.Mode.MULTIPLY);
        btnGreen.getBackground().setColorFilter(getResources().getColor(R.color.flat_green), PorterDuff.Mode.MULTIPLY);
        btnPink.getBackground().setColorFilter(getResources().getColor(R.color.flat_pink), PorterDuff.Mode.MULTIPLY);
        btnPurple.getBackground().setColorFilter(getResources().getColor(R.color.flat_purple), PorterDuff.Mode.MULTIPLY);
        btnYellow.getBackground().setColorFilter(getResources().getColor(R.color.flat_yellow), PorterDuff.Mode.MULTIPLY);
    }
}
