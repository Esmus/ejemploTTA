package es.tta.ejemploclase.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Miren on 30/12/2015.
 */
public class Status  {

    final  private String user_dni;
    final  private String user_password;
    private int id;
    private String user;
    private int lessonNumber;
    private String lessonTitle;
    private int nextTest;
    private int nextExercise;


    public Status(String user_dni, String user_password, int id, String user, int lessonNumber, String lessonTitle, int nextTest, int nextExercise) {
        this.user_dni = user_dni;
        this.user_password = user_password;
        this.id = id;
        this.user = user;
        this.lessonNumber = lessonNumber;
        this.lessonTitle = lessonTitle;
        this.nextTest = nextTest;
        this.nextExercise = nextExercise;
    }

    public int getNextExercise() {
        return nextExercise;
    }

    public void setNextExercise(int nextExercise) {
        this.nextExercise = nextExercise;
    }

    public int getNextTest() {
        return nextTest;
    }

    public void setNextTest(int nextTest) {
        this.nextTest = nextTest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }


}
