package tecno.academy.TecnoAcademy.StudentApp;

import androidx.appcompat.app.AppCompatActivity;
import tecno.academy.TecnoAcademy.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameWon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);
    }
    public void PlayAgain(View view) {
        Intent intent = new Intent(GameWon.this, MainGameActivity.class);
        startActivity(intent);
        finish();
    }
}
