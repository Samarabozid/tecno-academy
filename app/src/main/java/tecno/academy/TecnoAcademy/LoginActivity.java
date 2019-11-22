package tecno.academy.TecnoAcademy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import tecno.academy.TecnoAcademy.AdminApp.AdminMainActivity;
import tecno.academy.TecnoAcademy.StudentApp.RegisterActivity;
import tecno.academy.TecnoAcademy.StudentApp.StudentMainActivity;

public class LoginActivity extends AppCompatActivity
{

    EditText email_field,password_field;
    MaterialRippleLayout login;
    TextView reRegister;
    FirebaseAuth firebaseAuth;
    String email_txt,password_txt;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_field = findViewById(R.id.email);
        password_field = findViewById(R.id.password);

        firebaseAuth = firebaseAuth.getInstance();

        login = findViewById(R.id.login);
        reRegister = findViewById(R.id.reRegister);

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)
           {
               email_txt = email_field.getText().toString();
               password_txt = password_field.getText().toString();
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

               progressDialog = new ProgressDialog(LoginActivity.this);
               progressDialog.setMessage("برجاء الانتظار ...");
               progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
               progressDialog.show();
               progressDialog.setCancelable(false);

               loginUserAccount(email_txt,password_txt);
           }
       });

        reRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void loginUserAccount(String email , String  password)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            if (task.getResult().getUser().getUid().equals("kCEYHrGQ66Zu5MxmVI9dY9XlCHV2"))
                            {
                                progressDialog.dismiss();

                                Intent intent = new Intent(LoginActivity.this.getApplicationContext(), AdminMainActivity.class);
                                startActivity(intent);
                                finish();
                            } else
                                {
                                    progressDialog.dismiss();

                                    Intent intent = new Intent(LoginActivity.this.getApplicationContext(), StudentMainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                        } else {
                            String taskmessage = task.getException().getMessage();
                            Toast.makeText(LoginActivity.this.getApplicationContext(), taskmessage, Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }
}