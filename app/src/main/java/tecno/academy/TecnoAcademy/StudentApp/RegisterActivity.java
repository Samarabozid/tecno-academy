package tecno.academy.TecnoAcademy.StudentApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;
import tecno.academy.TecnoAcademy.LoginActivity;
import tecno.academy.TecnoAcademy.Models.StudentModel;
import tecno.academy.TecnoAcademy.R;

public class RegisterActivity extends AppCompatActivity
{
    EditText user_name, user_email, user_password;
    Spinner levels;
    String user_level;
    MaterialRippleLayout createAccount;

    TextView reRegister;

    CircleImageView circleImageView;
    Uri photoPath;

    String name,email,password;

    private FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.email);
        user_password = findViewById(R.id.password);
        createAccount = findViewById(R.id.createAccount);
        levels = findViewById(R.id.spinner1);

        reRegister = findViewById(R.id.reRegister);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference.keepSynced(true);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference().child("images");

        circleImageView = findViewById(R.id.profile_picture);

        reRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        circleImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                        .setAspectRatio(1, 1)
                        .start(RegisterActivity.this);
            }
        });

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.levels));
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levels.setAdapter(arrayAdapter1);

        levels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                user_level = String.valueOf(adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                name = user_name.getText().toString();
                email = user_email.getText().toString();
                password = user_password.getText().toString();

                if (TextUtils.isEmpty(name))
                {
                    Toast.makeText(RegisterActivity.this.getApplicationContext(), "ادخل اسمك الكامل..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(RegisterActivity.this.getApplicationContext(), "ادخل بريدك الإلكترونى..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(RegisterActivity.this.getApplicationContext(), "ادخل كلمة السر..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (photoPath == null)
                {
                    Toast.makeText(RegisterActivity.this.getApplicationContext(), "ادخل صورتك الشخصيه", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (user_level.equals("اختر الصف الدراسى"))
                {
                    Toast.makeText(RegisterActivity.this.getApplicationContext(), "اختر صفك الدراسى أولا", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setMessage("برجاء الانتظار ...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                progressDialog.setCancelable(false);

                CreateAccount(name,email,password,user_level);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == Activity.RESULT_OK)
            {
                if (result != null)
                {
                    photoPath = result.getUri();

                    Picasso.get()
                            .load(photoPath)
                            .placeholder(R.drawable.addphoto)
                            .error(R.drawable.addphoto)
                            .into(circleImageView);
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
    }

    private void uploadImage(final String name, final String email, final String level)
    {
        UploadTask uploadTask;

        final StorageReference ref = storageReference.child("images/" + photoPath.getLastPathSegment());

        uploadTask = ref.putFile(photoPath);

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
        {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
            {
                if (!task.isSuccessful())
                {
                    throw task.getException();
                }
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>()
        {
            @Override
            public void onComplete(@NonNull Task<Uri> task)
            {
                Uri downloadUri = task.getResult();

                String imageUrl = downloadUri.toString();

                AddUser(name, email, level, imageUrl);
            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void AddUser(String name, String email, String level, String imageUrl)
    {
        StudentModel studentModel = new StudentModel(getUID(),name,email,level,imageUrl);
        databaseReference.child("Students").child(getUID()).setValue(studentModel);
        databaseReference.child("SectionedStudents").child(level).child(getUID()).setValue(studentModel);
        progressDialog.dismiss();

        startActivity(new Intent(getApplicationContext(), StudentMainActivity.class));
        finish();
    }

    private void CreateAccount(final String name, final String email, String password, final String level)
    {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            uploadImage(name, email, level);
                        } else {
                            String error_message = task.getException().getMessage();
                            Toast.makeText(RegisterActivity.this.getApplicationContext(), error_message, Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }
    private String getUID()
    {
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        return id;
    }
}