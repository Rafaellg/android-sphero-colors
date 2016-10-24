package com.kreativ.spherocolors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HowToPlayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        Button btnStartFromHow = (Button) findViewById(R.id.btnStartFromHow);
        btnStartFromHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HowToPlayActivity.this, PreGameActivity.class);
                startActivity(intent);
            }
        });
    }
}
