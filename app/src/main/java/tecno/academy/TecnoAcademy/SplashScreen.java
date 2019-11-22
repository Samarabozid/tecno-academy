package tecno.academy.TecnoAcademy;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

import tecno.academy.TecnoAcademy.AdminApp.AdminMainActivity;
import tecno.academy.TecnoAcademy.R;
import tecno.academy.TecnoAcademy.StudentApp.StudentMainActivity;
import tecno.academy.TecnoAcademy.TeacherApp.TeacherMainActivity;

public class SplashScreen extends AppCompatActivity
{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null)
        {
            if (user.getUid().equals("kCEYHrGQ66Zu5MxmVI9dY9XlCHV2"))
            {
                TimerTask task = new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        // go to the main activity
                        Intent i = new Intent(getApplicationContext(), AdminMainActivity.class);
                        startActivity(i);
                        // kill current activity
                        finish();
                    }
                };
                // Show splash screen for 3 seconds
                new Timer().schedule(task, 3000);
            } else
                {
                    databaseReference.child("Students").addListenerForSingleValueEvent(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            if (dataSnapshot.hasChild(user.getUid()))
                            {
                                TimerTask task = new TimerTask()
                                {
                                    @Override
                                    public void run()
                                    {
                                        // go to the main activity
                                        Intent i = new Intent(getApplicationContext(), StudentMainActivity.class);
                                        startActivity(i);
                                        // kill current activity
                                        finish();
                                    }
                                };
                                // Show splash screen for 3 seconds
                                new Timer().schedule(task, 3000);
                            } else
                                {
                                    TimerTask task = new TimerTask()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            // go to the main activity
                                            Intent i = new Intent(getApplicationContext(), TeacherMainActivity.class);
                                            startActivity(i);
                                            // kill current activity
                                            finish();
                                        }
                                    };
                                    // Show splash screen for 3 seconds
                                    new Timer().schedule(task, 3000);
                                }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError)
                        {
                            Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
        } else
        {
            TimerTask task = new TimerTask()
            {
                @Override
                public void run()
                {
                    // go to the main activity
                    Intent i = new Intent(getApplicationContext(), OnBoardActivity.class);
                    startActivity(i);
                    // kill current activity
                    finish();
                }
            };
            // Show splash screen for 3 seconds
            new Timer().schedule(task, 3000);
        }
    }
}