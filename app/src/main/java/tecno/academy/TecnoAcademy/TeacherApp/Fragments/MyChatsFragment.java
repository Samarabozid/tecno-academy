package tecno.academy.TecnoAcademy.TeacherApp.Fragments;

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
import tecno.academy.TecnoAcademy.R;
import tecno.academy.TecnoAcademy.TeacherApp.TeacherChatActivity;

public class MyChatsFragment extends Fragment
{
    View view;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<StudentModel> t;
    teacherAdapter adapter;

    String k;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.my_chats_fragment, container, false);
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

        databaseReference.child("StudentsChatDetails").child(getuId()).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                t.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    StudentModel teacherModel = snapshot.getValue(StudentModel.class);
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
        List<StudentModel> studentModels;

        teacherAdapter(List<StudentModel> studentModels)
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
            final StudentModel studentModel = studentModels.get(position);

            String name = studentModel.getName();
            String sub = studentModel.getLevel();
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
                                    databaseReference.child("TeachersChat").child(getuId()).child(studentModel.getStudent_id()).removeValue();
                                    databaseReference.child("TeachersChatDetails").child(getuId()).child(studentModel.getStudent_id()).removeValue();
                                    databaseReference.child("StudentsChatDetails").child(getuId()).child(studentModel.getStudent_id()).removeValue();

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
                    Intent intent = new Intent(getContext(), TeacherChatActivity.class);
                    intent.putExtra("yy",studentModel);
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
