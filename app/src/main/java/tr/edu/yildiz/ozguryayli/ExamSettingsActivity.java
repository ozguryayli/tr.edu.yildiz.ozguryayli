package tr.edu.yildiz.ozguryayli;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ExamSettingsActivity extends AppCompatActivity {

    TextView textTime;
    TextView textScore;
    SeekBar seekbar;
    Button buttonSave;
    TextView level;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_settings);

        textTime = findViewById(R.id.editTextNumber);
        textScore = findViewById(R.id.editTextNumber2);
        seekbar = findViewById(R.id.seekBar2);
        buttonSave = findViewById(R.id.button5);
        level = findViewById(R.id.textView17);

        sharedPreferences = this.getSharedPreferences("tr.edu.yildiz.ozguryayli", Context.MODE_PRIVATE);
        int storedTime = sharedPreferences.getInt("storedTime",0);
        int storedScore = sharedPreferences.getInt("storedScore",0);
        int storedLevel = sharedPreferences.getInt("storedLevel",3);


        textTime.setText(String.valueOf(storedTime));
        textScore.setText(String.valueOf(storedScore));

        seekbar.setProgress(storedLevel);

        level.setText("level : "+String.valueOf(storedLevel+2));
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                level.setText("level : "+String.valueOf(progress+2));


                int time = Integer.parseInt(textTime.getText().toString());
                int score = Integer.parseInt(textScore.getText().toString());
                int level = seekbar.getProgress();

                if(textTime.getText().length()!=0 && textScore.getText().length()!=0){
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







            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void saveSettings(View view){

        int time = Integer.parseInt(textTime.getText().toString());
        int score = Integer.parseInt(textScore.getText().toString());
        int level = seekbar.getProgress();

        if(textTime.getText().length()!=0 && textScore.getText().length()!=0){
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
    }
}