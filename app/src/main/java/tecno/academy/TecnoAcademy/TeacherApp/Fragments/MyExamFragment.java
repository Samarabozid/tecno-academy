package tecno.academy.TecnoAcademy.TeacherApp.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import tecno.academy.TecnoAcademy.StudentApp.StartExamActivity;

public class MyExamFragment extends Fragment
{
    View view;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<QuestionModel> ee;

   examAdapter adapter;
   String s;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.my_exam_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerview);

        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        ee = new ArrayList<>();

        databaseReference.child("Exams").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                ee.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    QuestionModel questionModel = snapshot.getValue(QuestionModel.class);
                    ee.add(questionModel);
                    s = snapshot.getKey();
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
            View view = LayoutInflater.from(getContext()).inflate(R.layout.my_exam_item, parent, false);
            return new examAdapter.examVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull examAdapter.examVH holder, final int position)
        {
            final QuestionModel questionModel = questionModels.get(position);
            String name = questionModel.getEx_title();
            //final String img = examModel.getExam_image();

            holder.title.setText(name);

            holder.linearLayout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    new AlertDialog.Builder(getContext())
                            .setTitle("حذف الامتحان؟")
                            .setMessage("هل أنت متأكد من الحذف؟")
                            .setPositiveButton("نعم", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    databaseReference.child("Exams").child(FirebaseAuth.getInstance().getUid()).child(s).removeValue();

                                    questionModels.remove(position);
                                    notifyItemRemoved(position);
                                }
                            })
                            .setNegativeButton("لا", null)
                            .show();
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

            examVH(@NonNull View itemView)
            {
                super(itemView);

                title = itemView.findViewById(R.id.exam_title);
                linearLayout = itemView.findViewById(R.id.exam_lin);

            }
        }
    }
    String getuId()
    {
        return FirebaseAuth.getInstance().getUid();
    }
}