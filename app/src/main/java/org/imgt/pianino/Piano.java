package org.imgt.pianino;


import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


public class Piano extends AppCompatActivity{

    private Button bDo,bRe,bMi,bFa,bSol,bLa,bSi,bDoA;
    private Animation pulsarse;
    private SoundPool soundPool;
    private int idDo, idRe,idMi,idFa,idSol,idLa,idSi,idDoA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano);

        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        idDo = soundPool.load(getApplicationContext(),R.raw.notado,0);
        idRe = soundPool.load(getApplicationContext(),R.raw.notare,0);
        idMi = soundPool.load(getApplicationContext(),R.raw.notami,0);
        idFa = soundPool.load(getApplicationContext(),R.raw.notafa,0);
        idSol = soundPool.load(getApplicationContext(),R.raw.notasol,0);
        idLa = soundPool.load(getApplicationContext(),R.raw.notala,0);
        idSi = soundPool.load(getApplicationContext(),R.raw.notasi,0);
        idDoA = soundPool.load(getApplicationContext(),R.raw.notado_agudo,0);




        bDo= findViewById(R.id.b_do);
        bRe= findViewById(R.id.b_re);
        bMi= findViewById(R.id.b_mi);
        bFa= findViewById(R.id.b_fa);
        bSol= findViewById(R.id.b_sol);
        bLa= findViewById(R.id.b_la);
        bSi= findViewById(R.id.b_si);
        bDoA= findViewById(R.id.b_do_agudo);

        bDo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                soundPool.play(idDo, 1 ,1 , 0, 0, 1);
            }
        });



        bRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(idRe, 1 ,1 , 0, 0, 1);
            }
        });
        bMi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(idMi, 1 ,1 , 0, 0, 1);
            }
        });
        bFa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(idFa, 1 ,1 , 0, 0, 1);
            }
        });
        bSol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(idSol, 1 ,1 , 0, 0, 1);
            }
        });
        bLa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(idLa, 1 ,1 , 0, 0, 1);
            }
        });
        bSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(idSi, 1 ,1 , 0, 0, 1);
            }
        });
        bDoA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(idDoA, 1 ,1 , 0, 0, 1);
            }
        });

    }


}
