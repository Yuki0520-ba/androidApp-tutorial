package com.yuki.whowrolelt;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchBook extends AsyncTask<String,Void,String> {

    private WeakReference<TextView> mTitleText;
    private WeakReference<TextView> mAuthortext;


    @Override
    protected String doInBackground(String... strings) {
        //NetworkUtils.getBookInfoでAPIを叩く
        return NetworkUtils.getBookInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        //getBookInho関数からJSON形式のオブジェクト（String）が返ってくる。
        //ここでは帰ってきたオブジェクトをJSON方に変換
        // 使えるように処理し、UIに表示
        try{
            JSONObject jsonObject=new JSONObject(s);
            JSONArray itemsArray =jsonObject.getJSONArray("items");

            int i=0;
            String title =null;
            String authors=null;

            while(i<itemsArray.length() && (authors==null && title==null)){
                JSONObject book=itemsArray.getJSONObject(i);
                JSONObject volumeInfo=book.getJSONObject("volumeInfo");

                try{
                    title=volumeInfo.getString("title");
                    authors=volumeInfo.getString("authors");
                }catch(Exception e){
                    e.printStackTrace();
                }
                i++;
            }
            //TextViewに帰ってきた文字を格納、UIに表示
            if(title!=null && authors!=null){
                mTitleText.get().setText(title);
                mAuthortext.get().setText(authors);
            }else{
                mAuthortext.get().setText("");
                mTitleText.get().setText(R.string.no_results);
            }

        }catch(JSONException e){
            mTitleText.get().setText(R.string.no_results);
            mAuthortext.get().setText("");
            e.printStackTrace();
        }
    }


    FetchBook(TextView titleText,TextView authorText){
        this.mAuthortext=new WeakReference<>(authorText);
        this.mTitleText=new WeakReference<>(titleText);
    }
}
