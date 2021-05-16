package tr.edu.yildiz.ozguryayli;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ListQuestionsActivity extends AppCompatActivity {
    int iperson;
    public RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    public List<SharedPreferences> questionList = new ArrayList<SharedPreferences>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);

        Intent intent = getIntent();
        iperson=intent.getIntExtra("iperson",0);

        recyclerView = findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(MainActivity.personList.get(iperson).questionList.size(),iperson);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();




    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Sign Up Act. on stop girdi");

        FileOutputStream outStream = null;

        try {
            File f = new File(getFilesDir() + "/persons.dat");
            f.delete();
            File file =new File(getFilesDir() + "/persons.dat");
            outStream = new FileOutputStream(file, true);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            for(Person i : MainActivity.personList){

                objectOutStream.writeObject(i);
                System.out.println("sign up yazma kısmı"+i.getEmail());

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