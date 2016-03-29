package com.example.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.remoteservice.RemoteStub;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private RemoteStub mBinder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startbutton = (Button)this.findViewById(R.id.startbutton);
        startbutton.setOnClickListener(this);

        Button getbutton = (Button)this.findViewById(R.id.getbutton);
        getbutton.setOnClickListener(this);

        Button stopbutton = (Button)this.findViewById(R.id.stopbutton);
        stopbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.startbutton:
                // bindService를 통해 원격서비스 연결시도
                Intent intent = new Intent();
                ComponentName name = new ComponentName(
                        "com.example.remoteservice", // 패키지명
                        "com.example.remoteservice.RemoteService" //원격서비스 클래스명
                );
                intent.setComponent(name);
                bindService(intent, srvConn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.getbutton:
                String packageName = null;
                try
                {
                    packageName = mBinder.getServerPackageName();
                }
                catch (RemoteException e)
                {
                    // 예외처리
                }

                //TextView에 원격서비스의 패키지명을출력한다.
                TextView text = (TextView)findViewById(R.id.packagenametxt);
                text.setText(packageName);
                break;
            case R.id.stopbutton:
                // 원격서비스연결을종료한다.
                this.unbindService(srvConn);
                break;
            default:
                break;
        }
    }

    // 원격서비스연결결과를받는다.
    ServiceConnection srvConn = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            // 원격서비스연결성공
            mBinder= RemoteStub.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0)
        {
            // 원격서비스연결종료
        }
    };
}
