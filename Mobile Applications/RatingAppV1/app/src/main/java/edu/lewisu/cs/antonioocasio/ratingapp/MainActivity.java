package edu.lewisu.cs.antonioocasio.ratingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private RatingBar gameRatingBar;
    private Button submitButton;
    private GameRating gameRating;
    private EditText gameNameEditText, commentEditText, gameTypeEditText;
    private Spinner ConsoleTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.submitButton);
        gameRatingBar = findViewById(R.id.gameRatingBar);
        gameNameEditText = findViewById(R.id.gameNameEditText);
        commentEditText = findViewById(R.id.gameReviewEditText);
        gameTypeEditText = findViewById(R.id.gameTypeEditText);
        ConsoleTypeSpinner = findViewById(R.id.gameTypeSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.consoles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ConsoleTypeSpinner.setAdapter(adapter);
        ConsoleTypeSpinner.setOnItemSelectedListener(this);

        submitButton.setOnClickListener(new SubmitButtonClickListener());

        gameRatingBar.setOnRatingBarChangeListener(new RatingChangedListener());

        gameRating = new GameRating();

    }

    private class RatingChangedListener implements RatingBar.OnRatingBarChangeListener{
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b){
            gameRatingBar.setRating((int) v);
        }
    }

    private class SubmitButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v){
            gameRating.setGameName(gameNameEditText.getText().toString());
            gameRating.setGameType(gameTypeEditText.getText().toString());
            gameRating.setConsoleType(ConsoleTypeSpinner.getSelectedItem().toString());
            gameRating.setComment(commentEditText.getText().toString());
            gameRating.setRating(gameRatingBar.getRating());
            Toast.makeText(getApplicationContext(), gameRating.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
