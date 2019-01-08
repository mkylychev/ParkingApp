package test.home.com.parkingapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import test.home.com.parkingapp.R;
import test.home.com.parkingapp.activity.MapsActivity.MapsActivity;
import test.home.com.parkingapp.services.LocationService;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if(!runtimePermissions())
            startService(new Intent(getApplicationContext(), LocationService.class));

    }

    private boolean runtimePermissions() {
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);

            return true;
        }
        startMapActivity();
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if( grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED){
                startService(new Intent(getApplicationContext(), LocationService.class));
                startActivity(new Intent(SplashScreenActivity.this, MapsActivity.class));
            } else {
                startMapActivity();
                runtimePermissions();
            }
    }

    private void startMapActivity(){
        Handler handler = new Handler();
        Runnable runnable = () -> startActivity(new Intent(SplashScreenActivity.this, MapsActivity.class));
        handler.postDelayed(runnable, 2000);
    }
}
