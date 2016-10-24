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

public class PreGameFragment extends Fragment {

    private Button btnYes, btnNo;
    private ConvenienceRobot mRobot;

    @SuppressLint("ValidFragment")
    public PreGameFragment(ConvenienceRobot robot) {
        mRobot = robot;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pre_game, container, false);

        btnYes = (Button) view.findViewById(R.id.btnReadYes);
        btnYes.getBackground().setColorFilter(getResources().getColor(R.color.flat_green), PorterDuff.Mode.MULTIPLY);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadGameFragment readGameFragment = new ReadGameFragment(mRobot);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, readGameFragment).addToBackStack(null).commit();
            }
        });

        btnNo = (Button) view.findViewById(R.id.btnReadNo);
        btnNo.getBackground().setColorFilter(getResources().getColor(R.color.flat_purple), PorterDuff.Mode.MULTIPLY);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoReadGameFragment noReadGameFragment = new NoReadGameFragment(mRobot);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, noReadGameFragment).addToBackStack(null).commit();
            }
        });

        return view;
    }

}
