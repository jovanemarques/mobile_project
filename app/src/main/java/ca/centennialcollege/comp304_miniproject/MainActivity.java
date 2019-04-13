package ca.centennialcollege.comp304_miniproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;

import ca.centennialcollege.comp304_miniproject.models.DataRepository;
import ca.centennialcollege.comp304_miniproject.services.MusicService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataRepository.SeedData();
    }

    public void btnStartService_onClick(View view) {
        startService(new Intent(getBaseContext(), MusicService.class));
    }

    public void btnStopService_onClick(View view) {
        stopService(new Intent(getBaseContext(), MusicService.class));
    }
}
