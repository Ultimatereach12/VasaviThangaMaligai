package com.ultimatereach.vasavithangamaligai;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {

    ImageButton logout, two, one, three, all;
    String email;
    int a = 0, b = 0, c = 0, d = 0;
    int threeload = 0, twoload = 0, oneload = 0, allload = 0;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        logout = (ImageButton) findViewById(R.id.logout);
        all = (ImageButton) findViewById(R.id.all);
        one = (ImageButton) findViewById(R.id.one);
        two = (ImageButton) findViewById(R.id.two);
        three = (ImageButton) findViewById(R.id.three);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            email = user.getEmail();
        }

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference one_t = database.getReference("/one");
                DatabaseReference two_t = database.getReference("/two");
                DatabaseReference three_t = database.getReference("/three");
                DatabaseReference all_t = database.getReference("/all");

                if (a == 0){
                    one_t.setValue(1);
                    two_t.setValue(1);
                    three_t.setValue(1);
                    all_t.setValue(1);
                    all.getBackground().setColorFilter(Color.parseColor("#FF8C00"), PorterDuff.Mode.OVERLAY);
                    one.getBackground().setColorFilter(Color.parseColor("#FF8C00"), PorterDuff.Mode.OVERLAY);
                    three.getBackground().setColorFilter(Color.parseColor("#FF8C00"), PorterDuff.Mode.OVERLAY);
                    two.getBackground().setColorFilter(Color.parseColor("#FF8C00"), PorterDuff.Mode.OVERLAY);
                    a += 1;
                }else{
                    one_t.setValue(0);
                    two_t.setValue(0);
                    three_t.setValue(0);
                    all_t.setValue(0);
                    all.getBackground().clearColorFilter();
                    one.getBackground().clearColorFilter();
                    three.getBackground().clearColorFilter();
                    two.getBackground().clearColorFilter();
                    a = 0;
                }
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference y = database.getReference("/one");
                if (b == 0){
                    y.setValue(1);
                    one.getBackground().setColorFilter(Color.parseColor("#FF8C00"), PorterDuff.Mode.OVERLAY);
                    b += 1;
                }else{
                    y.setValue(0);
                    one.getBackground().clearColorFilter();
                    b = 0;
                }
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference q = database.getReference("/two");

                if (c == 0){
                    q.setValue(1);
                    two.getBackground().setColorFilter(Color.parseColor("#FF8C00"), PorterDuff.Mode.OVERLAY);

                    c += 1;
                }else{
                    q.setValue(0);
                    two.getBackground().clearColorFilter();

                    c = 0;
                }
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference w = database.getReference("/three");

                if (d == 0){
                    w.setValue(1);
                    three.getBackground().setColorFilter(Color.parseColor("#FF8C00"), PorterDuff.Mode.OVERLAY);

                    d += 1;
                }else{
                    w.setValue(0);
                    three.getBackground().clearColorFilter();

                    d = 0;
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor e=sp.edit();
                e.clear();
                e.apply();
                Intent i = new Intent(Dashboard.this, Login.class);
                startActivity(i);

                finish();
            }
        });

        start();
        all();
    }

    public void start(){

        DatabaseReference p = database.getReference("/three");
        DatabaseReference o = database.getReference("/one");
        DatabaseReference l = database.getReference("/two");
        DatabaseReference aa = database.getReference("/all");

        aa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.getValue());
                int chng = Integer.valueOf(value);
                if(chng == 1){
                    all.getBackground().setColorFilter(Color.parseColor("#FF8C00"), PorterDuff.Mode.OVERLAY);
                    allload = 1;
                    all();
                } else {
                    all.getBackground().clearColorFilter();
                    allload = 0;
                    all();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Dashboard.this, "Failed1", Toast.LENGTH_LONG).show();
            }
        });

        p.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.getValue());
                int chng = Integer.valueOf(value);
                if(chng == 1){
                    three.getBackground().setColorFilter(Color.parseColor("#FF8C00"), PorterDuff.Mode.OVERLAY);
                    threeload = 1;
                    all();
                } else {
                    three.getBackground().clearColorFilter();
                    threeload = 0;
                    all();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Dashboard.this, "Failed2", Toast.LENGTH_LONG).show();
            }
        });

        o.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.getValue());
                int chng = Integer.valueOf(value);
                if(chng == 1){
                    one.getBackground().setColorFilter(Color.parseColor("#FF8C00"), PorterDuff.Mode.OVERLAY);
                    oneload = 1;
                    all();
                } else {
                    one.getBackground().clearColorFilter();
                    oneload = 0;
                    all();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Dashboard.this, "Failed3", Toast.LENGTH_LONG).show();
            }
        });

        l.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.getValue());
                int chng = Integer.valueOf(value);
                if(chng == 1){
                    two.getBackground().setColorFilter(Color.parseColor("#FF8C00"), PorterDuff.Mode.OVERLAY);
                    twoload += 1;
                    all();
                }
                if (chng == 0){
                    two.getBackground().clearColorFilter();
                    twoload = 0;
                    all();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Dashboard.this, "Failed4", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void all(){
        if (twoload == 1 || oneload == 1 || threeload == 1) {
            all.getBackground().setColorFilter(Color.parseColor("#FF8C00"), PorterDuff.Mode.OVERLAY);
            a = 1;
        }
        if (twoload == 0 && oneload == 0 && threeload == 0) {
            all.getBackground().clearColorFilter();
            a = 0;
        }
    }
}
