package tecno.academy.TecnoAcademy.TeacherApp.TeacherExam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
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

import tecno.academy.TecnoAcademy.Models.QuestionModel;
import tecno.academy.TecnoAcademy.R;
import tecno.academy.TecnoAcademy.StudentApp.ExamPhotosActivity;
import tecno.academy.TecnoAcademy.StudentApp.StartExamActivity;
import tecno.academy.TecnoAcademy.TeacherApp.Fragments.MyExamFragment;
import tecno.academy.TecnoAcademy.TeacherApp.TeacherMainActivity;

public class ViewExamActivity extends AppCompatActivity
{
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

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

    String k;
    //String key;

    Button done,view;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exam);

        done = findViewById(R.id.done);
        view = findViewById(R.id.view_img);

        Bundle b = getIntent().getExtras() ;
        k = b.getString("ex");

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference().child("images");

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

        done.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TeacherMainActivity.class);
                startActivity(i);
            }
        });

        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(), ViewPhotosActivity.class);
                i.putExtra("kk",k);
                startActivity(i);
            }
        });

        databaseReference.child("Exams").child(getuId()).child(k).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                QuestionModel questionModel = dataSnapshot.getValue(QuestionModel.class);

                title=questionModel.getEx_title();
                q1 = questionModel.getQues1();
                q2 = questionModel.getQues2();
                q3 = questionModel.getQues3();
                q4 = questionModel.getQues4();
                q5 = questionModel.getQues5();
                q6 = questionModel.getQues1();
                q7 = questionModel.getQues2();
                q8 = questionModel.getQues3();
                q9 = questionModel.getQues4();
                q10 = questionModel.getQues5();

                c1 = questionModel.getCorrectAnswer1();
                correctAnswer1.setText(c1);
                c2 = questionModel.getCorrectAnswer2();
                correctAnswer2.setText(c2);
                c3 = questionModel.getCorrectAnswer3();
                correctAnswer3.setText(c3);
                c4 = questionModel.getCorrectAnswer4();
                correctAnswer4.setText(c4);
                c5 = questionModel.getCorrectAnswer5();
                correctAnswer5.setText(c5);
                c6 = questionModel.getCorrectAnswer6();
                correctAnswer6.setText(c6);
                c7 = questionModel.getCorrectAnswer7();
                correctAnswer7.setText(c7);
                c8 = questionModel.getCorrectAnswer8();
                correctAnswer8.setText(c8);
                c9 = questionModel.getCorrectAnswer9();
                correctAnswer9.setText(c9);
                c10 = questionModel.getCorrectAnswer10();
                correctAnswer10.setText(c10);

                optA1 = questionModel.getOpt11();
                optB1 = questionModel.getOpt12();
                optC1 = questionModel.getOpt13();
                optD1 = questionModel.getOpt14();

                optA2 = questionModel.getOpt21();
                optB2 = questionModel.getOpt22();
                optC2 = questionModel.getOpt23();
                optD2 = questionModel.getOpt24();

                optA3 = questionModel.getOpt33();
                optB3 = questionModel.getOpt32();
                optC3 = questionModel.getOpt33();
                optD3 = questionModel.getOpt34();

                optA4 = questionModel.getOpt41();
                optB4 = questionModel.getOpt42();
                optC4 = questionModel.getOpt43();
                optD4 = questionModel.getOpt44();

                optA5 = questionModel.getOpt51();
                optB5 = questionModel.getOpt52();
                optC5 = questionModel.getOpt53();
                optD5 = questionModel.getOpt54();

                optA6 = questionModel.getOpt61();
                optB6 = questionModel.getOpt62();
                optC6 = questionModel.getOpt63();
                optD6 = questionModel.getOpt64();

                optA7 = questionModel.getOpt71();
                optB7 = questionModel.getOpt72();
                optC7 = questionModel.getOpt73();
                optD7 = questionModel.getOpt74();

                optA8 = questionModel.getOpt81();
                optB8 = questionModel.getOpt82();
                optC8 = questionModel.getOpt83();
                optD8 = questionModel.getOpt84();

                optA9 = questionModel.getOpt91();
                optB9 = questionModel.getOpt92();
                optC9 = questionModel.getOpt93();
                optD9 = questionModel.getOpt94();

                optA10 = questionModel.getOpt101();
                optB10 = questionModel.getOpt102();
                optC10 = questionModel.getOpt103();
                optD10 = questionModel.getOpt104();

                quiz_title.setText(title);
                question1.setText(q1);
                question2.setText(q2);
                question3.setText(q3);
                question4.setText(q4);
                question5.setText(q5);
                question6.setText(q6);
                question7.setText(q7);
                question8.setText(q8);
                question9.setText(q9);
                question10.setText(q10);

                ansA1.setText(optA1);
                ansB1.setText(optB1);
                ansC1.setText(optC1);
                ansD1.setText(optD1);

                ansA2.setText(optA2);
                ansB2.setText(optB2);
                ansC2.setText(optC2);
                ansD2.setText(optD2);

                ansA3.setText(optA3);
                ansB3.setText(optB3);
                ansC3.setText(optC3);
                ansD3.setText(optD3);

                ansA4.setText(optA4);
                ansB4.setText(optB4);
                ansC4.setText(optC4);
                ansD4.setText(optD4);

                ansA5.setText(optA5);
                ansB5.setText(optB5);
                ansC5.setText(optC5);
                ansD5.setText(optD5);

                ansA6.setText(optA6);
                ansB6.setText(optB6);
                ansC6.setText(optC6);
                ansD6.setText(optD6);

                ansA7.setText(optA7);
                ansB7.setText(optB7);
                ansC7.setText(optC7);
                ansD7.setText(optD7);

                ansA8.setText(optA8);
                ansB8.setText(optB8);
                ansC8.setText(optC8);
                ansD8.setText(optD8);

                ansA9.setText(optA9);
                ansB9.setText(optB9);
                ansC9.setText(optC9);
                ansD9.setText(optD9);

                ansA10.setText(optA10);
                ansB10.setText(optB10);
                ansC10.setText(optC10);
                ansD10.setText(optD10);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
            }
        });
    }
    String getuId()
    {
        return FirebaseAuth.getInstance().getUid();
    }
}