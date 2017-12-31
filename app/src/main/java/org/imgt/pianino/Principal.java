package org.imgt.pianino;

import android.app.Activity;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Principal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ImageView tit_pianino= findViewById(R.id.tit_pianino);
        Animation apareceTitulo= AnimationUtils.loadAnimation(this, R.anim.anim_tit_pianino);
        tit_pianino.startAnimation(apareceTitulo);


    }
}
