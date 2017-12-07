package com.example.haohanzhang.myapplication;

import java.net.*;

public class UDPReceiver {

    private final static int PACKETSIZE = 100 ;
    private int port;
    private DatagramSocket socket;

    public UDPReceiver(String args[])
    {
        if( args.length != 1 )
        {
            System.out.println( "usage: UDPReceiver port" ) ;
        }
        try
        {
            port = Integer.parseInt( args[0] ) ;
            socket = new DatagramSocket(port);}
            catch( Exception e )
            {
                System.out.println( e ) ;
            }


    }

    public String Receive()
    {
        try
        {
            for( ;; )
            {
                DatagramPacket packet = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;
                socket.receive(packet) ;
                return new String(packet.getData()).trim();
            }
        }
        catch(Exception e)
        {
            System.out.println(e) ;
            return null;
        }
    }

}


