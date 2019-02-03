package com.android.example.signinform;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.example.signinform.data.AccountManager;
import com.android.example.signinform.data.User;

public class MainActivity extends AppCompatActivity {

    public static final String USER_KEY = "userKey";
    private EditText email;
    private EditText lastname;
    private EditText firstname;
    private TextView confirmResult;

    public static final int SIGN_IN_RESULT = 101;
    public static final String SIGN_IN_RESULT_DATA_KEY = "result_data_key";
    public static final String EMAIL_KEY = "email";
    public static final String LASTNAME_KEY = "nom";
    public static final String FIRSTNAME_KEY = "prenom";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnOK = (Button) findViewById(R.id.btn_ok);

        email = (EditText) findViewById(R.id.email);

        lastname = (EditText) findViewById(R.id.nom);

        firstname = (EditText) findViewById(R.id.prenom);

        confirmResult = (TextView) findViewById(R.id.confirmResult);
        confirmResult.setVisibility(View.INVISIBLE);


        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent confirmIntent = new Intent(MainActivity.this,ConfirmSignIn.class);
                confirmIntent.putExtra(EMAIL_KEY,email.getText().toString());
                confirmIntent.putExtra(LASTNAME_KEY,lastname.getText().toString());
                confirmIntent.putExtra(FIRSTNAME_KEY,firstname.getText().toString());
                startActivityForResult(confirmIntent,SIGN_IN_RESULT);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == SIGN_IN_RESULT){
            //TODO (1) : externaliser les ressources string pour I18N dans :
            // MainActivity, ConfirmSignin et les 2 fichiers layout xml

            //TODO (2) : Afficher le text en rouge en cas d'annulation, en bleu si creation user ok

            boolean confirmationResult = false;
            if (data.hasExtra(SIGN_IN_RESULT_DATA_KEY)){
                confirmationResult = data.getBooleanExtra(SIGN_IN_RESULT_DATA_KEY,false);
            }

            String userKey = data.getStringExtra(USER_KEY);
            if (confirmationResult){
                User monUser = AccountManager.getInstance().getUser(userKey);
                confirmResult.setVisibility(View.VISIBLE);

                confirmResult.setText("Felicitations : "+ monUser.getFirstname() +" Votre compte à bien été crée");

            }else{
                confirmResult.setVisibility(View.VISIBLE);
                confirmResult.setText("Creation de compte annulée par l\'utilisateur");
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
