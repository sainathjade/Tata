package glansa.tata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;



public class Second extends AppCompatActivity {
    TextView gid,empid,orderid;
    SharedPreferences sp;
    public static final String MY_PREF = "MyPreferences";
    String Groupid,content;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondscreen);
        Intent intent = getIntent();
        gid=(TextView)findViewById(R.id.textView7);
        orderid=(TextView)findViewById(R.id.textView9);
//        gid=(TextView)findViewById(R.id.textView7);
        sp = getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
//        Groupid = intent.getStringExtra("gid");
        Groupid = sp.getString("gid", "");
        content = intent.getStringExtra("content");
        Log.e("gidss",Groupid);
        gid.setText(Groupid);
        orderid.setText(content);
//        sp = getSharedPreferences(MY_PREF, 0);


    }
}
