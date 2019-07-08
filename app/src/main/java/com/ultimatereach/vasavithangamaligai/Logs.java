package com.ultimatereach.vasavithangamaligai;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Logs extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    TextView load, load_res, load_work, load_con, load_ceo, load_cfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        load = (TextView) findViewById(R.id.log_details);
        load_ceo = (TextView) findViewById(R.id.log_ceo);
        load_cfo = (TextView) findViewById(R.id.log_cfo);
        load_con = (TextView) findViewById(R.id.log_con);
        load_res = (TextView) findViewById(R.id.log_reception);
        load_work = (TextView) findViewById(R.id.log_work);

        start();
    }

    public void start(){

        DatabaseReference p = database.getReference("/office/conferencechange");
        DatabaseReference o = database.getReference("/office/receptionchange");
        DatabaseReference i = database.getReference("/office/ceochange");
        DatabaseReference u = database.getReference("/office/cfochange");
        DatabaseReference l = database.getReference("/office/workareachange");
        DatabaseReference k = database.getReference("/office/allroom");

        k.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.getValue());
                load.setText("All room: \n"+value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        p.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.getValue());
                load_con.setText("Conference: \n"+value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        o.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.getValue());
                load_res.setText("Reception: \n"+value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        i.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.getValue());
                load_ceo.setText("CEO Room: \n"+value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        u.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.getValue());
                load_cfo.setText("CFO Room: \n"+value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        l.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.getValue());
                load_work.setText("Work area: \n"+value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
