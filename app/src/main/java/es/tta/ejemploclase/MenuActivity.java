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
import es.tta.ejemploclase.model.Status;
import es.tta.ejemploclase.model.Test;
import es.tta.ejemploclase.presentation.Data;
import es.tta.ejemploclase.prof.views.ProgressTask;

public class MenuActivity extends ModelActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent =getIntent();
        TextView textLogin=(TextView)findViewById(R.id.menu_login);
        textLogin.setText(("Bienvenido "+ data.getExtraDni()));


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Status user= server.getStatus(data.getExtraDni(),data.getAuthToken());

                    data.putUserId(user.getId());
                    data.putUserName(user.getUser());
                    // data.putAuthToken(dni + ":" + password);
                    data.setNextText(user.getNextTest());
                    data.setNextExercise(user.getNextExercise());

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }).start();


    }

    public void test(View view){


        new ProgressTask<Test>(this){


            @Override
            protected Test work() throws Exception {


                return server.getTest(data.getNextText());
            }

            @Override
            protected void onFinish(Test result) {

                data.putTest(result);
                startModelActivity(TestActivity.class);

            }
        }.execute();


    }

    public void ejercicio (View view){

        new ProgressTask<Exercise>(this){


            @Override
            protected Exercise work() throws Exception {


                return server.getExercise(data.getNextExercise());
            }

            @Override
            protected void onFinish(Exercise result) {

                data.putExercise(result);
                startModelActivity(ExerciseActivity.class);

            }
        }.execute();

    }

    public void seguimiento(View view){

        Toast toast = Toast.makeText(this, "No implementada la accion de seguimiento", Toast.LENGTH_SHORT);
        toast.show();


    }
}
