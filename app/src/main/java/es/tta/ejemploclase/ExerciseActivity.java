package es.tta.ejemploclase;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.content.Intent;

import es.tta.ejemploclase.R;
import es.tta.ejemploclase.model.Exercise;
import es.tta.ejemploclase.presentation.Data;


public class ExerciseActivity extends ModelActivity {


    public final static short READ_REQUEST_CODE = 0;
    public final static short VIDEO_REQUEST_CODE = 1;
    public final static short AUDIO_REQUEST_CODE = 2;
    public final static short PICTURE_REQUEST_CODE = 3;
    private   Uri pictureUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Exercise exercise= data.getExercise();
        TextView textWording=(TextView)findViewById(R.id.textView4);
        textWording.setText(exercise.getWording());

    }

    public void subirFichero(View view) {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, READ_REQUEST_CODE);

    }

    public void sacarFoto(View view) {


        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(this, R.string.no_camera, Toast.LENGTH_SHORT).show();
        } else {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!=null){

                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                try {
                    File file = File.createTempFile("tta", ".jpg", dir);
                    pictureUri = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                    startActivityForResult(intent,PICTURE_REQUEST_CODE);
                }catch (IOException e){
                  e.printStackTrace();
                }
            } else {
                Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void recordAudio(View view) {

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            Toast.makeText(this, R.string.no_micro, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivityForResult(intent,AUDIO_REQUEST_CODE);

            } else {
                Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void recordVideo(View view) {

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(this, R.string.no_camera, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent,VIDEO_REQUEST_CODE);

            } else {
                Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {

            return;
        }
        switch (requestCode) {

            case READ_REQUEST_CODE:
            case VIDEO_REQUEST_CODE:
            case AUDIO_REQUEST_CODE:
                subirFichero(data.getData());
                break;
            case PICTURE_REQUEST_CODE:

                subirFichero(pictureUri);
                break;
        }

    }

    public void subirFichero(final Uri uri) {

        ConnectivityManager connMgr= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= connMgr.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()){

            File file = new File(uri.getPath());
            final String name = file.getName();
            final View view = findViewById(R.id.textView4);

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        server.postExercise(uri,data.getUserId(),data.getExercise().getId(),name);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            }).start();

        } else{
            //display error
            Toast.makeText(this, "No tienes conexion de red", Toast.LENGTH_SHORT).show();
        }

    }
}