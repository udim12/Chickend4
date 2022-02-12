package EMA.chickend.GUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.List;

import EMA.chickend.Logic.Classes.Game;
import EMA.chickend.Logic.Classes.Level;
import EMA.chickend.R;

public class AllLevelsOverviewForm extends Activity
{
    private LinearLayout   m_RelativeLayout_MainLayout = null;
    private List<Level>    m_AllLevels                 = null;
    private LinearLayout[] m_RowsLayouts               = null;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_levels_overview_form);

        Game.getInstance().loadLevels(this);

        this.m_RelativeLayout_MainLayout = findViewById(R.id.RelativeLayout_MainLayout);
        this.m_RelativeLayout_MainLayout.setBackgroundResource(R.drawable.background_color_gradient);
        this.m_AllLevels = Game.getInstance().getLevels();

        ScrollView ScrollView_Layout = findViewById(R.id.ScrollView_Layout);
        ScrollView_Layout.setBackgroundResource(R.color.black);
        //ScrollView_Layout.getBackground().setAlpha(160);

        embedLevelsAsVisualComponents();
    }

    private void embedLevelsAsVisualComponents()
    {
        LinearLayout currentLine;
        int currentLineIndex = -1;

        Level currentLevel;
        RelativeLayout visualComponentOfTheCurrentLevel;
        RelativeLayout.LayoutParams layoutParams;

        if (this.m_AllLevels.size() % 3 == 0)
        {
            this.m_RowsLayouts = new LinearLayout[this.m_AllLevels.size() / 3];
        }
        else
        {
            this.m_RowsLayouts = new LinearLayout[(this.m_AllLevels.size() / 3) + 1];
        }

        for (int i = 0; i < this.m_RowsLayouts.length; i++)
        {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(20, 20, 20, 20);
            params.gravity = Gravity.CENTER_HORIZONTAL;

            this.m_RowsLayouts[i] = new LinearLayout(this);
            this.m_RowsLayouts[i].setLayoutParams(params);

            this.m_RelativeLayout_MainLayout.addView(this.m_RowsLayouts[i]);
        }

        for (int currentLevelIndex = 0; currentLevelIndex < this.m_AllLevels.size(); currentLevelIndex++)
        {
            if (currentLevelIndex % 3 == 0)
            {
                currentLineIndex += 1;
            }

            currentLine = this.m_RowsLayouts[currentLineIndex];
            currentLevel = this.m_AllLevels.get(currentLevelIndex);

            visualComponentOfTheCurrentLevel = currentLevel.getVisualComponentOfLevelObject(this);

            layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(20, 20, 20, 20);

            visualComponentOfTheCurrentLevel.setLayoutParams(layoutParams);

            /*
            00  01  02
            03  04  05
            06  07  08
            09  10  11
            12  13  14
            15  16  17
            18  19  20
            */

            visualComponentOfTheCurrentLevel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RelativeLayout currentRelativeLayout = (RelativeLayout) v;

                    TextView currentTextView = (TextView) currentRelativeLayout.getChildAt(3);

                    int levelNumber = Integer.valueOf(currentTextView.getText().toString());
                    int levelIndex = levelNumber - 1;
                    Level currentLevelWhichShouldBePlayed = Game.getInstance().getLevels().get(levelIndex);

                    // If the current level is still locked
                    if (currentLevelWhichShouldBePlayed.isLocked() == true)
                    {
                        Toast.makeText(AllLevelsOverviewForm.this, R.string.locked_level_message, Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Intent intent = new Intent(AllLevelsOverviewForm.this, PlayLevelActivity.class);
                        intent.putExtra("Level", levelNumber);
                        startActivity(intent);
                    }
                }
            });

            currentLine.addView(visualComponentOfTheCurrentLevel);
        }

        Game.getInstance().getLevels().get(0).setIsLocked(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}