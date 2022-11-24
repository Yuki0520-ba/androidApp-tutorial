package com.yuki.testapp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY="com.yuki.testapp3.extra.REPLY";
    private EditText mReply;
    private static final String LOG_TAG=SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intnent =getIntent();
        String message=intnent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView=findViewById(R.id.text_message);
        textView.setText(message);

        mReply=findViewById(R.id.editText_second);
    }

    public void returnReply(View view) {
        String reply=mReply.getText().toString();
        Intent replyIntent=new Intent();
        replyIntent.putExtra(EXTRA_REPLY,reply);

        setResult(RESULT_OK,replyIntent);
        Log.d(LOG_TAG,"End SecondActivity");
        finish();
    }
}