package EMA.chickend.Logic.Classes.Chickens;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.core.content.ContextCompat;

import EMA.chickend.Logic.Classes.AppUtils;
import EMA.chickend.Logic.Interfaces.IBlowable;

public abstract class Chicken extends androidx.appcompat.widget.AppCompatImageView implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener{

    private ValueAnimator m_Animator = null;
    private IBlowable m_Listener = null;
    private boolean m_Killed = false;

    // set the speed of the chicken in milliseconds
    private int m_Speed            = 0;
    private int m_ClicksToDeath = 0;

    private int m_Image;

    private float  m_Size          = 0.0f;
    private float  m_XPosition     = 0;
    private float  m_YPosition     = 0;
    //private MediaPlayer chicken_sound =  null;
    private int soundId;
    private ChickensSounds chickenSound = ChickensSounds.getInstance();


    public Chicken(Context context) {
        super(context);
    }

    public Chicken(Context context, int rawHeight,int i_Speed,int i_ClicksToDeath, int i_Image, int i_SoundId ) {
        super(context);

        this.m_Speed         = i_Speed;
        this.m_ClicksToDeath = i_ClicksToDeath;
        this.m_Image         = i_Image;
        this.soundId = i_SoundId;

        m_Listener = (IBlowable) context;

        Drawable chicken = ContextCompat.getDrawable(context, i_Image);

        this.setImageDrawable(chicken);

        rawHeight *= 3;

        int rawWidth = rawHeight / 2;

        int dpHeight = AppUtils.ConvertPixelsToDPs(context.getResources(), rawHeight);

        int dpWidth = AppUtils.ConvertPixelsToDPs(context.getResources(), rawWidth);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpWidth, dpHeight);
        setLayoutParams(params);
    }

    public void chickenAppearances(int screenHeight,int duration) {
        m_Animator = new ValueAnimator();
        m_Animator.setDuration(duration);
        m_Animator.setFloatValues(0f,screenHeight);
        m_Animator.setInterpolator(new LinearInterpolator());
        m_Animator.setTarget(this);
        m_Animator.addListener(this);
        m_Animator.addUpdateListener(this);

        // start the chicken attack.
        m_Animator.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            this.m_ClicksToDeath--;
            chickenSound.playSound(soundId);
//            chicken_sound.start();
//
//            try {
//                if (chicken_sound.isPlaying()) {
//                    chicken_sound.stop();
//                    chicken_sound.release();
//                    chicken_sound = null;
//                    chicken_sound = MediaPlayer.create(this.getContext(), this.soundId);
//                } chicken_sound.start();
//            } catch(Exception e) { e.printStackTrace(); }


            if (this.m_ClicksToDeath == 0)
            {
                if (!this.getKilled())
                {
                    this.getListener().killChicken(this, true);
                    setKilled(true);
                    this.getAnimator().cancel();
                }
                if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    performClick();
                }
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onAnimationEnd(Animator animation, boolean isReverse) {
        if(!m_Killed) {
            m_Listener.killChicken(this,false);
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        setY((float) animation.getAnimatedValue());
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public IBlowable getListener() {
        return m_Listener;
    }

    public void setListener(IBlowable m_Listener) {
        this.m_Listener = m_Listener;
    }

    public boolean getKilled() {
        return m_Killed;
    }

    public ValueAnimator getAnimator() {
        return m_Animator;
    }

    public void setM_Animator(ValueAnimator m_Animator) {
        this.m_Animator = m_Animator;
    }

    public int getSpeed()
    {
        return m_Speed;
    }

    public void setKilled(boolean killed) {
        m_Killed = killed;
        if (killed) {
            m_Animator.cancel();
        }
    }

    public void setSpeed(int m_Speed)
    {
        this.m_Speed = m_Speed;
    }

    public float getSize()
    {
        return m_Size;
    }

    public void setSize(float m_Size)
    {
        this.m_Size = m_Size;
    }

    public int getClicksToDeath()
    {
        return m_ClicksToDeath;
    }

    public void setClicksToDeath(int m_ClicksToDeath)
    {
        this.m_ClicksToDeath = m_ClicksToDeath;
    }

    public int getImage()
    {
        return m_Image;
    }

    public void setImage(int i_Image)
    {
        this.m_Image = i_Image;
    }

    public float getXPosition()
    {
        return m_XPosition;
    }

    public void setXPosition(int m_XPosition)
    {
        this.m_XPosition = m_XPosition;
    }

    public float getYPosition()
    {
        return m_YPosition;
    }

    public void setYPosition(int m_YPosition)
    {
        this.m_YPosition = m_YPosition;
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationStart(Animator animation, boolean isReverse) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {

    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    /*
    public void setContext(Context i_Context)
    {
    }
    */
}