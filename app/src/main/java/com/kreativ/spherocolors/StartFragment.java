package com.kreativ.spherocolors;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.orbotix.ConvenienceRobot;

@SuppressLint("ValidFragment")
public class StartFragment extends Fragment {

    private Button btnStart, btnRules;
    private ConvenienceRobot mRobot;

    @SuppressLint("ValidFragment")
    public StartFragment(ConvenienceRobot robot) {
        mRobot = robot;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        btnStart = (Button) view.findViewById(R.id.btnStart);
        btnStart.getBackground().setColorFilter(getResources().getColor(R.color.flat_green), PorterDuff.Mode.MULTIPLY);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreGameFragment preGameFragment = new PreGameFragment(mRobot);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, preGameFragment).addToBackStack(null).commit();
            }
        });

        btnRules = (Button) view.findViewById(R.id.btnRules);
        btnRules.getBackground().setColorFilter(getResources().getColor(R.color.flat_purple), PorterDuff.Mode.MULTIPLY);
        btnRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RulesFragment rulesFragment = new RulesFragment(mRobot);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, rulesFragment).addToBackStack(null).commit();
            }
        });

        return view;
    }

}
