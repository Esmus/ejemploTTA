package es.tta.ejemploclase;

/**
 * Created by Miren on 17/12/2015.
 */
public class Test {


    protected String getWording(){

       String preguntaTest="¿Cual de las siguientes opciones NO se indica en el fichero de manifiesto de la app?";

        return preguntaTest;
    }

    public Choice[]getChoices(){

        Choice [] choices= new Choice[5];

        choices[0]=new Choice("Versión de la aplicación",false);
        choices[1]= new Choice("Listado de componentes de la aplicación",false);
        choices[2]= new Choice(  "Opciones del menú de ajustes",true);
        choices[3]= new Choice( "Nivel mínimo de la API Android requerida",false);
        choices[4]= new Choice("Nombre del paquete java de la aplicación",false);

        return choices;

    }



    public class Choice{
        private String wording;
        private boolean correcta;

        public Choice(String wording, boolean correcta){

            this.wording=wording;
            this.correcta=correcta;

        }

        public String getWording(){

            return this.wording;
        }

        public boolean isCorrecta(){
            return this.correcta;
        }


    }





}
