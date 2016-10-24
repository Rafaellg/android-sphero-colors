package com.kreativ.spherocolors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PreGameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);

        Button btnReadNo = (Button) findViewById(R.id.btnReadNo);
        btnReadNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreGameActivity.this, GameNoReadActivity.class);
                startActivity(intent);
            }
        });

        Button btnReadYes = (Button) findViewById(R.id.btnReadYes);
        btnReadYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreGameActivity.this, GameReadActivity.class);
                startActivity(intent);
            }
        });
    }
}
