package com.example.gcuitfesst;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class AppNotificationHandler extends FirebaseMessagingService {
    public static int Notification_id=1;
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        generateNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());

    }

    private void generateNotification(String body, String title) {
        Intent intent = new Intent(this,LoginPageScreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel("MyNotification","MyNotification",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notificationbuilder = new NotificationCompat.Builder(this,"MyNotification");
                notificationbuilder.setSmallIcon(R.drawable.notificationlogo);
                notificationbuilder.setContentTitle(title);
                notificationbuilder.setContentText(body);
                notificationbuilder.setAutoCancel(true);
                notificationbuilder.setSound(soundUri);
                notificationbuilder.setContentIntent(pendingIntent);

        NotificationManagerCompat manager =NotificationManagerCompat.from(this);
if(Notification_id>1073741824)
{
    Notification_id=0;
}
manager.notify(Notification_id,notificationbuilder.build());

    }
}
