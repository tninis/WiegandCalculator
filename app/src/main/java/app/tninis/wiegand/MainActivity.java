package app.tninis.wiegand;

import android.content.Intent;
import android.content.IntentSender;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    EditText CN;
    EditText FC;
    TextView HexRes;
    TextView DecRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Wiegand Calc - 26bit Format");
        ActionBar actionBar =getSupportActionBar();

        CN=(EditText)findViewById(R.id.CN);
        FC=(EditText)findViewById(R.id.FC);
        HexRes=(TextView)findViewById(R.id.textView5);
        DecRes=(TextView)findViewById(R.id.textView6);

        final Button button = findViewById(R.id.btn_calc);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if(CN.getText().toString().matches("")) {
                    Toast.makeText(MainActivity.this, "Card Number Cannot Be Empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(FC.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this, "Facility Code Cannot Be Empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                String CNbin=String.format("%16s", Integer.toBinaryString(Integer.parseInt(CN.getText().toString()))).replace(' ', '0');
                String FCbin=String.format("%8s", Integer.toBinaryString(Integer.parseInt(FC.getText().toString()))).replace(' ', '0');
                //HexRes.setText(CNbin);
                //DecRes.setText(FCbin);

                String combine=FCbin+CNbin;
                char[] CN_FN=combine.toCharArray();
                int counter=0;
                int evenParity=0;
                int oddParity=0;

                for (int i=0;i<12;i++)
                {
                    if(CN_FN[i]=='1')
                        counter++;
                }
                if(counter%2==1)
                    evenParity=1;

                counter=0;
                for (int i=12;i<24;i++)
                {
                    if(CN_FN[i]=='1')
                        counter++;
                }

                if(counter%2==0)
                    oddParity=1;

String test1=evenParity+""+combine+""+oddParity;

                HexRes.setText(new BigInteger(test1, 2).toString(16).toUpperCase());
                DecRes.setText(new BigInteger(test1, 2).toString(10));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.action_about:
            Intent intentInfo = new Intent(this, AboutActivity.class);
            startActivity(intentInfo);
            return(true);
        case R.id.action_info:
            Intent intentAbout = new Intent(this, InfoActivity.class);
            startActivity(intentAbout);
            return(true);

    }
        return(super.onOptionsItemSelected(item));
    }


}
