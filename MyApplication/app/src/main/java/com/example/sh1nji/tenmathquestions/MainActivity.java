package com.example.sh1nji.tenmathquestions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends Activity {

    private RadioGroup radioGroup;
    private Button btn_start;

    private int numOfProblems = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){

        // RadioGroup => Number of Problems on one run
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn_five:
                        numOfProblems = 5;
                        break;
                    case R.id.btn_ten:
                        numOfProblems = 10;
                        break;
                    case R.id.btn_twenty:
                        numOfProblems = 20;
                        break;
                }
            }
        });
        // default check on option of 10
        radioGroup.check(R.id.btn_ten);

        // Start Button
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                intent.putExtra("NUMBER", numOfProblems);
                startActivity(intent);
            }
        });
    }
}
