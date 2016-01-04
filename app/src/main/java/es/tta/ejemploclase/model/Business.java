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

    public Test getTest(int id)throws IOException,JSONException{


        try{

            Test test= new Test();
            JSONObject json= rest.getJson(String.format("getTest?id=%d",id));
            test.setWording(json.getString("wording"));
            JSONArray array= json.getJSONArray("choices");

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






}
