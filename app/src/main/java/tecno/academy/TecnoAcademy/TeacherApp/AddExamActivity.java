package tecno.academy.TecnoAcademy.TeacherApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import tecno.academy.TecnoAcademy.Models.QuestionModel;
import tecno.academy.TecnoAcademy.R;

public class AddExamActivity extends AppCompatActivity
{
    ImageView imageView1,imageView2,imageView3
            ,imageView4,imageView5,imageView6
            ,imageView7,imageView8,imageView9
            ,imageView10;

    String imageUrl1,imageUrl2,imageUrl3,imageUrl4,imageUrl5,imageUrl6,imageUrl7,imageUrl8,imageUrl9,imageUrl10;

    Uri photoPath1,photoPath2,photoPath3,photoPath4,photoPath5,photoPath6,photoPath7,photoPath8,photoPath9,photoPath10;

    EditText title;
    EditText question1,option1,option2,option3,option4,correct_answer1;
    EditText question2,option21,option22,option23,option24,correct_answer2;
    EditText question3,option31,option32,option33,option34,correct_answer3;
    EditText question4,option41,option42,option43,option44,correct_answer4;
    EditText question5,option51,option52,option53,option54,correct_answer5;
    EditText question6,option61,option62,option63,option64,correct_answer6;
    EditText question7,option71,option72,option73,option74,correct_answer7;
    EditText question8,option81,option82,option83,option84,correct_answer8;
    EditText question9,option91,option92,option93,option94,correct_answer9;
    EditText question10,option101,option102,option103,option104,correct_answer10;
    Button create_exam;

    String exam_title;
    String ques1,o1,o2,o3,o4,ca1;
    String ques2,o21,o22,o23,o24,ca2;
    String ques3,o31,o32,o33,o34,ca3;
    String ques4,o41,o42,o43,o44,ca4;
    String ques5,o51,o52,o53,o54,ca5;
    String ques6,o61,o62,o63,o64,ca6;
    String ques7,o71,o72,o73,o74,ca7;
    String ques8,o81,o82,o83,o84,ca8;
    String ques9,o91,o92,o93,o94,ca9;
    String ques10,o101,o102,o103,o104,ca10;
    int exam_score;

    EditText score;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);

        imageView1 = findViewById(R.id.image1);
        imageView2 = findViewById(R.id.image2);
        imageView3 = findViewById(R.id.image3);
        imageView4 = findViewById(R.id.image4);
        imageView5 = findViewById(R.id.image5);
        imageView6 = findViewById(R.id.image6);
        imageView7 = findViewById(R.id.image7);
        imageView8 = findViewById(R.id.image8);
        imageView9 = findViewById(R.id.image9);
        imageView10 = findViewById(R.id.image10);

        score = findViewById(R.id.score);

        title = findViewById(R.id.title);
        question1 = findViewById(R.id.question1);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        correct_answer1 = findViewById(R.id.correct_answer);

        question2 = findViewById(R.id.question2);
        option21 = findViewById(R.id.option21);
        option22 = findViewById(R.id.option22);
        option23 = findViewById(R.id.option23);
        option24 = findViewById(R.id.option24);
        correct_answer2 = findViewById(R.id.correct_answer2);

        question3 = findViewById(R.id.question3);
        option31 = findViewById(R.id.option31);
        option32 = findViewById(R.id.option32);
        option33 = findViewById(R.id.option33);
        option34 = findViewById(R.id.option34);
        correct_answer3 = findViewById(R.id.correct_answer3);

        question4 = findViewById(R.id.question4);
        option41 = findViewById(R.id.option41);
        option42 = findViewById(R.id.option42);
        option43 = findViewById(R.id.option43);
        option44 = findViewById(R.id.option44);
        correct_answer4 = findViewById(R.id.correct_answer4);

        question5 = findViewById(R.id.question5);
        option51 = findViewById(R.id.option51);
        option52 = findViewById(R.id.option52);
        option53 = findViewById(R.id.option53);
        option54 = findViewById(R.id.option54);
        correct_answer5 = findViewById(R.id.correct_answer5);

        question6 = findViewById(R.id.question6);
        option61 = findViewById(R.id.option61);
        option62 = findViewById(R.id.option62);
        option63 = findViewById(R.id.option63);
        option64 = findViewById(R.id.option64);
        correct_answer6 = findViewById(R.id.correct_answer6);

        question7 = findViewById(R.id.question7);
        option71 = findViewById(R.id.option71);
        option72 = findViewById(R.id.option72);
        option73 = findViewById(R.id.option73);
        option74 = findViewById(R.id.option74);
        correct_answer7 = findViewById(R.id.correct_answer7);

        question8 = findViewById(R.id.question8);
        option81 = findViewById(R.id.option81);
        option82 = findViewById(R.id.option82);
        option83 = findViewById(R.id.option83);
        option84 = findViewById(R.id.option84);
        correct_answer8 = findViewById(R.id.correct_answer8);

        question9 = findViewById(R.id.question9);
        option91 = findViewById(R.id.option91);
        option92 = findViewById(R.id.option92);
        option93 = findViewById(R.id.option93);
        option94 = findViewById(R.id.option94);
        correct_answer9 = findViewById(R.id.correct_answer9);

        question10 = findViewById(R.id.question10);
        option101 = findViewById(R.id.option101);
        option102 = findViewById(R.id.option102);
        option103 = findViewById(R.id.option103);
        option104 = findViewById(R.id.option104);
        correct_answer10 = findViewById(R.id.correct_answer10);

        create_exam = findViewById(R.id.create_exam);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference().child("images");

        imageView1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                        .setAspectRatio(1, 1)
                        .start(AddExamActivity.this);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                        .setAspectRatio(1, 1)
                        .start(AddExamActivity.this);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View view)
        {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                    .setAspectRatio(1, 1)
                    .start(AddExamActivity.this);
        }
        });

        imageView4.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View view)
        {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                    .setAspectRatio(1, 1)
                    .start(AddExamActivity.this);
        }
        });

        imageView5.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View view)
        {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                    .setAspectRatio(1, 1)
                    .start(AddExamActivity.this);
           }
        });

        imageView6.setOnClickListener(new View.OnClickListener()
        {
        @Override
        public void onClick(View view)
        {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                    .setAspectRatio(1, 1)
                    .start(AddExamActivity.this);
               }
        });
        imageView7.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                    .setAspectRatio(1, 1)
                    .start(AddExamActivity.this);
        }
    });
    imageView8.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                    .setAspectRatio(1, 1)
                    .start(AddExamActivity.this);
        }
    });
    imageView9.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                    .setAspectRatio(1, 1)
                    .start(AddExamActivity.this);
        }
    });
    imageView10.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                    .setAspectRatio(1, 1)
                    .start(AddExamActivity.this);
        }
    });

        create_exam.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                exam_title = title.getText().toString();
                ques1 = question1.getText().toString();
                o1 = option1.getText().toString();
                o2 = option2.getText().toString();
                o3 = option3.getText().toString();
                o4 = option4.getText().toString();
                ca1 = correct_answer1.getText().toString();

                ques2 = question2.getText().toString();
                o21 = option21.getText().toString();
                o22 = option22.getText().toString();
                o23 = option23.getText().toString();
                o24 = option4.getText().toString();
                ca2 = correct_answer2.getText().toString();

                ques3 = question3.getText().toString();
                o31 = option31.getText().toString();
                o32 = option32.getText().toString();
                o33 = option33.getText().toString();
                o34 = option4.getText().toString();
                ca3 = correct_answer3.getText().toString();

                ques4 = question4.getText().toString();
                o41 = option41.getText().toString();
                o42 = option22.getText().toString();
                o43 = option43.getText().toString();
                o44 = option4.getText().toString();
                ca4 = correct_answer4.getText().toString();

                ques5 = question5.getText().toString();
                o51 = option51.getText().toString();
                o52 = option52.getText().toString();
                o53 = option53.getText().toString();
                o54 = option4.getText().toString();
                ca5 = correct_answer5.getText().toString();

                ques6 = question6.getText().toString();
                o61 = option61.getText().toString();
                o62 = option62.getText().toString();
                o63 = option63.getText().toString();
                o64 = option64.getText().toString();
                ca6 = correct_answer6.getText().toString();

                ques7 = question7.getText().toString();
                o71 = option71.getText().toString();
                o72 = option72.getText().toString();
                o73 = option73.getText().toString();
                o74 = option74.getText().toString();
                ca7 = correct_answer7.getText().toString();

                ques8 = question8.getText().toString();
                o81 = option81.getText().toString();
                o82 = option82.getText().toString();
                o83 = option83.getText().toString();
                o84 = option84.getText().toString();
                ca8 = correct_answer8.getText().toString();

                ques9 = question9.getText().toString();
                o91 = option91.getText().toString();
                o92 = option92.getText().toString();
                o93 = option93.getText().toString();
                o94 = option94.getText().toString();
                ca9 = correct_answer9.getText().toString();

                ques10 = question10.getText().toString();
                o101 = option101.getText().toString();
                o102 = option102.getText().toString();
                o103 = option103.getText().toString();
                o104 = option104.getText().toString();
                ca10 = correct_answer10.getText().toString();

                if (TextUtils.isEmpty(exam_title))
                {
                    Toast.makeText(getApplicationContext(), "Enter title", Toast.LENGTH_SHORT).show();
                    title.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques1))
                {
                    Toast.makeText(getApplicationContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o1))
                {
                    Toast.makeText(getApplicationContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o2))
                {
                    Toast.makeText(getApplicationContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o3))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o4))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option4.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca1))
                {
                    Toast.makeText(getApplicationContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer1.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(ques2))
                {
                    Toast.makeText(getApplicationContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o21))
                {
                    Toast.makeText(getApplicationContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option21.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o22))
                {
                    Toast.makeText(getApplicationContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option22.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o23))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option23.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o24))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option24.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca2))
                {
                    Toast.makeText(getApplicationContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques3))
                {
                    Toast.makeText(getApplicationContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o31))
                {
                    Toast.makeText(getApplicationContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option31.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o32))
                {
                    Toast.makeText(getApplicationContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option32.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o33))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option33.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o34))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option34.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca3))
                {
                    Toast.makeText(getApplicationContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques4))
                {
                    Toast.makeText(getApplicationContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question4.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o41))
                {
                    Toast.makeText(getApplicationContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option41.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o42))
                {
                    Toast.makeText(getApplicationContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option42.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o43))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option43.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o44))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option44.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca4))
                {
                    Toast.makeText(getApplicationContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer4.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques5))
                {
                    Toast.makeText(getApplicationContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question5.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o51))
                {
                    Toast.makeText(getApplicationContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option51.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o52))
                {
                    Toast.makeText(getApplicationContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option52.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o53))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option53.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o54))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option54.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca5))
                {
                    Toast.makeText(getApplicationContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer5.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(ques6))
                {
                    Toast.makeText(getApplicationContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question6.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o61))
                {
                    Toast.makeText(getApplicationContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option61.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o62))
                {
                    Toast.makeText(getApplicationContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option62.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o63))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option63.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o64))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option64.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca6))
                {
                    Toast.makeText(getApplicationContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer6.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(ques7))
                {
                    Toast.makeText(getApplicationContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question7.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o71))
                {
                    Toast.makeText(getApplicationContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option71.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o72))
                {
                    Toast.makeText(getApplicationContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option72.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o73))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option73.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o74))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option74.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca7))
                {
                    Toast.makeText(getApplicationContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer7.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques8))
                {
                    Toast.makeText(getApplicationContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question8.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o81))
                {
                    Toast.makeText(getApplicationContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option81.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o82))
                {
                    Toast.makeText(getApplicationContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option82.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o83))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option83.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o84))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option84.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca8))
                {
                    Toast.makeText(getApplicationContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer8.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(ques9))
                {
                    Toast.makeText(getApplicationContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question9.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o91))
                {
                    Toast.makeText(getApplicationContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option91.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o92))
                {
                    Toast.makeText(getApplicationContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option92.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o93))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option93.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o94))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option94.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca9))
                {
                    Toast.makeText(getApplicationContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer9.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques10))
                {
                    Toast.makeText(getApplicationContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question10.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o101))
                {
                    Toast.makeText(getApplicationContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option101.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o102))
                {
                    Toast.makeText(getApplicationContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option102.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o103))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option103.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o104))
                {
                    Toast.makeText(getApplicationContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option104.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca10))
                {
                    Toast.makeText(getApplicationContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer10.requestFocus();
                    return;
                }

                addExam(exam_score,exam_title
                        ,ques1,o1,o2,o3,o4,ca1,imageUrl1
                        ,ques2,o21,o22,o23,o24,ca2,imageUrl2
                        ,ques3,o31,o32,o33,o34,ca3,imageUrl3
                        ,ques4,o41,o42,o43,o44,ca4,imageUrl4
                        ,ques5,o51,o52,o53,o54,ca5,imageUrl5
                        ,ques6,o61,o62,o63,o64,ca6,imageUrl6
                        ,ques7,o71,o72,o73,o74,ca7,imageUrl7
                        ,ques8,o81,o82,o83,o84,ca8,imageUrl8
                        ,ques9,o91,o92,o93,o94,ca9,imageUrl9
                        ,ques10,o101,o102,o103,o104,ca10,imageUrl10);

                /*uploadImage1();
                uploadImage2();
                uploadImage3();
                uploadImage4();
                uploadImage5();
                uploadImage6();
                uploadImage7();
                uploadImage8();
                uploadImage9();
                uploadImage10();*/

            }
        });
    }

    private void addExam(int exam_score,String exam_title
            ,String ques1, String o1, String o2,String o3,String o4, String ca1,String image1
            ,String ques2,String o21,String o22,String o23,String o24,String ca2,String image2
            ,String ques3,String o31,String o32,String o33,String o34,String ca3,String image3
            ,String ques4,String o41,String o42,String o43,String o44,String ca4,String image4
            ,String ques5,String o51,String o52,String o53,String o54,String ca5,String image5
            ,String ques6,String o61,String o62,String o63,String o64,String ca6,String image6
            ,String ques7,String o71,String o72,String o73,String o74,String ca7,String image7
            ,String ques8,String o81,String o82,String o83,String o84,String ca8,String image8
            ,String ques9,String o91,String o92,String o93,String o94,String ca9,String image9
            ,String ques10,String o101,String o102,String o103,String o104,String ca10,String image10)
    {
        QuestionModel examModel = new QuestionModel(exam_score,exam_title
                ,ques1,o1,o2,o3,o4,ca1,image1
                ,ques2,o21,o22,o23,o24,ca2,image2
                ,ques3,o31,o32,o33,o34,ca3,image3
                ,ques4,o41,o42,o43,o44,ca4,image4
                ,ques5,o51,o52,o53,o54,ca5,image5
                ,ques6,o61,o62,o63,o64,ca6,image6
                ,ques7,o71,o72,o73,o74,ca7,image7
                ,ques8,o81,o82,o83,o84,ca8,image8
                ,ques9,o91,o92,o93,o94,ca9,image9
                ,ques10,o101,o102,o103,o104,ca10,image10);

        String k = databaseReference.child("Exams").child(getuId()).push().getKey();
        databaseReference.child("Exams").child(getuId()).child(k).setValue(examModel);

        Intent i = new Intent(getApplicationContext(), TeacherMainActivity.class);
        startActivity(i);
        finish();
    }

    String getuId()
    {
        return FirebaseAuth.getInstance().getUid();
    }

    /*public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        {
            CropImage.ActivityResult result1 = CropImage.getActivityResult(data);
            CropImage.ActivityResult result2 = CropImage.getActivityResult(data);
            CropImage.ActivityResult result3 = CropImage.getActivityResult(data);
            CropImage.ActivityResult result4 = CropImage.getActivityResult(data);
            CropImage.ActivityResult result5 = CropImage.getActivityResult(data);
            CropImage.ActivityResult result6 = CropImage.getActivityResult(data);
            CropImage.ActivityResult result7 = CropImage.getActivityResult(data);
            CropImage.ActivityResult result8 = CropImage.getActivityResult(data);
            CropImage.ActivityResult result9 = CropImage.getActivityResult(data);
            CropImage.ActivityResult result10 = CropImage.getActivityResult(data);

            if (resultCode == Activity.RESULT_OK)
            {
                if (result1 != null) {
                    photoPath1 = result1.getUri();
                    Picasso.get()
                            .load(photoPath1)
                            .placeholder(R.drawable.addphoto)
                            .error(R.drawable.addphoto)
                            .into(imageView1);

                }else if(result2 != null)
                {
                    photoPath2 = result2.getUri();

                    Picasso.get()
                            .load(photoPath2)
                            .placeholder(R.drawable.addphoto)
                            .error(R.drawable.addphoto)
                            .into(imageView2);
                }else if(result3 != null)
                {
                    photoPath3 = result3.getUri();

                    Picasso.get()
                            .load(photoPath3)
                            .placeholder(R.drawable.addphoto)
                            .error(R.drawable.addphoto)
                            .into(imageView3);
                }else if(result4 != null)
                {
                    photoPath4 = result4.getUri();

                    Picasso.get()
                            .load(photoPath4)
                            .placeholder(R.drawable.addphoto)
                            .error(R.drawable.addphoto)
                            .into(imageView4);
                }else if(result5 != null)
                {
                    photoPath5 = result5.getUri();

                    Picasso.get()
                            .load(photoPath5)
                            .placeholder(R.drawable.addphoto)
                            .error(R.drawable.addphoto)
                            .into(imageView5);
                }else if(result6 != null)
                {
                    photoPath6 = result6.getUri();

                    Picasso.get()
                            .load(photoPath6)
                            .placeholder(R.drawable.addphoto)
                            .error(R.drawable.addphoto)
                            .into(imageView6);
                }else if(result7 != null)
                {
                    photoPath7 = result7.getUri();

                    Picasso.get()
                            .load(photoPath7)
                            .placeholder(R.drawable.addphoto)
                            .error(R.drawable.addphoto)
                            .into(imageView7);
                }else if(result8 != null)
                {
                    photoPath8 = result8.getUri();

                    Picasso.get()
                            .load(photoPath8)
                            .placeholder(R.drawable.addphoto)
                            .error(R.drawable.addphoto)
                            .into(imageView8);
                }else if(result9 != null)
                {
                    photoPath9 = result9.getUri();

                    Picasso.get()
                            .load(photoPath9)
                            .placeholder(R.drawable.addphoto)
                            .error(R.drawable.addphoto)
                            .into(imageView9);
                }else if(result10 != null)
                {
                    photoPath10 = result10.getUri();

                    Picasso.get()
                            .load(photoPath10)
                            .placeholder(R.drawable.addphoto)
                            .error(R.drawable.addphoto)
                            .into(imageView10);
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error1 = result1.getError();
                Exception error2 = result2.getError();
                Exception error3 = result3.getError();
                Exception error4 = result4.getError();
                Exception error5 = result5.getError();
                Exception error6 = result6.getError();
                Exception error7 = result7.getError();
                Exception error8 = result8.getError();
                Exception error9 = result9.getError();
                Exception error10 = result10.getError();

            }
        }
    }

    private void uploadImage1()
    {
        UploadTask uploadTask;

        final StorageReference ref = storageReference.child("images/" + photoPath1.getLastPathSegment());

        uploadTask = ref.putFile(photoPath1);

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

                 imageUrl1 = downloadUri.toString();

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

        final StorageReference ref = storageReference.child("images/" + photoPath2.getLastPathSegment());

        uploadTask = ref.putFile(photoPath2);

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

                imageUrl2 = downloadUri.toString();


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

        final StorageReference ref = storageReference.child("images/" + photoPath3.getLastPathSegment());

        uploadTask = ref.putFile(photoPath3);

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

                imageUrl3 = downloadUri.toString();


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

        final StorageReference ref = storageReference.child("images/" + photoPath4.getLastPathSegment());

        uploadTask = ref.putFile(photoPath4);

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

                imageUrl4 = downloadUri.toString();


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

        final StorageReference ref = storageReference.child("images/" + photoPath5.getLastPathSegment());

        uploadTask = ref.putFile(photoPath5);

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

                imageUrl5 = downloadUri.toString();

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

        final StorageReference ref = storageReference.child("images/" + photoPath6.getLastPathSegment());

        uploadTask = ref.putFile(photoPath6);

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

                imageUrl6 = downloadUri.toString();


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

        final StorageReference ref = storageReference.child("images/" + photoPath7.getLastPathSegment());

        uploadTask = ref.putFile(photoPath7);

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

                imageUrl7 = downloadUri.toString();


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

        final StorageReference ref = storageReference.child("images/" + photoPath8.getLastPathSegment());

        uploadTask = ref.putFile(photoPath8);

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

                imageUrl8 = downloadUri.toString();


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

        final StorageReference ref = storageReference.child("images/" + photoPath9.getLastPathSegment());

        uploadTask = ref.putFile(photoPath9);

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

                imageUrl9 = downloadUri.toString();


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

        final StorageReference ref = storageReference.child("images/" + photoPath10.getLastPathSegment());

        uploadTask = ref.putFile(photoPath10);

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

                imageUrl10 = downloadUri.toString();

                addExam(exam_score,exam_title
                        ,ques1,o1,o2,o3,o4,ca1,imageUrl1
                        ,ques2,o21,o22,o23,o24,ca2,imageUrl2
                        ,ques3,o31,o32,o33,o34,ca3,imageUrl3
                        ,ques4,o41,o42,o43,o44,ca4,imageUrl4
                        ,ques5,o51,o52,o53,o54,ca5,imageUrl5
                        ,ques6,o61,o62,o63,o64,ca6,imageUrl6
                        ,ques7,o71,o72,o73,o74,ca7,imageUrl7
                        ,ques8,o81,o82,o83,o84,ca8,imageUrl8
                        ,ques9,o91,o92,o93,o94,ca9,imageUrl9
                        ,ques10,o101,o102,o103,o104,ca10,imageUrl10);

            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}