package com.yuki.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_toast).setOnClickListener(click1);
        findViewById(R.id.button1).setOnClickListener(click2);

    }


    public View.OnClickListener click1=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ImageView image_woman = (ImageView) findViewById(R.id.uruuru);
            if(image_woman.isShown()) {
                image_woman.setVisibility(View.INVISIBLE);
                Log.d("MainActivitty","hello worman");
            }else{
                image_woman.setVisibility(View.VISIBLE);
            }
        }
    };

    public View.OnClickListener click2=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView text_count=findViewById(R.id.textView);
            int count=Integer.parseInt(text_count.getText().toString())+1;

            text_count.setText(String.valueOf(count));

        }
    };


}



