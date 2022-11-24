package com.yuki.testapp4;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


//暗黙的インテントの実装
//・入力されたURLのwebサイトの表示
//・入力された場所のGoogle mapの表示
//・シェアするかどうか、誰にシェアするかどうかの表示
//これらの処理を暗黙的インテントで行う
//一部コードが動かなかったのでネットでググったものを代用


public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEditText;
    private EditText mLocationEditTexit;
    private EditText mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebsiteEditText=findViewById(R.id.website_edittext);
        mLocationEditTexit=findViewById(R.id.location_edittext);
        mShareTextEditText=findViewById(R.id.share_edittext);
    }


    //・入力されたURLのwebサイトの表示
    public void openWebsite(View view) {
        String url=mWebsiteEditText.getText().toString();

    //ウェブサイト上のコード(https://android.keicode.com/basics/intent-type.php)
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);

     //チュートリアル上のコード
 /*
        Uri webpage=Uri.parse(url);
        Intent intent =new Intent(android.content.Intent.ACTION_VIEW,webpage);

        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else{
            Log.d("ImplictIntents","Can`t handle this!");
        }
  */

    }

    //・入力された場所のGoogle mapの表示
    public void openLocation(View view) {
        String loc=mLocationEditTexit.getText().toString();
        Uri addressUri=Uri.parse("geo:0,0?q="+loc);

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?q="+loc));
        startActivity(intent);

        /*
        Intent intent =new Intent(Intent.ACTION_VIEW,addressUri);

        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else {
            Log.d("ImplictIntents", "Can`t handle this intent!");
        }
        */
    }


    //・シェアするかどうか、誰にシェアするかどうかの表示
    public void shareText(View view) {
        String txt=mShareTextEditText.getText().toString();
        String mimeType="text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with:")
                .setText(txt)
                .startChooser();
    }

}