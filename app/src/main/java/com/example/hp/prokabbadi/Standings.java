package com.example.hp.prokabbadi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Standings extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private TextView teams,played,wins,loss,pts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);
        teams=(TextView)findViewById(R.id.teams);
        played=(TextView)findViewById(R.id.played);
        wins=(TextView)findViewById(R.id.wins);
        loss=(TextView)findViewById(R.id.loss);
        pts=(TextView)findViewById(R.id.points);


    }

    public void updatescore(View view) {



        DocumentReference user = db.collection("Standings").document("teams");
        user.get().addOnCompleteListener(new OnCompleteListener< DocumentSnapshot >() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot doc = task.getResult();

        //Teams
        StringBuilder teams1=new StringBuilder("");
        teams1.append(doc.get("teamsname"));
        teams.setText(teams1.toString());

        //Played
        StringBuilder played1=new StringBuilder("");
        played1.append(doc.get("played"));
        played.setText(played1.toString());

        //Wins
        StringBuilder wins1=new StringBuilder("");
        wins1.append(doc.get("wins"));
        wins.setText(wins1.toString());


        //Loss
        StringBuilder loss1=new StringBuilder("");
        loss1.append(doc.get("loss"));
        loss.setText(loss1.toString());

        //Points
        StringBuilder points=new StringBuilder("");
        points.append(doc.get("points"));
        pts.setText(points.toString());

                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Standings.this, "Refresh Again", Toast.LENGTH_SHORT).show();
                    }
                });
    }}