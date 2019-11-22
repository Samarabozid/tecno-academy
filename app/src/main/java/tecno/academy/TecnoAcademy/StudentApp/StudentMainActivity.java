package tecno.academy.TecnoAcademy.StudentApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import tecno.academy.TecnoAcademy.Models.StudentModel;
import tecno.academy.TecnoAcademy.R;
import tecno.academy.TecnoAcademy.SignUpActivity;
import tecno.academy.TecnoAcademy.StudentApp.Fragments.ChatFragment;
import tecno.academy.TecnoAcademy.StudentApp.Fragments.ExamFragment;
import tecno.academy.TecnoAcademy.StudentApp.Fragments.GameFragment;
import tecno.academy.TecnoAcademy.StudentApp.Fragments.TeachersFragment;

public class StudentMainActivity extends AppCompatActivity
{
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentPagerAdapter fragmentPagerAdapter;

    CircleImageView circleImageView;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        circleImageView = findViewById(R.id.img);

        circleImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PopupMenu popup = new PopupMenu(StudentMainActivity.this, circleImageView);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.student_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        switch (item.getItemId())
                        {
                            case R.id.logout:
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                                finish();
                                return true;
                            default:
                                return true;
                        }
                    }});
                popup.show(); //showing popup menu
            }
        });

        databaseReference.child("Students").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                StudentModel studentModel = dataSnapshot.getValue(StudentModel.class);

                Picasso.get()
                        .load(studentModel.getImageUrl())
                        .error(R.drawable.addphoto)
                        .placeholder(R.drawable.addphoto)
                        .into(circleImageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
    {
        Fragment[] fragments = new Fragment[]
                {
                        new TeachersFragment(),
                        new ChatFragment(),
                        new GameFragment(),
                        new ExamFragment()
                };

        String [] strings = new String[]
                {
                        "المعلمين",
                        "المحادثات",
                        "العب الأن",
                        "الامتحانات"
                };

        @Override
        public Fragment getItem(int position)
        {
            return fragments [position];
        }

        @Override
        public int getCount()
        {
            return fragments.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position)
        {
            return strings [position];
        }
    };

        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed()
    {
        finishAffinity();
    }
}