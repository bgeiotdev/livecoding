package com.android.example.signinform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.example.signinform.data.AccountManager;
import com.android.example.signinform.data.User;

public class ConfirmSignIn extends AppCompatActivity {

    private TextView signInInfo;
    private  String strEmail;
    private  String strLastName;
    private  String strFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_sign_in);
        signInInfo = (TextView) findViewById(R.id.extra_msg);


        confirmIntentDisplay();


        Button btnConfirm = (Button) findViewById(R.id.btn_confirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (strEmail !=null || strLastName !=null||strFirstName !=null ){
                     AccountManager am = AccountManager.getInstance();
                     User newUser = new User(strEmail,strLastName,strFirstName);
                     am.addUser(newUser);
                     Intent data = new Intent();
                     data.putExtra(MainActivity.SIGN_IN_RESULT_DATA_KEY,true);
                     data.putExtra(MainActivity.USER_KEY,newUser.getEmail());

                     ConfirmSignIn.this.setResult(MainActivity.SIGN_IN_RESULT,data);
                     finish();
                 }


            }
        });



        Button btnCancel= (Button) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(MainActivity.SIGN_IN_RESULT_DATA_KEY,false);

                ConfirmSignIn.this.setResult(MainActivity.SIGN_IN_RESULT,data);
                finish();
            }
        });

    }


    private void confirmIntentDisplay(){
        Intent startingIntent  = getIntent();
        signInInfo.append(getString(R.string.signin_msg));

        if (startingIntent.hasExtra(MainActivity.EMAIL_KEY)){
            strEmail = startingIntent.getStringExtra(MainActivity.EMAIL_KEY);
            if (strEmail !=null){
                signInInfo.append("email :  " + strEmail +"\n\n");
            }
        }


        if (startingIntent.hasExtra(MainActivity.LASTNAME_KEY)){
            strLastName = startingIntent.getStringExtra(MainActivity.LASTNAME_KEY);
            if (strLastName !=null){
                signInInfo.append("last name :  " + strLastName +"\n\n");
            }
        }


        if (startingIntent.hasExtra(MainActivity.FIRSTNAME_KEY)){
            strFirstName = startingIntent.getStringExtra(MainActivity.FIRSTNAME_KEY);
            if (strFirstName !=null){
                signInInfo.append("first name :  " + strFirstName +"\n\n");
            }
        }
    }
}
