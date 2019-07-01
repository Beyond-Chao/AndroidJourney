package com.example.a05_uiwidgettest;

public class Question {

    private int mTextResId;

    private boolean mAnswerTrue;

    private boolean mCheatAnswer;

    public Question(int mTextResId, boolean mAnswerTrue) {
        this.mTextResId = mTextResId;
        this.mAnswerTrue = mAnswerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean isCheatAnswer() {
        return mCheatAnswer;
    }

    public void setCheatAnswer(boolean cheatAnswer) {
        mCheatAnswer = cheatAnswer;
    }
}
