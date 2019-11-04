package com.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    static final String CANAL_ID = "mi_canal";
    static final int NOTIFICACION_ID = 1;
    private Button boton;
    private EditText editTextNum1,editTextNum2,editTextResul;
    private TextView editTextSol;
    private int correctas=0;
    int num1;
    int num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = findViewById(R.id.buttonCompror);
        editTextNum1 = findViewById(R.id.editTextNum1);
        editTextNum2 = findViewById(R.id.editTextNum2);
        editTextResul = findViewById(R.id.editTextResul);
        editTextSol = findViewById(R.id.textView3);



        cargarNumeros();
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(num1+num2==Integer.parseInt(editTextResul.getText().toString()))
                {

                    correctas++;
                    if(correctas==10)
                    {
                        //Creamos notificacion
                        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        //Creamos el canal. SOLO puede hacerse en dispositivos con ver.8 o más.
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel notificationChannel = new NotificationChannel(
                                    CANAL_ID, "Mis notificaciones", NotificationManager.IMPORTANCE_DEFAULT);
                            notificationChannel.setDescription("Descripción del canal");
                            notificationManager.createNotificationChannel(notificationChannel);
                        }
                        NotificationCompat.Builder notificacion =
                                new NotificationCompat.Builder(MainActivity.this, CANAL_ID)
                                        .setSmallIcon(R.drawable.ic_chat)
                                        .setContentTitle("Enhorabuena!!!")
                                        .setContentText("Has hacertado 10 preguntas");
                        notificationManager.notify(NOTIFICACION_ID, notificacion.build());


                    }
                    else
                    {
                        cargarNumeros();

                    }

                    cargarNumeros();
                    editTextSol.setText("Respuestas Correctas: "+correctas);
                }


            }

        });
    }

    private void cargarNumeros()
    {
        num1= (int)(Math.random()*100)+1;
        num2= (int)(Math.random()*100)+1;
        editTextNum1.setText(num1+"");
        editTextNum2.setText(num2+"");
    }


}
