package tr.edu.yildiz.ozguryayli;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public Button buttonLog;
    public Button buttonSign;
    public TextView textMail;
    public TextView textPas;
    public static List<Person> personList = new ArrayList<Person>();
    Integer counter;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLog = (Button) findViewById(R.id.button2);
        buttonSign = (Button) findViewById(R.id.button3);
        textMail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        textPas = (EditText) findViewById(R.id.editTextTextPassword);
        counter = 0;
        MainActivity.personList.clear();
        try {

            File f = new File(getFilesDir()+"/persons.dat");

            try {
                FileInputStream fileIn = new FileInputStream(f);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);

                boolean Read = true;
                try{
                    while(Read) {
                            Person person = (Person) objectIn.readObject();
                            MainActivity.personList.add(person);
                            System.out.println("main okuma kısmı"+person.getEmail());
              //              objectIn = new ObjectInputStream(fileIn);
                    }

                }
                catch(EOFException e) {
                    Read = false;
                }
                    objectIn.close();
                    fileIn.close();


            }catch (Exception ex) {
                ex.printStackTrace();

            }

        }
        catch (Exception e){
            Log.v("Error Serialization",e.getMessage());
            e.printStackTrace();
        }

    }

    public void signUp(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);

    }
    public static boolean contains( String email, String pas) {
        for(Person i : personList) {
            if(i != null && i.getEmail().equals(email) && i.getPassword().equals(pas)) {
                return true;
            }
        }
        return false;
    }

    public  void openMenu(View view){
     //   System.out.println(Person.personList.get(0).name+Person.personList.get(0).email);



        if(contains(textMail.getText().toString(),textPas.getText().toString())){
            Toast toast2 = Toast.makeText(getApplicationContext(), "Successful Login", Toast.LENGTH_SHORT);
            toast2.show();
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra("email",textMail.getText().toString());
            startActivity(intent);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_SHORT);
            toast.show();
            counter++;
            if(counter>=3){
                Toast toast2 = Toast.makeText(getApplicationContext(), "You have logged in incorrectly 3 times", Toast.LENGTH_SHORT);
                toast2.show();
                Intent intent = new Intent(this, SignUpActivity.class);

                startActivity(intent);
            }
        }



    }
}


