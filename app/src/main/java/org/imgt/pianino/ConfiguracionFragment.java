package org.imgt.pianino;

import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * Created by Isabel María on 02/01/2018.
 */

public class ConfiguracionFragment extends PreferenceFragment {


    private EditTextPreference nombre;
    private SharedPreferences pref;
    private SoundPool soundPool;
    private int idError;
    private SwitchPreference switchMusica, switchModo;

    //private SwitchPreference switchSonido;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.configuracion);

        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        idError = soundPool.load(getActivity(), R.raw.error, 0);

         //pref = getActivity().getSharedPreferences("datos_aplicacion", Context.MODE_PRIVATE);
        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        nombre = (EditTextPreference) findPreference("nombrejugador");
        nombre.setTitle(pref.getString("nombrejugador",null));

        nombre.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object
                            newValue) {
                        String nuevoNombre= (String)newValue;

                        if(nuevoNombre.equals("")){
                            soundPool.play(idError, 1, 1, 1, 0, 1);
                            Toast.makeText(getActivity(), getResources().getString(R.string.falta_nombre),
                                    Toast.LENGTH_SHORT).show();
                            return false;
                        }else{
                            SharedPreferences.Editor editor=pref.edit();
                            editor.putString("nombrejugador", nuevoNombre);
                            editor.apply();
                            nombre.setTitle(pref.getString("nombrejugador",""));
                            return true;
                        }

                    }
                });

        switchMusica= (SwitchPreference) findPreference("musicaonoff");
        if (pref.getBoolean("musicaonoff",true)) {
            //Activar música
            //mp.start();
            switchMusica.setIcon(getResources().getDrawable(R.drawable.ico_music));
        }else{
            //Desactivar música
            //mp.pause();
            switchMusica.setIcon(null);
        }
        switchMusica.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object
                            newValue) {
                        boolean activado= (boolean)newValue;
                        if(activado){
                            //Muestra icono
                            switchMusica.setIcon(getResources().getDrawable(R.drawable.ico_music));
                        }else{
                            //Oculta icono
                            switchMusica.setIcon(null);
                        }
                        return true;
                    }
                });

        switchModo= (SwitchPreference) findPreference("modopiano");
        if (pref.getBoolean("modopiano",true)) {
            switchModo.setIcon(getResources().getDrawable(R.drawable.bisillo2));
        }else{
            switchModo.setIcon(getResources().getDrawable(R.drawable.icovoz));
        }
        switchModo.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object
                            newValue) {
                        boolean activado= (boolean)newValue;
                        if(activado){
                            switchModo.setIcon(getResources().getDrawable(R.drawable.bisillo2));
                        }else{
                            //Oculta icono
                            switchModo.setIcon(getResources().getDrawable(R.drawable.icovoz));
                        }
                        return true;
                    }
                });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        //view.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        view.setBackground(getResources().getDrawable(R.drawable.degradado));
        return view;
    }

}