package es.tta.ejemploclase.presentation;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import es.tta.ejemploclase.model.Exercise;
import es.tta.ejemploclase.model.Test;
import es.tta.ejemploclase.model.Test.Choice;

import static es.tta.ejemploclase.model.Test.*;

/**
 * Created by Miren on 17/12/2015.
 */

//clase que obtiene los datos y crea el test
public class Data {

    private final static String EXTRA_USER = "es.tta.example.user";
    private final static String EXTRA_AUTH = "es.tta.example.auth";
    private final static String EXTRA_NAME = "es.tta.example.name";
    private final static String EXTRA_EXERCISE_ID = "es.tta.example.exerciseId";
    private final static String EXTRA_EXERCISE_WORDING = "es.tta.example.exerciseWording";
    private final static String EXTRA_TEST="es.tta.example.Test";
    private final static String EXTRA_DNI="es.tta.example.DNI";
    private int nextText;

    public int getNextExercise() {
        return nextExercise;
    }

    public void setNextExercise(int nextExercise) {
        this.nextExercise = nextExercise;
    }

    public int getNextText() {
        return nextText;
    }

    public void setNextText(int nextText) {
        this.nextText = nextText;
    }

    private int nextExercise;

    private final Bundle bundle;

    public Data(Bundle bundle) {

        if(bundle ==null)
          bundle = new Bundle();

        this.bundle = bundle;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void putUserId(int id) {

        bundle.putInt(EXTRA_USER, id);
    }

    public int getUserId() {
        return bundle.getInt(EXTRA_USER);
    }

    public void putAuthToken(String auth) {

        bundle.putString(EXTRA_AUTH, auth);
    }

    public void putUserDni(String dni){

        bundle.putString(EXTRA_DNI,dni);
    }

    public String getExtraDni(){
        return bundle.getString(EXTRA_DNI);
    }

    public String getAuthToken() {

        return bundle.getString(EXTRA_AUTH);
    }

    public void putUserName(String name) {

        bundle.getString(EXTRA_NAME);
    }




public void putExercise( Exercise exercise){

    bundle.putInt(EXTRA_EXERCISE_ID, exercise.getId());
    bundle.putString(EXTRA_EXERCISE_WORDING, exercise.getWording());

}

    public Exercise getExercise(){

        Exercise exercise= new Exercise();
        exercise.setId(bundle.getInt(EXTRA_EXERCISE_ID));
        exercise.setWording(bundle.getString(EXTRA_EXERCISE_WORDING));
        return exercise;
    }


    public Test getTest(){

        try{

            Test test= new Test();
            JSONObject json= new JSONObject(bundle.getString(EXTRA_TEST));
            test.setWording(json.getString("wording"));
            JSONArray array= json.getJSONArray("choices");
            for(int i=0; i<array.length();i++){
                JSONObject item= array.getJSONObject(i);
                Test.Choice choice= new Test.Choice();
                choice.setId(item.getInt("id"));
                choice.setAnswer(item.getString("answer"));
                choice.setCorrect(item.getBoolean("correct"));
                choice.setAdvise(item.optString("advise", null));
                choice.setMime(item.optString("mime", null));
                test.getChoices().add(choice);
            }

            return test;
        }catch (JSONException e){
            return null;
        }


    }
    public void putTest(Test test)  {

        try {
            JSONObject json = new JSONObject();
            json.put("wording", test.getWording());
            JSONArray array = new JSONArray();
            for (Test.Choice choice : test.getChoices()) {

                JSONObject item = new JSONObject();
                item.put("id", choice.getId());
                item.put("answer", choice.getAnswer());
                item.put("correct", choice.isCorrect());
                item.put("advise", choice.getAdvise());
                item.put("mime", choice.getMime());
                array.put(item);

            }

            json.put("choices", array);
            bundle.putString(EXTRA_TEST, json.toString());

        }catch (JSONException e){
            e.printStackTrace();
        }


    }


}
