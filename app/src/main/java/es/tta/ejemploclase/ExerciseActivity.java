package es.tta.ejemploclase;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    public void subirFichero(View view){


        Toast toast = Toast.makeText(this, "No implementada la accion de subirFichero", Toast.LENGTH_SHORT);
        toast.show();

    }

    public void sacarFoto(View view){

        Toast toast = Toast.makeText(this, "No implementada la accion de subirFoto", Toast.LENGTH_SHORT);
        toast.show();


    }

    public void recordAudio(View view){

        Toast toast = Toast.makeText(this, "No implementada la accion de recordAudio", Toast.LENGTH_SHORT);
        toast.show();


    }

    public void recordVideo(View view){

        Toast toast = Toast.makeText(this, "No implementada la accion de recordVideo", Toast.LENGTH_SHORT);
        toast.show();

    }

}
