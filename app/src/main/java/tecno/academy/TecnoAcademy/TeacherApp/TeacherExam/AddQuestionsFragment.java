package tecno.academy.TecnoAcademy.TeacherApp.TeacherExam;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import tecno.academy.TecnoAcademy.AdminApp.AddTeacherActivity;
import tecno.academy.TecnoAcademy.Models.QuestionModel;
import tecno.academy.TecnoAcademy.R;
import tecno.academy.TecnoAcademy.StudentApp.RegisterActivity;
import tecno.academy.TecnoAcademy.TeacherApp.TeacherMainActivity;

import static android.app.Activity.RESULT_OK;

public class AddQuestionsFragment extends Fragment
{
    ProgressDialog progressDialog;

    View view;
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

    String exam_title,exam_key;
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

    ImageView image;
    Uri photoPath;

    Button upload,view_img;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_add_questions, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        image = view.findViewById(R.id.imageview);
        upload = view.findViewById(R.id.upload_img);
        view_img = view.findViewById(R.id.view_img);

        score = view.findViewById(R.id.score);

        title = view.findViewById(R.id.title);
        question1 = view.findViewById(R.id.question1);
        option1 = view.findViewById(R.id.option1);
        option2 = view.findViewById(R.id.option2);
        option3 = view.findViewById(R.id.option3);
        option4 = view.findViewById(R.id.option4);
        correct_answer1 = view.findViewById(R.id.correct_answer);

        question2 = view.findViewById(R.id.question2);
        option21 = view.findViewById(R.id.option21);
        option22 = view.findViewById(R.id.option22);
        option23 = view.findViewById(R.id.option23);
        option24 = view.findViewById(R.id.option24);
        correct_answer2 = view.findViewById(R.id.correct_answer2);

        question3 = view.findViewById(R.id.question3);
        option31 = view.findViewById(R.id.option31);
        option32 = view.findViewById(R.id.option32);
        option33 = view.findViewById(R.id.option33);
        option34 = view.findViewById(R.id.option34);
        correct_answer3 = view.findViewById(R.id.correct_answer3);

        question4 = view.findViewById(R.id.question4);
        option41 = view.findViewById(R.id.option41);
        option42 = view.findViewById(R.id.option42);
        option43 = view.findViewById(R.id.option43);
        option44 = view.findViewById(R.id.option44);
        correct_answer4 = view.findViewById(R.id.correct_answer4);

        question5 = view.findViewById(R.id.question5);
        option51 = view.findViewById(R.id.option51);
        option52 = view.findViewById(R.id.option52);
        option53 = view.findViewById(R.id.option53);
        option54 = view.findViewById(R.id.option54);
        correct_answer5 = view.findViewById(R.id.correct_answer5);

        question6 = view.findViewById(R.id.question6);
        option61 = view.findViewById(R.id.option61);
        option62 = view.findViewById(R.id.option62);
        option63 = view.findViewById(R.id.option63);
        option64 = view.findViewById(R.id.option64);
        correct_answer6 = view.findViewById(R.id.correct_answer6);

        question7 = view.findViewById(R.id.question7);
        option71 = view.findViewById(R.id.option71);
        option72 = view.findViewById(R.id.option72);
        option73 = view.findViewById(R.id.option73);
        option74 = view.findViewById(R.id.option74);
        correct_answer7 = view.findViewById(R.id.correct_answer7);

        question8 = view.findViewById(R.id.question8);
        option81 = view.findViewById(R.id.option81);
        option82 = view.findViewById(R.id.option82);
        option83 = view.findViewById(R.id.option83);
        option84 = view.findViewById(R.id.option84);
        correct_answer8 = view.findViewById(R.id.correct_answer8);

        question9 = view.findViewById(R.id.question9);
        option91 = view.findViewById(R.id.option91);
        option92 = view.findViewById(R.id.option92);
        option93 = view.findViewById(R.id.option93);
        option94 = view.findViewById(R.id.option94);
        correct_answer9 = view.findViewById(R.id.correct_answer9);

        question10 = view.findViewById(R.id.question10);
        option101 = view.findViewById(R.id.option101);
        option102 = view.findViewById(R.id.option102);
        option103 = view.findViewById(R.id.option103);
        option104 = view.findViewById(R.id.option104);
        correct_answer10 = view.findViewById(R.id.correct_answer10);

        create_exam = view.findViewById(R.id.create_exam);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference().child("images");

        image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                /*Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);*/

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                        .setAspectRatio(1, 1)
                        .start(getContext(),AddQuestionsFragment.this);
            }
        });

        view_img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (exam_key == null)
                {
                    Toast.makeText(getContext(), "قم بإضافة الصور أولا", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent(getContext(),AddPhotosActivity.class);
                i.putExtra("KeyOfExam",exam_key);
                startActivity(i);
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
                    Toast.makeText(getContext(), "Enter title", Toast.LENGTH_SHORT).show();
                    title.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques1))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o1))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o2))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o3))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o4))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option4.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca1))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer1.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(ques2))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o21))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option21.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o22))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option22.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o23))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option23.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o24))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option24.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca2))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques3))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o31))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option31.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o32))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option32.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o33))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option33.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o34))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option34.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca3))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques4))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question4.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o41))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option41.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o42))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option42.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o43))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option43.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o44))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option44.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca4))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer4.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques5))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question5.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o51))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option51.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o52))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option52.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o53))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option53.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o54))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option54.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca5))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer5.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(ques6))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question6.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o61))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option61.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o62))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option62.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o63))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option63.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o64))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option64.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca6))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer6.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(ques7))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question7.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o71))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option71.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o72))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option72.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o73))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option73.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o74))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option74.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca7))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer7.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques8))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question8.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o81))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option81.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o82))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option82.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o83))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option83.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o84))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option84.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca8))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer8.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(ques9))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question9.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o91))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option91.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o92))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option92.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o93))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option93.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o94))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option94.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca9))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer9.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques10))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question10.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o101))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option101.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o102))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option102.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o103))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option103.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o104))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option104.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca10))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer10.requestFocus();
                    return;
                }

                progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("برجاء الانتظار ...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                progressDialog.setCancelable(false);

                addExam(exam_score,exam_title
                        ,ques1,o1,o2,o3,o4,ca1
                        ,ques2,o21,o22,o23,o24,ca2
                        ,ques3,o31,o32,o33,o34,ca3
                        ,ques4,o41,o42,o43,o44,ca4
                        ,ques5,o51,o52,o53,o54,ca5
                        ,ques6,o61,o62,o63,o64,ca6
                        ,ques7,o71,o72,o73,o74,ca7
                        ,ques8,o81,o82,o83,o84,ca8
                        ,ques9,o91,o92,o93,o94,ca9
                        ,ques10,o101,o102,o103,o104,ca10);
            }
        });

        upload.setOnClickListener(new View.OnClickListener()
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
                    Toast.makeText(getContext(), "Enter title", Toast.LENGTH_SHORT).show();
                    title.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques1))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o1))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o2))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o3))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o4))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option4.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca1))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer1.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(ques2))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o21))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option21.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o22))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option22.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o23))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option23.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o24))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option24.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca2))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques3))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o31))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option31.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o32))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option32.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o33))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option33.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o34))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option34.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca3))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer3.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques4))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question4.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o41))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option41.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o42))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option42.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o43))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option43.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o44))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option44.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca4))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer4.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques5))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question5.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o51))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option51.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o52))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option52.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o53))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option53.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o54))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option54.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca5))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer5.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(ques6))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question6.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o61))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option61.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o62))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option62.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o63))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option63.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o64))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option64.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca6))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer6.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(ques7))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question7.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o71))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option71.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o72))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option72.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o73))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option73.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o74))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option74.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca7))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer7.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques8))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question8.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o81))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option81.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o82))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option82.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o83))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option83.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o84))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option84.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca8))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer8.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(ques9))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question9.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o91))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option91.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o92))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option92.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o93))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option93.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o94))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option94.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca9))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer9.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ques10))
                {
                    Toast.makeText(getContext(), "Enter question", Toast.LENGTH_SHORT).show();
                    question10.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o101))
                {
                    Toast.makeText(getContext(), "Enter option1", Toast.LENGTH_SHORT).show();
                    option101.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o102))
                {
                    Toast.makeText(getContext(), "Enter option2", Toast.LENGTH_SHORT).show();
                    option102.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o103))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option103.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(o104))
                {
                    Toast.makeText(getContext(), "Enter option3", Toast.LENGTH_SHORT).show();
                    option104.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ca10))
                {
                    Toast.makeText(getContext(), "Enter correct answer", Toast.LENGTH_SHORT).show();
                    correct_answer10.requestFocus();
                    return;
                }

                if (photoPath == null)
                {
                    Toast.makeText(getContext(), "اختر الصوره أولا", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (exam_key == null)
                {
                    Toast.makeText(getContext(), "قم بإنشاء الإمتحان أولا ثم أضف الصور", Toast.LENGTH_LONG).show();
                    return;
                }

                progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("برجاء الانتظار ...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                progressDialog.setCancelable(false);

                uploadImage();
            }
        });
    }

    private void addExam(int exam_score,String exam_title
            ,String ques1, String o1, String o2,String o3,String o4, String ca1
            ,String ques2,String o21,String o22,String o23,String o24,String ca2
            ,String ques3,String o31,String o32,String o33,String o34,String ca3
            ,String ques4,String o41,String o42,String o43,String o44,String ca4
            ,String ques5,String o51,String o52,String o53,String o54,String ca5
            ,String ques6,String o61,String o62,String o63,String o64,String ca6
            ,String ques7,String o71,String o72,String o73,String o74,String ca7
            ,String ques8,String o81,String o82,String o83,String o84,String ca8
            ,String ques9,String o91,String o92,String o93,String o94,String ca9
            ,String ques10,String o101,String o102,String o103,String o104,String ca10)
    {
        QuestionModel examModel = new QuestionModel(exam_score,exam_key,exam_title
                ,ques1,o1,o2,o3,o4,ca1
                ,ques2,o21,o22,o23,o24,ca2
                ,ques3,o31,o32,o33,o34,ca3
                ,ques4,o41,o42,o43,o44,ca4
                ,ques5,o51,o52,o53,o54,ca5
                ,ques6,o61,o62,o63,o64,ca6
                ,ques7,o71,o72,o73,o74,ca7
                ,ques8,o81,o82,o83,o84,ca8
                ,ques9,o91,o92,o93,o94,ca9
                ,ques10,o101,o102,o103,o104,ca10);

        exam_key = databaseReference.child("Exams").child(getuId()).push().getKey();
        databaseReference.child("Exams").child(getuId()).child(exam_key).setValue(examModel);
        create_exam.setEnabled(false);
        create_exam.setVisibility(View.INVISIBLE);
        create_exam.setClickable(false);
        progressDialog.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
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
                            .into(image);
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
    }

    private void uploadImage()
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
                AddPhoto(imageUrl);
            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception exception)
            {
                Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void AddPhoto(String imageUrl)
    {
        QuestionModel questionModel = new QuestionModel(exam_key,imageUrl);
        String m = databaseReference.child("ExamPhotos").child(getuId()).child(exam_key).push().getKey();
        databaseReference.child("ExamPhotos").child(getuId()).child(exam_key).child(m).setValue(questionModel);

        progressDialog.dismiss();

        Intent i = new Intent(getContext(),AddPhotosActivity.class);
        i.putExtra("KeyOfExam",exam_key);
        startActivity(i);
    }

    String getuId()
    {
        return FirebaseAuth.getInstance().getUid();
    }
}