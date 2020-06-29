package tecno.academy.TecnoAcademy.StudentApp;

import androidx.appcompat.app.AppCompatActivity;
import tecno.academy.TecnoAcademy.R;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayGame extends AppCompatActivity
{
    Button playAgain;
    TextView wrongAnsText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        //Initialize
        playAgain =  findViewById(R.id.playAgainButton);
        wrongAnsText = findViewById(R.id.wrongAns);

        //play again button onclick listener
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainGameActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
