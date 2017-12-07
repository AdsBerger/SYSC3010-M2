package com.example.haohanzhang.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.media.MediaPlayer;
import android.widget.Toast;


import com.jupiter.calculator.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Button btn_5s, btn_10s, btn_5min, btn_clear, btn_send;
    EditText et_input;
    private String[] Sender = {"172.20.10.3","8006"};
    private UDPSender sender = new UDPSender(Sender);
    private String[] Receiver = {"RPiIP"};
    private UDPReceiver receiver = new UDPReceiver(Receiver);
    public String output;
    private MediaPlayer mediaPlayer;//MediaPlayer对象
    private MyCount mc;
    private Boolean Onprocess = false;



    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_5s = findViewById(R.id.btn_5s);
        btn_10s = findViewById(R.id.btn_10s);
        btn_5min = findViewById(R.id.btn_5min);
        btn_clear = findViewById(R.id.btn_clear);
        btn_send = findViewById(R.id.btn_send);
        et_input = findViewById(R.id.et_input);

        btn_5s.setOnClickListener(this);
        btn_10s.setOnClickListener(this);
        btn_5min.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btn_send.setOnClickListener(this);
    }


    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_5s:
                add5s();
                et_input.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_10s:
                add10s();
                et_input.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_5min:
                add5min();
                et_input.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_clear:
                clear();
                et_input.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_send:
                sendMessage();

                break;
        }
    }


    private void sendMessage()
    {
        if(!Onprocess)
        {
            sender.send(output);
            if (output.equals("5 seconds")) {
                mc = new MyCount(5000, 1000);
                mc.start();
                Onprocess = true;
            } else if (output.equals("10 seconds")) {
                mc = new MyCount(10000, 1000);
                mc.start();
                Onprocess = true;
            } else if (output.equals("5 minutes")) {
                mc = new MyCount(300000, 1000);
                mc.start();
                Onprocess = true;
            }
        }

    }

    private void ReceiveMessage()
    {
        String Data = receiver.Receive();
        play(Data);

    }


    private void add5s()
    {
        if(!Onprocess)
        {
            output = "5 seconds";
            et_input.setText(output);
        }
    }

    private void add10s()
    {
        if(!Onprocess)
        {
            output = "10 seconds";
            et_input.setText(output);}
    }

    private void add5min()
    {
        if(!Onprocess)
        {
            output = "5 minutes";
            et_input.setText(output);}
    }


    private void clear()
    {
        if(!Onprocess)
        {
            output = " ";
            et_input.setText(output);}
    }

    private void play(String data)
    {
        mediaPlayer = MediaPlayer.create(this,R.raw.music);
        mediaPlayer.start();
        new AlertDialog.Builder(MainActivity.this).setTitle("Alarm").setMessage(data).setPositiveButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                mediaPlayer.stop();
                MainActivity.this.finish();
            }
        }).show();
    }


    class MyCount extends CountDownTimer
    {
        public MyCount(long milli, long Interval)
        {
            super(milli,Interval);
        }

        public void onFinish()
        {
            et_input.setText("Work Complete");
            play("Work Complete,press‘cancel’ to quit");
            Onprocess = false;
        }

        public void onTick(long l)
        {
            et_input.setText("In Process");
            Toast.makeText(MainActivity.this, l/1000+" seconds remaining", Toast.LENGTH_LONG).show();
        }
    }


}

