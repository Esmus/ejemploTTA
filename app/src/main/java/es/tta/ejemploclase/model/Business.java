package es.tta.ejemploclase.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import es.tta.ejemploclase.prof.comms.RestClient;

/**
 * Created by Miren on 30/12/2015.
 */
public class Business {

    private final RestClient rest;


    public Business(RestClient rest){
        this.rest=rest;
    }

    public Status getStatus(String dni)throws IOException, JSONException{

        return null;
    }

    public Test getTest(int id) {

        //la clase random genera numeros aleatorios
        // Random  rnd = new Random();
        // int pos = rnd.nextInt(3);
        // return test[pos];

        try{

            Test test= new Test();
            JSONObject json= new JSONObject(bundle.getString(EXTRA_TEST));
            test.setWording(json.getString("wording"));
            JSONArray array= json.getJSONArray("choices");

            for(int i=0; i< array.length();i++){


                JSONObject item= array.getJSONObject(i);
                Test.Choice choice= new Test.Choice();
                choice.setId(item.getInt("id"));
                choice.setWording(item.getString("wording"));
                choice.setCorrect(item.getBoolean("correct"));
                choice.setAdvise(item.optString("advise",null));
                choice.setMime(item.optString("mime",null));
                test.getChoices().add(choice);

            }

            return test;

        }catch (JSONException e){
            return null;
        }
    }

    public Exercise getExercise(int id) throws IOException,JSONException{


        JSONObject json= rest.getJson(String.format("getExercise?id=%d",id));
        Exercise exercise= new Exercise();
        exercise.setId(json.getInt("id"));
        exercise.setWording(json.getString("wording"));
        return exercise;

    }



    public void uploadSolution(int userId, int exerciseId, InputStream is, String filename) throws  IOException{


    }
    public void uploadChoice (int userId, int choiceId) throws JSONException, IOException{

        JSONObject json= new JSONObject();
        json.put("userId", userId);
        json.put("choiceId", choiceId);
        rest.postJson(json, "postChoice");
    }




}
