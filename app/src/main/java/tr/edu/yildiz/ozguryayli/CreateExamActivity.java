package tr.edu.yildiz.ozguryayli;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.UUID;

public class CreateExamActivity extends AppCompatActivity {
    int iperson;
    Button createButton;
    Button sendMessage;
    TextView timeExam;
    TextView scoreExam;
    TextView level;
    SeekBar seekbarExam;
    SharedPreferences sharedPreferences;
    public RecyclerAdapterExam examRecyclerAdapter;
    RecyclerView recyclerView;
    int seekLevel;
    UUID uuid = UUID.randomUUID();
    String uniq = uuid.toString();
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exam);

        createButton = findViewById(R.id.buttonCreate);
        timeExam = findViewById(R.id.timeExam);
        scoreExam = findViewById(R.id.scoreExam);
        seekbarExam = findViewById(R.id.seekBarExam);
        level = findViewById(R.id.textView19);
        sendMessage = findViewById(R.id.buttonmessage);

        Intent intent = getIntent();
        iperson=intent.getIntExtra("iperson",0);

        sharedPreferences = this.getSharedPreferences("tr.edu.yildiz.ozguryayli", Context.MODE_PRIVATE);
        int storedTime = sharedPreferences.getInt("storedTime",0);
        int storedScore = sharedPreferences.getInt("storedScore",0);
        int storedLevel = sharedPreferences.getInt("storedLevel",3);
        seekLevel=storedLevel+2;

        timeExam.setText(String.valueOf(storedTime));
        scoreExam.setText(String.valueOf(storedScore));

        seekbarExam.setProgress(storedLevel);

        level.setText("level  "+String.valueOf(storedLevel+2));
        seekbarExam.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                level.setText("level "+String.valueOf(progress+2));
                seekLevel=storedLevel+2;




                int time = Integer.parseInt(timeExam.getText().toString());
                int score = Integer.parseInt(scoreExam.getText().toString());
                int level = seekbarExam.getProgress();

                if(timeExam.getText().length()!=0 && scoreExam.getText().length()!=0){
                    sharedPreferences.edit().putInt("storedTime",time).apply();
                    sharedPreferences.edit().putInt("storedScore",score).apply();
                    sharedPreferences.edit().putInt("storedLevel",level).apply();

                    Toast toast = Toast.makeText(getApplicationContext(), "Exam settings are saved", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Empty fields must be filled", Toast.LENGTH_SHORT);
                    toast.show();
                }
                finish();
                Intent intent = new Intent(recyclerView.getContext(), CreateExamActivity.class);
                intent.putExtra("iperson",iperson);
                startActivity(intent);

                examRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        recyclerView = findViewById(R.id.examRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        examRecyclerAdapter = new RecyclerAdapterExam(MainActivity.personList.get(iperson).questionList.size(),iperson,storedLevel+2);
        recyclerView.setAdapter(examRecyclerAdapter);
        examRecyclerAdapter.notifyDataSetChanged();
    }


    public void create(View view)  {
        int i;


        Exam exam = new Exam();
        Character right = 'A' ;
        String fileName;


        if(timeExam.getText().length()!=0 && scoreExam.getText().length()!=0){
            fileName = getFilesDir()+"/"+MainActivity.personList.get(iperson).getEmail()+"sExam"+uniq+".txt";

            try {

                text = "Exam Time : "+timeExam.getText()+"\n"+
                        "Question Score : "+scoreExam.getText()+"\n"+
                        "Difficulty Level : "+level.getText()+"\n";


                for(i=0;i<RecyclerAdapterExam.examQuestions.size();i++){
                    exam.questionList.add(RecyclerAdapterExam.examQuestions.get(i));


                    if(RecyclerAdapterExam.examQuestions.get(i).getAnsRight()==1){
                        right = 'A';
                    }
                    else if (RecyclerAdapterExam.examQuestions.get(i).getAnsRight()==2){
                        right = 'B';
                    }
                    else if (RecyclerAdapterExam.examQuestions.get(i).getAnsRight()==3){
                        right = 'C';
                    }
                    else if (RecyclerAdapterExam.examQuestions.get(i).getAnsRight()==4){
                        right = 'D';
                    }
                    else if (RecyclerAdapterExam.examQuestions.get(i).getAnsRight()==5){
                        right = 'E';
                    }


                    text = text+"Q"+ i + ") "+RecyclerAdapterExam.examQuestions.get(i).getQues() + "\n"+
                            "A)" + RecyclerAdapterExam.examQuestions.get(i).getAnsA() + "\n"+
                            "B)" + RecyclerAdapterExam.examQuestions.get(i).getAnsB() + "\n"+
                            "C)" + RecyclerAdapterExam.examQuestions.get(i).getAnsC() + "\n"+
                            "D)" + RecyclerAdapterExam.examQuestions.get(i).getAnsD() + "\n"+
                            "E)" + RecyclerAdapterExam.examQuestions.get(i).getAnsE() + "\n"+

                            "Right option is : " + right + "\n";
                    if(RecyclerAdapterExam.examQuestions.get(i).getURI()!=null){
                        text = text + "Attachment : "+RecyclerAdapterExam.examQuestions.get(i).getURI().getPath() + "\n\n";
                    }
                }

                exam.setExamTime(Integer.parseInt(timeExam.getText().toString()));
                exam.setQuestionScore(Integer.parseInt(scoreExam.getText().toString())); ;
                exam.setDifficultyLevel(seekLevel);


                saveToFile(fileName,text);
                Toast toast = Toast.makeText(getApplicationContext(), "Exam is created in files folder", Toast.LENGTH_SHORT);
                toast.show();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "Empty fields must be filled", Toast.LENGTH_SHORT);
            toast.show();
        }


    }



    public void saveToFile(String fileName,String text) throws IOException {
        File file =new File(fileName);
        FileWriter fw= new FileWriter(file,true);
        PrintWriter pw= new PrintWriter(fw);
        pw.println(text);
        pw.close();

    }

    public void sendMessage(View view){
        Uri uri = Uri.parse(getFilesDir()+"/"+MainActivity.personList.get(iperson).getEmail()+"sExam"+uniq+".txt");
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
    //    intent.putExtra(Intent.EXTRA_STREAM,uri);
        intent.putExtra(Intent.EXTRA_TEXT,text);
        startActivity(Intent.createChooser(intent,"send txt"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("create exam Act. on stop girdi");

        FileOutputStream outStream = null;

        try {
            File f = new File(getFilesDir() + "/persons.dat");
            f.delete();
            File file =new File(getFilesDir() + "/persons.dat");
            outStream = new FileOutputStream(file, true);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            for(Person person : MainActivity.personList){

                objectOutStream.writeObject(person);
                System.out.println("create examyazma kısmı"+person.getEmail());

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