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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreDetails extends AppCompatActivity {

    String teamOne;
    String teamTwo;
    Button matchDetails;
    Button newMatch;
    Button matchDetails1;
    int tOvers;
    int tPlayers;
    int wickets1;
    int wickets2;
    int totalRuns1;
    int totalRuns2;
    int over_1;
    int over_2;
    TextView battingTeam1;
    TextView currentScore1;
    TextView oversDone1;
    TextView battingTeam2;
    TextView currentScore2;
    TextView oversDone2;
    TextView target;
    TextView fTeam1;
    TextView fTeam2;
    DataBaseHelper myDB;
    LinearLayout batting1, batting2, fielding1, fielding2;
    ArrayList<String> players = new ArrayList<>();
    public RecyclerView recyclerViewBatting1;
    public RecyclerView.Adapter mAdapterBatting11;
    public RecyclerView.LayoutManager layoutManagerBatting1;

   // inputbarlayout = (LinearLayout) findViewById(R.id.input_layout);
  //   inputbarlayout.setVisibility(LinearLayout.GONE);
    // searchbarlayout.setVisibility(LinearLayout.VISIBLE);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socre_details);


        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.team_details);

        battingTeam1 = (TextView) findViewById(R.id.battingTeam1);
        currentScore1= (TextView) findViewById(R.id.currentScore1);
        oversDone1 = (TextView) findViewById(R.id.oversDone1);
        fTeam1 = (TextView) findViewById(R.id.team_1);
        fTeam2 = (TextView) findViewById(R.id.team_2);

        fielding1 = (LinearLayout) findViewById(R.id.linear_fielding1);
        fielding2 = (LinearLayout) findViewById(R.id.linear_fielding2);
        fielding1.setVisibility(LinearLayout.GONE);
        fielding2.setVisibility(LinearLayout.GONE);

        battingTeam2 = (TextView) findViewById(R.id.battingTeam2);
        currentScore2= (TextView) findViewById(R.id.currentScore2);
        oversDone2 = (TextView) findViewById(R.id.oversDone2);
        target = (TextView) findViewById(R.id.target);
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
        battingTeam1.setText(teamOne);
        currentScore1.setText(String.valueOf(totalRuns1)+"/"+String.valueOf(wickets1));
        oversDone1.setText(String.valueOf(over_1)+"("+String.valueOf(tOvers)+")");
        fTeam1.setText(teamOne+ " Bowling Details");
        fTeam2.setText(teamTwo + " Bowling Details");

        battingTeam2.setText(teamTwo);
        currentScore2.setText(String.valueOf(totalRuns2)+"/"+String.valueOf(wickets2));
        oversDone2.setText(String.valueOf(over_2)+"("+String.valueOf(tOvers)+")");
        target.setText(String.valueOf((totalRuns1+1)));


    }

    public void viewScore(){
        System.out.println("33333333333333333333333333333333333333 "+ players.get(0));
        try{
            Cursor res = myDB.getAllData();
            if(res.getCount()>0){
                System.out.println("4444444444444444444444444444444444 "+ players.get(0));
                res.moveToFirst();

            }

            do {
                String player_name = res.getString(1);
                System.out.println("222222222222222222222222player name "+ player_name);

            }while(res.moveToNext());

            System.out.println("2222222222222222222222222 "+ players.get(0));
            System.out.println("2222222222222222222222222 "+ players.toString());

            recyclerViewBatting1 = (RecyclerView) findViewById(R.id.batting1);
            recyclerViewBatting1.setHasFixedSize(true);
            layoutManagerBatting1 = new LinearLayoutManager(this);
            recyclerViewBatting1.setLayoutManager(layoutManagerBatting1);
            mAdapterBatting11 = new MyAdapterTeam1(players);
            recyclerViewBatting1.setAdapter(mAdapterBatting11);

        }catch (Exception e){

        }

    }
}
