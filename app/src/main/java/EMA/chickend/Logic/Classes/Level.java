package EMA.chickend.Logic.Classes;

import android.content.Context;
import android.graphics.Typeface;
import android.view.ContextThemeWrapper;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import EMA.chickend.Logic.Classes.Chickens.Chick;
import EMA.chickend.Logic.Classes.Chickens.Chicken;
import EMA.chickend.Logic.Classes.Chickens.CovidChicken;
import EMA.chickend.Logic.Classes.Chickens.CrazyChicken;
import EMA.chickend.Logic.Classes.Chickens.MotherChicken;
import EMA.chickend.Logic.Classes.Chickens.RegularChicken;
import EMA.chickend.R;

public class Level implements Serializable {

    private LogicLevel m_CurrentLogicLevel = null;
    private Chicken[] chickens;
    private RelativeLayout m_VisualComponentOfCurrentLevel = null;

    public Level(int levelNumber, boolean isLocked, int numberOfLives, int theme)
    {
        this.m_CurrentLogicLevel = new LogicLevel(levelNumber, isLocked, numberOfLives, theme);
    }

    public List<Chicken> generateChickens(Context i_Context)
    {
        String[] set = null;
        Random randomGenerator = new Random();
        int currentChickenTypeIndex;
        String currentChickenType;

        if (1 <= this.m_CurrentLogicLevel.getLevelNumber() && this.m_CurrentLogicLevel.getLevelNumber() <= 3)
        {
            set = new String[]{"Chick"};
        }
        else if (4 <= this.m_CurrentLogicLevel.getLevelNumber() && this.m_CurrentLogicLevel.getLevelNumber() <= 6)
        {
            set = new String[]{"Chick", "RegularChicken"};
        }
        else if (7 <= this.m_CurrentLogicLevel.getLevelNumber() && this.m_CurrentLogicLevel.getLevelNumber() <= 9)
        {
            set = new String[]{"Chick", "RegularChicken", "MotherChicken"};
        }
        else if (10 <= this.m_CurrentLogicLevel.getLevelNumber() && this.m_CurrentLogicLevel.getLevelNumber() <= 12)
        {
            set = new String[]{"Chick", "RegularChicken", "MotherChicken", "CrazyChicken"};
        }
        else if (13 <= this.m_CurrentLogicLevel.getLevelNumber())
        {
            set = new String[]{"Chick", "RegularChicken", "MotherChicken", "CrazyChicken", "CovidChicken"};
        }

        this.chickens = new Chicken[ThreadLocalRandom.current().nextInt(30, 41)];

        for (int i = 0; i <  this.chickens.length; i++)
        {
            currentChickenTypeIndex = randomGenerator.nextInt(set.length + 1);

            if (currentChickenTypeIndex == 0)
            {
                currentChickenTypeIndex = 1;
            }

            currentChickenType = set[currentChickenTypeIndex - 1];

            if (currentChickenType.equals("RegularChicken"))
            {
                this.chickens[i] = new RegularChicken(i_Context);
            }
            else if (currentChickenType.equals("Chick"))
            {
                this.chickens[i] = new Chick(i_Context);
            }
            else if (currentChickenType.equals("CrazyChicken"))
            {
                this.chickens[i] = new CrazyChicken(i_Context);
            }
            else if (currentChickenType.equals("MotherChicken"))
            {
                this.chickens[i] = new MotherChicken(i_Context);
            }
            else if (currentChickenType.equals("CovidChicken"))
            {
                this.chickens[i] = new CovidChicken(i_Context);
            }
        }

        return null;
    }

    public int getLevelNumber() {
        return this.m_CurrentLogicLevel.getLevelNumber();
    }

    public Chicken[] getChickens() {
        return chickens;
    }

    public int getNumberOfLives()
    {
        return this.m_CurrentLogicLevel.getNumberOfLives();
    }

    public boolean isLocked()
    {
        return this.m_CurrentLogicLevel.isLocked();
    }

    // TODO: handle lastly..
    public void setIsLocked(boolean newLevelLockState)
    {
        this.m_CurrentLogicLevel.setIsLocked(newLevelLockState);

        if (this.m_VisualComponentOfCurrentLevel != null)
        {
            ((ImageView) this.m_VisualComponentOfCurrentLevel.getChildAt(1)).setImageResource(this.getLevelLockImage());
            this.setImageAlpha();
            this.m_VisualComponentOfCurrentLevel.invalidate();
        }

        // Game.getInstance().setNumberOfOpenLevels();
    }

    public void setNumberOfLives(int numberOfLives)
    {
        this.m_CurrentLogicLevel.setNumberOfLives(numberOfLives);

        if (this.m_VisualComponentOfCurrentLevel != null)
        {
            ((RatingBar) this.m_VisualComponentOfCurrentLevel.getChildAt(2)).setNumStars(numberOfLives);
            this.m_VisualComponentOfCurrentLevel.invalidate();
        }
    }

    public void setImageAlpha()
    {
        // If the level is open
        if (this.m_CurrentLogicLevel.isLocked() == false)
        {
            ((ImageView)this.m_VisualComponentOfCurrentLevel.getChildAt(0)).setImageAlpha(230);
        }
    }

    public int getLevelLockImage()
    {
        if (this.m_CurrentLogicLevel.isLocked() == true)
        {
            return R.drawable.ic_baseline_lock_24;
        }
        else
        {
            return R.drawable.ic_baseline_unlock_24;
        }
    }

    public int getTheme()
    {
        return this.m_CurrentLogicLevel.getTheme();
    }

    public RelativeLayout getVisualComponentOfLevelObject(Context i_Context)
    {
        // Dynamically load the suitable id of the current RelativeLayout
        int currentRelativeLayoutId = AppUtils.getIdValueByIdStringName(String.format("id_of_relativelayout_of_level_%d", this.getLevelNumber()), i_Context);

        // Dynamically load the suitable id of the current ImageView of the theme iamge
        int currentThemeImageViewId = AppUtils.getIdValueByIdStringName(String.format("id_of_imageview_of_level_%d", this.getLevelNumber()), i_Context);

        // Dynamically load the suitable id of the current ImageView of the lock image
        int currentLockerImageViewId = AppUtils.getIdValueByIdStringName(String.format("id_of_lockimageview_of_level_%d", this.getLevelNumber()), i_Context);

        // Dynamically load the suitable id of the current TextView
        int currentTextViewId = AppUtils.getIdValueByIdStringName(String.format("id_of_textview_of_level_%d", this.getLevelNumber()), i_Context);

        // Dynamically load the suitable id of the current RatingBar
        int currentRatingBarId = AppUtils.getIdValueByIdStringName(String.format("id_of_ratingbar_of_level_%d", this.getLevelNumber()), i_Context);

        /* relative layout*/
        this.m_VisualComponentOfCurrentLevel = new RelativeLayout(i_Context);
        this.m_VisualComponentOfCurrentLevel.setId(currentRelativeLayoutId);

        /* image background */
        ImageView imageBackground = new ImageView(i_Context);
        imageBackground.setId(currentThemeImageViewId);
        int dp300 = AppUtils.ConvertPixelsToDPs(i_Context.getResources(),300);
        RelativeLayout.LayoutParams imageBackgroundParams = new RelativeLayout.LayoutParams(dp300,dp300);
        imageBackground.setLayoutParams(imageBackgroundParams);
        imageBackground.setImageResource(this.getTheme());
        // set the pictures to fit the layout dimensions
        imageBackground.setScaleType(ImageView.ScaleType.FIT_XY);

        // If the level is open
        if (this.m_CurrentLogicLevel.isLocked() == false)
        {
            imageBackground.setImageAlpha(230);
        }
        else
        {
            imageBackground.setImageAlpha(100);
        }

        // imageBackground.setImageAlpha(100);
        // this.setImageAlpha();

        /* rating bar */
        RatingBar ratingBar = new RatingBar(new ContextThemeWrapper(i_Context,R.style.ratingBar),null ,R.style.ratingBar);
        ratingBar.setId(currentRatingBarId);
        RelativeLayout.LayoutParams ratingBarParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        ratingBarParams.addRule(RelativeLayout.BELOW,currentThemeImageViewId);
        ratingBarParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        int dp8 = AppUtils.ConvertPixelsToDPs(i_Context.getResources(), 8);
        ratingBarParams.setMargins(0,dp8,0,0);
        ratingBar.setLayoutParams(ratingBarParams);

        ratingBar.setNumStars(this.getNumberOfLives());
        ratingBar.setRating(this.getNumberOfLives());

        /*text view level number*/
        TextView textView = new TextView(i_Context);
        RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        textViewParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textViewParams.addRule(RelativeLayout.CENTER_VERTICAL);
        textView.setLayoutParams(textViewParams);
        textView.setId(currentTextViewId);
        textView.setText(this.getLevelNumber() + "");
        textView.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
        int dp10 = AppUtils.ConvertDPsToPixels(i_Context.getResources(),15);
        textView.setTextSize(dp10);


        /*image lock*/
        ImageView imageLock = new ImageView(i_Context);
        imageLock.setId(currentLockerImageViewId);
        imageLock.setImageResource(this.getLevelLockImage());
        int dp80 = AppUtils.ConvertPixelsToDPs(i_Context.getResources(),80);
        RelativeLayout.LayoutParams imageLockParams = new RelativeLayout.LayoutParams(dp80,dp80);
        imageLockParams.addRule(RelativeLayout.ALIGN_TOP);
        imageLockParams.addRule(RelativeLayout.ALIGN_RIGHT,currentThemeImageViewId);
        imageLock.setLayoutParams(imageLockParams);
        imageLock.setRotation(340);

        this.m_VisualComponentOfCurrentLevel.addView(imageBackground);
        this.m_VisualComponentOfCurrentLevel.addView(imageLock);
        this.m_VisualComponentOfCurrentLevel.addView(ratingBar);
        this.m_VisualComponentOfCurrentLevel.addView(textView);

        return this.m_VisualComponentOfCurrentLevel;
    }

    public RelativeLayout getVisualComponent()
    {
        return this.m_VisualComponentOfCurrentLevel;
    }

    public LogicLevel getLogicLevel()
    {
        return this.m_CurrentLogicLevel;
    }
}