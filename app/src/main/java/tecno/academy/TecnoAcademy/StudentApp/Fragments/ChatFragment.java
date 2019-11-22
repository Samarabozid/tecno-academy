package tecno.academy.TecnoAcademy.StudentApp.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import tecno.academy.TecnoAcademy.Models.TeacherModel;
import tecno.academy.TecnoAcademy.R;
import tecno.academy.TecnoAcademy.StudentApp.ChatActivity;

public class ChatFragment extends Fragment
{
    View view;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<TeacherModel> t;
    teacherAdapter adapter;
    String k;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_chat, container, false);
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

        databaseReference.child("TeachersChatDetails").child(getuId()).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                t.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    TeacherModel teacherModel = snapshot.getValue(TeacherModel.class);
                    k = snapshot.getKey();
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
        List<TeacherModel> studentModels;

        teacherAdapter(List<TeacherModel> studentModels)
        {
            this.studentModels = studentModels;
        }

        @NonNull
        @Override
        public teacherVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.teacher_chat_item, parent,false);
            return new teacherVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull teacherVH holder, final int position)
        {
            final TeacherModel studentModel = studentModels.get(position);

            String name = studentModel.getName();
            String sub = studentModel.getSubject_name();
            String img = studentModel.getImageUrl();

            holder.name.setText(name);
            holder.sub.setText(sub);

            Picasso.get()
                    .load(img)
                    .into(holder.circleImageView);

            holder.delete.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    new AlertDialog.Builder(getContext())
                            .setTitle("حذف المحادثة؟")
                            .setMessage("هل أنت متأكد من الحذف؟")
                            .setPositiveButton("نعم", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    databaseReference.child("StudentsChat").child(getuId()).child(studentModel.getTeacher_id()).removeValue();
                                    databaseReference.child("StudentsChatDetails").child(getuId()).child(studentModel.getTeacher_id()).removeValue();
                                    databaseReference.child("TeachersChatDetails").child(getuId()).child(studentModel.getTeacher_id()).removeValue();

                                    studentModels.remove(position);
                                    notifyItemRemoved(position);
                                }
                            })
                            .setNegativeButton("لا", null)
                            .show();
                }
            });

            holder.linearLayout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(getContext(), ChatActivity.class);
                    intent.putExtra("kk",studentModel);
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
            ImageView delete;
            TextView name,sub;
            LinearLayout linearLayout;

            teacherVH(@NonNull View itemView)
            {
                super(itemView);
                circleImageView = itemView.findViewById(R.id.user_profile);
                delete = itemView.findViewById(R.id.delete);
                name = itemView.findViewById(R.id.teacher_name);
                sub = itemView.findViewById(R.id.subject_name);
                linearLayout = itemView.findViewById(R.id.teacher_lin);
            }
        }
    }
    String getuId()
    {
        return FirebaseAuth.getInstance().getUid();
    }
}