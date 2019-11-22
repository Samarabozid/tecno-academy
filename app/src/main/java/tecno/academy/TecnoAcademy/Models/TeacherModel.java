package tecno.academy.TecnoAcademy.Models;

import java.io.Serializable;

public class TeacherModel implements Serializable
{
    String teacher_id,name,email,level,imageUrl,subject_name;

    public TeacherModel()
    {
    }

    public TeacherModel(String teacher_id, String name, String email, String level, String imageUrl, String subject_name) {
        this.teacher_id = teacher_id;
        this.name = name;
        this.email = email;
        this.level = level;
        this.imageUrl = imageUrl;
        this.subject_name = subject_name;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
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

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
}
