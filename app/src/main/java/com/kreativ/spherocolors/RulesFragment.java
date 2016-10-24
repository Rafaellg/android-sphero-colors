package com.kreativ.spherocolors;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.orbotix.ConvenienceRobot;

public class RulesFragment extends Fragment {

    private Button btnStartFromHow;
    private ConvenienceRobot mRobot;

    @SuppressLint("ValidFragment")
    public RulesFragment(ConvenienceRobot robot) {
        mRobot = robot;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rules, container, false);

        btnStartFromHow = (Button) view.findViewById(R.id.btnStartFromHow);
        btnStartFromHow.getBackground().setColorFilter(getResources().getColor(R.color.flat_green), PorterDuff.Mode.MULTIPLY);
        btnStartFromHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreGameFragment preGameFragment = new PreGameFragment(mRobot);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, preGameFragment).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
