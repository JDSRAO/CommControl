package com.android.tinku.commcontrol;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpService extends IntentService
{
    public static boolean Started = false;
    public UdpService()
    {
        super("UdpService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        try
        {
            if (android.os.Debug.isDebuggerConnected()) {
                android.os.Debug.waitForDebugger();
            }
            SharedPreferences Prefs = getSharedPreferences(ApplicationConstants.ApplicationPrefs, Context.MODE_PRIVATE);
            DatagramSocket s = new DatagramSocket();
            //String local_ip=InetAddress.getLocalHost().getHostAddress();
            String local_ip = "192.168.43.103";
            String[] ip_component = local_ip.split("\\.");
            String broadcast=ip_component[0]+"."+ip_component[1]+"."+ip_component[2]+"."+"255";
            InetAddress local = InetAddress.getByName(broadcast);
            while (true)
            {
                Started = true;
                String data = Prefs.getString(ApplicationConstants.DirectionKey,"");

                if (data != "")
                {
                    String messageStr = data;
                    int server_port = 55555;
                    try {
                        int msg_length = messageStr.length();
                        byte[] message = messageStr.getBytes();
                        DatagramPacket p = new DatagramPacket(message, msg_length, local, server_port);
                        p.setData(message);
                        s.send(p);
                        System.out.println(" pressed in service ");
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
        catch (Exception ex )
        {
            System.out.println(ex);
        }
    }
}
