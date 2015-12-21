package es.tta.ejemploclase;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private int correct=0;
    private int  adviseTipo;
    private String advise;
    private LinearLayout layout;

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
            if(choice.isCorrect()){
                correct=i;
            }
            i++;

           }

        advise=test.getAdvice();
        adviseTipo = test.getTipoAdvise();
        layout = (LinearLayout) findViewById(R.id.test_layout);

    }


    public void ayuda(View view) throws IOException {
        view.setEnabled(false);
        switch(adviseTipo){
            case Test.HTML_ADVISE:
                showHtml(advise);
                break;
            case Test.AUDIO_ADVISE:
                showAudio(advise);
                break;
            case Test.VIDEO_ADVISE:
                showVideo(advise);
                break;
        }
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


    private void showVideo(String advise){
        VideoView video = new VideoView(this);
        video.setVideoURI(Uri.parse(advise));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        video.setLayoutParams(params);

        //botones
        MediaController controller = new MediaController(this){
            @Override
            public void hide(){
                //para que no se esconda sobreescribimos el metodo
            }

            @Override
            public boolean dispatchKeyEvent(KeyEvent event){
                if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
                    finish();//cuando el usuario de a la tecla atars se finalice
                return super.dispatchKeyEvent(event);//resto llamamos a los metodos de la clase
            }
        };
        controller.setAnchorView(video);
        video.setMediaController(controller);

        layout.addView(video);
        video.start();
    }

    private void showHtml(String advise){
        if (advise.substring(0, 10).contains("://")) {
            Uri uri = Uri.parse(advise);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else {
            WebView web = new WebView(this);
            //web.loadUrl(advise)
            web.loadData(advise, "text/html", null);
            web.setBackgroundColor(Color.TRANSPARENT);//para que se vea bien
            web.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
            layout.addView(web);
        }
    }

    private void showAudio(String advise) throws IOException {
        View view = new View(this);
        AudioPlayer audio = new AudioPlayer(view);
        audio.setAudioUri(Uri.parse(advise));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);

        layout.addView(view);
        audio.start();
    }










}
