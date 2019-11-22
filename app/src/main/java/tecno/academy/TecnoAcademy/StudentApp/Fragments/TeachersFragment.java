package tecno.academy.TecnoAcademy.StudentApp.Fragments;

import android.content.Intent;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import tecno.academy.TecnoAcademy.Models.StudentModel;
import tecno.academy.TecnoAcademy.Models.TeacherModel;
import tecno.academy.TecnoAcademy.R;
import tecno.academy.TecnoAcademy.StudentApp.DetailsActivity;

public class TeachersFragment extends Fragment
{
    View view;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;

    List<TeacherModel> t;
    teacherAdapter adapter;

    String level;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.teachers_fragment, container, false);
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

        t = new ArrayList<>();

        databaseReference.child("Students").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                StudentModel studentModel = dataSnapshot.getValue(StudentModel.class);
                level = studentModel.getLevel();
                getT(level);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

    void getT(String l)
    {
        databaseReference.child("SectionedTeachers").child(l).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                t.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    TeacherModel teacherModel = snapshot.getValue(TeacherModel.class);
                    t.add(teacherModel);
                }

                adapter = new teacherAdapter(t);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

    class teacherAdapter extends RecyclerView.Adapter<teacherAdapter.teacherVH>
    {
        List<TeacherModel> teacherModels;

        teacherAdapter(List<TeacherModel> teacherModels)
        {
            this.teacherModels = teacherModels;
        }

        @NonNull
        @Override
        public teacherVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.teacher_item, parent,false);
            return new teacherVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull teacherVH holder, final int position)
        {
            final TeacherModel teacherModel = teacherModels.get(position);

            String name = teacherModel.getName();
            String sub = teacherModel.getSubject_name();
            String img = teacherModel.getImageUrl();

            holder.name.setText(name);
            holder.sub.setText(sub);

            Picasso.get()
                    .load(img)
                    .into(holder.circleImageView);

            holder.linearLayout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(getContext(), DetailsActivity.class);
                    intent.putExtra("key",teacherModel);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return teacherModels.size();
        }

        class teacherVH extends RecyclerView.ViewHolder
        {
            CircleImageView circleImageView;
            TextView name,sub;
            LinearLayout linearLayout;

            public teacherVH(@NonNull View itemView)
            {
                super(itemView);
                circleImageView = itemView.findViewById(R.id.user_profile);
                name = itemView.findViewById(R.id.teacher_name);
                sub = itemView.findViewById(R.id.subject_name);
                linearLayout = itemView.findViewById(R.id.teacher_lin);
            }
        }
    }
}