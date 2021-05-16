package tr.edu.yildiz.ozguryayli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    public String name;
    public String surname;
    public String phone;
    public String date;
    public String email;
    public String password;
    public String image;
    public List<Question> questionList = new ArrayList<Question>();
    public List<Exam> examList = new ArrayList<Exam>(); // sonrası hata oluştu


    public Person() {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.date = date;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }
}
