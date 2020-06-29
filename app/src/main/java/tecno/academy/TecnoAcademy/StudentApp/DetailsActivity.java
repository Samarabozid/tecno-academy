package tecno.academy.TecnoAcademy.StudentApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import tecno.academy.TecnoAcademy.AdminApp.AdminMainActivity;
import tecno.academy.TecnoAcademy.Models.GroupModel;
import tecno.academy.TecnoAcademy.Models.PdfModel;
import tecno.academy.TecnoAcademy.Models.TeacherModel;
import tecno.academy.TecnoAcademy.Models.VideoModel;
import tecno.academy.TecnoAcademy.R;
import tecno.academy.TecnoAcademy.TeacherApp.TeacherMainActivity;
import tecno.academy.TecnoAcademy.TeacherApp.TeacherRegisterActivity;

public class DetailsActivity extends AppCompatActivity
{
    CircleImageView circleImageView;
    TextView name_txt,sub_txt;
    ImageView imageView,skype;
    Button see_gp,see_ex;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<PdfModel> t;
    teacherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final TeacherModel teacherModel = (TeacherModel) getIntent().getSerializableExtra("key");
        //Log.d("teacherid","Details Activity " + teacherModel.getTeacher_id());

        circleImageView = findViewById(R.id.user_profile);
        name_txt = findViewById(R.id.teacher_name);
        sub_txt = findViewById(R.id.subject_name);
        imageView = findViewById(R.id.chat_img);
        skype= findViewById(R.id.skype_img);

        see_gp = findViewById(R.id.see_gp);
        see_ex = findViewById(R.id.see_ex);

        String name = teacherModel.getName();
        String sub = teacherModel.getSubject_name();
        final String img = teacherModel.getImageUrl();

        name_txt.setText(name);
        sub_txt.setText(sub);

        Picasso.get()
                .load(img)
                .into(circleImageView);

        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.putExtra("kk", teacherModel);
                startActivity(intent);
            }
        });

        skype.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                new AlertDialog.Builder(DetailsActivity.this)
                        .setTitle("مكالمة فيديو")
                        .setMessage("من فضلك اتواصل مع المدرس عشان تحجز معاه محاضره اونلاين")
                        .setPositiveButton("ok", null).show();
            }
        });

        see_gp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(DetailsActivity.this, GroupsActivity.class);
                intent.putExtra("gp", teacherModel.getTeacher_id());
                startActivity(intent);
            }
        });

        see_ex.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), ExamsActivity.class);
                intent.putExtra("ex", teacherModel.getTeacher_id());
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerview);

        layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        t = new ArrayList<>();

        databaseReference.child("TeachersPdfs").child(teacherModel.getTeacher_id()).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                t.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    PdfModel teacherModel = snapshot.getValue(PdfModel.class);
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
        List<PdfModel> teacherModels;

        teacherAdapter(List<PdfModel> teacherModels)
        {
            this.teacherModels = teacherModels;
        }

        @NonNull
        @Override
        public teacherVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.pdf_item, parent,false);
            return new teacherVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull teacherVH holder, final int position)
        {
            final PdfModel teacherModel = teacherModels.get(position);

            String name = teacherModel.getTitle();
            final String img = teacherModel.getUrl();

            holder.title.setText(name);

            holder.linearLayout.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(img));
                    startActivity(i);
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
            TextView title;
            LinearLayout linearLayout;

            teacherVH(@NonNull View itemView)
            {
                super(itemView);
                title = itemView.findViewById(R.id.pdf_title);
                linearLayout = itemView.findViewById(R.id.pdf_lin);
            }
        }
    }
}
