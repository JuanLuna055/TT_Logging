package com.example.tt_logging.Receta;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.tt_logging.Menu_principla_Activity;
import com.example.tt_logging.R;
import com.example.tt_logging.Receta.repeticiones.Recepcion_Activity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WorkManager_noti extends Worker {

    private NotificationManager notificationManager;
    Notification notification;


    public WorkManager_noti(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    /// Metodo Guarar noti
    // Long: Duaracion en la que debe
    public static void Guardarnoti (Long duration, Data data, String tag){
        System.out.println("Estamos en Guardar noti -->"+ duration +"Tag: "+tag);
        OneTimeWorkRequest noti = new OneTimeWorkRequest.Builder(WorkManager_noti.class)
                .setInitialDelay(duration, TimeUnit.MILLISECONDS).addTag(tag)
                .setInputData(data).build();

        WorkManager instance =WorkManager.getInstance();
        instance.enqueue(noti);
    }

    @NonNull
    @Override
    public Result doWork() {
        Context context = this.getApplicationContext();
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        String titulo = getInputData().getString("titulo");
        String detalle = getInputData().getString("detalle");
        String id =  getInputData().getString("id_noti");
        oreo(titulo,detalle,id);
        System.out.println("Se ejecuto la alarma: "+titulo+" Detalle: "+detalle+" Id: "+id);
        return Result.success();
    }

    // t= titulo y d = detalle
    public void oreo(String t, String d, String id_listelement){
        String id = "messange";

        NotificationManager nm = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),id);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Intent intent = new Intent(getApplicationContext(), Recepcion_Activity.class);
        Bundle bundle = new Bundle();
        bundle.putString("medicina",id_listelement);
        intent.putExtras(bundle);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel nc = new NotificationChannel(id,"nuevo",NotificationManager.IMPORTANCE_HIGH);
            nc.setDescription("Notification FCM");
            nc.setShowBadge(true);
            assert nm != null;
            System.out.println("Bandera 1");
            nm.createNotificationChannel(nc);
        }
/*


        // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),1,intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(t).setCategory(Notification.CATEGORY_SERVICE)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSound(soundUri)
                .setTicker("Nueva notification")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(d)
                .setContentIntent(pendingIntent)
                .setContentInfo("nuevo")
                .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

        Random random = new Random();
        int idNotify = random.nextInt(8000);

        assert nm != null;
        nm.notify(idNotify,builder.build());

 */
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),1,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification = new NotificationCompat.Builder(getApplicationContext())
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_medical_services_black_24dp)
                .setSound(soundUri)
                .setColor(Color.BLUE)
                .setVibrate(new long[]{1000,1000,1000,1000})
                .setAutoCancel(true)
                .setContentTitle(t).setCategory(Notification.CATEGORY_SERVICE)
                .setContentText(d).build();
        nm.notify(123,notification);

        System.out.println("Bandera 2");
    }
}
