package tecno.academy.TecnoAcademy.TeacherApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import tecno.academy.TecnoAcademy.R;

public class TeacherRegisterActivity extends AppCompatActivity
{
    EditText email,password;
    Button signin;

    String email_txt,password_txt;

    FirebaseAuth auth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.signin);

        auth = FirebaseAuth.getInstance();

        signin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                email_txt = email.getText().toString();
                password_txt = password.getText().toString();

                if (TextUtils.isEmpty(email_txt))
                {
                    Toast.makeText(getApplicationContext(), "ادخل بريدك الإلكترونى..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password_txt))
                {
                    Toast.makeText(getApplicationContext(), "ادخل كلمة السر..", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog = new ProgressDialog(TeacherRegisterActivity.this);
                progressDialog.setMessage("برجاء الانتظار ...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                progressDialog.setCancelable(false);

                si(email_txt,password_txt);
            }
        });
    }

    private void si(String email_txt, String password_txt)
    {
        auth.signInWithEmailAndPassword(email_txt,password_txt)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            progressDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), TeacherMainActivity.class));
                            finish();
                        } else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                    }
                });
    }
}
