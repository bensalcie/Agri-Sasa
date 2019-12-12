package org.likesyou.bensalcie.pushharder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText etAdmNo,etPass;
    private Button btnLogin;
    private FirebaseAuth mFirebaseAuth;

    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etAdmNo=findViewById(R.id.etAdm);
        etPass=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
mFirebaseAuth=FirebaseAuth.getInstance();
        pd=new ProgressDialog(this);
        pd.setTitle("Logging in");
        pd.setMessage("Just a moment...");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                String adm=etAdmNo.getText().toString().trim();
                String pass=etPass.getText().toString().trim();
                if (!TextUtils.isEmpty(adm) && !TextUtils.isEmpty(pass))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(adm,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                pd.dismiss();
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                finish();


                            }else{
                                pd.dismiss();

                                toast(task.getException().getMessage());
                            }
                        }
                    });
                }else {
                    pd.dismiss();

                    toast("You left a blank !");
                }
            }
        });
    }

    private void toast(String s) {
        Toast.makeText(this, "Message: "+s, Toast.LENGTH_SHORT).show();
    }

    public void signup(View view) {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        finish();
    }
}
