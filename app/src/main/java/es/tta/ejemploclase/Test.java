package es.tta.ejemploclase;

/**
 * Created by Miren on 17/12/2015.
 */

//clase que contiene el test
public class Test {


    private String wording;
    private String advise;
    private Choice[] choices;
    private int adviseTipo;

    //constantes tipos advise
    static public final short HTML_ADVISE = 0;
    static public final short VIDEO_ADVISE = 1;
    static public final short AUDIO_ADVISE = 2;

    //constructor
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

    }


    public class Choice{

        private String wording;
        private boolean correct;

        public Choice(String Swording, boolean Correct){
            wording=Swording;
            correct=Correct;
        }

        public String getWording(){ return wording;  }

        public boolean isCorrect(){
            return correct;
        }
    }

    public String getWording(){

        return wording;
    }

    public Choice[] getChoices(){
        return choices;
    }

    public String getAdvice(){

        return advise;
    }

    public int getTipoAdvise(){
        return adviseTipo;
    }











}
