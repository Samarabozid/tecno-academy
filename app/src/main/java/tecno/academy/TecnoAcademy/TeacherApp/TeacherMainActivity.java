package tecno.academy.TecnoAcademy.TeacherApp;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import tecno.academy.TecnoAcademy.R;
import tecno.academy.TecnoAcademy.SignUpActivity;
import tecno.academy.TecnoAcademy.TeacherApp.Fragments.MyChatsFragment;
import tecno.academy.TecnoAcademy.TeacherApp.Fragments.MyExamFragment;
import tecno.academy.TecnoAcademy.TeacherApp.Fragments.MyGroupsFragment;
import tecno.academy.TecnoAcademy.TeacherApp.Fragments.MyPdfFragment;
import tecno.academy.TecnoAcademy.TeacherApp.Fragments.MySkypeFragment;
import tecno.academy.TecnoAcademy.TeacherApp.Fragments.MyStudentsFragment;

public class TeacherMainActivity extends AppCompatActivity
{
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentPagerAdapter fragmentPagerAdapter;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        floatingActionButton = findViewById(R.id.add_pdf_fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PopupMenu popup = new PopupMenu(TeacherMainActivity.this, floatingActionButton);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.teacher_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        switch (item.getItemId())
                        {
                            case R.id.add_teacher:
                                Intent intent = new Intent(getApplicationContext(), AddPdfActivity.class);
                                startActivity(intent);
                                return true;
                            case R.id.add_gp:
                                Intent intent3 = new Intent(getApplicationContext(), AddGroupActivity.class);
                                startActivity(intent3);
                                return true;

                            case R.id.add_exam:
                                Intent intent4 = new Intent(getApplicationContext(), AddExamActivity.class);
                                startActivity(intent4);
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

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            Fragment [] fragments = new Fragment[]
                    {
                            new MyPdfFragment(),
                            new MyChatsFragment(),
                            new MyGroupsFragment(),
                            new MySkypeFragment(),
                            new MyExamFragment(),
                            new MyStudentsFragment()
                    };

            String [] strings = new String[]
                    {
                            "ملفاتي",
                            "المحادثات",
                            "مجموعاتي",
                            "شرح أونلاين",
                            "امتحاناتى",
                            "طلابى"
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
