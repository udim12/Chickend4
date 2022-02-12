package EMA.chickend.Logic.Classes;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import EMA.chickend.Logic.Interfaces.IGame;
import EMA.chickend.R;

/**
 * This class is A SINGLETON CLASS which represents a Game
 * (Source: https://stackoverflow.com/a/25236059/2196301)
 */
public class Game implements IGame {
    private static Game _instance;
    private List<Level> levels;
    // private int numberOfOpenLevels = 0;

    private Game()
    {
        this.levels = new ArrayList<>();
    }

    /*
    public Game(List<Level> levels) {
        this.levels = levels;
    }
    */

    public static Game getInstance()
    {
        if (_instance == null)
        {
            _instance = new Game();
        }
        return _instance;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    @Override
    public void playGame() {

    }

    @Override
    public void exitGame() {

    }

    public void loadLevels(Context i_Context)
    {
        try {
            // Deserialize the list of the levels (Source: https://howtodoinjava.com/java/collections/arraylist/serialize-deserialize-arraylist/)
            FileInputStream fis = i_Context.openFileInput("Chickend.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);

            List<LogicLevel> logics = (List) ois.readObject();
            this.levels = new ArrayList<Level>();

            LogicLevel currentLogicLevel;

            ois.close();
            fis.close();

            for (int i = 0; i < logics.size(); i++) {
                currentLogicLevel = logics.get(i);
                this.levels.add(new Level(currentLogicLevel.getLevelNumber(), currentLogicLevel.isLocked(), currentLogicLevel.getNumberOfLives(), currentLogicLevel.getTheme()));
            }
        }
        catch (Exception e)
        {
            Level level;
            // Create the list with the chickens of the current level
            // code...

            int currentTheme = 0;
            int currentLevelNumber = 0;

            // Generate the levels of the game
            for (int i = 0; i < 20; i++)
            {
                currentLevelNumber = (i + 1);

                if (1 <= currentLevelNumber && currentLevelNumber <= 3)
                {
                    currentTheme = R.drawable.background___ancient_egypt;
                }
                else if (4 <= currentLevelNumber && currentLevelNumber <= 6)
                {
                    currentTheme = R.drawable.background___beach_of_mosh;
                }
                else if (7 <= currentLevelNumber && currentLevelNumber <= 9)
                {
                    currentTheme = R.drawable.background___route_66;
                }
                else if (10 <= currentLevelNumber && currentLevelNumber <= 12)
                {
                    currentTheme = R.drawable.background___life_of_xp;
                }
                else if (13 <= currentLevelNumber)
                {
                    currentTheme = R.drawable.background___brazil_tour;
                }

                level = new Level(currentLevelNumber, true, 5, currentTheme);
                this.levels.add(level);
            }

            // "Open" the first level in the game.
            this.levels.get(0).setIsLocked(false);
        }

        /*
        try
        {
            // Check if the file with the data of the game exists (Source: https://stackoverflow.com/a/16238330/2196301)
            File file = new File("Chickend.dat");
            if(file.isFile() && file.canRead())
            {
                // Deserialize the list of the levels (Source: https://howtodoinjava.com/java/collections/arraylist/serialize-deserialize-arraylist/)
                FileInputStream fis = new FileInputStream("Chickend.dat");
                ObjectInputStream ois = new ObjectInputStream(fis);

                List<LogicLevel> logics = (List) ois.readObject();
                this.levels = new ArrayList<Level>();
                // this.levels = (List) ois.readObject();

                LogicLevel currentLogicLevel;

                ois.close();
                fis.close();

                for (int i = 0; i < logics.size(); i++)
                {
                    currentLogicLevel = logics.get(i);
                    this.levels.add(new Level(currentLogicLevel.getLevelNumber(), currentLogicLevel.isLocked(), currentLogicLevel.getNumberOfLives(), currentLogicLevel.getTheme()));
                }
            }
            // If the file doesn't exist - behave like it's the first time the game has been opened.
            else
            {
                Level level;
                // Create the list with the chickens of the current level
                // code...

                int currentTheme = 0;
                int currentLevelNumber = 0;

                // Generate the levels of the game
                for (int i = 0; i < 20; i++)
                {
                    currentLevelNumber = (i + 1);

                    if (1 <= currentLevelNumber && currentLevelNumber <= 3)
                    {
                        currentTheme = R.drawable.background___ancient_egypt;
                    }
                    else if (4 <= currentLevelNumber && currentLevelNumber <= 6)
                    {
                        currentTheme = R.drawable.background___beach_of_mosh;
                    }
                    else if (7 <= currentLevelNumber && currentLevelNumber <= 9)
                    {
                        currentTheme = R.drawable.background___route_66;
                    }
                    else if (10 <= currentLevelNumber && currentLevelNumber <= 12)
                    {
                        currentTheme = R.drawable.background___life_of_xp;
                    }
                    else if (13 <= currentLevelNumber)
                    {
                        currentTheme = R.drawable.background___brazil_tour;
                    }

                    level = new Level(currentLevelNumber, true, 5, currentTheme);
                    this.levels.add(level);
                }

                // "Open" the first level in the game.
                this.levels.get(0).setIsLocked(false);
            }
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
        */
    }

    /*
    public void setNumberOfOpenLevels()
    {
        this.numberOfOpenLevels++;
    }

    public int getNumberOfOpenLevels()
    {
        return this.numberOfOpenLevels;
    }

    public int getThemeOfCurrentLastOpenLevel()
    {
        return this.levels.get(this.numberOfOpenLevels - 1).getTheme();
    }
    */

    public List<LogicLevel> getAllLogicLevels()
    {
        List<LogicLevel> logics = new ArrayList<LogicLevel>();

        for (int i = 0; i < this.levels.size(); i++)
        {
            logics.add(this.levels.get(i).getLogicLevel());
        }

        return logics;
    }

    public void saveLevels(Context i_Context)
    {
        // Serialize the list of the levels (Source: https://howtodoinjava.com/java/collections/arraylist/serialize-deserialize-arraylist/)
        try
        {
            FileOutputStream fos = i_Context.openFileOutput("Chickend.dat", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.getAllLogicLevels());
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}