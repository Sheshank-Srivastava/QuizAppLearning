package com.example.quizapplearning;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.security.AllPermission;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private TextView mTxtQuestion, mQuizStatsTextView;
    private Button btnTrue;
    private Button btnWrong;
    private ProgressBar mProgressBar;

    private int mQuestionIndex;
    private QuizModel mTempQuestion;

    private int mUserScore;

    private QuizModel[] questionCollection = new QuizModel[]{
            new QuizModel(R.string.q1, true),
            new QuizModel(R.string.q2, false),
            new QuizModel(R.string.q3, false),
            new QuizModel(R.string.q4, true),
            new QuizModel(R.string.q5, false),
            new QuizModel(R.string.q6, true),
            new QuizModel(R.string.q7, true),
            new QuizModel(R.string.q8, true),
            new QuizModel(R.string.q9, false),
            new QuizModel(R.string.q10, true),
    };
    final int USER_PROGRESS = (int) Math.ceil(100.0/questionCollection.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtQuestion = findViewById(R.id.txtQuestion);
        mTempQuestion = questionCollection[mQuestionIndex];
        mTxtQuestion.setText(mTempQuestion.getmQuestion());

        mProgressBar = findViewById(R.id.quizPB);
        mQuizStatsTextView = findViewById(R.id.txtQuizStats);

        btnTrue = findViewById(R.id.btnTrue);
        btnWrong = findViewById(R.id.btnWrong);

        btnTrue.setOnClickListener(this);
        btnWrong.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTrue:
                evaluateUsersAnswer(true);
                break;
            case R.id.btnWrong:
                evaluateUsersAnswer(false);
                break;
        }
        mQuestionIndex = (mQuestionIndex + 1) % 10;
        if (mQuestionIndex == 0){
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
            quizAlert.setTitle("The quiz is finished");
            quizAlert.setMessage("Your score is "+mUserScore);
            quizAlert.setCancelable(false);
            quizAlert.setPositiveButton("Finished the Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            quizAlert.show();
        }
        mTempQuestion = questionCollection[mQuestionIndex];
        mTxtQuestion.setText(mTempQuestion.getmQuestion());
        mProgressBar.incrementProgressBy(USER_PROGRESS);
    }

    private void evaluateUsersAnswer(boolean userGuess) {
        int feedBackMessage = userGuess == mTempQuestion.ismAnswer() ? R.string.correct_toast_message : R.string.incorrect_toast_message;

        if (feedBackMessage == R.string.correct_toast_message) {
            mUserScore += 1;
        }
        mQuizStatsTextView.setText(String.valueOf(mUserScore));
        Toast.makeText(this, feedBackMessage, Toast.LENGTH_SHORT).show();
    }
}
