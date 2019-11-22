package tecno.academy.TecnoAcademy.StudentApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import tecno.academy.TecnoAcademy.Models.GroupModel;
import tecno.academy.TecnoAcademy.R;

public class GroupsActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<GroupModel> gg;
    groupAdapter adapter;

    String k;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        k = getIntent().getStringExtra("gp");

        recyclerView = findViewById(R.id.recyclerview);

        layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        gg = new ArrayList<>();

        new AlertDialog.Builder(GroupsActivity.this)
               // .setTitle("المجموعات")
                .setMessage("اختر المجموعه التى تريد الانضمام اليها")
                .setPositiveButton("OK", null).show();

        databaseReference.child("Groups").child(k).addValueEventListener(new ValueEventListener()
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
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.group_item, parent, false);
            return new groupVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull groupVH holder, int position)
        {
            GroupModel groupModel = groupModels.get(position);

            String t = groupModel.getTitle();
            String d = groupModel.getDate();
            String m = groupModel.getTime();

            holder.title.setText(t);
            holder.date.setText(d);
            holder.time.setText(m);
        }

        @Override
        public int getItemCount()
        {
            return groupModels.size();
        }

        class groupVH extends RecyclerView.ViewHolder
        {
            TextView title,date,time;

            groupVH(@NonNull View itemView)
            {
                super(itemView);

                title = itemView.findViewById(R.id.gp_title);
                date = itemView.findViewById(R.id.gp_date);
                time = itemView.findViewById(R.id.gp_time);
            }
        }
    }
}