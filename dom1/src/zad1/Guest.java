package zad1;

public class Guest implements Runnable {

	/** ime gosta */
	public String name;

	/** pozoriste u koje se ide */
	private Theatre theatre;
	/** konstruktor */
	public Guest(String name, Theatre theatre) {
		this.name = name;
		this.theatre = theatre;
	}

	/** opisuje ponasanje gosta */
	public void run() {
		walk();
		theatre.leaveCounter(this, theatre.approachCounter(this));
	}

	public void walk() {
		try {
			Thread.sleep( (int) Math.floor(Math.random()*20000) );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}