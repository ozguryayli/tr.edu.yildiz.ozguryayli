package tr.edu.yildiz.ozguryayli;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    public Button button;
    public TextView textMail;
    public TextView textPas;
    public TextView textPas2;
    public TextView textName;
    public TextView textSurname;
    public TextView textPhone;
    public TextView textDate;
    public ImageView imageView;
    Bitmap selectedImage;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        button = (Button) findViewById(R.id.button);


        textName = (EditText) findViewById(R.id.editTextTextPersonName);
        textMail = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        textPas = (EditText) findViewById(R.id.editTextTextPassword2);
        textPas2 = (EditText) findViewById(R.id.editTextTextPassword3);
        textSurname = (EditText) findViewById(R.id.editTextTextPersonName2);
        textPhone = (EditText) findViewById(R.id.editTextPhone);
        textDate = (EditText) findViewById(R.id.editTextDate);
        imageView = (ImageView) findViewById(R.id.imageView);
    }


    public void selectImage(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
        else{
            Intent intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentGallery,2);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentGallery,2);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            Uri dataOfImage = data.getData();
            try {

                if(Build.VERSION.SDK_INT >= 28){
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(),dataOfImage);
                    selectedImage = ImageDecoder.decodeBitmap(source);
                    imageView.setImageBitmap(selectedImage);
                }
                else{
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),dataOfImage);
                    imageView.setImageBitmap(selectedImage);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


    public static boolean contains(List<Person> personList, String email) {
        for(Person i : personList) {
            if(i != null && i.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }


    public void sign(View view){
        Person person = new Person();
        person.setEmail(textMail.getText().toString());
        person.setDate(textDate.getText().toString());
        person.setEmail(textMail.getText().toString());
        person.setName(textName.getText().toString());
        person.setPassword(textPas.getText().toString());
        person.setPhone(textPhone.getText().toString());
        person.setSurname(textSurname.getText().toString());

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if(selectedImage!= null){
            selectedImage.compress(Bitmap.CompressFormat.PNG, 50, stream);
        }
        else{
            Bitmap photo = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.login);
            photo.compress(Bitmap.CompressFormat.PNG,50,stream);
        }
        byte[] byteArray = stream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        person.setImage(encoded);

        if(textSurname.getText().length()!=0 && textName.getText().length()!=0 && textMail.getText().length()!=0 && textPas.getText().length()!=0 && textPas2.getText().length()!=0){
            if(textPas.getText().toString().equals(textPas2.getText().toString())==true){
                if(!contains(MainActivity.personList, textMail.getText().toString())){
                    MainActivity.personList.add(person);
                    Toast toast = Toast.makeText(getApplicationContext(), "Successful Sign Up", Toast.LENGTH_SHORT);
                    toast.show();

                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "This mail account already exists", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(), "Enter the same password in two fields", Toast.LENGTH_SHORT);
                toast.show();
                textPas.setText("");
                textPas2.setText("");
            }
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "There should be no empty text field space", Toast.LENGTH_SHORT);
            toast.show();
        }


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