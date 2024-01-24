package Logic;
import java.util.TimerTask;
import java.util.Timer;

/**
 * GameTimer is a class that manages the timing aspects of the game.
 * It provides methods to start and stop the timer, and get the elapsed time.
 */
public class GameTimer {

    private Timer timer;
    private TimerTask timerTask;
    private int elapsedSeconds;


    /**
     * Default constructor for the GameTimer class.
     * Initializes the elapsed time to 0.
     */
    public GameTimer() {
        this.elapsedSeconds = 0;
    }

    /**
     * Starts the timer.
     * Initializes the timer and the task, and schedules the task to run every second.
     */
    public void startTimer() {
        // Initialize the timer and the task
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                elapsedSeconds++;

                // I've tied the tickrate to the playermovement, remove this if you want it to tick every second
                // if (game != null) {
                //     game.gameTick();  // ensure game is not null before calling gameTick
                // }
                // game.gameTick(); // Calling game's tick method every second
                // Here can also add logic to update the game or UI as the timer progresses
            }
        };
        // Schedule the timer task to run every second
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    /**
     * Stops the timer.
     * Cancels the timer if it is not null.
     */
    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    /**
     * Returns the elapsed time in seconds.
     * @return The elapsed time in seconds.
     */
    public int getElapsedSeconds() {
        return elapsedSeconds;
    }
}