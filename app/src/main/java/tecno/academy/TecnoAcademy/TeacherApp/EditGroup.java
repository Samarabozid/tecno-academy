package tecno.academy.TecnoAcademy.TeacherApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tecno.academy.TecnoAcademy.Models.GroupModel;
import tecno.academy.TecnoAcademy.R;

public class EditGroup extends AppCompatActivity
{
    EditText title_field1,date_field1,time_field1;

    Button save;

    String id,title,date,time;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_group);

        id = getIntent().getStringExtra("GroupKey");

        title_field1 = findViewById(R.id.gp_title_field1);
        date_field1 = findViewById(R.id.gp_date_field1);
        time_field1 = findViewById(R.id.gp_time_field1);
        save = findViewById(R.id.save_edits);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        title_field1.setVisibility(View.VISIBLE);
        date_field1.setVisibility(View.VISIBLE);
        time_field1.setVisibility(View.VISIBLE);
        save.setVisibility(View.VISIBLE);


        databaseReference.child("Groups").child(getuId()).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    GroupModel groupModel = snapshot.getValue(GroupModel.class);

                    time = groupModel.getTime();
                    date = groupModel.getDate();
                    title = groupModel.getTitle();

                    time_field1.setText(time);
                    date_field1.setText(date);
                    title_field1.setText(title);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                title = title_field1.getText().toString();
                date = date_field1.getText().toString();
                time = time_field1.getText().toString();

                if (TextUtils.isEmpty(title))
                {
                    Toast.makeText(getApplicationContext(), "ادخل اسم المجموعة", Toast.LENGTH_SHORT).show();
                    title_field1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(date))
                {
                    Toast.makeText(getApplicationContext(), "ادخل تاريخ المجموعة", Toast.LENGTH_SHORT).show();
                    title_field1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(time))
                {
                    Toast.makeText(getApplicationContext(), "ادخل توقيت المجموعة", Toast.LENGTH_SHORT).show();
                    title_field1.requestFocus();
                    return;
                }
                EditGp(title,date,time);
            }
        });
    }

    private void EditGp(String title, String date, String time)
    {
        GroupModel groupModel = new GroupModel(id,title,date,time);

        databaseReference.child("Groups").child(getuId()).child(id).setValue(groupModel);

        startActivity(new Intent(getApplicationContext(), TeacherMainActivity.class));
        finish();
    }

    String getuId()
    {
        return FirebaseAuth.getInstance().getUid();
    }
}