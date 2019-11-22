package tecno.academy.TecnoAcademy.Models;

import java.io.Serializable;

public class StudentModel implements Serializable
{

    String student_id,name,email,level,imageUrl;

    public StudentModel() {
    }

    public StudentModel(String student_id, String name, String email, String level, String imageUrl) {
        this.student_id = student_id;
        this.name = name;
        this.email = email;
        this.level = level;
        this.imageUrl = imageUrl;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
