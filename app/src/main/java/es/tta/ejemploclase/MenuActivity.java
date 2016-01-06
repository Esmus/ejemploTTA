package es.tta.ejemploclase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import es.tta.ejemploclase.model.Exercise;
import es.tta.ejemploclase.model.Test;
import es.tta.ejemploclase.presentation.Data;

public class MenuActivity extends ModelActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent =getIntent();
        TextView textLogin=(TextView)findViewById(R.id.menu_login);
        textLogin.setText(("Bienvenido "+intent.getStringExtra(MainActivity.EXTRA_LOGIN)));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void test(View view){



        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                   Test test= server.getTest(1);
                    data.putTest(test);


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        Intent intent= newIntent(TestActivity.class);
        startActivity(intent);


    }

    public void ejercicio (View view){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Exercise exercise =server.getExercise(1);
                    data.putExercise(exercise);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }).start();

        Intent intent= newIntent(ExerciseActivity.class);
        startActivity(intent);

    }

    public void seguimiento(View view){

        Toast toast = Toast.makeText(this, "No implementada la accion de seguimiento", Toast.LENGTH_SHORT);
        toast.show();


    }
}
