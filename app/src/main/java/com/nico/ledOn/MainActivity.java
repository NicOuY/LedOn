package com.nico.ledOn;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;

public class MainActivity extends AppCompatActivity {

    private Camera camera;
    private boolean isLighOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        camera = Camera.open();
        final Parameters p = camera.getParameters();

        p.setFlashMode(Parameters.FLASH_MODE_TORCH);
        camera.setParameters(p);
        camera.startPreview();
        isLighOn = true;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLighOn) {
                    p.setFlashMode(Parameters.FLASH_MODE_OFF);
                    camera.setParameters(p);
                    camera.stopPreview();
                    isLighOn = false;
                    Snackbar.make(view, "torch is turn off!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    // Log.i("MainActivity", "torch is turn off!");
                } else {
                    p.setFlashMode(Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(p);
                    camera.startPreview();
                    isLighOn = true;
                    Snackbar.make(view, "torch is turn on!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    // Log.i("MainActivity", "torch is turn on!");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /* if (id == R.id.action_exit) {
            // return true;
            System.exit(0);
        } */

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (camera != null) {
            camera.release();
        }
    }

    public void onBackPressed() {
        System.exit(0);
    }
}
