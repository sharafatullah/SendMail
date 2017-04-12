package com.sendmail;

import android.accounts.AuthenticatorException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sendmail.customeViews.GMailSender;

import javax.mail.AuthenticationFailedException;

public class SenaMail extends AppCompatActivity {

    EditText edtto,edtsub,edtbody;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sena_mail);

        edtto=(EditText)findViewById(R.id.edt_to);
        edtsub=(EditText)findViewById(R.id.edt_subject);
        edtbody=(EditText)findViewById(R.id.edt_Body);
        btnSend=(Button)findViewById(R.id.btn_send);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edtto.getText().toString().length() == 0){
                    edtto.setError("Enter Send Email Id");
                }else if(edtsub.getText().toString().length() == 0){
                    edtsub.setError("Enter subject");
                }else if(edtbody.getText().toString().length() == 0){
                    edtbody.setError("Enter Content to send");
                }else{

                    try {
                        GMailSender sender = new GMailSender("sharafatshaikh7@gmail.com", "9833268978");
                        sender.sendMail(edtsub.getText().toString(),
                                edtbody.getText().toString(),
                                "sharafatshaikh7@gmail.com",
                                edtto.getText().toString());

                        clear();
                        Toast.makeText(SenaMail.this,"Mail Send",Toast.LENGTH_LONG).show();
                    } catch (AuthenticationFailedException e) {
                        Log.e("SendMail", e.getMessage(), e);
                    }catch (Exception e){
                        Log.e("SendMail", e.getMessage(), e);
                    }
                }
            }
        });
    }

    private void clear(){
        edtto.setText("");
        edtsub.setText("");
        edtbody.setText("");
    }
}
