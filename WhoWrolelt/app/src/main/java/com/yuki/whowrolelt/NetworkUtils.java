package com.yuki.whowrolelt;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG=NetworkUtils.class.getSimpleName();

    // GETで送るパラーメータ達
    private static final String BOOK_BASE_URL="https://www.googleapis.com/books/v1/volumes?";
    private static final String QUERY_PARM="q";
    private static final String MAX_RESULTS="maxResults";
    private static final String PRINT_TYPE="printType";


    /*
    APIを叩く。
    引数：検索する書籍のキーワード<String>
    戻り値：変えてきたJSON形式の文字列<String>
     */
    static String getBookInfo(String queryString){
        HttpURLConnection urlCorrection=null;
        BufferedReader reader =null;
        String bookJSONString=null;

        try{
            //URIを生成、URLに変換
            Uri builtUri=Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARM,queryString)
                    .appendQueryParameter(MAX_RESULTS,"10")
                    .appendQueryParameter(PRINT_TYPE,"books")
                    .build();

            URL requestsURL=new URL(builtUri.toString());

            //リクエストを行う
            urlCorrection=(HttpURLConnection) requestsURL.openConnection();
            urlCorrection.setRequestMethod("GET");
            urlCorrection.connect();

            InputStream inputStream=urlCorrection.getInputStream();
            reader =new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder=new StringBuilder();

            //１行づつ読み込む
            String line;
            while((line=reader.readLine())!=null){
                builder.append(line);
                //「¥n」をつけるとデバックが楽になる。なくても結果には影響なし
                builder.append("\n");
            }
            if(builder.length()==0){
                return null;
            }

            bookJSONString=builder.toString();

        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(urlCorrection!=null){
                urlCorrection.disconnect();
            }
            if (reader!=null){
                try{
                    reader.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG,bookJSONString);

        return bookJSONString;
    }
}
