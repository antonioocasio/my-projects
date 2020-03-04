package edu.lewisu.cs.antonioocasio.quizapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private TextView questionTextView;
    private ImageButton nextButtonImg;
    private ImageButton previousButtonImg;

    private Question[] questions = new Question[]{
            new Question(R.string.question1, true),
            new Question(R.string.question2, false),
            new Question(R.string.question3, true)
    };

    private int currentIndex = 0;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate called");

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButtonImg = findViewById(R.id.next_button);
        previousButtonImg = findViewById(R.id.previous_button);

        questionTextView = findViewById(R.id.question_text_view);


        trueButton.setOnClickListener(new TrueButtonClickListener());
        falseButton.setOnClickListener(new FalseButtonClickListener());

        nextButtonImg.setOnClickListener(new NextButtonClickListener());
        previousButtonImg.setOnClickListener(new PreviousButtonClickListener());

        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt("question");
        }

        updateQuestion();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState called");
        outState.putInt("question", currentIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }

    private void updateQuestion(){
        int question = questions[currentIndex].getTextResId();
        questionTextView.setText(question);
    }

    private void checkAnswer(boolean userPress){
        boolean correctAnswer = questions[currentIndex].isAnswerTrue();
        int toastTextId;
        if (userPress == correctAnswer){
            toastTextId = R.string.correct;
        } else {
            toastTextId = R.string.incorrect;
        }
        Toast.makeText(this, toastTextId, Toast.LENGTH_SHORT).show();
    }

    private class TrueButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            checkAnswer(true);
        }
    }

    private class FalseButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            checkAnswer(false);
        }
    }

    private class NextButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            currentIndex = (currentIndex + 1) % questions.length;
            updateQuestion();
        }
    }

    private class PreviousButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            currentIndex -= 1;
            if(currentIndex < 0){
                currentIndex = questions.length - 1;
            }
            updateQuestion();
        }
    }
}
