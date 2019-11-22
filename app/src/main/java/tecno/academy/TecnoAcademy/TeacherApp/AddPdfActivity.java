package tecno.academy.TecnoAcademy.TeacherApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import tecno.academy.TecnoAcademy.Models.PdfModel;
import tecno.academy.TecnoAcademy.R;

public class AddPdfActivity extends AppCompatActivity
{
    Button select_pdf,upload_pdf;
    TextView textView;
    EditText pdf_field;

    final static int PICK_PDF_CODE = 2342;

    CircularProgressBar seekBar;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Uri pdf;

    int i = 0;

    UploadTask uploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pdf);

        select_pdf = findViewById(R.id.add_pdf_btn);
        upload_pdf = findViewById(R.id.upload_pdf);
        seekBar = findViewById(R.id.seekbar);
        textView = findViewById(R.id.progress_txt);
        pdf_field = findViewById(R.id.pdf_title_field);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference().child("PDF");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        seekBar.setVisibility(View.INVISIBLE);

        select_pdf.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getpdf();
            }
        });

        upload_pdf.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (i == 0)
                {
                    String s = pdf_field.getText().toString();

                    if (TextUtils.isEmpty(s))
                    {
                        Toast.makeText(getApplicationContext(), "اختر اسم الملف أولا", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (pdf == null)
                    {
                        Toast.makeText(getApplicationContext(), "اختر ملف أولا", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    seekBar.setVisibility(View.VISIBLE);
                    uploadFile(pdf,s);
                    select_pdf.setEnabled(false);
                    pdf_field.setEnabled(false);

                    i = 1;
                } else if (i == 1)
                {
                    /*startActivity(new Intent(getApplicationContext(), AddPdfActivity.class));
                    finish();*/
                    uploadTask.cancel();

                    uploadTask.addOnCanceledListener(new OnCanceledListener()
                    {
                        @Override
                        public void onCanceled()
                        {
                            pdf = null;
                            select_pdf.setText("اضافة ملف");
                            upload_pdf.setText("رفع الملف");
                            seekBar.setVisibility(View.INVISIBLE);
                            textView.setText("");
                            pdf_field.setText("");
                            Toast.makeText(getApplicationContext(), "تم ايقاف الرفع", Toast.LENGTH_SHORT).show();
                            select_pdf.setEnabled(true);
                            pdf_field.setEnabled(true);
                        }
                    });

                    i = 0;
                }
            }
        });
    }

    void getpdf()
    {
        //for greater than lolipop versions we need the permissions asked on runtime
        //so if the permission is not available user will go to the screen to allow storage permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            return;
        }

        //creating an intent for file chooser
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //when the user choses the file
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            //if a file is selected
            if (data.getData() != null)
            {
                pdf = data.getData();
                select_pdf.setText("تم اختيار ملف");
            }else{
                Toast.makeText(this, "لم يتم اختيار أى ملف", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadFile(Uri data, final String s)
    {
        final StorageReference ref = storageReference.child("PDF/" + System.currentTimeMillis() + ".pdf");

        uploadTask = ref.putFile(data);

        Task<Uri> urlTask = uploadTask
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
        {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
            {
                if (!task.isSuccessful())
                {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>()
        {
            @Override
            public void onComplete(@NonNull Task<Uri> task)
            {
                if (task.isComplete() && task.isSuccessful())
                {
                    Uri downloadUri = task.getResult();
                    String imageUrl = downloadUri.toString();

                    Toast.makeText(getApplicationContext(), "تم رفع الملف بنجاح", Toast.LENGTH_SHORT).show();

                    PdfModel pdfModel = new PdfModel(imageUrl, s);

                    String k = databaseReference.child("TeachersPdfs").child(getuId()).push().getKey();
                    databaseReference.child("TeachersPdfs").child(getuId()).child(k).setValue(pdfModel);

                    startActivity(new Intent(getApplicationContext(), TeacherMainActivity.class));
                }
            }
        });

        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>()
        {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot)
            {
                 upload_pdf.setText("إيقاف الرفع");

                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                seekBar.setProgress((int) progress);
                textView.setText((int) progress + "%");
            }
        });
    }

    String getuId()
    {
        return FirebaseAuth.getInstance().getUid();
    }
}
