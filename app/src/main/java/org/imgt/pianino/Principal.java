package org.imgt.pianino;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity{// implements DialogNombre.Communicator{

    SharedPreferences pref;
    ImageView tit_pianino, b_settings,b_piano,b_animales,b_colores,b_puntuaciones;
    MediaPlayer mp;
    TextView holaJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        holaJugador= findViewById(R.id.TV_hola_jugador);

        //Botones de notala pantalla principal
        b_settings= findViewById(R.id.ico_settings);
        b_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation pulsarse= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_boton_pulsado);
                b_settings.startAnimation(pulsarse);
                lanzarConfiguracion(null);
            }
        });

        b_piano= findViewById(R.id.ico_teclado);
        b_piano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation pulsarse= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_boton_pulsado);
                b_piano.startAnimation(pulsarse);
                lanzarPiano(null);
            }
        });

        b_animales= findViewById(R.id.ico_animales);
        b_animales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation pulsarse= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_boton_pulsado);
                b_animales.startAnimation(pulsarse);
                //lanzarAnimales(null);
            }
        });

        b_colores= findViewById(R.id.ico_colores);
        b_colores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation pulsarse= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_boton_pulsado);
                b_colores.startAnimation(pulsarse);
                //lanzarColores(null);
            }
        });

        b_puntuaciones= findViewById(R.id.ico_scores);
        b_puntuaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation pulsarse= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_boton_pulsado);
                b_puntuaciones.startAnimation(pulsarse);
                //lanzarPuntuaciones(null);
            }
        });

        //Aparición del título Pianino
        tit_pianino= findViewById(R.id.tit_pianino);
        Animation apareceTitulo= AnimationUtils.loadAnimation(this, R.anim.anim_tit_pianino);
        tit_pianino.startAnimation(apareceTitulo);

        //Aparición botones al inicio
        Animation apareceBotonDeAbajo= AnimationUtils.loadAnimation(this, R.anim.anim_botones_inicio_principal);
        Animation apareceBotonDeIzquierda= AnimationUtils.loadAnimation(this, R.anim.anim_boton_desp_derecha);
        Animation apareceBotonDeDerecha= AnimationUtils.loadAnimation(this, R.anim.anim_boton_desp_izquierda);
        b_settings.startAnimation(apareceBotonDeIzquierda);
        b_piano.startAnimation(apareceBotonDeIzquierda);
        b_animales.startAnimation(apareceBotonDeAbajo);
        ImageView chica= findViewById(R.id.chica);
        chica.startAnimation(apareceBotonDeAbajo);
        b_colores.startAnimation(apareceBotonDeDerecha);
        b_puntuaciones.startAnimation(apareceBotonDeDerecha);

        //Recuperamos las preferencias
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        //Música
        mp= MediaPlayer.create(this,R.raw.pussycat);
        mp.setLooping(true);
    }

    public void lanzarConfiguracion(View view){
        Intent i= new Intent(this, Configuracion.class);
        startActivity(i);
    }

    public void lanzarPiano(View view){
        Intent i= new Intent(this, Piano.class);
        startActivity(i);
    }
    public void lanzarAnimales(View view){
        //Intent i= new Intent(this, Animales.class);
        //startActivity(i);
    }
    public void lanzarColores(View view){
        //Intent i= new Intent(this, Colores.class);
        //startActivity(i);
    }
    public void lanzarPuntuaciones(View view){
        //Intent i= new Intent(this, Puntuaciones.class);
        //startActivity(i);
    }

    public void pedirNombre(View view){
        Intent i= new Intent(this, PrimeraVez.class);
        startActivity(i);
    }



    //CICLO DE VIDA DE LA APLICACIÓN
    @Override protected void onStart() {
        super.onStart();
        //Cuadro de diálogo para pedir nombre al usuario
        if(pref.getString("nombrejugador",null)==null) {
            pedirNombre(null);
        }else{
            //Toast.makeText(this,"¡Hola, "+pref.getString("nombrejugador",null)+"!", Toast.LENGTH_LONG).show();
            holaJugador.setText(getString(R.string.hola)+", "+pref.getString("nombrejugador",null)+"!");
            Animation fadeIn= AnimationUtils.loadAnimation(this, R.anim.anim_fade_in);
            holaJugador.startAnimation(fadeIn);
        }

        //Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onResume() {
        super.onResume();
        //Activa o desactiva notala música según notala configuración guardada
        if (pref.getBoolean("musicaonoff",true)) {
            mp.start();
        }else{
            mp.pause();
        }
        //Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onPause() {
        //Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        mp.pause();
        super.onPause();
    }

    @Override protected void onStop() {
        //Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        //mp.pause();
        super.onStop();
    }

    @Override protected void onRestart() {
        super.onRestart();
        //Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onDestroy() {
        //Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    //Guardado del estado inicial (pág.277)
    @Override protected void onSaveInstanceState(Bundle estadoGuardado){
        super.onSaveInstanceState(estadoGuardado);
        if ( mp != null ) {
            int pos = mp.getCurrentPosition();
            estadoGuardado.putInt("posicion", pos);
        }
    }

    @Override protected void onRestoreInstanceState(Bundle estadoGuardado){
        super.onRestoreInstanceState(estadoGuardado);
        if (estadoGuardado != null && mp != null) {
            int pos = estadoGuardado.getInt("posicion");
            mp.seekTo(pos);

        }
    }



    //Accediendo a los valores de las preferencias
    public void mostrarPreferencias(View view){
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        String s = "música: " + pref.getBoolean("musica",true)
                +", gráficos: " + pref.getString("graficos","?")
                +", fragmentos: " + pref.getString("fragmentos","0")
                +", multijugador: " + pref.getBoolean("multijugador", false)
                +", maxjugadores: " + pref.getString("maxjugadores","1")
                +", tipoconexion: " + pref.getString("tipoconexion","?");
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
