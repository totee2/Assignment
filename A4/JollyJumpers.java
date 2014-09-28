import java.io.BufferedReader;
import java.io.FileReader;

public class JollyJumpers {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("Jollies"));
			String line;
			while ((line = br.readLine()) != null) {
				// int num = line.charAt(0);
				String[] numbers = line.split(" ");
				boolean[] valid = new boolean[Integer.parseInt(numbers[0]) - 1];
				for (int i = 1; i < numbers.length - 1; i++) {
					int diff = Math.abs(Integer.parseInt(numbers[i])
							- Integer.parseInt(numbers[i + 1]));
					if (0 < diff && diff <= valid.length)
						valid[diff-1] = true;
				}
				boolean validOv = true;
				for (int i = 0; i < valid.length; i++) {
					if (!valid[i]) {
						validOv = false;
						break;
					}
				}
				if (validOv) {
					System.out.println("Jolly");
				} else {
					System.out.println("Not Jolly");
				}
			}
		} catch (Exception e) {
			System.out.println("Fehler");
		}
	}
}
