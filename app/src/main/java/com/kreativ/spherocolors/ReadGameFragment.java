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
public class ReadGameFragment extends Fragment {

    private Button btnTextRed, btnTextBlue, btnTextGreen, btnTextPink, btnTextPurple, btnTextYellow, btnBegin;
    private TextView txtScore, txtHighScore;
    private Chronometer timer;
    private ConvenienceRobot mRobot;
    private int actualColor, score = 0, highScore = 0, lastColor = -1;
    private boolean enable = false;
    private int PONTUACAO_ERRO = -100, PONTUACAO_ACERTO = 100;
    private SharedPreferences preferences;

    @SuppressLint("ValidFragment")
    public ReadGameFragment(ConvenienceRobot robot) {
        mRobot = robot;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_read_game, container, false);

        preferences = getContext().getSharedPreferences(getString(R.string.pref_title), 0);
        highScore = preferences.getInt(getString(R.string.pref_high_score_read), 0);

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
                        preferences.edit().putInt(getString(R.string.pref_high_score_read), score).commit();
                        txtHighScore.setText(""+score);
                    }

                    Snackbar.make(view, getString(R.string.time_over), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        btnTextRed = (Button) view.findViewById(R.id.btnTextRed);
        btnTextBlue = (Button) view.findViewById(R.id.btnTextBlue);
        btnTextGreen = (Button) view.findViewById(R.id.btnTextGreen);
        btnTextPink = (Button) view.findViewById(R.id.btnTextPink);
        btnTextPurple = (Button) view.findViewById(R.id.btnTextPurple);
        btnTextYellow = (Button) view.findViewById(R.id.btnTextYellow);
        btnBegin = (Button) view.findViewById(R.id.btnBegin);

        btnTextRed.getBackground().setColorFilter(getResources().getColor(R.color.btn_default), PorterDuff.Mode.MULTIPLY);
        btnTextBlue.getBackground().setColorFilter(getResources().getColor(R.color.btn_default), PorterDuff.Mode.MULTIPLY);
        btnTextGreen.getBackground().setColorFilter(getResources().getColor(R.color.btn_default), PorterDuff.Mode.MULTIPLY);
        btnTextPink.getBackground().setColorFilter(getResources().getColor(R.color.btn_default), PorterDuff.Mode.MULTIPLY);
        btnTextPurple.getBackground().setColorFilter(getResources().getColor(R.color.btn_default), PorterDuff.Mode.MULTIPLY);
        btnTextYellow.getBackground().setColorFilter(getResources().getColor(R.color.btn_default), PorterDuff.Mode.MULTIPLY);
        btnBegin.getBackground().setColorFilter(getResources().getColor(R.color.flat_green), PorterDuff.Mode.MULTIPLY);

        btnTextRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyColor(getResources().getColor(R.color.flat_red));
            }
        });
        btnTextBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyColor(getResources().getColor(R.color.flat_blue));
            }
        });
        btnTextGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyColor(getResources().getColor(R.color.flat_green));
            }
        });
        btnTextPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyColor(getResources().getColor(R.color.flat_purple));
            }
        });
        btnTextYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyColor(getResources().getColor(R.color.flat_yellow));
            }
        });
        btnTextPink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyColor(getResources().getColor(R.color.flat_pink));
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
            num = (int) (Math.random() * 6);
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
        } else {
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
