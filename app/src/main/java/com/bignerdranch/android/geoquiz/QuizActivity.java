package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity.java";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private TextView mScoreTextView;
    private int score = 0;
    private TextView mQuestionTextView;
    private Button mFirstButton;
    private Button mSecondButton;
    private Button mThirdButton;
    private Button mFourthButton;
    private EditText mFreeResponse;
    private Button mSubmitButton;




    /*private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_sports, true),
            new TrueFalse(R.string.question_music, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_animal, true),
    };*/

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_sports, true, Question.Q_TYPE.TRUE_FALSE),
            new Question(R.string.question_music, false, Question.Q_TYPE.TRUE_FALSE),
            new Question(R.string.question_africa, false, Question.Q_TYPE.TRUE_FALSE),
            new Question(R.string.question_americas, true, Question.Q_TYPE.TRUE_FALSE),
            new Question(R.string.question_animal, true, Question.Q_TYPE.TRUE_FALSE),
            new Question(R.string.question_science, R.string.question_science_answer, 1, Question.Q_TYPE.MULTI),
            new Question(R.string.question_tv, R.string.question_tv_answer, Question.Q_TYPE.FREE),
    };

    private int mCurrentIndex =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFirstButton = (Button)findViewById(R.id.first_button);
        mSecondButton = (Button)findViewById(R.id.second_button);
        mThirdButton = (Button)findViewById(R.id.third_button);
        mFourthButton = (Button)findViewById(R.id.fourth_button);
        mFreeResponse = (EditText)findViewById(R.id.free_text);
        mSubmitButton = (Button)findViewById(R.id.submit_button);

        invisible();
        //Score Display
        mScoreTextView = (TextView)findViewById(R.id.score_view);
        //Question Display
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        //TextView Listener
        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                    invisible();
                    updateQuestion();
                }

        });
        //Create view for question type



        //Next Button Listener
        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                invisible();
                updateQuestion();

            }
        });
        //Previous Button Listener
        mPrevButton = (Button) findViewById(R.id.previous_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex == 0)
                    mCurrentIndex = mQuestionBank.length - 1;
                else
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                invisible();
                updateQuestion();
            }
        });

        updateQuestion();
    }
    //Check answer to see if incorrect or correct
    private void checkTrueAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueFalse();
        //boolean answerIsTrue = mQuestionBank[mCurrentIndex].getAnswer();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            updateScore();
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
        mTrueButton.setVisibility(View.INVISIBLE);
        mFalseButton.setVisibility(View.INVISIBLE);
    }
    //Check Multiple Choice Answer
    private void checkMultiAnswer(int userPressed){
        int messageResId = 0;
        if(userPressed == mQuestionBank[mCurrentIndex].getAnswer()){
            messageResId = R.string.correct_toast;
            updateScore();
        }else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
        mFirstButton.setVisibility(View.INVISIBLE);
        mSecondButton.setVisibility(View.INVISIBLE);
        mThirdButton.setVisibility(View.INVISIBLE);
        mFourthButton.setVisibility(View.INVISIBLE);

    }
    //Check FreeResponse answer
    private void checkFreeAnswer(String answer){
        int messageResId = 0;
        answer.toLowerCase();
        String checkable = getResources().getString(mQuestionBank[mCurrentIndex].getAnswerTextResId());
        checkable.toLowerCase();
        if(answer.equals(checkable)){
            messageResId = R.string.correct_toast;
            updateScore();
        }else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
        mFreeResponse.setVisibility(View.INVISIBLE);
        mSubmitButton.setVisibility(View.INVISIBLE);


    }
    //Go to next question
    private void updateQuestion(){
        //int question = mQuestionBank[mCurrentIndex].getQuestion();
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        switch (mQuestionBank[mCurrentIndex].getQuestionType()) {
            case TRUE_FALSE:
                trueFalse();
                break;
            case MULTI:
                multipleChoice();
                break;
            case FREE:
                freeResponse();
                break;
        }

    }

    //Change Score
    private void updateScore(){
        score++;
        mScoreTextView.setText(Integer.toString(score));
    }
    //public void setContentView(int layoutResID){
    // }

    public void trueFalse(){
        //True Button Listener

        mTrueButton.setVisibility(View.VISIBLE);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTrueAnswer(true);
            }
        });
        //False Button Listener

        mFalseButton.setVisibility(View.VISIBLE);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTrueAnswer(false);
            }
        });

    }
    public void multipleChoice(){
        String answers = getResources().getString(mQuestionBank[mCurrentIndex].getAnswerTextResId());
        String parts[] = answers.split(",");
        String first = parts[0];
        String second = parts[1];
        String third = parts[2];
        String fourth = parts[3];

        mFirstButton.setText(first);

        mSecondButton.setText(second);

        mThirdButton.setText(third);

        mFourthButton.setText(fourth);
        mFirstButton.setVisibility(View.VISIBLE);
        mSecondButton.setVisibility(View.VISIBLE);
        mThirdButton.setVisibility(View.VISIBLE);
        mFourthButton.setVisibility(View.VISIBLE);

        //Listener
        mFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMultiAnswer(1);
            }
        });
        mSecondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMultiAnswer(2);
            }
        });
        mThirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMultiAnswer(3);
            }
        });
        mFourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMultiAnswer(4);
            }
        });

    }
    public void freeResponse(){
        mFreeResponse.setVisibility(View.VISIBLE);
        mSubmitButton.setVisibility(View.VISIBLE);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = mFreeResponse.getText().toString();
                checkFreeAnswer(answer);
            }
        });
    }

    public void invisible(){
        mTrueButton.setVisibility(View.INVISIBLE);
        mFalseButton.setVisibility(View.INVISIBLE);
        mFirstButton.setVisibility(View.INVISIBLE);
        mSecondButton.setVisibility(View.INVISIBLE);
        mThirdButton.setVisibility(View.INVISIBLE);
        mFourthButton.setVisibility(View.INVISIBLE);
        mFreeResponse.setVisibility(View.INVISIBLE);
        mSubmitButton.setVisibility(View.INVISIBLE);
    }


    //Logs
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
