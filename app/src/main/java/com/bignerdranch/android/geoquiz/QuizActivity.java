package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private TextView mScoreTextView;
    private int score = 0;
    private TextView mQuestionTextView;


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
        setContentView(R.layout.activity_quiz);

        //Score Display
        mScoreTextView = (TextView)findViewById(R.id.score_view);
        //Question Display
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        //TextView Listener
        mQuestionTextView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                    updateQuestion();
                }

        });
        //Create view for question type
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


        //Next Button Listener
        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
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
    }
    //Go to next question
    private void updateQuestion(){
        //int question = mQuestionBank[mCurrentIndex].getQuestion();
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
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
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTrueAnswer(true);
            }
        });
        //False Button Listener
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTrueAnswer(false);
            }
        });
    }
    public void multipleChoice(){
        //True Button Listener
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTrueAnswer(true);
            }
        });
        //False Button Listener
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTrueAnswer(false);
            }
        });
    }
    public void freeResponse(){
        //True Button Listener
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTrueAnswer(true);
            }
        });
        //False Button Listener
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTrueAnswer(false);
            }
        });
    }
}
