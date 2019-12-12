package org.likesyou.bensalcie.pushharder;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView profileLabel,usersLabel,notificationLabel,tvUsername;
    private ViewPager mViewPager;
    private PagerViewAdapter pagerViewAdapter;
    private FirebaseAuth mAuth;
    private DatabaseReference myUsersDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();
        myUsersDatabase= FirebaseDatabase.getInstance().getReference().child("ENACTUS UOE").child("Users");
        myUsersDatabase.keepSynced(true);
        profileLabel=findViewById(R.id.profileLabel);
        usersLabel=findViewById(R.id.usersLabel);
        notificationLabel=findViewById(R.id.notificationLabel);
        mViewPager=findViewById(R.id.mainViewPager);
        tvUsername=findViewById(R.id.useraname);



        profileLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mViewPager.setCurrentItem(0);
            }
        });
        usersLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);

            }
        });
        notificationLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(2);

            }
        });
        pagerViewAdapter=new PagerViewAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerViewAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position ,float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int i) {

                changeTabs(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void changeTabs(int position) {
        if (position==0)
        {

            profileLabel.setTextColor(getResources().getColor(R.color.textTabBright));
       profileLabel.setTextSize(22);
            usersLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            usersLabel.setTextSize(16);
            notificationLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            notificationLabel.setTextSize(16);
        }
        if (position==1)
        {

            profileLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            profileLabel.setTextSize(16);
            usersLabel.setTextColor(getResources().getColor(R.color.textTabBright));
            usersLabel.setTextSize(22);
            notificationLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            notificationLabel.setTextSize(16);
        }
        if (position==2)
        {

            profileLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            profileLabel.setTextSize(16);
            usersLabel.setTextColor(getResources().getColor(R.color.textTabLight));
            usersLabel.setTextSize(16);
            notificationLabel.setTextColor(getResources().getColor(R.color.textTabBright));
            notificationLabel.setTextSize(22);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser=mAuth.getCurrentUser();
        if (currentUser==null)
        {
           sendToLogin();
        }else {
            verifyUserDetails();
        }

    }

    private void verifyUserDetails() {
        String userId=mAuth.getCurrentUser().getUid();
        myUsersDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {
                    String name=dataSnapshot.child("name").getValue().toString();
                    tvUsername.setText("You are signed in as:  "+name.toUpperCase()+ "  Sign out ? ");
                    tvUsername.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Confirm Action");
                            builder.setMessage("Do you want to Sign out?");
                            builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    mAuth.signOut();
                                    sendToLogin();
                                }
                            });
                            builder.setNegativeButton("Later",null);
                            builder.show();

                        }
                    });

                }else {
                    startActivity(new Intent(   MainActivity.this,UserDetailsActivity.class));
                finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendToLogin() {
        startActivity(new Intent(MainActivity.this,LoginActivity.class));

        finish();
    }
}
