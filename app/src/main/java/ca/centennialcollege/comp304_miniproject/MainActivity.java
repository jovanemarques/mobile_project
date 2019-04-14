package ca.centennialcollege.comp304_miniproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import ca.centennialcollege.comp304_miniproject.models.DataRepository;
import ca.centennialcollege.comp304_miniproject.services.MusicService;
import ca.centennialcollege.comp304_miniproject.utils.ExternalAsset;

public class MainActivity extends AppCompatActivity {

    ImageButton btnMusic;

    boolean hasMusic = false;

    final private int REQUEST_INTERNET = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initial data
        DataRepository.SeedData();

        // Verify permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, REQUEST_INTERNET);
        } else {
            new DownloadImageTask().execute("https://www.bdpinternational.com/uploads/attachments/cj8nd0rnt003ks6qpbpxxru4a-port-cover-image.0.218.4288.2412.max.jpg");
        }

        btnMusic = findViewById(R.id.btnMusic);
        btnMusic.setImageDrawable(getDrawable(R.drawable.baseline_music_note_black_18dp));
        btnMusic.setBackgroundColor(Color.GREEN);
    }

    public void btnMusic_onClick(View view) {
        if (hasMusic) {
            stopService(new Intent(getBaseContext(), MusicService.class));
            btnMusic.setImageDrawable(getDrawable(R.drawable.baseline_music_note_black_18dp));
            btnMusic.setBackgroundColor(Color.GREEN);
            hasMusic = false;
        } else {
            startService(new Intent(getBaseContext(), MusicService.class));
            btnMusic.setImageDrawable(getDrawable(R.drawable.baseline_music_off_black_18dp));
            btnMusic.setBackgroundColor(Color.RED);
            hasMusic = true;
        }
    }

    public void btnViewOrders_onClick(View view) {
        Intent intent = new Intent(this, OrdersActivity.class);
        startActivity(intent);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        protected Bitmap doInBackground(String... urls) {
            return ExternalAsset.DownloadImage(urls[0]);
        }

        protected void onPostExecute(Bitmap result) {
            ImageView img = (ImageView) findViewById(R.id.imgImage);
            img.setImageBitmap(result);
        }
    }


}


