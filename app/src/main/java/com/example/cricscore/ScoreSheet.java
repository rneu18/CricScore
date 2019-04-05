package com.example.cricscore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScoreSheet extends AppCompatActivity {

    int scoreA = 0;
    int wicketA = 0;
    int scoreB = 0;
    int wicketB = 0;
    String teamOne, teamTwo;
    int tOvers, tPlayers;
    int balls1 = 0;
    int balls2 = 0;
    LinearLayout scoreCardA, scoreCardB;
    TextView finalResult;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_sheet);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        teamOne = preferences.getString("TeamOne", null);
        teamTwo = preferences.getString("TeamTwo", null);
        tOvers = preferences.getInt("Overs", 0);
        tPlayers = preferences.getInt("Players", 0);

        TextView team1 = (TextView) findViewById(R.id.team_a);
        TextView team2 = (TextView) findViewById(R.id.team_b);
        team1.setText(teamOne);
        team2.setText(teamTwo);
        displayForTeamA(scoreA, wicketA, balls1);

        scoreCardA = (LinearLayout) findViewById(R.id.score_team_a);
        scoreCardB = (LinearLayout) findViewById(R.id.score_team_b);
        scoreCardB.setVisibility(LinearLayout.GONE);
        finalResult = (TextView) findViewById(R.id.result);

    }

    public void add1ToA(View view) {
        if (wicketA < tPlayers && balls1 <(tOvers*6)) {
            scoreA += 1;
            balls1 +=1;
            displayForTeamA(scoreA, wicketA, balls1);
        }else
            displayForTeamASummary(scoreA, wicketA);
    }

    public void add2ToA(View view) {
        if (wicketA <tPlayers && balls1 <(tOvers*6)) {

            scoreA += 2;
            balls1 +=1;
            displayForTeamA(scoreA, wicketA, balls1);
        }else
            displayForTeamASummary(scoreA, wicketA);
    }

    public void add3ToA(View view) {
        if (wicketA < tPlayers && balls1 <(tOvers*6)) {

            scoreA += 3;
            balls1 +=1;
            displayForTeamA(scoreA, wicketA, balls1);
        }else
            displayForTeamASummary(scoreA, wicketA);
    }



    public void add4ToA(View view) {
        if (wicketA <tPlayers && balls1 <(tOvers*6)) {

            scoreA += 4;
            balls1 +=1;
            displayForTeamA(scoreA, wicketA, balls1);
        }else
            displayForTeamASummary(scoreA, wicketA);
    }

    public void add5ToA(View view) {
        if (wicketA < tPlayers && balls1 <(tOvers*6)) {

            scoreA += 5;
            balls1 +=1;
            displayForTeamA(scoreA, wicketA, balls1);
        }else
            displayForTeamASummary(scoreA, wicketA);
    }

    public void add6ToA(View view) {
        if (wicketA < tPlayers && balls1 <(tOvers*6)) {

            scoreA += 6;
            balls1 +=1;
            displayForTeamA(scoreA, wicketA, balls1);
        }else
            displayForTeamASummary(scoreA, wicketA);
    }

    public void addDotBallToA(View view) {

        if (wicketA < tPlayers && balls1 <(tOvers*6)) {

            balls1 +=1;
            displayForTeamA(scoreA, wicketA, balls1);
        }else
            displayForTeamASummary(scoreA, wicketA);
    }

    public void addNoBallToA(View view) {
        if (wicketA < tPlayers && balls1 <(tOvers*6)) {
            scoreA += 1;


            displayForTeamA(scoreA, wicketA, balls1);
        }else
            displayForTeamASummary(scoreA, wicketA);
    }

    public void addWideToA(View view) {
        if (wicketA < tPlayers && balls1 <(tOvers*6)) {

            scoreA+=1;

            displayForTeamA(scoreA, wicketA, balls1);
        }else
            displayForTeamASummary(scoreA, wicketA);


    }

    public void add1WicketA(View view) {
        wicketA += 1;

        if (wicketA < tPlayers-1 && balls1 <(tOvers*6)){


            displayForTeamA(scoreA, wicketA, balls1);

        }

        else
            displayForTeamASummary(scoreA, wicketA);

    }

    public void displayForTeamA(int scoreA, int wicketA, int balls1) {
        TextView scoreView = (TextView) findViewById(R.id.scoreForA);
        scoreView.setText(String.valueOf(scoreA + "/" + wicketA +" ("+balls1/6+"."+balls1%6 +"/"+tOvers+")"));
    }

    public void displayForTeamASummary(int scoreA, int wicketA) {
        scoreCardA.setVisibility(LinearLayout.GONE);
        scoreCardB.setVisibility(LinearLayout.VISIBLE);
        int target = scoreA + 1;
        TextView scoreView = findViewById(R.id.scoreForA);
        scoreView.setText(String.valueOf("Innings Ended: " + scoreA + "/" + wicketA + "\n " + "Target for " +teamTwo+": " + target));
    }

    public void add1ToB(View view) {
        if (wicketB <tPlayers && (scoreB < scoreA + 1) && balls2 <(tOvers*6)) {
            scoreB += 1;
            balls2 += 1;
            displayForTeamB(scoreB, wicketB , balls2);
        } else

            displayForTeamBSummary(scoreB, wicketB);
    }

    public void add2ToB(View view) {
        if (wicketB <tPlayers && (scoreB < scoreA + 1) && balls2 <(tOvers*6)) {

            scoreB += 2;
            balls2 += 1;
            displayForTeamB(scoreB, wicketB , balls2);
        } else

            displayForTeamBSummary(scoreB, wicketB);
    }

    public void add3ToB(View view) {
        if (wicketB <tPlayers && (scoreB < scoreA + 1) && balls2 <(tOvers*6)) {

            scoreB += 3;
            balls2 += 1;
            displayForTeamB(scoreB, wicketB , balls2);
        } else

            displayForTeamBSummary(scoreB, wicketB);
    }

    public void add4ToB(View view) {
        if (wicketB <tPlayers && (scoreB < scoreA + 1) && balls2 <(tOvers*6)) {

            scoreB += 4;
            balls2 += 1;
            displayForTeamB(scoreB, wicketB , balls2);
        } else

            displayForTeamBSummary(scoreB, wicketB);
    }

    public void add5ToB(View view) {
        if (wicketB <tPlayers && (scoreB < scoreA + 1) && balls2 <(tOvers*6)) {

            scoreB += 5;
            balls2 += 1;
            displayForTeamB(scoreB, wicketB , balls2);
        } else

            displayForTeamBSummary(scoreB, wicketB);
    }

    public void add6ToB(View view) {
        if (wicketB <tPlayers && (scoreB < scoreA + 1) && balls2 <(tOvers*6)) {

            scoreB += 6;
            balls2 += 1;
            displayForTeamB(scoreB, wicketB , balls2);
        } else

            displayForTeamBSummary(scoreB, wicketB);
    }

    public void addDotToB(View view) {
        if (wicketB <tPlayers && (scoreB < scoreA + 1) && balls2 <(tOvers*6)) {

            balls2 += 1;
            displayForTeamB(scoreB, wicketB , balls2);
        } else

            displayForTeamBSummary(scoreB, wicketB);
    }

    public void addNoBallToB(View view) {
        if (wicketB <tPlayers && (scoreB < scoreA + 1) && balls2 <(tOvers*6)) {
            scoreB += 1;

            displayForTeamB(scoreB, wicketB , balls2);
        } else

            displayForTeamBSummary(scoreB, wicketB);
    }

    public void addWideTob(View view) {
        if (wicketB <tPlayers && (scoreB < scoreA + 1) && balls2 <(tOvers*6)) {
            scoreB += 1;
            displayForTeamB(scoreB, wicketB , balls2);
        } else

            displayForTeamBSummary(scoreB, wicketB);
    }

    public void add1WicketB(View view) {
        wicketB += 1;
        if (wicketB <tPlayers-1 && (scoreB < scoreA + 1) && balls2 <(tOvers*6)) {


            displayForTeamB(scoreB, wicketB , balls2);
        } else

            displayForTeamBSummary(scoreB, wicketB);
    }

    public void displayForTeamB(int scoreB, int wicketB, int balls2) {
        TextView scoreView = findViewById(R.id.scoreForB);
        scoreView.setText(String.valueOf(scoreB + "/" + wicketB +" ("+balls2/6 +"."+balls2%6+"/" +tOvers+")"));
    }

    public void displayForTeamBSummary(int scoreB, int wicketB) {
        result();

        scoreCardB.setVisibility(LinearLayout.GONE);
        TextView scoreView = findViewById(R.id.scoreForB);
        scoreView.setText(String.valueOf("Innings Ended : " + scoreB + "/" + wicketB));
    }

    public void reset(View view) {
        Intent myIntent = new Intent(this, ScoreDetails.class);
      //  myIntent.putExtra("total", tPlayers);
        startActivity(myIntent);
    }

    public void result() {
        String ch;
        if (scoreA > scoreB) {
            ch = teamOne;
            displayResult(ch);
        } else if (scoreB > scoreA) {
            ch = teamTwo;
            displayResult(ch);
        } else {
            ch = "T";
            displayResult(ch);
        }
    }

    public void displayResult(String ch) {

        if (ch == teamOne || ch == teamTwo) {
            finalResult.setText(String.valueOf(ch + " won the game!"));

        } else {
            finalResult.setText(String.valueOf("The game was a Tie! Well Played!"));

        }

    }

    public void removeRunToA(View view) {
        scoreA -= 1;

        displayForTeamA(scoreA, wicketA, balls1);
    }

    public void removeDotToA(View view) {

        balls1 -=1;
        displayForTeamA(scoreA, wicketA, balls1);
    }

    public void remove1WicketA(View view) {

        wicketA -=1;
        displayForTeamA(scoreA, wicketA, balls1);
    }
    public void removeRunToB(View view) {
        scoreB -= 1;

        displayForTeamB(scoreB, wicketB, balls2);
    }

    public void removeDotToB(View view) {
        balls2 -= 1;

        displayForTeamB(scoreB, wicketB, balls2);
    }

    public void remove1WicketB(View view) {
        wicketB -= 1;

        displayForTeamB(scoreB, wicketB, balls2);
    }
}


