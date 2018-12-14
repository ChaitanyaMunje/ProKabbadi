package com.example.hp.prokabbadi;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class HOME extends AppCompatActivity {

    // Access databasse from Live Wallpaper
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView matchno, matchtitle, score, img1url, img2url,player1url,player2url,player3url,player4url;
    private ImageView image1, image2,player1img,player2img,player3img,player4img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Pro Kabbadi");
        matchno = (TextView) findViewById(R.id.text_match_no);
        matchtitle = (TextView) findViewById(R.id.text_match);
        score = (TextView) findViewById(R.id.text_score);
        img1url = (TextView) findViewById(R.id.imageurl1);
        img2url = (TextView) findViewById(R.id.imageurl2);
        image1 = (ImageView) findViewById(R.id.image_team_1);
        image2 = (ImageView) findViewById(R.id.image_team_2);
        player1img=(ImageView)findViewById(R.id.player1);
        player2img=(ImageView)findViewById(R.id.player2);
        player3img=(ImageView)findViewById(R.id.player3);
        player4img=(ImageView)findViewById(R.id.player4);
        player1url=(TextView)findViewById(R.id.player1url);
        player2url=(TextView)findViewById(R.id.player1url);
        player3url=(TextView)findViewById(R.id.player1url);
        player4url=(TextView)findViewById(R.id.player1url);

        }


    public void update(View view) {
        DocumentReference user = db.collection("PhoneBook").document("Contacts");
        user.get().addOnCompleteListener(new OnCompleteListener< DocumentSnapshot >() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot doc = task.getResult();

                    //Match Number
                    StringBuilder matchnumber=new StringBuilder("");
                    matchnumber.append(doc.get("matchnumber"));
                    matchno.setText(matchnumber.toString());

                    //Match Title
                    StringBuilder matchtitle1 = new StringBuilder("");
                    matchtitle1.append(doc.get("matchtitle"));
                    matchtitle.setText(matchtitle1.toString());

                    //Score
                    StringBuilder score1=new StringBuilder("");
                    score1.append(doc.get("score"));
                    score.setText(score1.toString());



                    //Team 2 Image
                    StringBuilder imgu1=new StringBuilder("");
                    imgu1.append(doc.get("imageteamurl1"));
                    img1url.setText(imgu1.toString());
                    String imageuri1=img1url.getText().toString();
                    Picasso.get().load(imageuri1).into(image1);

                    //Team 1 Image
                    StringBuilder imgu2=new StringBuilder("");
                    imgu2.append(doc.get("imageteamurl2"));
                    img2url.setText(imgu2.toString());
                    String imageuri2=img2url.getText().toString();
                    Picasso.get().load(imageuri2).into(image2);

                   //Player1

                    StringBuilder player1st=new StringBuilder("");
                    player1st.append(doc.get("player1"));
                    player1url.setText(player1st.toString());
                    String player=player1url.getText().toString();
                    Picasso.get().load(player).into(player1img);

                    //Player2
                    StringBuilder player2st=new StringBuilder("");
                    player2st.append(doc.get("player2"));
                    player2url.setText(player2st.toString());
                    String player2str=player2url.getText().toString();
                    Picasso.get().load(player2str).into(player2img);

                    //Player3
                   StringBuilder player3st=new StringBuilder("");
                    player3st.append(doc.get("player3"));
                    player3url.setText(player3st.toString());
                    String player3str=player3url.getText().toString();
                    Picasso.get().load(player3str).into(player3img);

                    //Player4
                    StringBuilder player4st=new StringBuilder("");
                    player4st.append(doc.get("player4"));
                    player4url.setText(player4st.toString());
                    String player4str=player4url.getText().toString();
                    Picasso.get().load(player4str).into(player4img);


                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(HOME.this, "Refresh Again", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public void standings(View view) {
        Intent i=new Intent(HOME.this,Standings.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are You Sure?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
// StringBuilder fields = new StringBuilder("");
//                    fields.append("Name: ").append(doc.get("Name"));
//                    fields.append("\nEmail: ").append(doc.get("Email"));
//                    fields.append("\nPhone: ").append(doc.get("Phone"));
//                    txt.setText(fields.toString());