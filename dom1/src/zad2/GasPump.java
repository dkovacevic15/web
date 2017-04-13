package zad2;

import java.util.concurrent.Semaphore;

public class GasPump {

	private final Semaphore available = new Semaphore(1, true);
	
	public GasPump() {
		customerCount = 0;
	}

	/**
	 * Staje u red.
	 */
	public void access() {
		customerCount++;
		try {
			available.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Odlazi.
	 */
	public void leave() {
		customerCount--;
		available.release();
	}

	/**
	 * vraca broj musterija koje stoje u redu, ukljucujuci i onoga ko
	 * je zauzeo terminal za tocenje goriva.
	 */
	public synchronized int getCustomerCount() {
		return customerCount;
	}

	/**
	 * broj musterija koje stoje u redu, ukljucujuci
	 * i onoga ko trenutno koristi terminal za tocenje.
	 */
	public int customerCount;
}
