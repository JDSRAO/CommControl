package com.android.tinku.commcontrol;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import static android.os.Process.killProcess;

public class MainActivity extends WearableActivity {
    boolean loop = false;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mTextView = (TextView) findViewById(R.id.textView2);

        final Button btn1 = findViewById(R.id.button5);
        final Button btn2 = findViewById(R.id.button6);
        final Button btn3 = findViewById(R.id.button7);
        final Button btn4 = findViewById(R.id.button8);

        //mTextView.setText("u can see");
        btn1.setOnTouchListener(new View.OnTouchListener() {
            //@Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println(" pressed button");
                Intent startUdpService = new Intent(MainActivity.this, UdpService.class);
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println(" pressed ");
                        startUdpService.putExtra("value", "2");
                        v.performHapticFeedback(1);
                        startService(startUdpService);
                        //SendUdpData();
                        return true;
                    case MotionEvent.ACTION_UP:
                        System.out.println(" released ");
                        //stopService(startUdpService);
                        v.performHapticFeedback(1);
                        stopUdpService();
                        // startUdpService.putExtra("value", "5");
                        //startService(startUdpService);
                        return true;
                }
                return false;
            }
        });

        btn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent startUdpService = new Intent(MainActivity.this, UdpService.class);
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println(" pressed " + btn2.getText());
                        v.performHapticFeedback(1);
                        startUdpService.putExtra("value", "8");
                        startService(startUdpService);
                        return true;
                    case MotionEvent.ACTION_UP:
                        System.out.println(" released ");
                        v.performHapticFeedback(1);
                        stopUdpService();
                        return true;
                }
                return false;
            }
        });
        btn3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent startUdpService = new Intent(MainActivity.this, UdpService.class);
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println(" pressed ");
                        startUdpService.putExtra("value", "6");
                        v.performHapticFeedback(1);
                        startService(startUdpService);
                        return true;
                    case MotionEvent.ACTION_UP:
                        System.out.println(" released ");
                        v.performHapticFeedback(1);
                        stopUdpService();
                        return true;
                }
                return false;
            }
        });
        btn4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent startUdpService = new Intent(MainActivity.this, UdpService.class);
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println(" pressed ");
                        startUdpService.putExtra("value", "4");
                        v.performHapticFeedback(1);
                        startService(startUdpService);
                        return true;
                    case MotionEvent.ACTION_UP:
                        System.out.println(" released ");
                        v.performHapticFeedback(1);
                        stopUdpService();
                        return true;
                }
                return false;
            }
        });
    }

    private void stopUdpService()
    {
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

        Iterator<ActivityManager.RunningAppProcessInfo> iter = runningAppProcesses.iterator();

        while(iter.hasNext()){
            ActivityManager.RunningAppProcessInfo next = iter.next();

            String pricessName = getPackageName() + ":service";

            if(next.processName.equals(pricessName)){
                killProcess(next.pid);
                break;
            }
        }
        return;
    }

    private void SendUdpData()
    {
        while (loop)
        {
            if (!loop)
            {
                break;
            }
            String messageStr="I am Sending!";
            int server_port = 55555;
            try {
                DatagramSocket s = new DatagramSocket();
                InetAddress local = InetAddress.getByName("192.168.0.255");
                int msg_length = messageStr.length();
                byte[] message = messageStr.getBytes();
                DatagramPacket p = new DatagramPacket(message, msg_length, local, server_port);
                s.send(p);
            }catch (SocketException e) {
                e.printStackTrace();
            }catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
