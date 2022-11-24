package com.yuki.simpleasynctsak;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsnycTask extends AsyncTask <Void,Void,String> {

    private WeakReference<TextView> mTextView;

    public SimpleAsnycTask(TextView tv) {
        this.mTextView = new WeakReference<>(tv);
    }

    //メインの非同期処理を記述する関数
    @Override
    protected String doInBackground(Void... voids) {
        Random r=new Random();
        int n=r.nextInt(11);
        int s=n*200;

        try{
            Thread.sleep(s);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        //onPostExecuteに値を渡す
        return "awake at lsat sleep for "+s+"milliseconds!";
    }

    //引数：結果(doInBackgroundの戻り値）
    //TextViewに結果を表示
    protected void onPostExecute(String result ){
        mTextView.get().setText(result);
    }
}
