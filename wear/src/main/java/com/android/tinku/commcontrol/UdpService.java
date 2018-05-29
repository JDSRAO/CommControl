package com.android.tinku.commcontrol;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpService extends IntentService
{
    public UdpService()
    {
        super("UdpService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        try
        {
            //String data = intent.getDataString();
            String data = intent.getStringExtra("value");

            while (true)
            {
                String messageStr = data;
                int server_port = 55555;
                try {
                    DatagramSocket s = new DatagramSocket();
                    //String local_ip=InetAddress.getLocalHost().getHostAddress();
                    //String[] ip_component = local_ip.split("\\.");
                    //String broadcast=ip_component[0]+"."+ip_component[1]+"."+ip_component[2]+"."+"255";
                    InetAddress local = InetAddress.getByName("192.168.0.255");//broadcast);
                    int msg_length = messageStr.length();
                    byte[] message = messageStr.getBytes();
                    DatagramPacket p = new DatagramPacket(message, msg_length, local, server_port);
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
        catch (Exception ex )
        {

        }
    }
}
