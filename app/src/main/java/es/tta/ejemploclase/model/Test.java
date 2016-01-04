package es.tta.ejemploclase.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Miren on 17/12/2015.
 */

//clase que contiene el test
public class Test {

    private String wording;

    private ArrayList choices= new ArrayList<Choice>();



    //constantes tipos advise
    static public final short HTML_ADVISE = 0;
    static public final short VIDEO_ADVISE = 1;
    static public final short AUDIO_ADVISE = 2;

/*    //constructor
    public Test(String pregunta,String [] respuestas, boolean [] posicionCorrectas, int tipoAdvise,String Advise){

        advise = Advise;
        adviseTipo = tipoAdvise;
        wording = pregunta;
            choices = new Choice[posicionCorrectas.length];
            int j = 0;
            for(String choice : respuestas){
                choices[j] = new Choice(respuestas[j],posicionCorrectas[j]);
                j++;
            }

    }*/

    public Test(){}



    public static class Choice{

        private String wording;
        private boolean correct;
        private String advise;
        private int id;
        private String mime;
        private int adviseTipo;

       /* public Choice(String Swording, boolean Correct){
            wording=Swording;
            correct=Correct;
        }*/

        public Choice(){}

        public int getAdviseTipo(){
            return this.adviseTipo;
        }
        public String getWording(){ return wording;  }

        public boolean isCorrect(){
            return correct;
        }

        public String getAdvise(){

            return advise;
        }

        public String getMime(){

            return mime;
        }

        public int getId() {

            return id;
        }


        public void setCorrect(boolean correct){

            correct=correct;

        }


        public void setWording(String wording){
            wording=wording;
        }



        public void setAdvise(String advise){

            advise=advise;
        }

        public void setMime(String mime){

            mime=mime;

        }

        public void setId(int id) {

            id=id;

        }



    }

    public String getWording(){

        return wording;
    }

    public ArrayList<Choice> getChoices(){
        return choices;
    }




    public void setWording(String wording){

        this.wording=wording;

    }




}
