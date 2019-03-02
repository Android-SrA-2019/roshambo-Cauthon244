package android.example.assignment2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
//Adam Crooks Assignment 2 March 1st 2019
public class MainActivity extends AppCompatActivity {
    private Rochambo rcb = new Rochambo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Gets the ImageView for Player and CPU, and sets the players, and Animates Them.
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void paperButtonClick(View view) {
        rcb.playerMakesMove(1);
        ImageView ivp = findViewById(R.id.resultPlayer);
        ImageView ivcp = findViewById(R.id.resultCPU);
        animateOutcome(ivp, ivcp);
        ivp.setImageResource(R.drawable.paper);
        drawCPUChoice(ivcp);
        displayOutcome();
    }
    //Gets the ImageView for Player and CPU, and sets the players, and Animates Them.
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void rockButtonClick(View view) {
        rcb.playerMakesMove(0);
        ImageView ivp = findViewById(R.id.resultPlayer);
        ImageView ivcp = findViewById(R.id.resultCPU);
        animateOutcome(ivp, ivcp);
        ivp.setImageResource(R.drawable.rock);
        drawCPUChoice(ivcp);
        displayOutcome();
    }
    //Gets the ImageView for Player and CPU, and sets the players, and Animates Them.
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void scissorsButtonClick(View view) {
        rcb.playerMakesMove(2);
        ImageView ivp = findViewById(R.id.resultPlayer);
        ImageView ivcp = findViewById(R.id.resultCPU);
        animateOutcome(ivp, ivcp);
        ivp.setImageResource(R.drawable.scissors);
        drawCPUChoice(ivcp);
        displayOutcome();
    }
    //Finds the CPU's choice and Sets the Imageviews Source
    public void drawCPUChoice(ImageView game) {
        if (rcb.getGameMove() == 0) {
            game.setImageResource(R.drawable.rock);
        } else if (rcb.getGameMove() == 1) {
            game.setImageResource(R.drawable.paper);
        } else if (rcb.getGameMove() == 2) {
            game.setImageResource(R.drawable.scissors);
        } else {
            game.setImageResource(R.drawable.none);
        }
    }
    //Checks the Games outcome and sets the Outcome Textview appropriately
    public void displayOutcome() {
        TextView tv = findViewById(R.id.textViewResult);
        if (rcb.winLoseOrDraw() == R.string.win) {
            tv.setText(R.string.win);
        } else if (rcb.winLoseOrDraw() == R.string.lose) {
            tv.setText(R.string.lose);
        } else {
            tv.setText(R.string.draw);
        }
    }
    //Method to animate the Imageviews of the outcome
    public void animateOutcome(ImageView player, ImageView game) {
        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(player,
                "rotationX", 0f, 1080f)
                .setDuration(1000);

        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(game,
                "rotationX", 0f, 1080f)
                .setDuration(1000);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();
    }
}
