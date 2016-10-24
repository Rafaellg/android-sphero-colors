package com.kreativ.spherocolors;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.orbotix.ConvenienceRobot;
import com.orbotix.DualStackDiscoveryAgent;
import com.orbotix.common.DiscoveryException;
import com.orbotix.common.Robot;
import com.orbotix.common.RobotChangedStateListener;


public class StartActivity extends Activity implements RobotChangedStateListener {

    private CharSequence mTitle;
    public ConvenienceRobot mRobot;
    public DualStackDiscoveryAgent coneccao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        coneccao.getInstance().addRobotStateListener( this );

        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, PreGameActivity.class);
                startActivity(intent);
            }
        });
        Button btnHowToPlay = (Button) findViewById(R.id.btnHow);
        btnHowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, HowToPlayActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void handleRobotChangedState(Robot robot, RobotChangedStateNotificationType type) {
        switch (type) {
            case Online: {
                //Save the robot as a ConvenienceRobot for additional utility methods
                mRobot = new ConvenienceRobot( robot );

                //Create an OrbBasicControl for loading programs onto the robot
//                mOrbBasicControl = new OrbBasicControl( robot );
//                mOrbBasicControl.addEventListener((OrbBasicEventListener) this);
                Log.d("OK", "Sphero conectado");
                Toast.makeText(getApplicationContext(), "Sphero conectado!", Toast.LENGTH_SHORT).show();
                int color = getResources().getColor(R.color.flat_green);
                int r = Color.red(color);
                int g = Color.green(color);
                int b = Color.blue(color);
                float red = r/255f;
                float green = g/255f;
                float blue = b/255f;

                String cor = "CORE " + red + " " + green + " " + blue;
                Log.d("COREEEEES", cor);

//                mRobot.setLed(0.0f, 0.0f, 1.0f);
                mRobot.setLed(red,green,blue);
                break;
            }
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_start);
                break;
            case 2:
                mTitle = getString(R.string.title_how_to_play);
                break;
            case 3:
                mTitle = getString(R.string.title_settings);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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

//METODO DESGRAÇADO QUA TAVA FAZENDO A CONEXÃO COM BULINHA CAIR
//    @Override
//    protected void onStop() {
//        //If the DiscoveryAgent is in discovery mode, stop it.
//        if( coneccao.getInstance().isDiscovering() ) {
//            coneccao.getInstance().stopDiscovery();
//        }
//
//        //If a robot is connected to the device, disconnect it
//        if( mRobot != null ) {
//            mRobot.disconnect();
//            mRobot = null;
//        }
//
//        super.onStop();
//    }

}
