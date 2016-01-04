package es.tta.ejemploclase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

        Intent intent= new Intent(this, TestActivity.class);
        startActivity(intent);

    }

    public void ejercicio (View view){

        Intent intent= new Intent(this, ExerciseActivity.class);
        startActivity(intent);

    }

    public void seguimiento(View view){

        Toast toast = Toast.makeText(this, "No implementada la accion de seguimiento", Toast.LENGTH_SHORT);
        toast.show();


    }
}
