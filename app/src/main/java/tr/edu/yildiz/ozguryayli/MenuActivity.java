package tr.edu.yildiz.ozguryayli;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {

    public ImageView imageView;
    public Button buttonadd;
    public Button buttonlist;
    public Button buttoncreate;
    public Button buttonsettings;
    String email;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        imageView=findViewById(R.id.imageView2);
        buttonadd = findViewById(R.id.buttonadd);
        buttonlist = findViewById(R.id.buttonlist);
        buttonsettings = findViewById(R.id.buttonsettings);
        buttoncreate = findViewById(R.id.buttoncreate);
        Intent intent = getIntent();
        email=intent.getStringExtra("email");
        i=0;
        while(!MainActivity.personList.get(i).getEmail().equals(email)) {//bak
            i++;
        }
        byte[] decodedString = Base64.decode(MainActivity.personList.get(i).getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(decodedByte);
    }


    public void openQuestion(View view){
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("iperson",i);
        startActivity(intent);
    }

    public void listQuestion(View view){
        Intent intent = new Intent(this, ListQuestionsActivity.class);
        intent.putExtra("iperson",i);
        startActivity(intent);
    }

    public void createExam(View view){
        Intent intent = new Intent(this, CreateExamActivity.class);
        intent.putExtra("iperson",i);
        startActivity(intent);
    }

    public void examSettings(View view){
        Intent intent = new Intent(this, ExamSettingsActivity.class);
        intent.putExtra("iperson",i);
        startActivity(intent);
    }



}