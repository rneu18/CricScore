package com.example.cricscore;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ScoreDetails extends AppCompatActivity {

    String teamOne;
    String teamTwo;
    Button matchDetails;
    Button newMatch;
    Button matchDetails1;
    int tOvers;
    int tPlayers;
    int wickets;
    int totalRuns;
    int overs;
    TextView battingTeam;
    TextView currentScore;
    TextView oversDone;
    DataBaseHelper myDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socre_details);


        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.team_details);

        battingTeam = (TextView) findViewById(R.id.battingTeam);
        currentScore= (TextView) findViewById(R.id.currentScore);
        oversDone = (TextView) findViewById(R.id.oversDone);
        updateScore();


        newMatch = (Button) findViewById(R.id.refresh);
        newMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("TeamOne",null);
                editor.putString("TeamTwo",null);
                editor.putInt("Overs",0);
                editor.putInt("Players",0);
                editor.apply();
                startActivity(new Intent(ScoreDetails.this, ScoreDetails.class));
            }
        });

        if (teamOne == null){
            dialog.show();
            matchDetails = (Button) dialog.findViewById(R.id.submitTeams);
            matchDetails1 = (Button) dialog.findViewById(R.id.submitTeams1);


            matchDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try{
                        EditText input1 = (EditText) dialog.findViewById(R.id.totalPlayer);
                        EditText input2 = (EditText) dialog.findViewById(R.id.totalOvers);
                        final String team1 = ((EditText) dialog.findViewById(R.id.teamOne)).getText().toString();
                        final String team2 = ((EditText) dialog.findViewById(R.id.teamTwo)).getText().toString();
                        final int totalOvers = new Integer(input2.getText().toString()).intValue();
                        final int totalPlayers =  new Integer(input1.getText().toString()).intValue();

                        if (team2!=null && team1 != null && totalPlayers >0 && totalOvers>0){
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("TeamOne",team1);
                            editor.putString("TeamTwo",team2);
                            editor.putInt("Overs",totalOvers);
                            editor.putInt("Players",totalPlayers);
                            editor.apply();
                            updateScore();
                            dialog.dismiss();
                            nextActivity();
                        }

                    }catch(Exception e){

                    }
                }
            });

            matchDetails1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try{
                        EditText input1 = (EditText) dialog.findViewById(R.id.totalPlayer);
                        EditText input2 = (EditText) dialog.findViewById(R.id.totalOvers);
                        final String team1 = ((EditText) dialog.findViewById(R.id.teamOne)).getText().toString();
                        final String team2 = ((EditText) dialog.findViewById(R.id.teamTwo)).getText().toString();
                        final int totalOvers = new Integer(input2.getText().toString()).intValue();
                        final int totalPlayers =  new Integer(input1.getText().toString()).intValue();

                        if (team2!=null && team1 != null && totalPlayers >0 && totalOvers>0){
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("TeamOne",team1);
                            editor.putString("TeamTwo",team2);
                            editor.putInt("Overs",totalOvers);
                            editor.putInt("Players",totalPlayers);
                            editor.apply();
                            updateScore();
                            dialog.dismiss();
                            nextActivity1();
                        }

                    }catch(Exception e){

                    }
                }
            });

        }
    }

    private void nextActivity() {
        Intent myIntent = new Intent(this, ScoreSheet.class);
        myIntent.putExtra("total", tPlayers);
        startActivity(myIntent);

    }

    private void nextActivity1() {
        Intent myIntent = new Intent(this, EnterPlayer.class);
        myIntent.putExtra("total", tPlayers);
        startActivity(myIntent);

    }

    public void updateScore() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        teamOne = preferences.getString("TeamOne", null);
        teamTwo = preferences.getString("TeamTwo", null);
        tOvers = preferences.getInt("Overs", 0);
        tPlayers = preferences.getInt("Players", 0);
        battingTeam.setText(teamOne);
        currentScore.setText(String.valueOf(totalRuns)+"/"+String.valueOf(wickets));
        oversDone.setText(String.valueOf(overs)+"("+String.valueOf(tOvers)+")");
    }

    public void viewScore(){
        Cursor res = myDB.getAllData();
        if(res.getCount()>0){
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()){

            }
        }
    }
}
