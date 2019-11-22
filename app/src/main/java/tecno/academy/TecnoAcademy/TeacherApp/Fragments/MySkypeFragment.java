package tecno.academy.TecnoAcademy.TeacherApp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tecno.academy.TecnoAcademy.Models.VideoModel;
import tecno.academy.TecnoAcademy.R;
import tecno.academy.TecnoAcademy.TeacherApp.TeacherMainActivity;

public class MySkypeFragment extends Fragment {
    View view;

    EditText link;
    MaterialRippleLayout save;
    String mLink;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.my_skype_fragment, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        link = getActivity().findViewById(R.id.link);
        save = getActivity().findViewById(R.id.save);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mLink = link.getText().toString();

                if (TextUtils.isEmpty(mLink))
                {
                    Toast.makeText(getContext(), "ادخل اللينك أولا", Toast.LENGTH_SHORT).show();
                    link.requestFocus();
                    return;
                }

                VideoModel videoModel = new VideoModel(mLink);

                String k = databaseReference.child("Skype").child(getuId()).push().getKey();
                databaseReference.child("Skype").child(getuId()).child(k).setValue(videoModel);

                startActivity(new Intent(getContext(), TeacherMainActivity.class));
            }
        });
    }
    String getuId()
    {
        return FirebaseAuth.getInstance().getUid();
    }

}
