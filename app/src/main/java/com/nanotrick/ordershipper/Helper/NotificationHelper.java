package com.nanotrick.ordershipper.Helper;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.net.Uri;
import android.os.Build;

import com.nanotrick.ordershipper.R;


/**
 * Created by milymozz on 2018. 2. 20..
 */

public class NotificationHelper extends ContextWrapper {

    private static final String NANO_CHANNEL_ID = "com.nanotrick.ordershipper.nanotrick";
    private static final String NANO_CHANNEL_NAME = "OrderFood";

    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) // Only working this function if Api is 26 or higher
            createChanel();
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChanel() {
        NotificationChannel edtChannel = new NotificationChannel(
                NANO_CHANNEL_ID,
                NANO_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT);
        edtChannel.enableLights(false);
        edtChannel.enableVibration(true);
        edtChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(edtChannel);
    }

    public NotificationManager getManager() {
        if (manager == null)
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        return manager;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public Notification.Builder edtEatItChannelNotification(String title, String body, PendingIntent contentIntent, Uri soundUri) {
        return new Notification.Builder(getApplicationContext(),NANO_CHANNEL_ID)
                .setContentIntent(contentIntent)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_local_shipping_black_24dp)
                .setSound(soundUri)
                .setAutoCancel(false);
    }

    @TargetApi(Build.VERSION_CODES.O)
    public Notification.Builder edtEatItChannelNotification(String title, String body, Uri soundUri) {
        return new Notification.Builder(getApplicationContext(), NANO_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_local_shipping_black_24dp)
                .setSound(soundUri)
                .setAutoCancel(false);
    }

}
