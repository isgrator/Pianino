package org.imgt.pianino;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Locale;

public class ResultadoJuego extends AppCompatActivity {

    private SharedPreferences pref;
    private RatingBar rt;
    private TextView mensaje, puntos;
    private SoundPool soundPool;
    private MediaPlayer mp;
    int idSonido,idMensaje;
    ImageView b_star;
    boolean esingles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_juego);

        if(Locale.getDefault().getDisplayLanguage().equals("English")) {
            //Log.d("idioma",Locale.getDefault().getDisplayLanguage());
            esingles=true;
        }
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

        rt= findViewById(R.id.ratingBar);
        rt.setIsIndicator(true);
        mensaje= findViewById(R.id.TV_resultado);

        b_star= findViewById(R.id.b_star);
        b_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation pulsarse= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_boton_pulsado);
                b_star.startAnimation(pulsarse);
                Intent i= new Intent(getApplicationContext(), Principal.class);
                startActivity(i);
            }
        });



        int estrellas= calculaEstrellas();

        switch(estrellas){
            case 1: rt.setRating(1);
                mensaje.setText(getResources().getString(R.string.uyuyuy));

                mp= MediaPlayer.create(this,R.raw.mal_musica);
                mp.start();
                if(!esingles)
                    idMensaje= soundPool.load(getApplicationContext(),R.raw.mal,0);
                break;
            case 2: rt.setRating(2);
                mensaje.setText(getResources().getString(R.string.uyuyuy));

                mp= MediaPlayer.create(this,R.raw.mal_musica);
                mp.start();
                if(!esingles)
                    idMensaje= soundPool.load(getApplicationContext(),R.raw.mal,0);
                break;
            case 3: rt.setRating(3);
                mensaje.setText(getResources().getString(R.string.regulin));

                mp= MediaPlayer.create(this,R.raw.regular_musica);
                mp.start();
                if(!esingles)
                    idMensaje= soundPool.load(getApplicationContext(),R.raw.regular,0);
                break;
            case 4: rt.setRating(4);
                mensaje.setText(getResources().getString(R.string.bien));

                mp= MediaPlayer.create(this,R.raw.bien_musica);
                mp.start();
                if(!esingles)
                    idMensaje= soundPool.load(getApplicationContext(),R.raw.bien,0);
                break;
            case 5: rt.setRating(5);
                mensaje.setText(getResources().getString(R.string.perfecto));
                mp= MediaPlayer.create(this,R.raw.perfecto_musica);
                mp.start();
                if(!esingles)
                    idMensaje= soundPool.load(getApplicationContext(),R.raw.perfecto,0);
                break;
            default: rt.setRating(0);
                mensaje.setText(getResources().getString(R.string.muymal));
                mp= MediaPlayer.create(this,R.raw.crash);
                mp.start();

        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                soundPool.play(idSonido, 1 ,1 , 0, 0, 1);
                soundPool.play(idMensaje, 1 ,1 , 0, 0, 1);
            }
        }, 1000);

    }

    //Método que calcula cuñantas estrellas hay que mostrar
    public int calculaEstrellas(){
        int numEstrellas=0;
        int porcentaje=pref.getInt("porcentajeAciertos",0);

        if(porcentaje==0){
            numEstrellas=0;
        }else if( porcentaje>0 && porcentaje < 25){
            numEstrellas=1;
        }else if( porcentaje>=25 && porcentaje < 50){
            numEstrellas=2;
        }else if( porcentaje>=50 && porcentaje < 75){
            numEstrellas=3;
        }else if( porcentaje>=75 && porcentaje < 100){
            numEstrellas=4;
        }else if( porcentaje==100){
            numEstrellas=5;
        }
        return numEstrellas;
    }
}
