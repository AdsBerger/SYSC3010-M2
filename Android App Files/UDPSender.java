package com.example.haohanzhang.myapplication;

import java.io.IOException;
import java.net.*;

public class UDPSender {

    private InetAddress host;
    private  int port;
    public DatagramSocket socket;
    public DatagramPacket packet;
    byte [] data;


    public UDPSender(String[] args)
    {
        if( args.length != 2 )
        {
            System.out.println( "usage: java UDPSender host port" ) ;
        }
        socket = null ;
        try
        {
            // Convert the arguments first, to ensure that they are valid
            host = InetAddress.getByName( args[0] ) ;
            port = Integer.parseInt( args[1] ) ;
            socket = new DatagramSocket() ;}

            catch( Exception e ){
                System.out.println( e ) ;
            }
        //finally
          //  {
            //    if( socket != null )
              //      socket.close() ;
           // }
    }


    public void send(String args)
    {
        try{
        data = args.getBytes() ;
        packet = new DatagramPacket( data, data.length, host, port ) ;

            new Thread()
            {
                public void run() {
                    try {
                        socket.send(packet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

        }
        catch( Exception e ){
            System.out.println( e ) ;
        }
    }
}
