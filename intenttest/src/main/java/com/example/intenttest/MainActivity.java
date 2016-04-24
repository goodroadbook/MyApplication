package com.example.intenttest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if(checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                    {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 100);
                        Toast.makeText(this, "안드로이드 6.0부터 마시멜로부터 일부 권한에 대해 사용자에게 동의 필요!", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                uri = Uri.parse("tel:01099990000");
                intent = new Intent(Intent.ACTION_CALL, uri);
                startActivity(intent);
                break;
            case R.id.smsbutton:
                uri = Uri.parse("smsto:01099990000");
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "Hi");
                Uri.parse("smsto:01099990000");
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "Hi");
                startActivity(intent);
                break;
            case R.id.mainbutton:
                uri = Uri.parse("mailto:xxxxxxxxx@gamil.com");
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
                break;
            case R.id.webbutton:
                uri = Uri.parse("http://naver.com");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
