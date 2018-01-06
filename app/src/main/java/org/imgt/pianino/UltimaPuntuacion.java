package org.imgt.pianino;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class UltimaPuntuacion extends AppCompatActivity {
    private ImageView b_volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultima_puntuacion);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        String puntos= pref.getString("puntuacion","");

        TextView mensaje= findViewById(R.id.TV_puntos);
        if(puntos.equals("")){
            mensaje.setText(getResources().getString(R.string.no_has_jugado));

        }else {
            mensaje.setText(getResources().getString(R.string.ultima_puntuacion) + " " + puntos + " " + getResources().getString(R.string.puntos));
        }

        b_volver= findViewById(R.id.IV_flecha);
        b_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation pulsarse= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_boton_pulsado);
                b_volver.startAnimation(pulsarse);
                finish();
            }
        });

    }
}
