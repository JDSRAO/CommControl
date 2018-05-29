package com.android.tinku.commcontrol;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import static android.app.ActivityManager.*;
import static android.os.Process.killProcess;
import static java.lang.Process.*;


public class MainActivity extends AppCompatActivity {
    boolean loop = false;
    public SharedPreferences Prefs;// = getSharedPreferences(ApplicationConstants.ApplicationPrefs,Context.MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent startUdpService = new Intent(this, UdpService.class);

        Prefs = getSharedPreferences(ApplicationConstants.ApplicationPrefs,Context.MODE_PRIVATE);
        Prefs.edit().putString(ApplicationConstants.DirectionKey,"2").commit();
        Prefs.edit().putBoolean(ApplicationConstants.ServiceStartKey,true).commit();
        this.startService(startUdpService);
        final Button btn1 = findViewById(R.id.button1);
        final Button btn2 = findViewById(R.id.button2);
        final Button btn3 = findViewById(R.id.button3);
        final Button btn4 = findViewById(R.id.button4);
        final Button btn5 = findViewById(R.id.button5);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        String data = "5";

        btn1.setOnTouchListener(new View.OnTouchListener() {
            //@Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent startUdpService = new Intent(MainActivity.this, UdpService.class);
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        try
                        {
                            System.out.println(" pressed ");
                            loop = true;
                            v.performHapticFeedback(1);
                            startUdpService.putExtra("value", "2");
                            Prefs.edit().putString(ApplicationConstants.DirectionKey,"2").commit();
                            Prefs.edit().putBoolean(ApplicationConstants.ServiceStartKey,true).commit();
                            if (Prefs.getBoolean(ApplicationConstants.ServiceStartKey,true))
                            {
                                startService(startUdpService);
                            }
                        }
                        catch (Exception ex)
                        {
                            System.out.println(ex);
                        }
                        finally
                        {
                            return true;
                        }
                        //SendUdpData();

                    case MotionEvent.ACTION_UP:
                        System.out.println(" released ");
                        loop = false;
                        //stopService(startUdpService);
                        v.performHapticFeedback(1);
                        Prefs.edit().putString(ApplicationConstants.DirectionKey,"").commit();
                        //stopUdpService();
                       // startUdpService.putExtra("value", "5");
                        //startService(startUdpService);
                        return true;
                }
                return true;
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
                        startUdpService.putExtra("value", "4");
                        Prefs.edit().putString(ApplicationConstants.DirectionKey,"4").commit();
                        Prefs.edit().putBoolean(ApplicationConstants.ServiceStartKey,true).commit();
                        if (Prefs.getBoolean(ApplicationConstants.ServiceStartKey,true))
                        {
                            startService(startUdpService);
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        System.out.println(" released ");
                        v.performHapticFeedback(1);
                        Prefs.edit().putString(ApplicationConstants.DirectionKey,"").commit();
                        //stopUdpService();
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
                        v.performHapticFeedback(1);
                        startUdpService.putExtra("value", "5");
                        Prefs.edit().putString(ApplicationConstants.DirectionKey,"5");
                        if (!UdpService.Started)
                        {
                            startService(startUdpService);
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        System.out.println(" released ");
                        v.performHapticFeedback(1);
                        Prefs.edit().putString(ApplicationConstants.DirectionKey,"");
                        //stopUdpService();
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
                        v.performHapticFeedback(1);
                        startUdpService.putExtra("value", "6");
                        Prefs.edit().putString(ApplicationConstants.DirectionKey,"6");
                        if (!UdpService.Started)
                        {
                            startService(startUdpService);
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        System.out.println(" released ");
                        v.performHapticFeedback(1);
                        Prefs.edit().putString(ApplicationConstants.DirectionKey,"");
                        //stopUdpService();
                        return true;
                }
                return false;
            }
        });
        btn5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent startUdpService = new Intent(MainActivity.this, UdpService.class);
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println(" pressed ");
                        v.performHapticFeedback(1);
                        startUdpService.putExtra("value", "8");
                        Prefs.edit().putString(ApplicationConstants.DirectionKey,"8");
                        if (!UdpService.Started)
                        {
                            startService(startUdpService);
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        System.out.println(" released ");
                        v.performHapticFeedback(1);
                        Prefs.edit().putString(ApplicationConstants.DirectionKey,"");
                        //stopUdpService();
                        return true;
                }
                return false;
            }
        });


    }

    private void stopUdpService()
    {
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

        Iterator<RunningAppProcessInfo> iter = runningAppProcesses.iterator();

        while(iter.hasNext()){
            RunningAppProcessInfo next = iter.next();

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
