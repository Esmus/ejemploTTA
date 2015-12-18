package es.tta.ejemploclase;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private int correct=0;
    private String advise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Data data= new Data();
        Test test= data.getTest();



        TextView textWording=(TextView)findViewById(R.id.test_wording);
        textWording.setText(test.getWording());


        int i=0;

        RadioGroup group= (RadioGroup)findViewById(R.id.test_radioGroup);

        for(Test.Choice choice : test.getChoices()){

            RadioButton radio=new RadioButton(this);
            radio.setText(choice.getWording());
            radio.setOnClickListener(this);//hacer visible el botton enviar
            group.addView(radio);
            if(choice.isCorrecta()){
                correct=i;
            }
            i++;

           }


        advise="consejo......";

    }

    @Override
    public void onClick(View view){

        findViewById(R.id.test_button_send).setVisibility(View.VISIBLE);


    }

    public void send(View view){


        RadioGroup group= (RadioGroup)findViewById(R.id.test_radioGroup);
        findViewById(R.id.test_button_send).setVisibility(View.GONE);


        int choices=group.getChildCount();
        for(int i=0;i<choices;i++){
            group.getChildAt(i).setEnabled(false);//off el grupo

        }

        int selectedID = group.getCheckedRadioButtonId();
        group.getChildAt(correct).setBackgroundColor(Color.GREEN);
        View radioButton= group.findViewById( selectedID);
        int selected=group.indexOfChild(radioButton);


        if (selected != correct) {
            group.getChildAt(selected).setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(), "Has fallado!", Toast.LENGTH_SHORT).show();

            if(advise!=null && !advise.isEmpty() ){

               findViewById(R.id.test_button_ayuda).setVisibility(View.VISIBLE);


            }
        } else {
            Toast.makeText(getApplicationContext(), "Has acertado", Toast.LENGTH_SHORT).show();
        }



    }


    public void ayuda(View view){

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
}
