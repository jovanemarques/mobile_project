package ca.centennialcollege.comp304_miniproject.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import ca.centennialcollege.comp304_miniproject.R;

public class MusicService extends Service {

    MediaPlayer mediaPlayer;
    private static boolean running = false;

    public boolean isRunning() {
        return running;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!running) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.audio);
            mediaPlayer.start();
            running = true;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        running = false;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
