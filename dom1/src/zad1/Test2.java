package zad1;

public class Test2 {

	public static void main(String[] args) {

		Theatre t = new Theatre("SNP", 2);

		for (int i = 0; i < 15; i++) {
			new Thread(new Guest("Guest " + i, t)).start();
		}
	}

}
