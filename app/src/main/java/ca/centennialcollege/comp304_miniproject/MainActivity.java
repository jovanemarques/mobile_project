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
import android.widget.ToggleButton;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import ca.centennialcollege.comp304_miniproject.models.DataRepository;
import ca.centennialcollege.comp304_miniproject.services.MusicService;

public class MainActivity extends AppCompatActivity {

    ImageButton btnMusic;

    boolean hasMusic = false;

    final private int REQUEST_INTERNET = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataRepository.SeedData();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    REQUEST_INTERNET);

        } else {
            // for text
            // new DownloadTextTask().execute("http://jfdimarzio.com/test.htm");
            //for image
            new DownloadImageTask().execute("https://www.bdpinternational.com/uploads/attachments/cj8nd0rnt003ks6qpbpxxru4a-port-cover-image.0.218.4288.2412.max.jpg");
            // for web services
            //new AccessWebServiceTask().execute("Logistics");
        }

        btnMusic = findViewById(R.id.btnMusic);
        btnMusic.setImageDrawable(getDrawable(R.drawable.baseline_music_off_black_18dp));
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

    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (Exception ex) {
            Log.d("Networking", ex.getLocalizedMessage());
            throw new IOException("Error connecting");
        }
        return in;
    }

    private Bitmap DownloadImage(String URL) {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            Log.d("NetworkingActivity", e1.getLocalizedMessage());
        }
        return bitmap;
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        protected Bitmap doInBackground(String... urls) {
            return DownloadImage(urls[0]);
        }

        protected void onPostExecute(Bitmap result) {
            ImageView img = (ImageView) findViewById(R.id.imgImage);
            img.setImageBitmap(result);
        }
    }
}


