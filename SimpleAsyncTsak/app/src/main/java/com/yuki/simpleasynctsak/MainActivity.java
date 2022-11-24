package com.yuki.simpleasynctsak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TEXT_STATE="currentText";
    private TextView mTextView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView=(TextView) findViewById(R.id.textView1);
        if(savedInstanceState!=null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    public void startTask(View view) {
        mTextView.setText(R.string.napping);
        //サブクラスをインスタンス化。execute関数を呼び出すことで非同期処理を実行する。
        //その際にTextViewを引数としてSimpleAsnycTaskクラスのインスタンスを作成
        new SimpleAsnycTask(mTextView).execute();

    }
    //デバイスの回転などの構成変更に応じてアクティビティが破棄されたときに、TextViewのコンテンツを保持する
    protected void onSavedInstanceStace(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE,mTextView.getText().toString());

    }
}