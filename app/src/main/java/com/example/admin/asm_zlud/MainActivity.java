package com.example.admin.asm_zlud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.asm_zlud.service.LoginService;
import com.example.admin.asm_zlud.util.DialogBuilder;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    Button loginBtn;

    private LoginService loginService = new LoginService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText)findViewById(R.id.lgUserTv);
        et2=(EditText)findViewById(R.id.lgPasswordTv);
        loginBtn=(Button)findViewById(R.id.button);
        et1.setText("admin");
        et2.setText("admin");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=et1.getText().toString();
                String pw=et2.getText().toString();
                Log.i("idpw",id+pw);
                if(loginService.login(id,pw)){
                    Intent intent = new Intent(MainActivity.this,Mainager.class);
                    startActivity(intent);
                }
                else{
                    DialogBuilder dialogBuilder= new DialogBuilder();
                    dialogBuilder.show(getSupportFragmentManager(),"");
                }
            }
        });
    }
}
