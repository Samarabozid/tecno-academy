package tecno.academy.TecnoAcademy.StudentApp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EdgeEffect;

import tecno.academy.TecnoAcademy.R;
import tecno.academy.TecnoAcademy.StudentApp.ExamsActivity;
import tecno.academy.TecnoAcademy.StudentApp.HomeGameScreen;
import tecno.academy.TecnoAcademy.StudentApp.StudentMainActivity;

public class GameFragment extends Fragment
{
    View view;
    Button play;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_game, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        play = view.findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent i = new Intent(getContext(), HomeGameScreen.class);
                startActivity(i);

            }
        });

    }
}
