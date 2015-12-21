package es.tta.ejemploclase;

import java.util.Random;

/**
 * Created by Miren on 17/12/2015.
 */

//clase que obtiene los datos y crea el test
public class Data {


    Test [] test;

    public Data(){

        test = new Test [3];

        //test1
        String [] preguntas = new String[4];
        boolean [] correctas = new boolean[4];
        preguntas[0] = "guy ritchie";
        correctas[0] = false;
        preguntas[1] = "Andrew Niccol";
        correctas[1] = true;
        preguntas[2] = "Michael Haneke";
        correctas[2] = false;
        preguntas[3] = "Pedro Almodovar";
        correctas[3] = false;
        String advise = "<html><body> <b>El director de Gattaca es Andrew Niccol</b></body></html>";
        test[0] = new Test("¿Quien es el director de Gattaca?",preguntas,correctas,Test.HTML_ADVISE,advise);

        //test2
        preguntas = new String[4];
        correctas = new boolean[4];
        preguntas[0] = "Leonardo Di Caprio";
        correctas[0] = false;
        preguntas[1] = "Jake Gyllenhaal";
        correctas[1] = true;
        preguntas[2] = "Antonio Banderas";
        correctas[2] = false;
        preguntas[3] = "Brad Pitt";
        correctas[3] = false;
        advise = "";
        test[1] = new Test("¿Quien es el actor principal de Donnie darko?",preguntas,correctas,Test.VIDEO_ADVISE,advise);
        //test3
        preguntas = new String[2];
        correctas = new boolean[2];
        preguntas[0] = "Los humanos tienen 1 ojo";
        correctas[0] = false;
        preguntas[1] = "Los humanos tienen 2 ojos";
        correctas[1] = true;
        advise = "";
        test[2] = new Test("Cual de estas afirmaciones  es verdadera",preguntas,correctas,Test.AUDIO_ADVISE,advise);
    }


    //devolver un test de forma aleatoria
    protected Test getTest(){

        //la clase random genera numeros aleatorios
        Random  rnd = new Random();
        int pos = rnd.nextInt(3);
        return test[pos];
    }

}
