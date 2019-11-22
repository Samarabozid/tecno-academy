package tecno.academy.TecnoAcademy.TeacherApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tecno.academy.TecnoAcademy.Models.GroupModel;
import tecno.academy.TecnoAcademy.R;

public class AddGroupActivity extends AppCompatActivity
{
    EditText title_field,date_field,time_field;
    Button add;

    String title,date,time;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        title_field = findViewById(R.id.gp_title_field);
        date_field = findViewById(R.id.gp_date_field);
        time_field = findViewById(R.id.gp_time_field);
        add = findViewById(R.id.create_gp);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                title = title_field.getText().toString();
                date = date_field.getText().toString();
                time = time_field.getText().toString();

                if (TextUtils.isEmpty(title))
                {
                    Toast.makeText(getApplicationContext(), "ادخل اسم المجموعة", Toast.LENGTH_SHORT).show();
                    title_field.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(date))
                {
                    Toast.makeText(getApplicationContext(), "ادخل تاريخ المجموعة", Toast.LENGTH_SHORT).show();
                    title_field.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(time))
                {
                    Toast.makeText(getApplicationContext(), "ادخل توقيت المجموعة", Toast.LENGTH_SHORT).show();
                    title_field.requestFocus();
                    return;
                }
                addGp(title,date,time);
            }
        });
    }

    private void addGp(String title, String date, String time)
    {
        GroupModel groupModel = new GroupModel(title,date,time);

        String k = databaseReference.child("Groups").child(getuId()).push().getKey();
        databaseReference.child("Groups").child(getuId()).child(k).setValue(groupModel);

        startActivity(new Intent(getApplicationContext(), TeacherMainActivity.class));
        finish();
    }

    String getuId()
    {
        return FirebaseAuth.getInstance().getUid();
    }
}