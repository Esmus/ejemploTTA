package es.tta.ejemploclase;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.graphics.Color;
public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button sendButton = (Button) findViewById(R.id.test_button_send);
        final Button helpButton = (Button) findViewById(R.id.test_button_ayuda);

        String preguntaTest="¿Cual de las siguientes opciones NO se indica en el fichero de manifiesto de la app?";
        TextView textWording=(TextView)findViewById(R.id.test_wording);
        textWording.setText(preguntaTest);

        //rellenar el radioChoice

        String[] contenidoTest = {
                "Versión de la aplicación",
                "Listado de componentes de la aplicación",
                "Opciones del menú de ajustes",
                "Nivel mínimo de la API Android requerida",
                "Nombre del paquete java de la aplicación"
        };

        int i=0;

        final RadioGroup group= (RadioGroup)findViewById(R.id.test_radioGroup);


        for(i=0;i<contenidoTest.length;i++){

            RadioButton radio=new RadioButton(this);
            radio.setText(contenidoTest[i]);
            radio.setOnClickListener(this);//hacer visible el botton enviar
            group.addView(radio);


           }


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendButton.setVisibility(View.GONE); //La view desaparece  y su espacio queda libre


                int correct=2;
                Boolean advise=true;

                int choices=group.getChildCount();
                for(int i=0;i<choices;i++){
                    group.getChildAt(i).setEnabled(false);

                }

                int selected = group.getCheckedRadioButtonId()-1;
                group.getChildAt(correct).setBackgroundColor(Color.GREEN);


                if (selected != correct) {
                    group.getChildAt(selected).setBackgroundColor(Color.RED);
                    Toast.makeText(getApplicationContext(), "Has fallado!", Toast.LENGTH_SHORT).show();

                    if(advise!=null){
                        helpButton.setVisibility(View.VISIBLE);


                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Has acertado", Toast.LENGTH_SHORT).show();
                }


            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextView helpText = (TextView) findViewById(R.id.test_texto_ayuda);
                helpText.setText(R.string.test_texto_help);

                /*
                WebView web= new WebView(this);
                web.loadData(advise,"text/html",null);
                web.setBackgroundColor(Color.TRANSPARENT);
                web.setLayerType(WebView.LAYER_TYPE_SOFTWARE,null);
                layout.addView(web);
                */

            }
        });

    }

    @Override
    public void onClick(View view){

        findViewById(R.id.test_button_send).setVisibility(View.VISIBLE);

    }



}
