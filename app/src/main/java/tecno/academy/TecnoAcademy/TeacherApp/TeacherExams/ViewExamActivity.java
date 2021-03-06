package tecno.academy.TecnoAcademy.TeacherApp.TeacherExams;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import tecno.academy.TecnoAcademy.Models.QuestionModel;
import tecno.academy.TecnoAcademy.R;
import tecno.academy.TecnoAcademy.StudentApp.RegisterActivity;
import tecno.academy.TecnoAcademy.StudentApp.StartExamActivity;
import tecno.academy.TecnoAcademy.StudentApp.StudentMainActivity;
import tecno.academy.TecnoAcademy.TeacherApp.TeacherMainActivity;

public class ViewExamActivity extends AppCompatActivity {

    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9, img10;
    ImageView edit_img1, edit_img2, edit_img3, edit_img4, edit_img5, edit_img6, edit_img7, edit_img8, edit_img9, edit_img10;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ProgressDialog progressDialog;

    EditText quiz_title, question1, question2, question3, question4, question5,
            question6, question7, question8, question9, question10,
            correctAnswer1, correctAnswer2, correctAnswer3, correctAnswer4, correctAnswer5,
            correctAnswer6, correctAnswer7, correctAnswer8, correctAnswer9, correctAnswer10;

    EditText ansA1, ansB1, ansC1, ansD1, ansA2, ansB2, ansC2, ansD2, ansA3, ansB3, ansC3, ansD3, ansA4, ansB4, ansC4, ansD4, ansA5, ansB5, ansC5, ansD5, ansA6, ansB6, ansC6, ansD6, ansA7, ansB7, ansC7, ansD7, ansA8, ansB8, ansC8, ansD8, ansA9, ansB9, ansC9, ansD9, ansA10, ansB10, ansC10, ansD10;

    String title, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10,
            c1, c2, c3, c4, c5, c6, c7, c8, c9, c10
            ,imageurl1,imageurl2,imageurl3,imageurl4,imageurl5,imageurl6,imageurl7,imageurl8,imageurl9,imageurl10;

    private Uri mImageUri1 = null;
    private Uri mImageUri2 = null;
    private Uri mImageUri3 = null;
    private Uri mImageUri4 = null;
    private Uri mImageUri5 = null;
    private Uri mImageUri6 = null;
    private Uri mImageUri7 = null;
    private Uri mImageUri8 = null;
    private Uri mImageUri9 = null;
    private Uri mImageUri10 = null;

    private static final int GALLERY_REQUEST = 1;
    private static final int GALLERY_REQUEST2 = 2;
    private static final int GALLERY_REQUEST3 = 3;
    private static final int GALLERY_REQUEST4 = 4;
    private static final int GALLERY_REQUEST5 = 5;
    private static final int GALLERY_REQUEST6 = 6;
    private static final int GALLERY_REQUEST7 = 7;
    private static final int GALLERY_REQUEST8 = 8;
    private static final int GALLERY_REQUEST9 = 9;
    private static final int GALLERY_REQUEST10 = 10;

    String optA1, optB1, optC1, optD1;
    String optA2, optB2, optC2, optD2;
    String optA3, optB3, optC3, optD3;
    String optA4, optB4, optC4, optD4;
    String optA5, optB5, optC5, optD5;
    String optA6, optB6, optC6, optD6;
    String optA7, optB7, optC7, optD7;
    String optA8, optB8, optC8, optD8;
    String optA9, optB9, optC9, optD9;
    String optA10, optB10, optC10, optD10;

    boolean isImageFitToScreen;
    String k;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exam);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference().child("images");

        k = getIntent().getStringExtra("ex");

        done = findViewById(R.id.done);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img8 = findViewById(R.id.img8);
        img9 = findViewById(R.id.img9);
        img10 = findViewById(R.id.img10);

        edit_img1 = findViewById(R.id.edit_img1);
        edit_img2 = findViewById(R.id.edit_img2);
        edit_img3 = findViewById(R.id.edit_img3);
        edit_img4 = findViewById(R.id.edit_img4);
        edit_img5 = findViewById(R.id.edit_img5);
        edit_img6 = findViewById(R.id.edit_img6);
        edit_img7 = findViewById(R.id.edit_img7);
        edit_img8 = findViewById(R.id.edit_img8);
        edit_img9 = findViewById(R.id.edit_img9);
        edit_img10 = findViewById(R.id.edit_img10);

        correctAnswer1 = findViewById(R.id.correct1);
        correctAnswer2 = findViewById(R.id.correct2);
        correctAnswer3 = findViewById(R.id.correct3);
        correctAnswer4 = findViewById(R.id.correct4);
        correctAnswer5 = findViewById(R.id.correct5);
        correctAnswer6 = findViewById(R.id.correct6);
        correctAnswer7 = findViewById(R.id.correct7);
        correctAnswer8 = findViewById(R.id.correct8);
        correctAnswer9 = findViewById(R.id.correct9);
        correctAnswer10 = findViewById(R.id.correct10);

        quiz_title = findViewById(R.id.quiz_title);
        question1 = findViewById(R.id.txt_ques1);
        question2 = findViewById(R.id.txt_ques2);
        question3 = findViewById(R.id.txt_ques3);
        question4 = findViewById(R.id.txt_ques4);
        question5 = findViewById(R.id.txt_ques5);
        question6 = findViewById(R.id.txt_ques6);
        question7 = findViewById(R.id.txt_ques7);
        question8 = findViewById(R.id.txt_ques8);
        question9 = findViewById(R.id.txt_ques9);
        question10 = findViewById(R.id.txt_ques10);

        ansA1 = findViewById(R.id.ansA);
        ansB1 = findViewById(R.id.ansB);
        ansC1 = findViewById(R.id.ansC);
        ansD1 = findViewById(R.id.ansD);

        ansA2 = findViewById(R.id.ansA2);
        ansB2 = findViewById(R.id.ansB2);
        ansC2 = findViewById(R.id.ansC2);
        ansD2 = findViewById(R.id.ansD2);

        ansA3 = findViewById(R.id.ansA3);
        ansB3 = findViewById(R.id.ansB3);
        ansC3 = findViewById(R.id.ansC3);
        ansD3 = findViewById(R.id.ansD3);

        ansA4 = findViewById(R.id.ansA4);
        ansB4 = findViewById(R.id.ansB4);
        ansC4 = findViewById(R.id.ansC4);
        ansD4 = findViewById(R.id.ansD4);

        ansA5 = findViewById(R.id.ansA5);
        ansB5 = findViewById(R.id.ansB5);
        ansC5 = findViewById(R.id.ansC5);
        ansD5 = findViewById(R.id.ansD5);

        ansA6 = findViewById(R.id.ansA6);
        ansB6 = findViewById(R.id.ansB6);
        ansC6 = findViewById(R.id.ansC6);
        ansD6 = findViewById(R.id.ansD6);

        ansA7 = findViewById(R.id.ansA7);
        ansB7 = findViewById(R.id.ansB7);
        ansC7 = findViewById(R.id.ansC7);
        ansD7 = findViewById(R.id.ansD7);

        ansA8 = findViewById(R.id.ansA8);
        ansB8 = findViewById(R.id.ansB8);
        ansC8 = findViewById(R.id.ansC8);
        ansD8 = findViewById(R.id.ansD8);

        ansA9 = findViewById(R.id.ansA9);
        ansB9 = findViewById(R.id.ansB9);
        ansC9 = findViewById(R.id.ansC9);
        ansD9 = findViewById(R.id.ansD9);

        ansA10 = findViewById(R.id.ansA10);
        ansB10 = findViewById(R.id.ansB10);
        ansC10 = findViewById(R.id.ansC10);
        ansD10 = findViewById(R.id.ansD10);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImageFitToScreen) {
                    isImageFitToScreen = false;
                    img1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img1.setAdjustViewBounds(true);
                } else {
                    isImageFitToScreen = true;
                    img1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img1.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImageFitToScreen) {
                    isImageFitToScreen = false;
                    img2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img2.setAdjustViewBounds(true);
                } else {
                    isImageFitToScreen = true;
                    img2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img2.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImageFitToScreen) {
                    isImageFitToScreen = false;
                    img3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img3.setAdjustViewBounds(true);
                } else {
                    isImageFitToScreen = true;
                    img3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img3.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImageFitToScreen) {
                    isImageFitToScreen = false;
                    img4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img4.setAdjustViewBounds(true);
                } else {
                    isImageFitToScreen = true;
                    img4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img4.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImageFitToScreen) {
                    isImageFitToScreen = false;
                    img5.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img5.setAdjustViewBounds(true);
                } else {
                    isImageFitToScreen = true;
                    img5.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img5.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImageFitToScreen) {
                    isImageFitToScreen = false;
                    img6.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img6.setAdjustViewBounds(true);
                } else {
                    isImageFitToScreen = true;
                    img6.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img6.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImageFitToScreen) {
                    isImageFitToScreen = false;
                    img7.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img7.setAdjustViewBounds(true);
                } else {
                    isImageFitToScreen = true;
                    img7.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img7.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImageFitToScreen) {
                    isImageFitToScreen = false;
                    img8.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img8.setAdjustViewBounds(true);
                } else {
                    isImageFitToScreen = true;
                    img8.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img8.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImageFitToScreen) {
                    isImageFitToScreen = false;
                    img9.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img9.setAdjustViewBounds(true);
                } else {
                    isImageFitToScreen = true;
                    img9.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img9.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImageFitToScreen) {
                    isImageFitToScreen = false;
                    img10.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img10.setAdjustViewBounds(true);
                } else {
                    isImageFitToScreen = true;
                    img10.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img10.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        edit_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });

        edit_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST2);
            }
        });

        edit_img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST3);
            }
        });

        edit_img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST4);
            }
        });

        edit_img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST5);
            }
        });

        edit_img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST6);
            }
        });

        edit_img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST7);
            }
        });

        edit_img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST8);
            }
        });

        edit_img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST9);
            }
        });

        edit_img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST10);
            }
        });

        databaseReference.child("Exams").child(getuId()).child(k).child("Images").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                QuestionModel questionModel = dataSnapshot.getValue(QuestionModel.class);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Exams").child(getuId()).child(k).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                QuestionModel questionModel = dataSnapshot.getValue(QuestionModel.class);

                Picasso.get()
                        .load(questionModel.getImageurl1())
                        .error(R.drawable.addphoto)
                        .placeholder(R.drawable.addphoto)
                        .into(img1);

                Picasso.get()
                        .load(questionModel.getImageurl2())
                        .error(R.drawable.addphoto)
                        .placeholder(R.drawable.addphoto)
                        .into(img2);

                Picasso.get()
                        .load(questionModel.getImageurl3())
                        .error(R.drawable.addphoto)
                        .placeholder(R.drawable.addphoto)
                        .into(img3);

                Picasso.get()
                        .load(questionModel.getImageurl4())
                        .error(R.drawable.addphoto)
                        .placeholder(R.drawable.addphoto)
                        .into(img4);

                Picasso.get()
                        .load(questionModel.getImageurl5())
                        .error(R.drawable.addphoto)
                        .placeholder(R.drawable.addphoto)
                        .into(img5);

                Picasso.get()
                        .load(questionModel.getImageurl6())
                        .error(R.drawable.addphoto)
                        .placeholder(R.drawable.addphoto)
                        .into(img6);

                Picasso.get()
                        .load(questionModel.getImageurl7())
                        .error(R.drawable.addphoto)
                        .placeholder(R.drawable.addphoto)
                        .into(img7);

                Picasso.get()
                        .load(questionModel.getImageurl8())
                        .error(R.drawable.addphoto)
                        .placeholder(R.drawable.addphoto)
                        .into(img8);

                Picasso.get()
                        .load(questionModel.getImageurl9())
                        .error(R.drawable.addphoto)
                        .placeholder(R.drawable.addphoto)
                        .into(img9);

                Picasso.get()
                        .load(questionModel.getImageurl10())
                        .error(R.drawable.addphoto)
                        .placeholder(R.drawable.addphoto)
                        .into(img10);

                title = questionModel.getEx_title();
                quiz_title.setText(title);

                q1 = questionModel.getQuestion1();
                question1.setText(q1);
                c1 = questionModel.getCorrect_answer1();
                correctAnswer1.setText(c1);
                optA1 = questionModel.getOptionA1();
                optB1 = questionModel.getOptionB1();
                optC1 = questionModel.getOptionC1();
                optD1 = questionModel.getOptionD1();
                ansA1.setText(optA1);
                ansB1.setText(optB1);
                ansC1.setText(optC1);
                ansD1.setText(optD1);

                q2 = questionModel.getQuestion2();
                question2.setText(q2);
                c2 = questionModel.getCorrect_answer2();
                correctAnswer2.setText(c2);
                optA2 = questionModel.getOptionA2();
                optB2 = questionModel.getOptionB2();
                optC2 = questionModel.getOptionC2();
                optD2 = questionModel.getOptionD2();
                ansA2.setText(optA2);
                ansB2.setText(optB2);
                ansC2.setText(optC2);
                ansD2.setText(optD2);

                q3 = questionModel.getQuestion3();
                question3.setText(q3);
                c3 = questionModel.getCorrect_answer3();
                correctAnswer3.setText(c3);
                optA3 = questionModel.getOptionA3();
                optB3 = questionModel.getOptionB3();
                optC3 = questionModel.getOptionC3();
                optD3 = questionModel.getOptionD3();
                ansA3.setText(optA3);
                ansB3.setText(optB3);
                ansC3.setText(optC3);
                ansD3.setText(optD3);

                q4 = questionModel.getQuestion4();
                question4.setText(q4);
                c4 = questionModel.getCorrect_answer4();
                correctAnswer4.setText(c4);
                optA4 = questionModel.getOptionA4();
                optB4 = questionModel.getOptionB4();
                optC4 = questionModel.getOptionC4();
                optD4 = questionModel.getOptionD4();
                ansA4.setText(optA4);
                ansB4.setText(optB4);
                ansC4.setText(optC4);
                ansD4.setText(optD4);

                q5 = questionModel.getQuestion5();
                question5.setText(q5);
                c5 = questionModel.getCorrect_answer5();
                correctAnswer5.setText(c5);
                optA5 = questionModel.getOptionA5();
                optB5 = questionModel.getOptionB5();
                optC5 = questionModel.getOptionC5();
                optD5 = questionModel.getOptionD5();
                ansA5.setText(optA5);
                ansB5.setText(optB5);
                ansC5.setText(optC5);
                ansD5.setText(optD5);

                q6 = questionModel.getQuestion6();
                question6.setText(q6);
                c6 = questionModel.getCorrect_answer6();
                correctAnswer6.setText(c6);
                optA6 = questionModel.getOptionA6();
                optB6 = questionModel.getOptionB6();
                optC6 = questionModel.getOptionC6();
                optD6 = questionModel.getOptionD6();
                ansA6.setText(optA6);
                ansB6.setText(optB6);
                ansC6.setText(optC6);
                ansD6.setText(optD6);

                q7 = questionModel.getQuestion7();
                question7.setText(q7);
                c7 = questionModel.getCorrect_answer7();
                correctAnswer7.setText(c7);
                optA7 = questionModel.getOptionA7();
                optB7 = questionModel.getOptionB7();
                optC7 = questionModel.getOptionC7();
                optD7 = questionModel.getOptionD7();
                ansA7.setText(optA7);
                ansB7.setText(optB7);
                ansC7.setText(optC7);
                ansD7.setText(optD7);

                q8 = questionModel.getQuestion8();
                question8.setText(q8);
                c8 = questionModel.getCorrect_answer8();
                correctAnswer8.setText(c8);
                optA8 = questionModel.getOptionA8();
                optB8 = questionModel.getOptionB8();
                optC8 = questionModel.getOptionC8();
                optD8 = questionModel.getOptionD8();
                ansA8.setText(optA8);
                ansB8.setText(optB8);
                ansC8.setText(optC8);
                ansD8.setText(optD8);

                q9 = questionModel.getQuestion9();
                question9.setText(q9);
                c9 = questionModel.getCorrect_answer9();
                correctAnswer9.setText(c9);
                optA9 = questionModel.getOptionA9();
                optB9 = questionModel.getOptionB9();
                optC9 = questionModel.getOptionC9();
                optD9 = questionModel.getOptionD9();
                ansA9.setText(optA9);
                ansB9.setText(optB9);
                ansC9.setText(optC9);
                ansD9.setText(optD9);

                q10 = questionModel.getQuestion10();
                question10.setText(q10);
                c10 = questionModel.getCorrect_answer10();
                correctAnswer10.setText(c10);
                optA10 = questionModel.getOptionA10();
                optB10 = questionModel.getOptionB10();
                optC10 = questionModel.getOptionC10();
                optD10 = questionModel.getOptionD10();
                ansA10.setText(optA10);
                ansB10.setText(optB10);
                ansC10.setText(optC10);
                ansD10.setText(optD10);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = quiz_title.getText().toString();
                q1 = question1.getText().toString();
                optA1 = ansA1.getText().toString();
                optB1 = ansB1.getText().toString();
                optC1 = ansC1.getText().toString();
                optD1 = ansD1.getText().toString();
                c1 = correctAnswer1.getText().toString();

                q2 = question2.getText().toString();
                optA2 = ansA2.getText().toString();
                optB2 = ansB2.getText().toString();
                optC2 = ansC2.getText().toString();
                optD2 = ansD2.getText().toString();
                c2 = correctAnswer2.getText().toString();

                q3 = question3.getText().toString();
                optA3 = ansA3.getText().toString();
                optB3 = ansB3.getText().toString();
                optC3 = ansC3.getText().toString();
                optD3 = ansD3.getText().toString();
                c3 = correctAnswer3.getText().toString();

                q4 = question4.getText().toString();
                optA4 = ansA4.getText().toString();
                optB4 = ansB4.getText().toString();
                optC4 = ansC4.getText().toString();
                optD4 = ansD4.getText().toString();
                c4 = correctAnswer4.getText().toString();

                q5 = question5.getText().toString();
                optA5 = ansA5.getText().toString();
                optB5 = ansB5.getText().toString();
                optC5 = ansC5.getText().toString();
                optD5 = ansD5.getText().toString();
                c5 = correctAnswer5.getText().toString();

                q6 = question6.getText().toString();
                optA6 = ansA6.getText().toString();
                optB6 = ansB6.getText().toString();
                optC6 = ansC6.getText().toString();
                optD6 = ansD6.getText().toString();
                c6 = correctAnswer6.getText().toString();

                q7 = question7.getText().toString();
                optA7 = ansA7.getText().toString();
                optB7 = ansB7.getText().toString();
                optC7 = ansC7.getText().toString();
                optD7 = ansD7.getText().toString();
                c7 = correctAnswer7.getText().toString();

                q8 = question8.getText().toString();
                optA8 = ansA8.getText().toString();
                optB8 = ansB8.getText().toString();
                optC8 = ansC8.getText().toString();
                optD8 = ansD8.getText().toString();
                c8 = correctAnswer8.getText().toString();

                q9 = question9.getText().toString();
                optA9 = ansA9.getText().toString();
                optB9 = ansB9.getText().toString();
                optC9 = ansC9.getText().toString();
                optD9 = ansD9.getText().toString();
                c9 = correctAnswer9.getText().toString();

                q10 = question10.getText().toString();
                optA10 = ansA10.getText().toString();
                optB10 = ansB10.getText().toString();
                optC10 = ansC10.getText().toString();
                optD10 = ansD10.getText().toString();
                c10 = correctAnswer10.getText().toString();

                if (TextUtils.isEmpty(title))
                {
                    Toast.makeText(getApplicationContext(), "ادخل اسم الامتحان", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(q1))
                {
                    Toast.makeText(getApplicationContext(), "ادخل السؤال الاول", Toast.LENGTH_SHORT).show();
                    question1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optA1))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الاول للسؤال الاول", Toast.LENGTH_SHORT).show();
                    ansA1.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(optB1))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثانى للسؤال الاول", Toast.LENGTH_SHORT).show();
                    ansB1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optC1))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثالث للسؤال الاول", Toast.LENGTH_SHORT).show();
                    ansC1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optD1))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الرابع للسؤال الاول", Toast.LENGTH_SHORT).show();
                    ansD1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(c1))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الاجابه الصحيحه للسؤال الاول", Toast.LENGTH_SHORT).show();
                    correctAnswer1.requestFocus();
                    return;
                }
                if(mImageUri1 == null)
                {
                    Toast.makeText(getApplicationContext(), "قم بتغيير صورة السؤال الاول أولا او اختر نفس الصوره مرة اخرى", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(optA2))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الاول للسؤال الثانى", Toast.LENGTH_SHORT).show();
                    ansA2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optB2))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثانى للسؤال الثانى", Toast.LENGTH_SHORT).show();
                    ansB2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optC2))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثالث للسؤال الثانى", Toast.LENGTH_SHORT).show();
                    ansC2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optD2))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الرابع للسؤال الثانى", Toast.LENGTH_SHORT).show();
                    ansD2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(c2))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الاجابه الصحيحه للسؤال الثانى", Toast.LENGTH_SHORT).show();
                    correctAnswer2.requestFocus();
                    return;
                }
                if (mImageUri2 == null)
                {
                    Toast.makeText(getApplicationContext(), "قم بتغيير صورة السؤال الثانى أولا او اختر نفس الصوره مرة اخرى", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(optA3))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الاول للسؤال الثالث", Toast.LENGTH_SHORT).show();
                    ansA3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optB3))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثانى للسؤال الثالث", Toast.LENGTH_SHORT).show();
                    ansB3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optC3))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثالث للسؤال الثالث", Toast.LENGTH_SHORT).show();
                    ansC3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optD3))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الرابع للسؤال الثالث", Toast.LENGTH_SHORT).show();
                    ansD3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(c3))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الاجابه الصحيحه للسؤال الثالث", Toast.LENGTH_SHORT).show();
                    correctAnswer3.requestFocus();
                    return;
                }
                if (mImageUri3 == null)
                {
                    Toast.makeText(getApplicationContext(), "قم بتغيير صورة السؤال الثالث أولا او اختر نفس الصوره مرة اخرى", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(optA4))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الاول للسؤال الرابع", Toast.LENGTH_SHORT).show();
                    ansA4.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optB4))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثانى للسؤال الرابع", Toast.LENGTH_SHORT).show();
                    ansB4.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optC4))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثالث للسؤال الرابع", Toast.LENGTH_SHORT).show();
                    ansC4.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optD4))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الرابع للسؤال الرابع", Toast.LENGTH_SHORT).show();
                    ansD4.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(c4))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الاجابه الصحيحه للسؤال الرابع", Toast.LENGTH_SHORT).show();
                    correctAnswer4.requestFocus();
                    return;
                }
                if (mImageUri4 == null)
                {
                    Toast.makeText(getApplicationContext(), "قم بتغيير صورة السؤال الرابع أولا او اختر نفس الصوره مرة اخرى", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(optA5))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الاول للسؤال الخامس", Toast.LENGTH_SHORT).show();
                    ansA5.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optB5))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثانى للسؤال الخامس", Toast.LENGTH_SHORT).show();
                    ansB5.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optC5))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثالث للسؤال الخامس", Toast.LENGTH_SHORT).show();
                    ansC5.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optD5))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الرابع للسؤال الخامس", Toast.LENGTH_SHORT).show();
                    ansD5.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(c5))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الاجابه الصحيحه للسؤال الخامس", Toast.LENGTH_SHORT).show();
                    correctAnswer5.requestFocus();
                    return;
                }
                if (mImageUri5 == null)
                {
                    Toast.makeText(getApplicationContext(), "قم بتغيير صورة السؤال الخامس أولا او اختر نفس الصوره مرة اخرى", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(optA6))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الاول للسؤال السادس", Toast.LENGTH_SHORT).show();
                    ansA6.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optB6))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثانى للسؤال السادس", Toast.LENGTH_SHORT).show();
                    ansB6.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optC6))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثالث للسؤال السادس", Toast.LENGTH_SHORT).show();
                    ansC6.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optD6))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الرابع للسؤال السادس", Toast.LENGTH_SHORT).show();
                    ansD6.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(c6))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الاجابه الصحيحه للسؤال السادس", Toast.LENGTH_SHORT).show();
                    correctAnswer6.requestFocus();
                    return;
                }
                if (mImageUri6 == null)
                {
                    Toast.makeText(getApplicationContext(), "قم بتغيير صورة السؤال السادس أولا او اختر نفس الصوره مرة اخرى", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(optA7))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الاول للسؤال السابع", Toast.LENGTH_SHORT).show();
                    ansA7.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optB7))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثانى للسؤال السابع", Toast.LENGTH_SHORT).show();
                    ansB7.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optC7))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثالث للسؤال السابع", Toast.LENGTH_SHORT).show();
                    ansC7.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optD7))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الرابع للسؤال السابع", Toast.LENGTH_SHORT).show();
                    ansD7.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(c7))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الاجابه الصحيحه للسؤال السابع", Toast.LENGTH_SHORT).show();
                    correctAnswer7.requestFocus();
                    return;
                }
                if (mImageUri7 == null)
                {
                    Toast.makeText(getApplicationContext(), "قم بتغيير صورة السؤال السابع أولا او اختر نفس الصوره مرة اخرى", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(optA8))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الاول للسؤال الثامن", Toast.LENGTH_SHORT).show();
                    ansA8.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optB8))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثانى للسؤال الثامن", Toast.LENGTH_SHORT).show();
                    ansB8.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optC8))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثالث للسؤال الثامن", Toast.LENGTH_SHORT).show();
                    ansC8.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optD8))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الرابع للسؤال الثامن", Toast.LENGTH_SHORT).show();
                    ansD8.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(c8))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الاجابه الصحيحه للسؤال الثامن", Toast.LENGTH_SHORT).show();
                    correctAnswer8.requestFocus();
                    return;
                }
                if (mImageUri8 == null)
                {
                    Toast.makeText(getApplicationContext(), "قم بتغيير صورة السؤال الثامن أولا او اختر نفس الصوره مرة اخرى", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(optA9))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الاول للسؤال التاسع", Toast.LENGTH_SHORT).show();
                    ansA9.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optB9))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثانى للسؤال التاسع", Toast.LENGTH_SHORT).show();
                    ansB9.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optC9))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثالث للسؤال التاسع", Toast.LENGTH_SHORT).show();
                    ansC9.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optD9))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الرابع للسؤال التاسع", Toast.LENGTH_SHORT).show();
                    ansD9.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(c9))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الاجابه الصحيحه للسؤال التاسع", Toast.LENGTH_SHORT).show();
                    correctAnswer9.requestFocus();
                    return;
                }
                if (mImageUri9 == null)
                {
                    Toast.makeText(getApplicationContext(), "قم بتغيير صورة السؤال التاسع أولا او اختر نفس الصوره مرة اخرى", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(optA10))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الاول للسؤال الثالث", Toast.LENGTH_SHORT).show();
                    ansA3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optB10))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثانى للسؤال العاشر", Toast.LENGTH_SHORT).show();
                    ansB10.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optC10))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الثالث للسؤال العاشر", Toast.LENGTH_SHORT).show();
                    ansC10.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(optD10))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الخيار الرابع للسؤال العاشر", Toast.LENGTH_SHORT).show();
                    ansD10.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(c10))
                {
                    Toast.makeText(getApplicationContext(), "ادخل الاجابه الصحيحه للسؤال العاشر", Toast.LENGTH_SHORT).show();
                    correctAnswer10.requestFocus();
                    return;
                }
                if (mImageUri10 == null)
                {
                    Toast.makeText(getApplicationContext(), "قم بتغيير صورة السؤال العاشر أولا او اختر نفس الصوره مرة اخرى", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog = new ProgressDialog(ViewExamActivity.this);
                progressDialog.setMessage("برجاء الانتظار ...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                progressDialog.setCancelable(false);

                EditExam(title
                        ,q1,optA1, optB1, optC1, optD1, c1, imageurl1,
                        q2, optA2, optB2, optC2, optD2, c2, imageurl2,
                        q3, optA3, optB3, optC3, optD3, c3, imageurl3,
                        q4, optA4, optB4, optC4, optD4, c4, imageurl4,
                        q5, optA5, optB5, optC5, optD5, c5, imageurl5,
                        q6, optA6, optB6, optC6, optD6, c6, imageurl6,
                        q7, optA7, optB7, optC7, optD7, c7, imageurl7,
                        q8, optA8, optB8, optC8, optD8, c8, imageurl8,
                        q9, optA9, optB9, optC9, optD9, c9, imageurl9,
                        q10, optA10, optB10, optC10, optD10, c10, imageurl10);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            mImageUri1 = data.getData();
            img1.setImageURI(mImageUri1);
        }

        if (requestCode == GALLERY_REQUEST2 && resultCode == RESULT_OK) {
            mImageUri2 = data.getData();
            img2.setImageURI(mImageUri2);
        }

        if (requestCode == GALLERY_REQUEST3 && resultCode == RESULT_OK) {
            mImageUri3 = data.getData();
            img3.setImageURI(mImageUri3);
        }

        if (requestCode == GALLERY_REQUEST4 && resultCode == RESULT_OK) {
            mImageUri4 = data.getData();
            img4.setImageURI(mImageUri4);
        }

        if (requestCode == GALLERY_REQUEST5 && resultCode == RESULT_OK) {
            mImageUri5 = data.getData();
            img5.setImageURI(mImageUri5);
        }

        if (requestCode == GALLERY_REQUEST6 && resultCode == RESULT_OK) {
            mImageUri6 = data.getData();
            img6.setImageURI(mImageUri6);
        }

        if (requestCode == GALLERY_REQUEST7 && resultCode == RESULT_OK) {
            mImageUri7 = data.getData();
            img7.setImageURI(mImageUri7);
        }

        if (requestCode == GALLERY_REQUEST8 && resultCode == RESULT_OK) {
            mImageUri8 = data.getData();
            img8.setImageURI(mImageUri8);
        }

        if (requestCode == GALLERY_REQUEST9 && resultCode == RESULT_OK) {
            mImageUri9 = data.getData();
            img9.setImageURI(mImageUri9);
        }

        if (requestCode == GALLERY_REQUEST10 && resultCode == RESULT_OK) {
            mImageUri10 = data.getData();
            img10.setImageURI(mImageUri10);
        }
    }

    private void uploadImage1()
    {
        UploadTask uploadTask;

        final StorageReference ref = storageReference.child("images/" + mImageUri1.getLastPathSegment());

        uploadTask = ref.putFile(mImageUri1);

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

                imageurl1 = downloadUri.toString();

                databaseReference.child("Exams").child(getuId()).child(k).child("imageurl1").setValue(imageurl1);
            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadImage2()
    {
        UploadTask uploadTask;

        final StorageReference ref = storageReference.child("images/" + mImageUri2.getLastPathSegment());

        uploadTask = ref.putFile(mImageUri2);

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

                imageurl2 = downloadUri.toString();

                databaseReference.child("Exams").child(getuId()).child(k).child("imageurl2").setValue(imageurl2);

            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadImage3()
    {
        UploadTask uploadTask;

        final StorageReference ref = storageReference.child("images/" + mImageUri3.getLastPathSegment());

        uploadTask = ref.putFile(mImageUri3);

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

                imageurl3 = downloadUri.toString();

                databaseReference.child("Exams").child(getuId()).child(k).child("imageurl3").setValue(imageurl3);

            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadImage4()
    {
        UploadTask uploadTask;

        final StorageReference ref = storageReference.child("images/" + mImageUri4.getLastPathSegment());

        uploadTask = ref.putFile(mImageUri4);

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

                imageurl4 = downloadUri.toString();

                databaseReference.child("Exams").child(getuId()).child(k).child("imageurl4").setValue(imageurl4);

            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadImage5()
    {
        UploadTask uploadTask;

        final StorageReference ref = storageReference.child("images/" + mImageUri5.getLastPathSegment());

        uploadTask = ref.putFile(mImageUri5);

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

                imageurl5 = downloadUri.toString();

                databaseReference.child("Exams").child(getuId()).child(k).child("imageurl5").setValue(imageurl5);

            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadImage6()
    {
        UploadTask uploadTask;

        final StorageReference ref = storageReference.child("images/" + mImageUri6.getLastPathSegment());

        uploadTask = ref.putFile(mImageUri6);

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

                imageurl6 = downloadUri.toString();

                databaseReference.child("Exams").child(getuId()).child(k).child("imageurl6").setValue(imageurl6);

            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadImage7()
    {
        UploadTask uploadTask;

        final StorageReference ref = storageReference.child("images/" + mImageUri7.getLastPathSegment());

        uploadTask = ref.putFile(mImageUri7);

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

                imageurl7 = downloadUri.toString();

                databaseReference.child("Exams").child(getuId()).child(k).child("imageurl7").setValue(imageurl7);

            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadImage8()
    {
        UploadTask uploadTask;

        final StorageReference ref = storageReference.child("images/" + mImageUri8.getLastPathSegment());

        uploadTask = ref.putFile(mImageUri8);

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

                imageurl8 = downloadUri.toString();

                databaseReference.child("Exams").child(getuId()).child(k).child("imageurl8").setValue(imageurl8);

            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadImage9()
    {
        UploadTask uploadTask;

        final StorageReference ref = storageReference.child("images/" + mImageUri9.getLastPathSegment());

        uploadTask = ref.putFile(mImageUri9);

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

                imageurl9 = downloadUri.toString();

                databaseReference.child("Exams").child(getuId()).child(k).child("imageurl9").setValue(imageurl9);

            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadImage10()
    {
        UploadTask uploadTask;

        final StorageReference ref = storageReference.child("images/" + mImageUri10.getLastPathSegment());

        uploadTask = ref.putFile(mImageUri10);

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

                imageurl10 = downloadUri.toString();

                databaseReference.child("Exams").child(getuId()).child(k).child("imageurl10").setValue(imageurl10);

            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void EditExam(String title,
                          String question1,String optionA1,String optionB1,String optionC1,String optionD1,String correct_answer1,String imageurl1,
                          String question2,String optionA2,String optionB2,String optionC2,String optionD2,String correct_answer2,String imageurl2,
                          String question3,String optionA3,String optionB3,String optionC3,String optionD3,String correct_answer3,String imageurl3,
                          String question4,String optionA4,String optionB4,String optionC4,String optionD4,String correct_answer4,String imageurl4,
                          String question5,String optionA5,String optionB5,String optionC5,String optionD5,String correct_answer5,String imageurl5,
                          String question6,String optionA6,String optionB6,String optionC6,String optionD6,String correct_answer6,String imageurl6,
                          String question7,String optionA7,String optionB7,String optionC7,String optionD7,String correct_answer7,String imageurl7,
                          String question8,String optionA8,String optionB8,String optionC8,String optionD8,String correct_answer8,String imageurl8,
                          String question9,String optionA9,String optionB9,String optionC9,String optionD9,String correct_answer9,String imageurl9,
                          String question10,String optionA10,String optionB10,String optionC10,String optionD10,String correct_answer10,String imageurl10)
    {
        QuestionModel questionModel = new QuestionModel(getuId(),title,
                question1, optionA1, optionB1, optionC1, optionD1, correct_answer1, imageurl1,
                question2, optionA2, optionB2, optionC2, optionD2, correct_answer2, imageurl2,
                question3, optionA3, optionB3, optionC3, optionD3, correct_answer3, imageurl3,
                question4, optionA4, optionB4, optionC4, optionD4, correct_answer4, imageurl4,
                question5, optionA5, optionB5, optionC5, optionD5, correct_answer5, imageurl5,
                question6, optionA6, optionB6, optionC6, optionD6, correct_answer6, imageurl6,
                question7, optionA7, optionB7, optionC7, optionD7, correct_answer7, imageurl7,
                question8, optionA8, optionB8, optionC8, optionD8, correct_answer8, imageurl8,
                question9, optionA9, optionB9, optionC9, optionD9, correct_answer9, imageurl9,
                question10, optionA10, optionB10, optionC10, optionD10, correct_answer10, imageurl10);

        databaseReference.child("Exams").child(getuId()).child(k).setValue(questionModel);
        uploadImage1();
        uploadImage2();
        uploadImage3();
        uploadImage4();
        uploadImage5();
        uploadImage6();
        uploadImage7();
        uploadImage8();
        uploadImage9();
        uploadImage10();
        startActivity(new Intent(getApplicationContext(), TeacherMainActivity.class));
        finish();
    }

    String getuId() {
        return FirebaseAuth.getInstance().getUid();
    }
}