package zad1;

public class Theatre {

	/** naziv pozorista */
	public String name;

	/** niz saltera kojima pristupaju gosti; ako je vrednost i-tog elementa niza false, i-ti salter je slobodan  */
	public boolean[] counters;

	public Theatre(String name, int counterCount) {
		this.name = name;
		counters = new boolean[counterCount];
	}

	/** zauzimanje saltera ako je slobodan */
	public synchronized int approachCounter(Guest guest) {
		int counter = 0;
		try {
			while ( (counter = getCounter()) == -1) {
				wait();
			}
			counters[counter] = true;
			buyTicket(guest);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return counter;
	}

	/** oslobadjanje saltera */
	public synchronized void leaveCounter(Guest guest, int index) {
		counters[index] = false;
		notify();
	}

	/** vraca redni broj prvog slobodnog saltera; vraca -1 ako nema slobodnih saltera */
	private int getCounter() {
		for (int i = 0; i < counters.length; i++)
			if (counters[i] == false)
				return i;
		return -1;
	}

	private void buyTicket(Guest guest) {
		try {
			Thread.sleep(15000);
			System.out.println(guest.name + " bought a ticket!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}