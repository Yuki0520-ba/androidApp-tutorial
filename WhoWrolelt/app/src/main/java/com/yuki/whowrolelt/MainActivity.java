package com.yuki.whowrolelt;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.input.InputManager;
import android.media.browse.MediaBrowser;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.net.ConnectException;

public class MainActivity extends AppCompatActivity {
    private EditText mBookInput;
    private TextView mTitletext;
    private TextView mAuthortext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput=(EditText)findViewById(R.id.bookInput);
        mTitletext=(TextView)findViewById(R.id.titleText);
        mAuthortext=(TextView)findViewById(R.id.authorText);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void searchBooks(View view) {
        String queryString=mBookInput.getText().toString();

        //キーボードの入力がない時はキーボードを隠す
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if(inputManager==null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        //ネットワークの接続を確認する
        //ネットワークが条件を満たしてなかった時の処理を記述
        ConnectivityManager connMgr=(ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo=null;

        if(connMgr!=null) {
            netInfo = connMgr.getActiveNetworkInfo();
        }
        //ネットワークの情報があり、接続を確認できる、文字入力がある時
        if(netInfo!=null && netInfo.isConnected() && queryString.length()!=0){
            //２つのTextViewをコントラスタの引数にし、FetchBookクラスを作成
            //非同期処理の実行
            new FetchBook(mTitletext,mAuthortext).execute(queryString);
            //結果を表示するまで『Loading』を表示
            mAuthortext.setText("");
            mTitletext.setText(R.string.loading);
        }else{
            //文字入力がない時
            if(queryString.length()==0){
                mAuthortext.setText("");
                mTitletext.setText(R.string.no_search_term);
            }
            //ネットワークにエラーが起きている、接続が不安定の時
            else{
                mAuthortext.setText("");
                mTitletext.setText(R.string.no_network);
            }
        }

    }

}
