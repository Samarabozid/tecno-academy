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

import java.util.ArrayList;
import java.util.List;

import tecno.academy.TecnoAcademy.Models.GroupModel;
import tecno.academy.TecnoAcademy.R;
import tecno.academy.TecnoAcademy.TeacherApp.EditGroup;

public class MyGroupsFragment extends Fragment
{
    View view;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<GroupModel> gg;

    groupAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.my_groups_fragment, container, false);
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

        gg = new ArrayList<>();

        databaseReference.child("Groups").child(getuId()).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                gg.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    GroupModel teacherModel = snapshot.getValue(GroupModel.class);
                    gg.add(teacherModel);
                }

                adapter = new groupAdapter(gg);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
    class groupAdapter extends RecyclerView.Adapter<groupAdapter.groupVH>
    {
        List<GroupModel> groupModels;

        groupAdapter(List<GroupModel> groupModels)
        {
            this.groupModels = groupModels;
        }

        @NonNull
        @Override
        public groupVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.group_item, parent, false);
            return new groupVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull groupVH holder, final int position)
        {
            final GroupModel groupModel = groupModels.get(position);

            String t = groupModel.getTitle();
            String d = groupModel.getDate();
            String m = groupModel.getTime();
            final String key = groupModel.getId();

            holder.title.setText(t);
            holder.date.setText(d);
            holder.time.setText(m);

            holder.linearLayout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    new AlertDialog.Builder(getContext())
                            .setTitle("حذف المجموعة؟")
                            .setMessage("هل أنت متأكد من الحذف؟")
                            .setPositiveButton("نعم", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    databaseReference.child("Groups").child(getuId()).child(key).removeValue();

                                    groupModels.remove(position);
                                    notifyItemRemoved(position);
                                }
                            })
                            .setNegativeButton("لا", null)
                            .show();
                }
            });

            holder.edit.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent i = new Intent(getContext(), EditGroup.class);
                    i.putExtra("GroupKey",key);
                    startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount()

        {
            return groupModels.size();
        }

        class groupVH extends RecyclerView.ViewHolder
        {
            TextView title,date,time;
            LinearLayout linearLayout;
            ImageView edit;

            public groupVH(@NonNull View itemView)
            {
                super(itemView);

                linearLayout = itemView.findViewById(R.id.line);
                title = itemView.findViewById(R.id.gp_title);
                date = itemView.findViewById(R.id.gp_date);
                time = itemView.findViewById(R.id.gp_time);
                edit = itemView.findViewById(R.id.edit);
            }
        }
    }
    String getuId()
    {
        return FirebaseAuth.getInstance().getUid();
    }

}