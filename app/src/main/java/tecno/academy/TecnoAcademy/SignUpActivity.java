package tecno.academy.TecnoAcademy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.balysv.materialripple.MaterialRippleLayout;

import tecno.academy.TecnoAcademy.StudentApp.RegisterActivity;
import tecno.academy.TecnoAcademy.TeacherApp.TeacherRegisterActivity;

public class SignUpActivity extends AppCompatActivity
{
    MaterialRippleLayout teacher, student;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        teacher = findViewById(R.id.signInAsAteacher);
        student = findViewById(R.id.signInAsAstudent);

        teacher.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), TeacherRegisterActivity.class);
                startActivity(intent);

            }
        });
        student.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed()
    {
        finishAffinity();
    }
}
