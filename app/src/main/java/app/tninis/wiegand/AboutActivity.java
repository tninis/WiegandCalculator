package app.tninis.wiegand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import junit.runner.Version;

import org.w3c.dom.Text;

import app.tninis.wiegand.BuildConfig;
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_about);
        setTitle("About");
        TextView version=(TextView)findViewById(R.id.textView7);
        String versionName = BuildConfig.VERSION_NAME;
        version.setText(version.getText()+" "+versionName);
    }

    public void onClick(View v) {
        Intent emailintent = new Intent(android.content.Intent.ACTION_SEND);
        emailintent.setType("plain/text");
        emailintent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] {"theodor.ninis@gmail.com" });
        emailintent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        emailintent.putExtra(android.content.Intent.EXTRA_TEXT,"");
        startActivity(Intent.createChooser(emailintent, "Send e-mail..."));
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
