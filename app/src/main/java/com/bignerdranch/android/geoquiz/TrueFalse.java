package com.bignerdranch.android.geoquiz;

/**
 * Created by Peter on 1/20/2016.
 */
public class TrueFalse {//extends Question {
    private int mQuestion;
    private boolean mTrueQuestion;

    public TrueFalse(int question, boolean trueQuestion) {
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }

    /*public TrueFalse(int textResId, boolean answerTrue) {
        super(textResId, answerTrue);
    }*/

    public int getQuestion() {
        return mQuestion;
    }
    public void setQuestion(int question) {
        mQuestion = question;
    }
    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }
    public void setTrueQuestion(boolean trueQuestion) {
        mTrueQuestion = trueQuestion;
    }
}
