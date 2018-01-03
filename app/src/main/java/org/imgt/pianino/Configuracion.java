package org.imgt.pianino;


import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Configuracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_configuracion);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new ConfiguracionFragment())
                .commit();


    }
}
