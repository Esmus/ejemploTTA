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
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import es.tta.ejemploclase.presentation.Data;
import es.tta.ejemploclase.prof.views.AudioPlayer;
import es.tta.ejemploclase.R;
import es.tta.ejemploclase.model.Test;
import es.tta.ejemploclase.prof.views.ProgressTask;
import android.util.Log;


public class TestActivity extends ModelActivity implements View.OnClickListener {

    private int correct=0;
    //private int  adviseTipo;
    private String mime;
    private String advise;
    private LinearLayout layout;
    private Test test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        test= data.getTest();

        TextView textWording=(TextView)findViewById(R.id.test_wording);
        textWording.setText(test.getWording());

        int i=0;

        RadioGroup group= (RadioGroup)findViewById(R.id.test_radioGroup);

        ArrayList<Test.Choice> prueba= test.getChoices();

       // Toast.makeText(getApplicationContext(), "numero de choices"+prueba.size(), Toast.LENGTH_SHORT).show();


        for(Test.Choice choice : test.getChoices()){

            RadioButton radio=new RadioButton(this);
            radio.setText(choice.getAnswer());
            radio.setOnClickListener(this);//hacer visible el botton enviar
            group.addView(radio);
            advise=choice.getAdvise();
            mime=choice.getMime();

            if(choice.isCorrect()){
                correct=i;
            }
            i++;

           }

        layout = (LinearLayout) findViewById(R.id.test_layout);

    }

    public void ayuda(View view) throws IOException {
        view.setEnabled(false);
        switch(mime){
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

    public void send(final View view){

        RadioGroup group= (RadioGroup)findViewById(R.id.test_radioGroup);
        findViewById(R.id.test_button_send).setVisibility(View.GONE);


        int choices=group.getChildCount();
        for(int i=0;i<choices;i++){
            group.getChildAt(i).setEnabled(false);//off el grupo

        }

        int selectedID = group.getCheckedRadioButtonId();
        group.getChildAt(correct).setBackgroundColor(Color.GREEN);
        View radioButton= group.findViewById( selectedID);
        final int selected=group.indexOfChild(radioButton);

        if (selected != correct) {
            group.getChildAt(selected).setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(), "Has fallado!", Toast.LENGTH_SHORT).show();
            Test.Choice choice= test.getChoice(selected);
            advise = choice.getAdvise();
            mime= choice.getMime();
          Toast.makeText(getApplicationContext(), mime, Toast.LENGTH_SHORT).show();
           // Toast.makeText(getApplicationContext(), advise, Toast.LENGTH_SHORT).show();
            if(advise!=null && !advise.isEmpty() ){
               findViewById(R.id.test_button_ayuda).setVisibility(View.VISIBLE);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Has acertado", Toast.LENGTH_SHORT).show();
        }

        //enviamos el test al servidor

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    server.postTest(data.getUserId(),selected);
                }catch(Exception e){
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "error al subir el fichero", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Log.e("", e.getMessage(), e);
                }
            }
        }).start();

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
