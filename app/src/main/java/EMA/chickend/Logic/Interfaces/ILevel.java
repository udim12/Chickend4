package EMA.chickend.Logic.Interfaces;

/**
 * The ILevel interface represents a stage in the game
 * Each stage has different difficulty levels.
 */
public interface ILevel {
    /**
     * void PlayGame()
     *     Purpose: start the level in the game.
     */
    void PlayGame();

    /**
     * void ExitGame()
     *     Purpose: exit the level and return to the levels menu.
     */
    void ExitGame();
}