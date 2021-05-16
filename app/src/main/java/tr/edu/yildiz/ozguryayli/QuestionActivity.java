package tr.edu.yildiz.ozguryayli;

import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.URI;

public class QuestionActivity extends AppCompatActivity {
    public TextView textQuestion;
    public TextView textanswerA;
    public TextView textanswerB;
    public TextView textanswerC;
    public TextView textanswerD;
    public TextView textanswerE;
    public TextView textattach;
    public TextView textinfo;
    public Button savebutton;
    public Button attachbutton;
    public CheckBox boxA;
    public CheckBox boxB;
    public CheckBox boxC;
    public CheckBox boxD;
    public CheckBox boxE;
    int iperson;
    private static final int PICK_FROM_GALLERY=101;
    Uri URI =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        textQuestion = findViewById(R.id.Text);
        textanswerA = findViewById(R.id.AnswerA);
        textanswerB  = findViewById(R.id.AnswerB);
        textanswerC  = findViewById(R.id.AnswerC);
        textanswerD  = findViewById(R.id.AnswerD);
        textanswerE  = findViewById(R.id.AnswerE);
        textattach  = findViewById(R.id.textAttach);
        textinfo = findViewById(R.id.textView2);
        savebutton = findViewById(R.id.buttonsave);
        attachbutton = findViewById(R.id.buttonAttach);
        boxA = findViewById(R.id.checkBoxA);
        boxB = findViewById(R.id.checkBoxB);
        boxC = findViewById(R.id.checkBoxC);
        boxD = findViewById(R.id.checkBoxD);
        boxE = findViewById(R.id.checkBoxE);
        Intent intent = getIntent();
        iperson=intent.getIntExtra("iperson",0);

    }

    public void addQuestion(View view){

        if (textQuestion.length() != 0 && textanswerA.length() != 0 && textanswerB.length() != 0) {
            Question question = new Question();
            int count=0;
            int correct=0;
            if(boxA.isChecked()){
                count=count+1;
                correct=1;

            }

            if(boxB.isChecked()){
                count=count+1;
                correct=2;
            }

            if(boxC.isChecked()){
                count=count+1;
                correct=3;

            }

            if(boxD.isChecked()){
                count=count+1;
                correct=4;

            }

            if(boxE.isChecked()){
                count=count+1;
                correct=5;

            }

            if(count == 0){
                Toast.makeText(getApplicationContext(), "Correct answer not marked", Toast.LENGTH_SHORT).show();

            }
            else if(count == 1){
                Toast.makeText(getApplicationContext(), "Question is added successfully", Toast.LENGTH_SHORT).show();

                question.setQues(textQuestion.getText().toString());
                question.setAnsA(textanswerA.getText().toString());
                question.setAnsB(textanswerB.getText().toString());
                question.setAnsC(textanswerC.getText().toString());
                question.setAnsD(textanswerD.getText().toString());
                question.setAnsE(textanswerE.getText().toString());
                question.setURI(URI);
                if(correct==1)
                    question.setAnsRight(1);
                else if(correct==2)
                    question.setAnsRight(2);
                else if(correct==3)
                    question.setAnsRight(3);
                else if(correct==4)
                    question.setAnsRight(4);
                else if(correct==5)
                    question.setAnsRight(5);

                System.out.println(question.getAnsRight());
                MainActivity.personList.get(iperson).questionList.add(question);
            }
            else{
                Toast.makeText(getApplicationContext(), "There cannot be more than one correct option", Toast.LENGTH_SHORT).show();
            }


        }
        else{
            Toast.makeText(getApplicationContext(), "Question text / Answer text cannot be empty", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_FROM_GALLERY && resultCode==RESULT_OK){
            URI = data.getData();
            textattach.setText(URI.getLastPathSegment());
            textattach.setVisibility(View.VISIBLE);

        }
    }

    public void attach(View view){

        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-date",true);
        startActivityForResult(Intent.createChooser(intent,"Compelete action using "),PICK_FROM_GALLERY);

    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Soru ekleme Act. on stop girdi");
        FileOutputStream outStream = null;

        try {
            File f = new File(getFilesDir() + "/persons.dat");
            f.delete();
            File file =new File(getFilesDir() + "/persons.dat");
            outStream = new FileOutputStream(file, true);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            for(Person i : MainActivity.personList){

                objectOutStream.writeObject(i);
                System.out.println("soru oluşturma yazma kısmı"+i.getEmail());

            }
            objectOutStream.flush();
            objectOutStream.close();
            outStream.close();
        }
        catch (Exception e){
            Log.v("Error Serialization",e.getMessage());
            e.printStackTrace();
        }
    }
}