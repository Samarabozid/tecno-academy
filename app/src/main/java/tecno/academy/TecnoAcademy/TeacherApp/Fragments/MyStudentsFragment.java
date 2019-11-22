package tecno.academy.TecnoAcademy.TeacherApp.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import tecno.academy.TecnoAcademy.StudentApp.ChatActivity;
import tecno.academy.TecnoAcademy.TeacherApp.StudentDetailsActivity;
import tecno.academy.TecnoAcademy.TeacherApp.TeacherChatActivity;


public class MyStudentsFragment extends Fragment
{
    View view;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<StudentModel> t;
    studentAdapter adapter;

    String level;

    String k;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_my_students, container, false);
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

        databaseReference.child("Teachers").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                TeacherModel teacherModel = dataSnapshot.getValue(TeacherModel.class);
                level = teacherModel.getLevel();
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
        databaseReference.child("SectionedStudents").child(l).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                t.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    StudentModel studentModel = snapshot.getValue(StudentModel.class);
                    k = snapshot.getKey();
                    t.add(studentModel);
                }

                adapter = new studentAdapter(t);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

    class studentAdapter extends RecyclerView.Adapter<studentAdapter.teacherVH>
    {
        List<StudentModel> studentModels;

        studentAdapter(List<StudentModel> studentModels)
        {
            this.studentModels = studentModels;
        }

        @NonNull
        @Override
        public teacherVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.student_item, parent,false);
            return new teacherVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull teacherVH holder, final int position)
        {
            final StudentModel studentModel = studentModels.get(position);

            String name = studentModel.getName();
            String img = studentModel.getImageUrl();

            holder.name.setText(name);

            Picasso.get()
                    .load(img)
                    .into(holder.circleImageView);

            holder.delete.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    new AlertDialog.Builder(getContext())
                            .setTitle("حذف الطالب؟")
                            .setMessage("هل أنت متأكد من الحذف؟")
                            .setPositiveButton("نعم", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    databaseReference.child("SectionedStudents").child(level).child(studentModel.getStudent_id()).removeValue();
                                    databaseReference.child("Students").child(studentModel.getStudent_id()).removeValue();
                                    databaseReference.child("Score").child(studentModel.getStudent_id()).removeValue();
                                    databaseReference.child("StudentsChat").child(studentModel.getStudent_id()).child(getuId()).removeValue();
                                    databaseReference.child("StudentsChatDetails").child(studentModel.getStudent_id()).child(getuId()).removeValue();
                                    databaseReference.child("TeachersChatDetails").child(studentModel.getStudent_id()).child(getuId()).removeValue();

                                    studentModels.remove(position);
                                    notifyItemRemoved(position);
                                }
                            })
                            .setNegativeButton("لا", null)
                            .show();
                }
            });

            holder.chat.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(getContext(), TeacherChatActivity.class);
                    intent.putExtra("yy",studentModel);
                    startActivity(intent);
                }
            });

          holder.linearLayout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(getContext(), StudentDetailsActivity.class);
                    intent.putExtra("key",studentModel);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount()
        {
            return studentModels.size();
        }

        class teacherVH extends RecyclerView.ViewHolder
        {
            CircleImageView circleImageView;
            TextView name;
            ImageView chat,delete;
            LinearLayout linearLayout;

            public teacherVH(@NonNull View itemView)
            {
                super(itemView);
                circleImageView = itemView.findViewById(R.id.user_profile);
                name = itemView.findViewById(R.id.teacher_name);
                chat = itemView.findViewById(R.id.chat_img);
                delete = itemView.findViewById(R.id.delete);
                linearLayout = itemView.findViewById(R.id.studentLinear);
            }
        }
    }
    String getuId()
    {
        return FirebaseAuth.getInstance().getUid();
    }
}