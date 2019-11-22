package tecno.academy.TecnoAcademy.AdminApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import tecno.academy.TecnoAcademy.Models.TeacherModel;
import tecno.academy.TecnoAcademy.R;
import tecno.academy.TecnoAcademy.SignUpActivity;

public class AdminMainActivity extends AppCompatActivity
{
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<TeacherModel> t;
    teacherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        floatingActionButton = findViewById(R.id.add_teacher_fab);

        recyclerView = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        t = new ArrayList<>();

        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PopupMenu popup = new PopupMenu(AdminMainActivity.this, floatingActionButton);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.admin_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        switch (item.getItemId())
                        {
                            case R.id.add_teacher:
                                Intent intent = new Intent(getApplicationContext(), AddTeacherActivity.class);
                                startActivity(intent);
                                return true;
                            case R.id.logout:
                                FirebaseAuth.getInstance().signOut();
                                Intent intent2 = new Intent(getApplicationContext(), SignUpActivity.class);
                                startActivity(intent2);
                                finish();
                                return true;
                            default:
                                return true;
                        }
                    }});

                popup.show(); //showing popup menu
            }
        });

        databaseReference.child("Teachers").addValueEventListener(new ValueEventListener()
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
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.teacher_item, parent,false);
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
                    new AlertDialog.Builder(AdminMainActivity.this)
                            .setTitle("حذف المعلم؟")
                            .setMessage("هل أنت متأكد من الحذف؟")

                            .setPositiveButton("نعم", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    databaseReference.child("Teachers").child(teacherModel.getTeacher_id()).removeValue();
                                    databaseReference.child("SectionedTeachers").child(teacherModel.getLevel()).child(teacherModel.getTeacher_id()).removeValue();

                                    teacherModels.remove(position);
                                    notifyItemRemoved(position);
                                }
                            })
                            .setNegativeButton("لا", null)
                            .show();
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

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseAuth.getInstance().signInWithEmailAndPassword("admin@admin.com", "admin1234");
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
