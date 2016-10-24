package com.kreativ.spherocolors;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.orbotix.ConvenienceRobot;
import com.orbotix.DualStackDiscoveryAgent;
import com.orbotix.common.DiscoveryException;
import com.orbotix.common.Robot;
import com.orbotix.common.RobotChangedStateListener;

public class SpheroColorsActivity extends AppCompatActivity implements RobotChangedStateListener {

    public DualStackDiscoveryAgent coneccao;
    private ConvenienceRobot mRobot;
    private LinearLayout layLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sphero_colors);

        coneccao.getInstance().addRobotStateListener(this);
        layLoading = (LinearLayout) findViewById(R.id.layLoading);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //If the DiscoveryAgent is not already looking for robots, start discovery.
        if( !coneccao.getInstance().isDiscovering() ) {
            try {
                coneccao.getInstance().startDiscovery(getApplicationContext());
            } catch (DiscoveryException e) {
                Log.e("Sphero", "DiscoveryException: " + e.getMessage());
            }
        }
    }

    @Override

    public void handleRobotChangedState(Robot robot, RobotChangedStateNotificationType type) {
        switch (type) {
            case Online: {
                if (findViewById(R.id.fragment_container) != null) {
                    mRobot = new ConvenienceRobot(robot);
                    layLoading.setVisibility(View.GONE);
                    StartFragment firstFragment = new StartFragment(mRobot);
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();

                    View parentLayout = findViewById(R.id.parentView);
                    Snackbar.make(parentLayout, getString(R.string.sphero_connected), Snackbar.LENGTH_SHORT).show();

                    int color = getResources().getColor(R.color.flat_green);
                    int r = Color.red(color);
                    int g = Color.green(color);
                    int b = Color.blue(color);
                    float red = r/255f;
                    float green = g/255f;
                    float blue = b/255f;
                    mRobot.setLed(red, green, blue);
                }
                break;
            }
            case FailedConnect: {
                //If the DiscoveryAgent is not already looking for robots, start discovery.
                if( !coneccao.getInstance().isDiscovering() ) {
                    try {
                        coneccao.getInstance().startDiscovery(getApplicationContext());
                    } catch (DiscoveryException e) {
                        Log.e("Sphero", "DiscoveryException: " + e.getMessage());
                    }
                }
            }
        }
    }
}
