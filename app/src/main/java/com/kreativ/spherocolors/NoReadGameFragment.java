package com.kreativ.spherocolors;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.orbotix.ConvenienceRobot;

@SuppressLint("ValidFragment")
public class NoReadGameFragment extends Fragment {

    private Button btnRed, btnBlue, btnGreen, btnPink, btnPurple, btnYellow, btnBegin;
    private TextView txtScore, txtHighScore;
    private Chronometer timer;
    private ConvenienceRobot mRobot;
    private int actualColor, score = 0, highScore = 0, lastColor = -1;
    private boolean enable = false;
    private int PONTUACAO_ERRO = -100, PONTUACAO_ACERTO = 100;
    private SharedPreferences preferences;

    @SuppressLint("ValidFragment")
    public NoReadGameFragment(ConvenienceRobot robot) {
        mRobot = robot;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_no_read_game, container, false);

        preferences = getContext().getSharedPreferences(getString(R.string.pref_title), 0);
        highScore = preferences.getInt(getString(R.string.pref_high_score_no_read), 0);

        txtScore = (TextView) view.findViewById(R.id.txtScore);
        txtHighScore = (TextView) view.findViewById(R.id.txtHighScore);
        txtHighScore.setText(""+highScore);

        timer = (Chronometer) view.findViewById(R.id.timer);
        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(chronometer.getText().toString().equalsIgnoreCase(getString(R.string.chrono_max_time))) {
                    timer.stop();
                    enable = false;
                    btnBegin.setEnabled(true);
                    timer.setTextColor(Color.RED);

                    if(score > highScore) {
                        preferences.edit().putInt(getString(R.string.pref_high_score_no_read), score).commit();
                        txtHighScore.setText(""+score);
                    }

                    Snackbar.make(view, getString(R.string.time_over), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        btnRed = (Button) view.findViewById(R.id.btnColorRed);
        btnBlue = (Button) view.findViewById(R.id.btnColorBlue);
        btnGreen = (Button) view.findViewById(R.id.btnColorGreen);
        btnPink = (Button) view.findViewById(R.id.btnColorPink);
        btnPurple = (Button) view.findViewById(R.id.btnColorPurple);
        btnYellow = (Button) view.findViewById(R.id.btnColorYellow);
        btnBegin = (Button) view.findViewById(R.id.btnBegin);

        btnRed.getBackground().setColorFilter(getResources().getColor(R.color.flat_red), PorterDuff.Mode.MULTIPLY);
        btnBlue.getBackground().setColorFilter(getResources().getColor(R.color.flat_blue), PorterDuff.Mode.MULTIPLY);
        btnGreen.getBackground().setColorFilter(getResources().getColor(R.color.flat_green), PorterDuff.Mode.MULTIPLY);
        btnPink.getBackground().setColorFilter(getResources().getColor(R.color.flat_pink), PorterDuff.Mode.MULTIPLY);
        btnPurple.getBackground().setColorFilter(getResources().getColor(R.color.flat_purple), PorterDuff.Mode.MULTIPLY);
        btnYellow.getBackground().setColorFilter(getResources().getColor(R.color.flat_yellow), PorterDuff.Mode.MULTIPLY);
        btnBegin.getBackground().setColorFilter(getResources().getColor(R.color.flat_green), PorterDuff.Mode.MULTIPLY);

        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyColor(getResources().getColor(R.color.flat_red));
            }
        });
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyColor(getResources().getColor(R.color.flat_blue));
            }
        });
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyColor(getResources().getColor(R.color.flat_green));
            }
        });
        btnPink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyColor(getResources().getColor(R.color.flat_pink));
            }
        });
        btnPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyColor(getResources().getColor(R.color.flat_purple));
            }
        });
        btnYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyColor(getResources().getColor(R.color.flat_yellow));
            }
        });

        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reinicia o score
                score = 0;
                txtScore.setText("" + score);

                // Habilita os botões de cor
                enable = true;

                // Escolhe uma cor random
                changeSpheroColor();

                // Inicia o cronometro
                timer.setBase(SystemClock.elapsedRealtime());
                timer.setTextColor(Color.BLACK);
                timer.start();

                btnBegin.setEnabled(false);

                Snackbar.make(view, getString(R.string.game_started), Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void verifyColor(int btnColor) {
        if (enable) {
            if (actualColor == btnColor) {
                updateScore(true);
                //Toast.makeText(getContext(), "ACERTOU! =)", Toast.LENGTH_SHORT).show();
            } else {
                updateScore(false);
                mRobot.drive(0.0f, 0.2f);
                //Toast.makeText(getContext(), "ERROU! =(", Toast.LENGTH_SHORT).show();
            }
            changeSpheroColor();
        }
    }

    public void updateScore(boolean success) {
        score += success? PONTUACAO_ACERTO : PONTUACAO_ERRO;
        txtScore.setText("" + score);
    }

    public void changeSpheroColor() {
        int num = 0;
        int color = 0;

        while (lastColor == num) {
            num = (int) (Math.random() * 5);
        }

        if (num == 0) {
            color = getResources().getColor(R.color.flat_green);
        } else if (num == 1) {
            color = getResources().getColor(R.color.flat_red);
        } else if (num == 2) {
            color = getResources().getColor(R.color.flat_blue);
        } else if (num == 3) {
            color = getResources().getColor(R.color.flat_pink);
        } else if (num == 4) {
            color = getResources().getColor(R.color.flat_yellow);
        } else if (num == 5) {
            color = getResources().getColor(R.color.flat_purple);
        }

        actualColor = color;
        lastColor = num;
        setColorOnSphero(color);
    }

    public void setColorOnSphero(int color) {
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        float red = r/255f;
        float green = g/255f;
        float blue = b/255f;
        mRobot.setLed(red, green, blue);
    }
}
