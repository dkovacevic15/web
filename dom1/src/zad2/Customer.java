package zad2;

public class Customer extends Thread {
	
	private GasStation station;
	private int fuelType;
	private String name;

	public Customer(GasStation station, int fuelType, String name) {
		this.station = station;
		this.fuelType = fuelType;
		this.name = name;
	}

	public void run() {
		 // 1. musterija se voza gradom
		driveAround();
		
		// 2. dolazi u na pumpu, izabere svoj terminal za tocenje i staje u red
		System.out.println(name + " is approaching pump type " + fuelType);
		station.getGasPump(fuelType).access();
		
		// 3. toci gorivo
		System.out.println(name + " is getting fuel type " + fuelType);
		topUp();
		
		// 4. napusta pumpu (podrazumeva se da je platio ;)
		System.out.println(name + " is leaving the gas station");
		station.getGasPump(fuelType).leave();
	}
	
	private void driveAround() {
		try {
			Thread.sleep((int) Math.round(Math.random()*30000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void topUp() {
		try {
			Thread.sleep((int) Math.round(Math.random()*30000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}