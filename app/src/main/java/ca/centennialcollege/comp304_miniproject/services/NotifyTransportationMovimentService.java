package ca.centennialcollege.comp304_miniproject.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import ca.centennialcollege.comp304_miniproject.R;

public class NotifyTransportationMovimentService extends Service {

    String txtTitle = "Text Title";
    String txtContent = "Text Content";
    String CHANNEL_ID = "MINIPROJECT_APP_CHANNEL";
    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
            //.setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(txtTitle)
            .setContentText(txtContent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //CharSequence name = getString(R.string.channel_name);
            CharSequence name = "Mini Project Channel";
            //String description = getString(R.string.channel_description);
            String description = "Mini Project Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
