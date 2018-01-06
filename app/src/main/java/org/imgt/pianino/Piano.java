package org.imgt.pianino;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.TransitionDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;


public class Piano extends AppCompatActivity{

    private Button bDo,bRe,bMi,bFa,bSol,bLa,bSi,bDoA;
    private SoundPool soundPool;
    private int idDo, idRe,idMi,idFa,idSol,idLa,idSi,idDoA;
    private ImageView iDo,iRe,iMi,iFa,iSol,iLa,iSi,iDoA;
    private ImageButton bPiano,bSolfeo,bJugar;
    private SharedPreferences pref;
    private ImageView personaje;
    private TextView mensaje;
    private boolean modoPiano;
    private boolean modoJuego;
    private int notasTocadas;
    private int nuevaSecuencia[],secuenciaJugador[];
    private int puntuacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano);

        modoJuego=false;
        mensaje=findViewById(R.id.TV_mensajes);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        personaje= findViewById(R.id.personaje);

        ///////////////// PIANO //////////////////////////////////////

        //IMÁGENES NOTAS
        iDo= findViewById(R.id.IV_do);
        iRe= findViewById(R.id.IV_re);
        iMi= findViewById(R.id.IV_mi);
        iFa= findViewById(R.id.IV_fa);
        iSol= findViewById(R.id.IV_sol);
        iLa= findViewById(R.id.IV_la);
        iSi= findViewById(R.id.IV_si);
        iDoA= findViewById(R.id.IV_do_agudo);


        //SONIDOS
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

        //TECLAS DEL PIANO
        bDo= findViewById(R.id.b_do);
        bRe= findViewById(R.id.b_re);
        bMi= findViewById(R.id.b_mi);
        bFa= findViewById(R.id.b_fa);
        bSol= findViewById(R.id.b_sol);
        bLa= findViewById(R.id.b_la);
        bSi= findViewById(R.id.b_si);
        bDoA= findViewById(R.id.b_do_agudo);

        preparaPiano(pref.getBoolean("modopiano",true));
        //////////////////////////////////////////////////////////////////////////////

        //////////////////// MENU ////////////////////////////////////////////////////
        bPiano= findViewById(R.id.b_pianino);
        bSolfeo= findViewById(R.id.b_voz);
        bJugar= findViewById(R.id.b_jugar);

        bPiano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation pulsarse= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_boton_pulsado);
                bPiano.startAnimation(pulsarse);
                preparaPiano(true);
            }
        });

        bSolfeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation pulsarse= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_boton_pulsado);
                bSolfeo.startAnimation(pulsarse);
                preparaPiano(false);
            }
        });

        bJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation pulsarse= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_boton_pulsado);
                bJugar.startAnimation(pulsarse);
                lanzarJuegoPianino(null);
                modoJuego=true;
            }
        });
        //////////////////////////////////////////////////////////////////////////////


    }

    public void muestraSolucion(final Button tecla,ImageView iv,int idNota, int idFondoNormal, int idFondoPulsado){
        //soundPool.play(sonido, 1 ,1 , 0, 0, 1);
        final int fNormal= idFondoNormal;
        final int fPulsado= idFondoPulsado;
        final ImageView ivNota=iv;
        final int drawableNota=idNota;

        tecla.setBackground(getResources().getDrawable(fPulsado));
        tecla.setTextColor(getResources().getColor(R.color.colorBlanco));
        if(!modoPiano)
            personaje.setImageDrawable(getResources().getDrawable(R.drawable.cantante2peq));
        ivNota.setImageDrawable(getResources().getDrawable(drawableNota));

    }

    public void reproducirSecuencia(int[] nuevaSecuencia, boolean sol){
        final boolean solucion=sol;
        Handler handler = new Handler();

        //Reproducir secuencia
        for(int i=0;i<nuevaSecuencia.length;i++){

            int delay=i*1000;

            switch(nuevaSecuencia[i]){
                case 1:
                    Log.d("nota:","do");

                    handler.postDelayed(new Runnable() {
                        public void run() {
                            soundPool.play(idDo, 1 ,1 , 0, 0, 1);
                            if(solucion) muestraSolucion(bDo,iDo,R.drawable.do_penta, R.drawable.bdo, R.drawable.bdo_puls);
                        }
                    }, delay);
                    break;
                case 2:
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            soundPool.play(idRe, 1 ,1 , 0, 0, 1);
                            if(solucion) muestraSolucion(bRe,iRe,R.drawable.re_penta, R.drawable.re, R.drawable.re_puls);
                        }
                    }, delay);

                    Log.d("nota:","re");
                    break;
                case 3:

                    handler.postDelayed(new Runnable() {
                        public void run() {
                            soundPool.play(idMi, 1 ,1 , 0, 0, 1);
                            if(solucion) muestraSolucion(bRe,iMi,R.drawable.mi_penta, R.drawable.mi, R.drawable.mi_puls);
                        }
                    }, delay);
                    Log.d("nota:","mi");
                    break;
                case 4:

                    handler.postDelayed(new Runnable() {
                        public void run() {
                            soundPool.play(idFa, 1 ,1 , 0, 0, 1);
                            if(solucion) muestraSolucion(bFa,iFa,R.drawable.fa_penta, R.drawable.fa, R.drawable.fa_puls);
                        }
                    }, delay);
                    Log.d("nota:","fa");
                    break;
                case 5:

                    handler.postDelayed(new Runnable() {
                        public void run() {
                            soundPool.play(idSol, 1 ,1 , 0, 0, 1);
                            if(solucion) muestraSolucion(bSol,iSol,R.drawable.sol_penta, R.drawable.sol, R.drawable.sol_puls);
                        }
                    }, delay);
                    Log.d("nota:","sol");
                    break;
                case 6:

                    handler.postDelayed(new Runnable() {
                        public void run() {
                            soundPool.play(idLa, 1 ,1 , 0, 0, 1);
                            if(solucion) muestraSolucion(bLa,iLa,R.drawable.la_penta, R.drawable.la, R.drawable.la_puls);
                        }
                    }, delay);
                    Log.d("nota:","la");
                    break;
                case 7:

                    handler.postDelayed(new Runnable() {
                        public void run() {
                            soundPool.play(idSi, 1 ,1 , 0, 0, 1);
                            if(solucion) muestraSolucion(bSi,iSi,R.drawable.si_penta, R.drawable.si, R.drawable.si_puls);
                        }
                    }, delay);
                    Log.d("nota:","si");
                    break;
                case 8:

                    handler.postDelayed(new Runnable() {
                        public void run() {
                            soundPool.play(idDoA, 1 ,1 , 0, 0, 1);
                            if(solucion) muestraSolucion(bDoA,iDoA,R.drawable.do_agudo_penta, R.drawable.do_agudo, R.drawable.do_agudo_pul);
                        }
                    }, delay);
                    Log.d("nota:","doAgudo");
                    break;
            }

        }

    }

    public void lanzarJuegoPianino(View view){
        //Comienza el juego. Reseteamos.

        puntuacion=0;
        notasTocadas=0;
        mensaje.setText(R.string.repite);
        switch(pref.getString("dificultad", "1")){
            case "0": nuevaSecuencia=generarSecuencia(4);
                nuevaSecuencia[3]=nuevaSecuencia[0];
                secuenciaJugador= new int[4];
                break;
            case "1": nuevaSecuencia=generarSecuencia(7);
                nuevaSecuencia[6]=nuevaSecuencia[0];
                secuenciaJugador= new int[7];
                break;
            case "2": nuevaSecuencia=generarSecuencia(10);
                secuenciaJugador= new int[10];
                break;
            default: nuevaSecuencia= new int[7];
        }

        reproducirSecuencia(nuevaSecuencia,false);

    }

    //Método para comprobar si al tocar se ha llegado al final dela secuencia
    public void comprobarFinJuego() {

        if (notasTocadas == nuevaSecuencia.length) {
            //reproduce la secuencia esta vez mostrando la solución
            mensaje.setText(getResources().getString(R.string.solucion));
            Handler handler= new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    reproducirSecuencia(nuevaSecuencia, true);
                }
            }, 4000);

            modoJuego = false;
            int aciertos=0;
            //Calcular puntuacion y llevar a la pantalla de puntuaciones. De allí se volverá a la pantalla principal
            for(int i=0;i<nuevaSecuencia.length;i++){
                if(nuevaSecuencia[i]==secuenciaJugador[i]){
                    aciertos++;
                    puntuacion += 100;
                }
            }
            int porcentaje= Math.round(aciertos * 100 /nuevaSecuencia.length);
            SharedPreferences.Editor editor=pref.edit();
            editor.putString("puntuacion", String.valueOf(puntuacion));
            editor.putInt("porcentajeAciertos", porcentaje);
            editor.apply();
            int delay= nuevaSecuencia.length*1000+5000;
            final Intent i= new Intent(this, ResultadoJuego.class);
            handler= new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    startActivity(i);
                }
            }, delay);

            Toast.makeText(this,getResources().getString(R.string.has_ganado)+" "+ puntuacion+
                    getResources().getString(R.string.puntos),Toast.LENGTH_SHORT).show();

        }
    }

    //Genera un número aleatorio dentro del rango de números indicado
    public int randRange(int min, int max){
        int randomNum= (int) Math.floor(Math.random() * (max - min +1)) + min;
        return randomNum;
    }

    //Genera una secuencia aleatoria con la cantidad de notas indicada
    public int[] generarSecuencia(int cantidad){
        int secuencia[]= new int[cantidad];

        for(int i=0;i<cantidad;i++){
            secuencia[i]= randRange(1,8);
        }
        return secuencia;
    }




    //Método que crea un onTouchListener para una tecla del piano
    /*
        Entrada: Identificador del sonido
                Botón
                Fondo en estado normal
                Fondo mientras se pulsa la tecla
        Salida: Escuchador que reacciona al tocar el botón
     */
    public View.OnTouchListener escuchadorTeclaNota(int idSonido, final Button tecla,ImageView iv,int idNota, int idFondoNormal, int idFondoPulsado){

        final int fNormal= idFondoNormal;
        final int fPulsado= idFondoPulsado;
        final int sonido= idSonido;
        final ImageView ivNota=iv;
        final int drawableNota=idNota;
        View.OnTouchListener otl;

        otl =new View.OnTouchListener() {
            @Override public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Pressed
                    soundPool.play(sonido, 1 ,1 , 0, 0, 1);
                    tecla.setBackground(getResources().getDrawable(fPulsado));
                    tecla.setTextColor(getResources().getColor(R.color.colorBlanco));
                    if(!modoPiano)
                        personaje.setImageDrawable(getResources().getDrawable(R.drawable.cantante2peq));
                    ivNota.setImageDrawable(getResources().getDrawable(drawableNota));

                    if(modoJuego){
                        //Añadir esta nota a la secuencia del jugador
                        notasTocadas++;
                        Log.d("sonido_tocado","nota "+sonido);
                        secuenciaJugador[notasTocadas-1]=sonido;
                            comprobarFinJuego();

                    }

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    tecla.setBackground(getResources().getDrawable(fNormal));
                    tecla.setTextColor(getResources().getColor(R.color.colorNegro));
                    if(!modoPiano)
                        personaje.setImageDrawable(getResources().getDrawable(R.drawable.cantante1peq));

                    ivNota.setImageDrawable(getResources().getDrawable(R.drawable.penta));
                }
                return true;
            }
        };
        return otl;
    }

    public void preparaPiano(boolean piano){

        if(piano){
            modoPiano=true;
            idDo = soundPool.load(getApplicationContext(),R.raw.notado,0);
            idRe = soundPool.load(getApplicationContext(),R.raw.notare,0);
            idMi = soundPool.load(getApplicationContext(),R.raw.notami,0);
            idFa = soundPool.load(getApplicationContext(),R.raw.notafa,0);
            idSol = soundPool.load(getApplicationContext(),R.raw.notasol,0);
            idLa = soundPool.load(getApplicationContext(),R.raw.notala,0);
            idSi = soundPool.load(getApplicationContext(),R.raw.notasi,0);
            idDoA = soundPool.load(getApplicationContext(),R.raw.notado_agudo,0);

            mensaje.setText(getString(R.string.modo_piano));

            personaje.setImageDrawable(getResources().getDrawable(R.drawable.bisillo2));
        }else{
            modoPiano=false;
            idDo = soundPool.load(getApplicationContext(),R.raw.s_do,0);
            idRe = soundPool.load(getApplicationContext(),R.raw.s_re,0);
            idMi = soundPool.load(getApplicationContext(),R.raw.s_mi,0);
            idFa = soundPool.load(getApplicationContext(),R.raw.s_fa,0);
            idSol = soundPool.load(getApplicationContext(),R.raw.s_sol,0);
            idLa = soundPool.load(getApplicationContext(),R.raw.s_la,0);
            idSi = soundPool.load(getApplicationContext(),R.raw.s_si,0);
            idDoA = soundPool.load(getApplicationContext(),R.raw.s_do_agudo,0);

            mensaje.setText(getString(R.string.modo_solfeo));
            personaje.setImageDrawable(getResources().getDrawable(R.drawable.cantante1peq));
        }


        //Carga de acciones de cambio de apariencia y reprocucción de sonido en cadatecla
        bDo.setOnTouchListener(escuchadorTeclaNota(idDo, bDo,iDo,R.drawable.do_penta, R.drawable.bdo, R.drawable.bdo_puls));
        bRe.setOnTouchListener(escuchadorTeclaNota(idRe, bRe,iRe,R.drawable.re_penta, R.drawable.re, R.drawable.re_puls));
        bMi.setOnTouchListener(escuchadorTeclaNota(idMi, bMi,iMi,R.drawable.mi_penta, R.drawable.mi, R.drawable.mi_puls));
        bFa.setOnTouchListener(escuchadorTeclaNota(idFa, bFa,iFa,R.drawable.fa_penta, R.drawable.fa, R.drawable.fa_puls));
        bSol.setOnTouchListener(escuchadorTeclaNota(idSol, bSol,iSol,R.drawable.sol_penta, R.drawable.sol, R.drawable.sol_puls));
        bLa.setOnTouchListener(escuchadorTeclaNota(idLa, bLa,iLa,R.drawable.la_penta, R.drawable.la, R.drawable.la_puls));
        bSi.setOnTouchListener(escuchadorTeclaNota(idSi, bSi,iSi,R.drawable.si_penta, R.drawable.si, R.drawable.si_puls));
        bDoA.setOnTouchListener(escuchadorTeclaNota(idDoA, bDoA,iDoA,R.drawable.do_agudo_penta, R.drawable.do_agudo, R.drawable.do_agudo_pul));
    }
}
