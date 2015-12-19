package es.tta.ejemploclase;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.content.Intent;

public class ExerciseActivity extends AppCompatActivity {



    public final static short READ_REQUEST_CODE =0;
    public final static short VIDEO_REQUEST_CODE=1;
    public final static short AUDIO_REQUEST_CODE=2;
    public final static short PICTURE_REQUEST_CODE=3;


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

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(this, R.string.no_camera, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                try {
                    File file = File.createTempFile("ttaPicture", ".jpg", dir);
                    Uri pictureUri = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                    startActivityForResult(intent, PICTURE_REQUEST_CODE);
                } catch (IOException e) {

                }
            } else {
                Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void recordAudio(View view){

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            Toast.makeText(this, R.string.no_micro, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if (intent.resolveActivity(getPackageManager()) != null) {
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                try {
                    File file = File.createTempFile("ttaAudio", ".aac", dir);
                    Uri audioUri = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, audioUri);
                    intent.putExtra(MediaStore.Audio.Media.EXTRA_MAX_BYTES, 2000000); //2MB de grabacion
                    startActivityForResult(intent,AUDIO_REQUEST_CODE);
                } catch (IOException e) {

                }
            } else {
                Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void recordVideo(View view){

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(this, R.string.no_camera, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                try {
                    File file = File.createTempFile("ttaVideo", ".mp4", dir);
                    Uri videoUri = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                    intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 20000000); //20MB
                    intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 20000); //10 segundos
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); //Alta
                    startActivityForResult(intent,VIDEO_REQUEST_CODE);
                } catch (IOException e) {

                }
            } else {
                Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
            }
        }
    }





    @Override
    protected  void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode!= Activity.RESULT_OK){

            return;
        }
        switch (requestCode){

            case READ_REQUEST_CODE:
            case VIDEO_REQUEST_CODE:
            case AUDIO_REQUEST_CODE:

              //  subirFichero(data.getData());
                break;
            case PICTURE_REQUEST_CODE:

               // subirFichero(pictureUri);
                break;
        }

    }
}

