package tr.edu.yildiz.ozguryayli;

import android.net.Uri;

import java.io.Serializable;

public class Question implements Serializable {
    public String ques;
    public String ansA;
    public String ansB;
    public String ansC;
    public String ansD;
    public String ansE;
    public int ansRight;
    transient Uri URI;

    public Question() {
        this.ques = ques;
        this.ansA = ansA;
        this.ansB = ansB;
        this.ansC = ansC;
        this.ansD = ansD;
        this.ansE = ansE;
        this.ansRight = ansRight;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String getAnsA() {
        return ansA;
    }

    public void setAnsA(String ansA) {
        this.ansA = ansA;
    }

    public String getAnsB() {
        return ansB;
    }

    public void setAnsB(String ansB) {
        this.ansB = ansB;
    }

    public String getAnsC() {
        return ansC;
    }

    public void setAnsC(String ansC) {
        this.ansC = ansC;
    }

    public String getAnsD() {
        return ansD;
    }

    public void setAnsD(String ansD) {
        this.ansD = ansD;
    }

    public String getAnsE() {
        return ansE;
    }

    public void setAnsE(String ansE) {
        this.ansE = ansE;
    }

    public int getAnsRight() {
        return ansRight;
    }

    public void setAnsRight(int ansRight) {
        this.ansRight = ansRight;
    }

    public Uri getURI() {
        return URI;
    }

    public void setURI(Uri URI) {
        this.URI = URI;
    }
}
