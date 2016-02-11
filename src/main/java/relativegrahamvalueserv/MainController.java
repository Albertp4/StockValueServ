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
		StockInfo stock = new StockInfo();
		IntrinsicValue intrinsicValue = new IntrinsicValue();
		
		try {
			stock.setTicker(ticker);
			stock = intrinsicValue.calculateRGV(stock);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
