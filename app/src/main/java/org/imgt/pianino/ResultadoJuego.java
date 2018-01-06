package org.imgt.pianino;

import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultadoJuego extends AppCompatActivity {

    private SharedPreferences pref;
    private RatingBar rt;
    private TextView mensaje, puntos;
    private SoundPool soundPool;
    private MediaPlayer mp;
    int idSonido,idMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_juego);

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

        rt= findViewById(R.id.ratingBar);
        mensaje= findViewById(R.id.TV_resultado);




        int estrellas= calculaEstrellas();

        switch(estrellas){
            case 1: rt.setRating(1);
                mensaje.setText(getResources().getString(R.string.uyuyuy));
                //idSonido = soundPool.load(getApplicationContext(),R.raw.mal_musica,0);
                mp= MediaPlayer.create(this,R.raw.mal_musica);
                mp.start();
                idMensaje= soundPool.load(getApplicationContext(),R.raw.mal,0);
                break;
            case 2: rt.setRating(2);
                mensaje.setText(getResources().getString(R.string.uyuyuy));
                //idSonido = soundPool.load(getApplicationContext(),R.raw.mal_musica,0);
                mp= MediaPlayer.create(this,R.raw.mal_musica);
                mp.start();
                idMensaje= soundPool.load(getApplicationContext(),R.raw.mal,0);
                break;
            case 3: rt.setRating(3);
                mensaje.setText(getResources().getString(R.string.regulin));
                //idSonido = soundPool.load(getApplicationContext(),R.raw.regular_musica,0);
                mp= MediaPlayer.create(this,R.raw.regular_musica);
                mp.start();
                idMensaje= soundPool.load(getApplicationContext(),R.raw.regular,0);
                break;
            case 4: rt.setRating(4);
                mensaje.setText(getResources().getString(R.string.bien));
                //idSonido = soundPool.load(getApplicationContext(),R.raw.bien_musica,0);
                mp= MediaPlayer.create(this,R.raw.bien_musica);
                mp.start();
                idMensaje= soundPool.load(getApplicationContext(),R.raw.bien,0);
                break;
            case 5: rt.setRating(5);
                mensaje.setText(getResources().getString(R.string.perfecto));
                //idSonido = soundPool.load(getApplicationContext(),R.raw.perfecto_musica,0);
                mp= MediaPlayer.create(this,R.raw.perfecto_musica);
                mp.start();
                idMensaje= soundPool.load(getApplicationContext(),R.raw.perfecto,0);
                break;
            default: rt.setRating(0);
                mensaje.setText(getResources().getString(R.string.muymal));
                //idSonido = soundPool.load(getApplicationContext(),R.raw.crash,0);
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
