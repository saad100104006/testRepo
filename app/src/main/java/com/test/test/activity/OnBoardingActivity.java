package com.test.test.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.test.test.R;
import com.test.test.fragment.MainFragment;


public class OnBoardingActivity extends AppCompatActivity {

    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onbording);


        startBtn = (Button) findViewById(R.id.start_button);


        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "proxima_nova_reg.ttf");
        startBtn.setTypeface(custom_font);
        startBtn.setText(getResources().getString(R.string.start));


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(OnBoardingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_left);

            }
        });


    }
}
