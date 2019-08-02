package com.safety.womensafety;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sms extends AppCompatActivity {

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    EditText number;
    EditText message;
    Button Send;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        number=findViewById(R.id.etsms);
        message=findViewById(R.id.editText2);
        Send=findViewById(R.id.sms);

        Send.setEnabled(false);
        if (checkPermission(Manifest.permission.SEND_SMS)){
            Send.setEnabled(true);
        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},SEND_SMS_PERMISSION_REQUEST_CODE);
        }
    }


    public void fun(View view){
        String phonenumber=number.getText().toString();
        String smsMessage= message.getText().toString();
        if (phonenumber==null || phonenumber.length()==0 || smsMessage==null||smsMessage.length()==0){
            return;
        }

        if (checkPermission(Manifest.permission.SEND_SMS)){
            SmsManager smsManager= SmsManager.getDefault();
            smsManager.sendTextMessage(phonenumber,null,smsMessage,null,null);
            Toast.makeText(this,"Message Sent",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkPermission(String permission){
        int check= ContextCompat.checkSelfPermission(this,permission);
        return(check== PackageManager.PERMISSION_GRANTED);

    }

}
