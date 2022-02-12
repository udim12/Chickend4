package EMA.chickend;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import EMA.chickend.GUI.AllLevelsOverviewForm;

public class MainActivity extends AppCompatActivity {

    public static Context m_context;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_context = getApplicationContext();

        setContentView(R.layout.activity_main);

       // RelativeLayout layout = findViewById(R.id.RelativeLayout_Layout);
        //layout.getBackground().setAlpha(160);

        ImageView chick = findViewById(R.id.chick_chicken_start);
        ImageView regularChicken = findViewById(R.id.regular_chicken_start);
        ImageView motherChicken = findViewById(R.id.mother_chicken_start);

        motherChicken.getBackground().setAlpha(230);
        chick.getBackground().setAlpha(230);
        regularChicken.getBackground().setAlpha(230);

        Button startButton = findViewById(R.id.start_btn);
        Animation scale = AnimationUtils.loadAnimation(this, R.anim.scale);
        startButton.startAnimation(scale);

        startButton.setAlpha(1);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AllLevelsOverviewForm.class));
            }
        });

        Button instructionButton = findViewById(R.id.instruction_btn);
        instructionButton.startAnimation(scale);

        instructionButton.setAlpha(1);
        instructionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InstructionsForm.class));
            }
        });
    }
}