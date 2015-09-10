import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

	// A time counter takes in a length of time, and creates a timer that counts
	// down from this at each second 
	public class timeCounter {
	
		private Timer timer;
		private boolean lost = false;
		private int initialTime;
		private TimerCounter tc;
	
		public timeCounter(int time) {
			this.initialTime = time;
			this.lost = false;
			this.tc = new TimerCounter(initialTime);
			timer = new Timer(1000, tc);
			timer.start();

	}

	// Checks if the timer is done
	public boolean done() {
		return this.lost;
	}

	// Gets the time left
	public int getTime() {
		return tc.getTime();
	}

	// Allows the user to set the time, changing it from the current time
	public void setTime(int time) {
		this.tc.setTime(time);

		timer.start();

		if (time > 0) {
			this.lost = false;
		}
	}

	// Restarts the timer (used when the round is over or when they replay)
	public void restart() {
		this.lost = false;
		setTime(initialTime);
	}
	
	// Adds time to the current time
	public void addTime(int add) {
		this.lost = false;
		setTime(getTime() + add);
	}

	// Stops the timer (if moving to a different panel)
	public void stopTimer() {
		timer.stop();
	}

	// Timer implementation, what to do on each tick action
	public class TimerCounter implements ActionListener {
		private int countDown;

		public TimerCounter(int time) {
			this.countDown = time;
		}

		public void setTime(int time) {
			this.countDown = time;
		}

		public int getTime() {
			return this.countDown;
		}

		public void actionPerformed(ActionEvent tc) {
			countDown--;
			if (countDown <= 0) {
				timer.stop();
				lost = true;
			}
		}
	}

}
