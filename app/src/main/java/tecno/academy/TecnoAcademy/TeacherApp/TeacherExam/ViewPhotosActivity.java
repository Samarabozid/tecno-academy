package tecno.academy.TecnoAcademy.TeacherApp.TeacherExam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import tecno.academy.TecnoAcademy.Models.QuestionModel;
import tecno.academy.TecnoAcademy.R;

public class ViewPhotosActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    DividerItemDecoration dividerItemDecoration;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<QuestionModel> list;
    imageAdapter imageAdapter;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photos);

        key = getIntent().getStringExtra("kk");

        recyclerView = findViewById(R.id.recyclerview);

        list = new ArrayList<>();

        layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("ExamPhotos").child(getuId()).child(key).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                list.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    QuestionModel q = snapshot.getValue(QuestionModel.class);
                    list.add(q);
                }

                imageAdapter = new imageAdapter(list);
                recyclerView.setAdapter(imageAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
            }
        });

    }

    class imageAdapter extends RecyclerView.Adapter<imageAdapter.imageVH>
    {
        List<QuestionModel> questionModels;

        imageAdapter(List<QuestionModel> questionModels)
        {
            this.questionModels = questionModels;
        }

        @NonNull
        @Override
        public imageAdapter.imageVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.image_item, parent, false);
            return new imageAdapter.imageVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull imageAdapter.imageVH holder, final int position)
        {
            QuestionModel questionModel = questionModels.get(position);

            Picasso.get()
                    .load(questionModel.getImageUrl())
                    .into(holder.image);
        }

        @Override
        public int getItemCount() {
            return questionModels.size();
        }

        class imageVH extends RecyclerView.ViewHolder
        {
            TextView title;
            ImageView image;

            public imageVH(@NonNull View itemView)
            {
                super(itemView);

                image = itemView.findViewById(R.id.exam_photo);
                title = itemView.findViewById(R.id.exam_title);
            }
        }
    }
    String getuId()
    {
        return FirebaseAuth.getInstance().getUid();
    }
}