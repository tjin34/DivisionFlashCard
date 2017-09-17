package com.example.sh1nji.tenmathquestions;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sh1nJi on 2017/9/16.
 */

public class StartActivity extends Activity {

    private int numOfProblems = 0;

    private List<Question> questionList = new ArrayList<>();
    private List<Boolean> results = new ArrayList<>();
    private int currentIndex = 0;
    private Question currentQuestion;

    private ImageView iv_back;
    private TextView tv_numNow, tv_numTotal, tv_numerator, tv_denominator;
    private EditText et_answer;
    private Button btn_submitAndNext;
    private RelativeLayout rootView;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        progressDialog = new ProgressDialog(this);
        numOfProblems = getIntent().getIntExtra("NUMBER", 0);
        initView();
        initData(numOfProblems);
    }

    private void initView() {
        progressDialog.setMessage("Preparing Questions");
        progressDialog.show();

        // View References
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_numNow = (TextView) findViewById(R.id.tv_numNow);
        tv_numTotal = (TextView) findViewById(R.id.tv_numTotal);
        tv_denominator = (TextView) findViewById(R.id.tv_denominator);
        tv_numerator = (TextView) findViewById(R.id.tv_numerator);
        et_answer = (EditText) findViewById(R.id.et_answer);
        btn_submitAndNext = (Button) findViewById(R.id.btn_submitAndNext);
        rootView = (RelativeLayout) findViewById(R.id.rootView);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialog();
            }
        });

        // textView
        tv_numTotal.setText(String.valueOf(numOfProblems));
        tv_numNow.setText(String.valueOf(currentIndex+1));

        // Submit and next button
        btn_submitAndNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = et_answer.getText().toString();
                if (input.equals("")) {
                    Toast.makeText(StartActivity.this, "Your answer is empty!", Toast.LENGTH_SHORT).show();
                } else {
                    int answer = Integer.parseInt(input);
                    if (answer == currentQuestion.getResult()) {
                        results.add(true);
                    } else {
                        results.add(false);
                    }
                    if (currentIndex == questionList.size()-1) {
                        finish();
                        Toast.makeText(StartActivity.this, countCorrect(), Toast.LENGTH_LONG).show();
                    } else {
                        currentIndex += 1;
                        tv_numTotal.setText(String.valueOf(numOfProblems));
                        tv_numNow.setText(String.valueOf(currentIndex+1));
                        currentQuestion = questionList.get(currentIndex);
                        tv_numerator.setText(String.valueOf(currentQuestion.getNumerator()));
                        tv_denominator.setText(String.valueOf(currentQuestion.getDenominator()));
                        et_answer.setText("");
                    }
                }
            }
        });


        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });


    }

    private void initData(int num) {
        for (int i = 0; i < num; i++) {
            Question question = new Question();
            Random random= new Random();
            question.setResult(random.nextInt(9)+1);
            question.setDenominator(random.nextInt(20) + 10);
            question.setNumerator(question.getResult() * question.getDenominator());
            questionList.add(question);
        }

        currentQuestion = questionList.get(currentIndex);
        tv_numerator.setText(String.valueOf(currentQuestion.getNumerator()));
        tv_denominator.setText(String.valueOf(currentQuestion.getDenominator()));

        progressDialog.dismiss();
    }

    private void initDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this).setCancelable(true);
        dialog.setMessage("Quitting this run?");
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    private String countCorrect(){
        int corrects = 0;
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i) == true) {
                corrects += 1;
            }
        }
        return "You got "+corrects+" out of "+numOfProblems+" questions correctly!";
    }

}
