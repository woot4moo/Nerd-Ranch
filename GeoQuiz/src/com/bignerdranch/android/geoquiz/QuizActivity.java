package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.GeoQuiz.R;

public class QuizActivity extends Activity {

    private static final String TAG = "QuizActivity";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;

    private TextView mQuestionTextView;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
      new TrueFalse(R.string.question_oceans,true),
      new TrueFalse(R.string.question_mideast,false),
      new TrueFalse(R.string.question_africa,false),
      new TrueFalse(R.string.question_americas,true),
      new TrueFalse(R.string.question_asia,true)
    };

    private int mCurrentIndex = 0;

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId = 0;

        if(userPressedTrue == answerIsTrue){
            messageResId=R.string.correct_toast;
        }else{
            messageResId=R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId,Toast.LENGTH_SHORT).show();
    }
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1) %mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1) %mQuestionBank.length;
                updateQuestion();
            }
        });

        mPreviousButton = (Button) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex-1) %mQuestionBank.length;
                if(mCurrentIndex < 0){
                    mCurrentIndex = (mQuestionBank.length-1);
                }
                updateQuestion();
            }
        });
        updateQuestion();
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
}
