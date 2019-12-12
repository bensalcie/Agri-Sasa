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
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserDetailsActivity extends AppCompatActivity {
    private EditText etName,etPhone,etCounty,etSubcounty;
    private RadioButton rbCropGrowing,rbAnimalRearing,rbSmallScale,rbLargeScale;

    private Button btnUpdate;
    private String type_farming="";
    private String type_scale="";
    private DatabaseReference myUsersDatabase;
    private FirebaseAuth mAuth;
    private String userId;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        etName=findViewById(R.id.etName);
        etPhone=findViewById(R.id.etPhone);
        etCounty=findViewById(R.id.etCounty);
        etSubcounty=findViewById(R.id.etSubCounty);
        pd=new ProgressDialog(this);
        pd.setTitle("Completing profile setup");
        pd.setMessage("Just a moment");

        rbCropGrowing=findViewById(R.id.cropGrowing);
        rbAnimalRearing=findViewById(R.id.animalRearing);
        rbSmallScale=findViewById(R.id.smallScale);
        rbLargeScale=findViewById(R.id.largeScale);
        btnUpdate=findViewById(R.id.btnUpdate);

        mAuth=FirebaseAuth.getInstance();
        userId=mAuth.getCurrentUser().getUid();
        myUsersDatabase= FirebaseDatabase.getInstance().getReference().child("ENACTUS UOE").child("Users");



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                if (rbCropGrowing.isChecked())
                {
                    type_farming="Crop Growing";
                }
                if (rbAnimalRearing.isChecked())
                {
                    type_farming="Animal Rearing";

                }


                if (rbSmallScale.isChecked())
                {
                    type_scale="Small Scale";
                }

                if (rbLargeScale.isChecked())
                {
                    type_scale="Large Scale";
                }
                String name=etName.getText().toString().trim();
                String phone=etPhone.getText().toString().trim();
                String county=etCounty.getText().toString().trim();
                String subCounty=etSubcounty.getText().toString().trim();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone)
                        &&!TextUtils.isEmpty(county) &&!TextUtils.isEmpty(name)
                        &&!TextUtils.isEmpty(subCounty)

                && !TextUtils.isEmpty(type_scale) && !TextUtils.isEmpty(type_farming))

                {
                    //toast(name+type_farming+type_scale);
                    uploadData(name,phone,county,subCounty,type_scale,type_farming);

                }else {
                    pd.dismiss();
                    toast("You left some blanks");
                }
            }
        });

    }

    private void uploadData(String name, String phone, String county, String subCounty, String type_scale, String type_farming) {
DatabaseReference newUser=myUsersDatabase.child(userId);

        HashMap<String,Object> myMap=new HashMap<>();
        myMap.put("name",name);
        myMap.put("phone",phone);
        myMap.put("county",county);
        myMap.put("subcounty",subCounty);
        myMap.put("type_scale",type_scale);
        myMap.put("type_farming",type_farming);
        newUser.updateChildren(myMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    pd.dismiss();
                    startActivity(new Intent(UserDetailsActivity.this,MainActivity.class));
                    finish();

                }else {
                    pd.dismiss();
toast(task.getException().getMessage());
                }
            }
        });


    }

    private void toast(String s) {
        Toast.makeText(this, "Message: "+s, Toast.LENGTH_SHORT).show();
    }

}
