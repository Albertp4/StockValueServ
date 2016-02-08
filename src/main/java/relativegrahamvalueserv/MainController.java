package relativegrahamvalueserv;

import java.io.IOException;

/**
 * @author Frank
 *
 *Main executable for testing
 */
public class MainController {

	public static void main(String[] args) {
		String ticker = "PYPL";
		double stockRGV;
		IntrinsicValue intrinsicValue = new IntrinsicValue();
		
		try {
			stockRGV = intrinsicValue.calculateRGV(ticker);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
