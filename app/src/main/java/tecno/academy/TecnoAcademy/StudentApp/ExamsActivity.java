package tecno.academy.TecnoAcademy.StudentApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import tecno.academy.TecnoAcademy.Models.GroupModel;
import tecno.academy.TecnoAcademy.Models.QuestionModel;
import tecno.academy.TecnoAcademy.Models.TeacherModel;
import tecno.academy.TecnoAcademy.R;
import tecno.academy.TecnoAcademy.TeacherApp.Fragments.MyExamFragment;

public class ExamsActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<QuestionModel> ee;
    examAdapter adapter;

    ArrayList<String> keys ;
    String k;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);

        k = getIntent().getExtras().getString("ex");
        //Log.d("id_examAct","exam activity "+k);

        recyclerView = findViewById(R.id.recyclerview1);

        layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        ee = new ArrayList<>();
        keys  =  new ArrayList<>() ;

        databaseReference.child("Exams").child(k).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                ee.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    QuestionModel questionModel = snapshot.getValue(QuestionModel.class);
                    String examKey = snapshot.getKey() ;
                    keys.add(examKey);
                    ee.add(questionModel);
                }

                adapter = new examAdapter(ee);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

    class examAdapter extends RecyclerView.Adapter<examAdapter.examVH>
    {
        List<QuestionModel> questionModels;

        examAdapter(List<QuestionModel> questionModels)
        {
            this.questionModels = questionModels;
        }

        @NonNull
        @Override
        public examAdapter.examVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.my_exam_item, parent, false);
            return new examAdapter.examVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull examAdapter.examVH holder, final int position)
        {
            //final TeacherModel teacherModel = (TeacherModel) getIntent().getSerializableExtra("key");
            QuestionModel questionModel = questionModels.get(position);

            String name = questionModel.getEx_title();
            //final String img = examModel.getExam_image();

            holder.title.setText(name);
            holder.linearLayout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent i = new Intent(getApplicationContext(), StartExamActivity.class);
                    i.putExtra("ex",keys.get(position));
                    i.putExtra("te" , k);
                    startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return questionModels.size();
        }

        class examVH extends RecyclerView.ViewHolder
        {
            TextView title;
            LinearLayout linearLayout;


            public examVH(@NonNull View itemView)
            {
                super(itemView);

                title = itemView.findViewById(R.id.exam_title);
                linearLayout = itemView.findViewById(R.id.exam_lin);

            }
        }
    }
}
