package tecno.academy.TecnoAcademy.StudentApp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import tecno.academy.TecnoAcademy.Models.QuestionModel;
import tecno.academy.TecnoAcademy.R;

public class ExamFragment extends Fragment
{
    View view;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<QuestionModel> gg;
    examAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.exam_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerview);

        gg = new ArrayList<>();

        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("Score").child(getuId()).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                gg.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    QuestionModel questionModel = snapshot.getValue(QuestionModel.class);
                    gg.add(questionModel);
                }
                adapter = new examAdapter(gg);
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
            View view = LayoutInflater.from(getContext()).inflate(R.layout.score_item, parent, false);
            return new examAdapter.examVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull examAdapter.examVH holder, final int position)
        {
            QuestionModel questionModel = questionModels.get(position);

            String name = questionModel.getEx_title();
            final String s = String.valueOf(questionModel.getScore());

            holder.title.setText(name);
            holder.score.setText(s);
        }

        @Override
        public int getItemCount() {
            return questionModels.size();
        }

        class examVH extends RecyclerView.ViewHolder
        {
            TextView title,score;
            LinearLayout linearLayout;


           examVH(@NonNull View itemView)
            {
                super(itemView);
                score = itemView.findViewById(R.id.examScore);
                title = itemView.findViewById(R.id.examTitle);
                linearLayout = itemView.findViewById(R.id.lin);

            }
        }
    }
    String getuId()
    {
        return FirebaseAuth.getInstance().getUid();
    }
}
