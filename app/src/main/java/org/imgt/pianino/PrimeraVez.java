package org.imgt.pianino;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PrimeraVez extends AppCompatActivity {

    //MULTIMEDIA ///////
    SoundPool soundPool;
    int idError;

    Button bOk;
    EditText et_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_vez);

        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        idError = soundPool.load(getApplicationContext(), R.raw.error, 0);

        bOk= findViewById(R.id.b_ok);
        et_nombre= findViewById(R.id.et_nom_jugador);

        bOk.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(et_nombre.getText().toString().equals("")){
                    //Mover editText
                    soundPool.play(idError, 1, 1, 1, 0, 1);
                    Animation moverCampo= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_nombre_vacio);
                    et_nombre.startAnimation(moverCampo);

                }else {
                    guardarNombre();
                }
            }
        });

    }

    public void guardarNombre(){
        //SharedPreferences pref = getSharedPreferences("datos_aplicacion", Context.MODE_PRIVATE);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("nombrejugador", et_nombre.getText().toString());
        editor.apply();
        finish();
    }


}
