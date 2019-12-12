package org.likesyou.bensalcie.pushharder;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

private View v;
private TextView tvNotif;
private FirebaseAuth mAuth;
    private DatabaseReference myUsersDatabase;
    private String userId;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_notification, container, false);
        mAuth=FirebaseAuth.getInstance();
        myUsersDatabase= FirebaseDatabase.getInstance().getReference().child("ENACTUS UOE").child("Users");
userId=mAuth.getCurrentUser().getUid();
        tvNotif=v.findViewById(R.id.firstNotification);
        setupUploader();
    return v;
    }
    private void setupUploader() {
        myUsersDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {

                    String name=dataSnapshot.child("name").getValue().toString();

                    tvNotif.setText("Hello,  "+name+". Thank you for being part of Enactus UOE, we look forward to make this place a better platform to sell your products as you maximize your profits . We guarantee you a free platform to sell all your products and new customers as well. Stay tuned to have more of our services in future.");


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
