package com.example.sh1nji.tenmathquestions;

/**
 * Created by tjin3 on 2017/9/16.
 */

public class Question {
    private int numerator;
    private int denominator;
    private int result;

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getResult() {
        return result;
    }
}

