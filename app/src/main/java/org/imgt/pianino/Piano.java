package org.imgt.pianino;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.TransitionDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class Piano extends AppCompatActivity{

    private Button bDo,bRe,bMi,bFa,bSol,bLa,bSi,bDoA;
    private SoundPool soundPool;
    private int idDo, idRe,idMi,idFa,idSol,idLa,idSi,idDoA;
    private ImageButton bPiano,bSolfeo,bJugar;
    private SharedPreferences pref;
    private ImageView personaje;
    private TextView mensaje;
    private boolean modoPiano;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano);

        mensaje=findViewById(R.id.TV_mensajes);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        personaje= findViewById(R.id.personaje);

        ///////////////// PIANO //////////////////////////////////////
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
            }
        });
        //////////////////////////////////////////////////////////////////////////////


    }


    public void lanzarJuegoPianino(View view){
        Intent i= new Intent(this, JuegoPianino.class);
        startActivity(i);
    }



    //Método que crea un onTouchListener para una tecla del piano
    /*
        Entrada: Identificador del sonido
                Botón
                Fondo en estado normal
                Fondo mientras se pulsa la tecla
        Salida: Escuchador que reacciona al tocar el botón
     */
    public View.OnTouchListener escuchadorTeclaNota(int idSonido, final Button tecla, int idFondoNormal, int idFondoPulsado){

        final int fNormal= idFondoNormal;
        final int fPulsado= idFondoPulsado;
        final int sonido= idSonido;
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
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // Released
                    tecla.setBackground(getResources().getDrawable(fNormal));
                    tecla.setTextColor(getResources().getColor(R.color.colorNegro));
                    if(!modoPiano)
                        personaje.setImageDrawable(getResources().getDrawable(R.drawable.cantante1peq));
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
        bDo.setOnTouchListener(escuchadorTeclaNota(idDo, bDo, R.drawable.bdo, R.drawable.bdo_puls));
        bRe.setOnTouchListener(escuchadorTeclaNota(idRe, bRe, R.drawable.re, R.drawable.re_puls));
        bMi.setOnTouchListener(escuchadorTeclaNota(idMi, bMi, R.drawable.mi, R.drawable.mi_puls));
        bFa.setOnTouchListener(escuchadorTeclaNota(idFa, bFa, R.drawable.fa, R.drawable.fa_puls));
        bSol.setOnTouchListener(escuchadorTeclaNota(idSol, bSol, R.drawable.sol, R.drawable.sol_puls));
        bLa.setOnTouchListener(escuchadorTeclaNota(idLa, bLa, R.drawable.la, R.drawable.la_puls));
        bSi.setOnTouchListener(escuchadorTeclaNota(idSi, bSi, R.drawable.si, R.drawable.si_puls));
        bDoA.setOnTouchListener(escuchadorTeclaNota(idDoA, bDoA, R.drawable.do_agudo, R.drawable.do_agudo_pul));
    }
}
