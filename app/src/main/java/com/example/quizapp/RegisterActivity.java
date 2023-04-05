package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.application.ApplicationClass;
import com.example.quizapp.model.RegisterResponse;
import com.example.quizapp.model.UserRegister;
import com.example.quizapp.network.ApiInterFace;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText FirstName,LastName,Email,DateOfBirth,Password,PlatformId,PhoneNumber,UserName;

    private Button RegisterButton,LoginButton;
    ApiInterFace apiInterFace,apiInterFacedb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FirstName=findViewById(R.id.et_first_name);
        LastName=findViewById(R.id.et_lastname);
        Email=findViewById(R.id.et_email);
        DateOfBirth=findViewById(R.id.et_dob);
        Password=findViewById(R.id.et_password);
        PhoneNumber=findViewById(R.id.et_phone);
        UserName=findViewById(R.id.et_username);
        RegisterButton=findViewById(R.id.btn_createuser);
        LoginButton=findViewById(R.id.btn_login_user);

        apiInterFacedb=((ApplicationClass)getApplication()).userRetrofit.create(ApiInterFace.class);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRegister userRegister = new UserRegister();
//                if(TextUtils.isEmpty(FirstName.getText().toString()) ||TextUtils.isEmpty(LastName.getText().toString())
//
//                    ||TextUtils.isEmpty(Email.getText().toString()) ||TextUtils.isEmpty(DateOfBirth.getText().toString())
//
//                    ||TextUtils.isEmpty(Password.getText().toString())  || TextUtils.isEmpty(PhoneNumber.getText().toString())
//
//                    ||TextUtils.isEmpty(UserName.getText().toString())
//                )
//                {
//                    Toast.makeText(RegisterActivity.this, "some fiels", Toast.LENGTH_SHORT).show();
//                }
                userRegister.setFirstName(FirstName.getText().toString());
                userRegister.setLastName(LastName.getText().toString());
                userRegister.setEmail(Email.getText().toString());
                userRegister.setDob(DateOfBirth.getText().toString());
                userRegister.setPassword(Password.getText().toString());
                userRegister.setPlatformId("quiz");
                userRegister.setPhoneNumber(PhoneNumber.getText().toString());
                userRegister.setUsername(UserName.getText().toString());

                apiInterFace=((ApplicationClass)getApplication()).registerRetrofit.create(ApiInterFace.class);
                apiInterFace.registerUser(userRegister).enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if(response.body()!=null) {
                            RegisterResponse registerResponse = response.body();
                            Log.e("token", registerResponse.getUserId());
                            Toast.makeText(RegisterActivity.this, registerResponse.getUserId(), Toast.LENGTH_SHORT).show();
                            apiInterFacedb.sendUidandMailToDb(registerResponse.getUserId(),UserName.getText().toString()).enqueue(new Callback<Integer>() {
                                @Override
                                public void onResponse(Call<Integer> call, Response<Integer> response) {
                                    if(response.body()!=null)
                                    {
                                        Toast.makeText(RegisterActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                    }else{
                                        Toast.makeText(RegisterActivity.this, "no response from our db", Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onFailure(Call<Integer> call, Throwable t) {
                                    Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            Toast.makeText(RegisterActivity.this, "no response from infra", Toast.LENGTH_SHORT).show();
                        }


                        //     ((TextView) findViewById(R.id.tv_access)).setText(registerResponse.getUserId());

                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, t.getLocalizedMessage()+"infra", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}