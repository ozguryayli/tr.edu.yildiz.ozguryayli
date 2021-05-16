package tr.edu.yildiz.ozguryayli;

import android.content.Intent;
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

public class UpdateActivity extends AppCompatActivity {
    public TextView textQuestion;
    public TextView textanswerA;
    public TextView textanswerB;
    public TextView textanswerC;
    public TextView textanswerD;
    public TextView textanswerE;
    public Button updatebutton;
    public TextView textattt;
    public Button buttonatt;
    public CheckBox boxA;
    public CheckBox boxB;
    public CheckBox boxC;
    public CheckBox boxD;
    public CheckBox boxE;
    int iquestion;
    int iperson;
    int right;
    private static final int PICK_FROM_GALLERY=101;
    Uri URI =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        textQuestion = findViewById(R.id.utext);
        textanswerA = findViewById(R.id.uanswerA);
        textanswerB = findViewById(R.id.uanswerB);
        textanswerC = findViewById(R.id.uanswerC);
        textanswerD = findViewById(R.id.uanswerD);
        textanswerE = findViewById(R.id.uanswerE);
        updatebutton = findViewById(R.id.buttonupdate);
        textattt =findViewById(R.id.textView21);
        buttonatt=findViewById(R.id.button4);
        boxA = findViewById(R.id.ucheckBoxA);
        boxB = findViewById(R.id.ucheckBoxB);
        boxC = findViewById(R.id.ucheckBoxC);
        boxD = findViewById(R.id.ucheckBoxD);
        boxE = findViewById(R.id.ucheckBoxE);

        Intent intent = getIntent();
        iquestion=intent.getIntExtra("iquestion",0);
        iperson = intent.getIntExtra("iperson",0);

        textQuestion.setText(MainActivity.personList.get(iperson).questionList.get(iquestion).ques);
        textanswerA.setText(MainActivity.personList.get(iperson).questionList.get(iquestion).ansA);
        textanswerB.setText(MainActivity.personList.get(iperson).questionList.get(iquestion).ansB);
        textanswerC.setText(MainActivity.personList.get(iperson).questionList.get(iquestion).ansC);
        textanswerD.setText(MainActivity.personList.get(iperson).questionList.get(iquestion).ansD);
        textanswerE.setText(MainActivity.personList.get(iperson).questionList.get(iquestion).ansE);
        textattt.setText(MainActivity.personList.get(iperson).questionList.get(iquestion).URI.getPath());
        right=MainActivity.personList.get(iperson).questionList.get(iquestion).getAnsRight();
        if(right==1){
            boxA.setChecked(true);
        }
        else if (right==2){
            boxB.setChecked(true);
        }
        else if (right==3){
            boxC.setChecked(true);
        }
        else if (right==4){
            boxD.setChecked(true);
        }
        else{
            boxE.setChecked(true);
        }
    }

    public void update(View view){

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
                Toast.makeText(getApplicationContext(), "Question is updated successfully", Toast.LENGTH_SHORT).show();

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
                MainActivity.personList.get(iperson).questionList.get(iquestion).setQues(question.getQues());
                MainActivity.personList.get(iperson).questionList.get(iquestion).setAnsA(question.getAnsA());
                MainActivity.personList.get(iperson).questionList.get(iquestion).setAnsB(question.getAnsB());
                MainActivity.personList.get(iperson).questionList.get(iquestion).setAnsC(question.getAnsC());
                MainActivity.personList.get(iperson).questionList.get(iquestion).setAnsD(question.getAnsD());
                MainActivity.personList.get(iperson).questionList.get(iquestion).setAnsE(question.getAnsE());
                MainActivity.personList.get(iperson).questionList.get(iquestion).setAnsRight(question.getAnsRight());
                MainActivity.personList.get(iperson).questionList.get(iquestion).setURI(question.getURI());
                Intent intent = new Intent(this, ListQuestionsActivity.class);
                startActivity(intent);
                finish();
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
            textattt.setText(URI.getLastPathSegment());
            textattt.setVisibility(View.VISIBLE);

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
        System.out.println("Update Act. on stop girdi");

        FileOutputStream outStream = null;

        try {
            File f = new File(getFilesDir() + "/persons.dat");
            f.delete();
            File file =new File(getFilesDir() + "/persons.dat");
            outStream = new FileOutputStream(file, true);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            for(Person i : MainActivity.personList){

                objectOutStream.writeObject(i);
                System.out.println("update yazma kısmı"+i.getEmail());

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