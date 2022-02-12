package EMA.chickend.Logic.Classes.Chickens;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import EMA.chickend.Logic.Classes.Game;
import EMA.chickend.MainActivity;
import EMA.chickend.R;

public class ChickensSounds {
    private Context context = MainActivity.m_context;
    private static ChickensSounds _instance;
    private SoundPool chickenSounds;
    private int chick_sound;
    private int covid_chicken_sound;
    private int crazy_chicken_sound;
    private int mother_chicken_sound;
    private int regular_chicken_sound;

    public ChickensSounds() {
        AudioAttributes audioAttributes = new AudioAttributes
                .Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        SoundPool soundPool = new SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build();

        this.chickenSounds = soundPool;

        chick_sound = chickenSounds.load(
                context,
                R.raw.chick_sound,
                1
        );

        covid_chicken_sound = chickenSounds.load(
                context,
                R.raw.covid_chicken_sound,
                1
        );

        crazy_chicken_sound = chickenSounds.load(
                context,
                R.raw.crazy_chicken_sound,
                1
        );

        mother_chicken_sound = chickenSounds.load(
                context,
                R.raw.mother_chicken_sound,
                1
        );

        regular_chicken_sound = chickenSounds.load(
                context,
                R.raw.regular_chicken_sound,
                1
        );
    }

    public static ChickensSounds getInstance()
    {
        if (_instance == null)
        {
            _instance = new ChickensSounds();
        }
        return _instance;
    }



    public SoundPool getChickenSounds() {
        return chickenSounds;
    }

    public void playSound(int soundNum){
        switch (soundNum) {
            case 1:
                chickenSounds.play(chick_sound,R.raw.chick_sound,1,1,0,1);
                break;
            case 2:
                chickenSounds.play(regular_chicken_sound,R.raw.regular_chicken_sound,1,1,0,1);
                break;
            case 3:
                chickenSounds.play(mother_chicken_sound,R.raw.mother_chicken_sound,1,1,0,1);
                break;
            case 4:
                chickenSounds.play(covid_chicken_sound,R.raw.covid_chicken_sound,1,1,0,1);
                break;
            case 5:
                chickenSounds.play(crazy_chicken_sound,R.raw.crazy_chicken_sound,1,1,0,0);
                break;
        }
    }
}
