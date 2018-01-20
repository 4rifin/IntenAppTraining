package com.example.admin.intenapp;

import android.content.Intent;
import android.net.Uri;
import android.provider.Telephony;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText websiteEditText;
    private EditText textShare;
    private EditText telpNumber;
    private EditText textSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        websiteEditText = findViewById(R.id.editBrowser);
        textShare = findViewById(R.id.editShare);
        telpNumber = findViewById(R.id.editPhone);
        textSms = findViewById(R.id.editSms);
    }

    public void openLink(View view) {
        //String url = "http://google.com";
        String url = "http://" + (websiteEditText.getText().toString());
        Uri webPage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("Implicits Intents", "Can't Handle This Intens !");
        }
    }

    public void openTextShare(View view) {
        String text = textShare.getText().toString();
        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setChooserTitle("Share This Text Whit: ")
                .setText(text)
                .startChooser();
    }

    public void openTelp(View view) {
        String phoneNumber = String.format("tel: %s", telpNumber.getText().toString());
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse(phoneNumber));

        if (dialIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(dialIntent);
        } else {
            Log.d("Phone Call", "Cannot Call This Number");
        }
    }

    public void openSms(View view) {
        String inputSmsNumber = String.format("smsto: %s", telpNumber.getText().toString());
        String inputSmsText = textSms.getText().toString();

        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse(inputSmsNumber));
        smsIntent.putExtra("sms_body", inputSmsText);
        if (smsIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(smsIntent);
        } else {
            Log.d("SMS : ", "Cant Resolve app for ACTION_SENDTO Intent.");
        }

    }
}
