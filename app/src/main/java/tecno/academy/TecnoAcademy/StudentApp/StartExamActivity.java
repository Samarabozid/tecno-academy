package tecno.academy.TecnoAcademy.StudentApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import tecno.academy.TecnoAcademy.Models.QuestionModel;
import tecno.academy.TecnoAcademy.R;

public class StartExamActivity extends AppCompatActivity
{
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    TextView score;
    TextView quiz_title,question1,question2,question3,question4,question5,
            question6,question7,question8,question9,question10,
            correctAnswer1,correctAnswer2,correctAnswer3,correctAnswer4,correctAnswer5,
            correctAnswer6,correctAnswer7,correctAnswer8,correctAnswer9,correctAnswer10;

    RadioGroup radioGroup1,radioGroup2,radioGroup3,radioGroup4,radioGroup5,
            radioGroup6,radioGroup7,radioGroup8,radioGroup9,radioGroup10;


    RadioButton ansA1,ansB1,ansC1,ansD1
            ,ansA2,ansB2,ansC2,ansD2
            ,ansA3,ansB3,ansC3,ansD3
            ,ansA4,ansB4,ansC4,ansD4
            ,ansA5,ansB5,ansC5,ansD5
            ,ansA6,ansB6,ansC6,ansD6
            ,ansA7,ansB7,ansC7,ansD7
            ,ansA8,ansB8,ansC8,ansD8
            ,ansA9,ansB9,ansC9,ansD9
            ,ansA10,ansB10,ansC10,ansD10;

    String title,q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,
            c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;

    int quizScore = 0;

    String optA1,optB1,optC1,optD1;
    String optA2,optB2,optC2,optD2;
    String optA3,optB3,optC3,optD3;
    String optA4,optB4,optC4,optD4;
    String optA5,optB5,optC5,optD5;
    String optA6,optB6,optC6,optD6;
    String optA7,optB7,optC7,optD7;
    String optA8,optB8,optC8,optD8;
    String optA9,optB9,optC9,optD9;
    String optA10,optB10,optC10,optD10;

    String k , teacher_id;

    Button done;
    CheckBox check;
    boolean isImageFitToScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exam);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference().child("images");

        done = findViewById(R.id.done);
        check = findViewById(R.id.checkbox);

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

        score = findViewById(R.id.score);

        Bundle b = getIntent().getExtras() ;
        k = b.getString("ex");
        teacher_id = b.getString("te");

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

        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup3 = findViewById(R.id.radioGroup3);
        radioGroup4 = findViewById(R.id.radioGroup4);
        radioGroup5 = findViewById(R.id.radioGroup5);
        radioGroup6 = findViewById(R.id.radioGroup6);
        radioGroup7 = findViewById(R.id.radioGroup7);
        radioGroup8 = findViewById(R.id.radioGroup8);
        radioGroup9 = findViewById(R.id.radioGroup9);
        radioGroup10 = findViewById(R.id.radioGroup10);

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

        img1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isImageFitToScreen)
                {
                    isImageFitToScreen=false;
                    img1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img1.setAdjustViewBounds(true);
                }else
                {
                    isImageFitToScreen=true;
                    img1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img1.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }
            }
        });

        img2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    img2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img2.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    img2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img2.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        img3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    img3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img3.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    img3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img3.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        img4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    img4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img4.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    img4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img4.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        img5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    img5.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img5.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    img5.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img5.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        img6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    img6.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img6.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    img6.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img6.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        img7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    img7.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img7.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    img7.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img7.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        img8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    img8.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img8.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    img8.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img8.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        img9.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    img9.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img9.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    img9.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img9.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        img10.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    img10.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    img10.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    img10.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    img10.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });

        databaseReference.child("Exams").child(teacher_id).child(k).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                QuestionModel questionModel = dataSnapshot.getValue(QuestionModel.class);

                title = questionModel.getEx_title();
                quiz_title.setText(title);

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



        onRadioButtonClicked();

        done.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (check.isChecked())
                { addScore(title,quizScore);
                }else
                    {
                        Toast.makeText(StartExamActivity.this, "تأكد من حفظ الاجابات أولا", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    private void addScore(String title,int quizScore)
    {
        QuestionModel questionModel = new QuestionModel(quizScore,title);
        databaseReference.child("Score").child(getuId()).child(k).setValue(questionModel);

        Intent i = new Intent(getApplicationContext(),StudentMainActivity.class);
        startActivity(i);
    }

    public void onRadioButtonClicked()
    {
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {
                    //question1
                    case R.id.ansA:
                        if(optA1.equals(c1))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else
                        {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansB:
                        if(optB1.equals(c1))
                        {
                            if( quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else
                        {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansC:
                        if(optC1.equals(c1))
                        {
                            if( quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else
                        {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansD:
                        if(optD1.equals(c1))
                        {
                            if( quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else
                        {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;
                }
            }
        });
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch(i) {
                    //question2
                    case R.id.ansA2:
                        if (optA2.equals(c2))
                        {
                            if( quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansB2:
                        if (optB2.equals(c2))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansC2:
                        if (optC2.equals(c2))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansD2:
                        if(optD2.equals(c2))
                        {
                            if( quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else
                        {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;
                }
            }
        });
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    //question3
                    case R.id.ansA3:
                        if (optA3.equals(c3))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else
                        {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansB3:
                        if (optB3.equals(c3))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansC3:
                        if (optC3.equals(c3))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else
                        {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;
                    case R.id.ansD3:
                        if(optD3.equals(c3))
                        {
                            if( quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else
                        {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;
                }
            }
        });
        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    //question4
                    case R.id.ansA4:
                        if (optA4.equals(c4))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                            score.setText(String.format("%d", quizScore));
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansB4:
                        if (optB4.equals(c4))
                        {
                            if( quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansC4:
                        if (optC4.equals(c4))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansD4:
                        if(optD4.equals(c4))
                        {
                            if( quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else
                        {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;
                }
            }
        });
        radioGroup5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    //question5
                    case R.id.ansA5:
                        if (optA5.equals(c5))
                        {
                            if (quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansB5:
                        if (optB5.equals(c5))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansC5:
                        if (optC5.equals(c5))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;
                    case R.id.ansD5:
                        if(optD6.equals(c5))
                        {
                            if( quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else
                        {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;
                }
            }
        });

        radioGroup6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    //question6
                    case R.id.ansA6:
                        if (optA6.equals(c6))
                        {
                            if (quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansB6:
                        if (optB6.equals(c6))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansC6:
                        if (optC6.equals(c6))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansD6:
                        if(optD6.equals(c6))
                        {
                            if( quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else
                        {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                }
            }
        });

        radioGroup7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    //question7
                    case R.id.ansA7:
                        if (optA7.equals(c7))
                        {
                            if (quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansB7:
                        if (optB7.equals(c7))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansC7:
                        if (optC7.equals(c7))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;
                    case R.id.ansD7:
                        if(optD7.equals(c7))
                        {
                            if( quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else
                        {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;
                }
            }
        });

        radioGroup8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    //question8
                    case R.id.ansA8:
                        if (optA8.equals(c8))
                        {
                            if (quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansB8:
                        if (optB8.equals(c8))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansC8:
                        if (optC8.equals(c8))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;
                    case R.id.ansD8:
                        if(optD8.equals(c8))
                        {
                            if( quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else
                        {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;
                }
            }
        });

        radioGroup9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    //question9
                    case R.id.ansA9:
                        if (optA9.equals(c9))
                        {
                            if (quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansB9:
                        if (optB9.equals(c9))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansC9:
                        if (optC9.equals(c9))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;
                    case R.id.ansD9:
                        if(optD9.equals(c9))
                        {
                            if( quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else
                        {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;
                }
            }
        });

        radioGroup10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                switch (i)
                {
                    //question10
                    case R.id.ansA10:
                        if (optA10.equals(c10))
                        {
                            if (quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansB10:
                        if (optB10.equals(c10))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;

                    case R.id.ansC10:
                        if (optC10.equals(c10))
                        {
                            if(quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;
                    case R.id.ansD10:
                        if(optD10.equals(c10))
                        {
                            if( quizScore < 10)
                            {
                                quizScore++;
                                score.setText(String.format("%d", quizScore));
                            }
                        } else
                        {
                            score.setText(String.format("%d", quizScore));
                        }
                        break;
                }
            }
        });
    }
    String getuId()
    {
        return FirebaseAuth.getInstance().getUid();
    }
}