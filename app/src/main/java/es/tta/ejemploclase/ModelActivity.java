package es.tta.ejemploclase;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.content.Intent;
import android.os.Bundle;

import es.tta.ejemploclase.presentation.*;

import es.tta.ejemploclase.model.Business;
import es.tta.ejemploclase.presentation.Preferences;
import es.tta.ejemploclase.prof.comms.RestClient;

public abstract class ModelActivity extends AppCompatActivity {


    public static final String URL="http://u017633.ehu.eus:18080/AlumnoTta/rest/tta";
    protected RestClient rest;
    protected Business server;
    protected Preferences prefs;
    protected Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new Data(getIntent().getExtras());
        rest= new RestClient(URL);

        String auth= data.getAuthToken();
        if(auth !=null) {
            rest.setAuthorization(auth);
            rest.setHttpBasicAuth(data.getExtraDni(),data.getAuthToken());
        }
        server= new Business(rest);
        prefs= new Preferences(this);


    }

    protected <T> void startModelActivity(Class<T> cls){
        Intent intent= newIntent(cls);
        startActivity(intent);
    }


    protected <T> Intent newIntent(Class<T> cls){

        Intent intent= new Intent(getApplicationContext(),cls);
        intent.putExtras(data.getBundle());// metemos los datos de la actividad a si la nueva clase
        //que se crea coge los datos de la actividad, propagamos los datos
        return intent;
    }


}
