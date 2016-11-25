package glansa.tata;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText groupid;
    Button empid,orderid,submit;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sp;
    String text,scanContent,scanFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupid=(EditText)findViewById(R.id.text);
        empid=(Button)findViewById(R.id.button);
        orderid=(Button)findViewById(R.id.button2);
        submit=(Button)findViewById(R.id.button3);
        empid.setOnClickListener(this);
        orderid.setOnClickListener(this);
        submit.setOnClickListener(this);

//        sp = getSharedPreferences(MyPREFERENCES, 0);



    }

    @Override
    public void onClick(View view) {
        if(view==empid)
        {
            Toast.makeText(getApplicationContext(),"enter empid",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, BarcodeScanner.class);
            startActivity(intent);

        }
        else if (view==orderid)
        {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
            Toast.makeText(getApplicationContext(),"enter orderid",Toast.LENGTH_LONG).show();
        }
        else if (view==submit)
        {
            text=groupid.getText().toString();
            sp = getSharedPreferences(MyPREFERENCES, 0);
            SharedPreferences.Editor editor;
            editor = sp.edit();
//            editor.putString("groupid", text);
            Log.e("gid",text);

            editor.putString("groupid", text);
//            editor.putString(Email, e);
            editor.commit();
            Intent i=new Intent(MainActivity.this,Second.class);
            i.putExtra("gid",text);
            i.putExtra("content",scanContent);
//            i.putExtra("gid",scanFormat);

            startActivity(i);
//            i.putExtra("t")
            Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
             scanContent = scanningResult.getContents();
             scanFormat = scanningResult.getFormatName();
//            formatTxt.setText("FORMAT: " + scanFormat);
//            contentTxt.setText("CONTENT: " + scanContent);
            Toast toast = Toast.makeText(getApplicationContext(),
                    scanContent+scanFormat, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
