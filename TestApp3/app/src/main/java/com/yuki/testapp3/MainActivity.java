package com.yuki.testapp3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG=MainActivity.class.getSimpleName();
    public static final String EXTRA_MESSAGE="com.yuki.testapp3.extra.MESSAGE";

    private EditText mMessegeEditText;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;
    public static final int TEXT_REQUEST=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessegeEditText=findViewById(R.id.editText_main);
        mReplyHeadTextView=findViewById(R.id.text_header_reply);
        mReplyTextView=findViewById(R.id.text_message_reply);

        if(savedInstanceState!=null){
            boolean isVisible=savedInstanceState.getBoolean("reply_visible");
            if(isVisible){
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(savedInstanceState.getString("reply_text"));
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }


        Log.d(LOG_TAG,"_____________");
        Log.d(LOG_TAG,"onCreate");

    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG,"onStart");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }
    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }


    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG,"button clicked");
        //SecondActivityクラスへのIntnent を作成
        Intent intent=new Intent(this,SecondActivity.class);

        //EditTextの文字情報を取得、String型に変換し、インテントに入れる
        String message=mMessegeEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivityForResult(intent,TEXT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==TEXT_REQUEST){
            if (resultCode==RESULT_OK){
                String reply=data.getStringExtra(SecondActivity.EXTRA_REPLY);

                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setVisibility(View.VISIBLE);

                mReplyTextView.setText(reply);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mReplyHeadTextView.getVisibility()==View.VISIBLE){
            outState.putString("reply_text",mReplyTextView.getText().toString());
        }

    }

}