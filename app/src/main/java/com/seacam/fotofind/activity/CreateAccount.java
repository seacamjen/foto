package com.seacam.fotofind.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import info.androidhive.locationapi.R;

public class CreateAccount extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.nameCreateAccount) EditText mName;
    @Bind(R.id.emailCreateAccount) EditText mEmail;
    @Bind(R.id.passwordCreateAccount) EditText mPassword;
    @Bind(R.id.confirmPasswordCreateAccount) EditText mConfirmPassword;
    @Bind(R.id.createUserButton) Button mCreateButton;
    @Bind(R.id.loginFromCreateAccount) TextView mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        mCreateButton.setOnClickListener(this);
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mLogin) {
            Intent intent = new Intent(CreateAccount.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (view == mCreateButton) {
            createNewUser();
        }
    }
}
