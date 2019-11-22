package tecno.academy.TecnoAcademy.StudentApp;

import androidx.appcompat.app.AppCompatActivity;
import info.hoang8f.widget.FButton;
import tecno.academy.TecnoAcademy.R;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeGameScreen extends AppCompatActivity
{
    Button playGame,quit;
    TextView tQ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_game_screen);

        //the below method will initialize views
        initViews();

        //PlayGame button - it will take you to the MainGameActivity
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainGameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Quit button - This will quit the game
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initViews() {
        //initialize views here
        playGame =findViewById(R.id.playGame);
        quit = findViewById(R.id.quit);
        tQ = (TextView)findViewById(R.id.tQ);

        //Typeface - this is for fonts style
        //Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        //playGame.setTypeface(typeface);
        //quit.setTypeface(typeface);
        //tQ.setTypeface(typeface);
    }
}
