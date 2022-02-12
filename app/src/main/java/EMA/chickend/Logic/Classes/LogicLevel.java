package EMA.chickend.Logic.Classes;

import java.io.Serializable;

/**
 * The level class represents a stage in the game
 * Each stage has different difficulty levels.
 */
public class LogicLevel implements Serializable {
    private int levelNumber;
    private boolean isLocked;
    private int numberOfLives;
    private int theme;
    private int lockPhotoId;

    /**
     * public Level()
     *     Purpose: c'tor of the level class
     * @param levelNumber this object represents the level number.
     * @param isLocked
     * @param numberOfLives this object represents the number of lives the player have in the level.
     * @param theme
     */
    public LogicLevel(int levelNumber, boolean isLocked, int numberOfLives, int theme) {
        this.levelNumber = levelNumber;
        this.setIsLocked(isLocked);
        this.numberOfLives = numberOfLives;
        this.theme = theme;
    }

    public int getNumberOfLives() {
        return numberOfLives;
    }

    public void setNumberOfLives(int numberOfLives) {
        this.numberOfLives = numberOfLives;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public void setIsLocked(boolean newLevelLockState)
    {
        this.isLocked = newLevelLockState;
    }

    public boolean isLocked()
    {
        return this.isLocked;
    }

    public int getTheme()
    {
        return this.theme;
    }

    public void setTheme(int newTheme)
    {
        this.theme = newTheme;
    }
}