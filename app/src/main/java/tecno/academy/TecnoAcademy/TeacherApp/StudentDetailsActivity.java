package tecno.academy.TecnoAcademy.TeacherApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import tecno.academy.TecnoAcademy.Models.QuestionModel;
import tecno.academy.TecnoAcademy.Models.StudentModel;
import tecno.academy.TecnoAcademy.R;
import tecno.academy.TecnoAcademy.StudentApp.ExamsActivity;
import tecno.academy.TecnoAcademy.StudentApp.StartExamActivity;

public class StudentDetailsActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<QuestionModel> ee;
    examAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        final StudentModel studentModel = (StudentModel) getIntent().getSerializableExtra("key");

        recyclerView = findViewById(R.id.recyclerview);

        layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        ee = new ArrayList<>();

        databaseReference.child("Score").child(studentModel.getStudent_id()).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                ee.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    QuestionModel questionModel = snapshot.getValue(QuestionModel.class);
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
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_item, parent, false);
            return new examAdapter.examVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull examAdapter.examVH holder, final int position)
        {
            QuestionModel questionModel = questionModels.get(position);

            String name = questionModel.getEx_title();
            String s = String.valueOf(questionModel.getScore());
            
            holder.title.setText(name);
            holder.score.setText(s);

        }

        @Override
        public int getItemCount()
        {
            return questionModels.size();
        }

        class examVH extends RecyclerView.ViewHolder
        {
            TextView title,score;
            LinearLayout linearLayout;


            examVH(@NonNull View itemView)
            {
                super(itemView);

                title = itemView.findViewById(R.id.examTitle);
                score = itemView.findViewById(R.id.examScore);
                linearLayout = itemView.findViewById(R.id.lin);

            }
        }
    }
}
