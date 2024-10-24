import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class Alarm {
    private String label;
    private LocalTime time;
    private String ringtone;
    private boolean isActive;
    private int snoozeDuration; // in minutes

    // Constructor to initialize an alarm
    public Alarm(String label, LocalTime time, String ringtone, int snoozeDuration) {
        this.label = label;
        this.time = time;
        this.ringtone = ringtone;
        this.snoozeDuration = snoozeDuration;
        this.isActive = false;
    }

    // Method to activate the alarm
    public void activate() {
        isActive = true;
        System.out.println("Alarm '" + label + "' set for " + time + " activated.");
        scheduleAlarm();
    }

    // Method to deactivate the alarm
    public void deactivate() {
        isActive = false;
        System.out.println("Alarm '" + label + "' deactivated.");
    }

    // Method to snooze the alarm
    public void snooze() {
        if (isActive) {
            isActive = false;
            System.out.println("Alarm '" + label + "' snoozed for " + snoozeDuration + " minutes.");
            time = time.plusMinutes(snoozeDuration);
            activate(); // Re-activate the alarm after snoozing
        }
    }

    // Method to schedule the alarm using a Timer
    private void scheduleAlarm() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (isActive && LocalTime.now().equals(time)) {
                    ring();
                }
            }
        };
        timer.schedule(task, 0, 60000); // Check every minute if the time matches
    }

    // Method to simulate the alarm ringing
    private void ring() {
        System.out.println("🔔 Ring Ring! Time: " + time + " - " + label);
        playRingtone();
    }

    // Method to simulate playing the ringtone
    private void playRingtone() {
        System.out.println("Playing ringtone: " + ringtone);
    }

    // Main method to demonstrate the alarm functionality
    public static void main(String[] args) {
        // Create a new alarm for 7:00 AM
        Alarm morningAlarm = new Alarm("Morning Alarm", LocalTime.of(7, 0), "Morning_Bird.mp3", 10);
        morningAlarm.activate();

        // Simulate snoozing the alarm
        try {
            Thread.sleep(5000); // Wait for 5 seconds
            morningAlarm.snooze();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
