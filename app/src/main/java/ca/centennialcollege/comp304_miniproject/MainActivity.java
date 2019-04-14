package ca.centennialcollege.comp304_miniproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.content.Intent;
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
    }

    public void btnStartService_onClick(View view) {
        startService(new Intent(getBaseContext(), MusicService.class));
    }

    public void btnStopService_onClick(View view) {
        stopService(new Intent(getBaseContext(), MusicService.class));
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        protected Bitmap doInBackground(String... urls) {
            return ExternalAsset.DownloadImage(urls[0]);
        }

        protected void onPostExecute(Bitmap result) {
            ImageView img = (ImageView) findViewById(R.id.imageView);
            img.setImageBitmap(result);
        }
    }
}


