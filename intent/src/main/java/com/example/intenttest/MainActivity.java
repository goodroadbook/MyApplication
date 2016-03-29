package com.example.intenttest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button callbutton = (Button) this.findViewById(R.id.callbutton);
        callbutton.setOnClickListener(this);

        Button smsbutton = (Button) this.findViewById(R.id.smsbutton);
        smsbutton.setOnClickListener(this);

        Button mailbutton = (Button) this.findViewById(R.id.mainbutton);
        mailbutton.setOnClickListener(this);

        Button webbutton = (Button) this.findViewById(R.id.webbutton);
        webbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        Uri uri = null;

        switch (v.getId()) {
            case R.id.callbutton:
                uri = Uri.parse("tel:01073690833");
                intent = new Intent(Intent.ACTION_CALL, uri);
                startActivity(intent);
                break;
            case R.id.smsbutton:
                uri= Uri.parse("smsto:01073690833");
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "Hi");
                startActivity(intent);
                break;
            case R.id.mainbutton:
                uri= Uri.parse("mailto:jjinsama@gamil.com");
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
                break;
            case R.id.webbutton:
                uri= Uri.parse("http://naver.com");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
