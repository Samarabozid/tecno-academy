package tecno.academy.TecnoAcademy.TeacherApp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import tecno.academy.TecnoAcademy.Models.ChatModel;
import tecno.academy.TecnoAcademy.Models.StudentModel;
import tecno.academy.TecnoAcademy.Models.TeacherModel;
import tecno.academy.TecnoAcademy.R;

public class TeacherChatActivity extends AppCompatActivity
{
    EditText chat_msg_field;
    FloatingActionButton send_msg;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    TeacherModel teacherModel;
    StudentModel studentModel;
    List<ChatModel> c;
    Uri photoPath;
    private static final int RC_PHOTO_PICKER = 2;

    chatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_chat);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference().child("images");

        studentModel = (StudentModel) getIntent().getSerializableExtra("yy");

        chat_msg_field = findViewById(R.id.chat_msg);
        send_msg = findViewById(R.id.send_msg_fab);
        recyclerView = findViewById(R.id.recyclerview);

        c = new ArrayList<>();

        layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        send_msg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String s = chat_msg_field.getText().toString();

                if (TextUtils.isEmpty(s))
                {
                    Toast.makeText(getApplicationContext(), "اكتب رسالة أولا", Toast.LENGTH_SHORT).show();
                    return;
                }

                sendMsg(s);
            }
        });

        databaseReference.child("TeachersChat").child(getuId()).child(studentModel.getStudent_id()).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                c.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    ChatModel teacherModel = snapshot.getValue(ChatModel.class);
                    c.add(teacherModel);
                }

                adapter = new chatAdapter(c);
                recyclerView.setAdapter(adapter);
                recyclerView.scrollToPosition(c.size() - 1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
        getData();
    }

    class chatAdapter extends RecyclerView.Adapter<chatAdapter.chatVH>
    {
        List<ChatModel> chatModels;

        chatAdapter(List<ChatModel> chatModels)
        {
            this.chatModels = chatModels;
        }

        @NonNull
        @Override
        public chatVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.chat_item, parent, false);
            return new chatVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull chatVH holder, int position)
        {
            ChatModel chatModel = chatModels.get(position);

            holder.chat_txt.setVisibility(View.GONE);
            holder.chat_txt.setVisibility(View.VISIBLE);

            holder.chat_txt.setText(chatModel.getMsg());

            if (chatModel.getUid().equals(getuId()))
            {
                holder.linearLayout.setGravity(Gravity.END);
                holder.chat_txt.setBackgroundColor(getResources().getColor(R.color.blue2));
            } else
            {
                holder.linearLayout.setGravity(Gravity.START);
                holder.chat_txt.setBackgroundColor(getResources().getColor(R.color.blue));
            }
        }

        @Override
        public int getItemCount()
        {
            return chatModels.size();
        }

        class chatVH extends RecyclerView.ViewHolder
        {
            TextView chat_txt;
            LinearLayout linearLayout;

            chatVH(@NonNull View itemView)
            {
                super(itemView);

                chat_txt = itemView.findViewById(R.id.chat_txt);
                linearLayout = itemView.findViewById(R.id.chat_lin);
            }
        }
    }

    private void sendMsg(String s)
    {
        ChatModel chatModel = new ChatModel(s,getuId());

        String k = databaseReference.child("TeachersChat").child(getuId()).child(studentModel.getStudent_id()).push().getKey();
        databaseReference.child("TeachersChat").child(getuId()).child(studentModel.getStudent_id()).child(k).setValue(chatModel);
        databaseReference.child("StudentsChat").child(studentModel.getStudent_id()).child(getuId()).child(k).setValue(chatModel);

        databaseReference.child("TeachersChatDetails").child(studentModel.getStudent_id()).child(getuId()).setValue(teacherModel);
        databaseReference.child("TeachersChatDetails").child(getuId()).child(studentModel.getStudent_id()).setValue(teacherModel);

        databaseReference.child("StudentsChatDetails").child(getuId()).child(studentModel.getStudent_id()).setValue(studentModel);

        chat_msg_field.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
         if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK)
         {
             photoPath = data.getData();

             final StorageReference photoRef = storageReference.child("images/" + photoPath.getLastPathSegment());

             //upload photo to firebase storage
             photoRef.putFile(photoPath).addOnSuccessListener
                     (this, new OnSuccessListener<UploadTask.TaskSnapshot>()
                             {
                                 @Override
                                 public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                                 {
                                     Task<Uri> photoUrl = taskSnapshot.getStorage().getDownloadUrl();
                                     ChatModel chatModel = new ChatModel(null, getuId());
                                     //databaseReference.push().setValue(chatModel);
                                     String k = databaseReference.child("TeachersChat").child(getuId()).child(studentModel.getStudent_id()).push().getKey();
                                     databaseReference.child("TeachersChat").child(getuId()).child(studentModel.getStudent_id()).child(k).setValue(chatModel);
                                     databaseReference.child("StudentsChat").child(studentModel.getStudent_id()).child(getuId()).child(k).setValue(chatModel);

                                     databaseReference.child("TeachersChatDetails").child(studentModel.getStudent_id()).child(getuId()).setValue(teacherModel);
                                     databaseReference.child("TeachersChatDetails").child(getuId()).child(studentModel.getStudent_id()).setValue(teacherModel);

                                     databaseReference.child("StudentsChatDetails").child(getuId()).child(studentModel.getStudent_id()).setValue(studentModel);

                                     chat_msg_field.setText("");
                                 }
                             }
                     );
        }
    }

    void getData()
    {
        databaseReference.child("Teachers").child(getuId()).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                teacherModel = dataSnapshot.getValue(TeacherModel.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

    String getuId()
    {
        return FirebaseAuth.getInstance().getUid();
    }
}
