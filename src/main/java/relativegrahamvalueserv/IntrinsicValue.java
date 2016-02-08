package relativegrahamvalueserv;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 * @author Frank
 * 
 * Calculates the Relative Graham Value of a given stock ticker (e.i "PYPL"). 
 * Information on Benjamin Graham Intrinsic value formula can be found here
 * https://en.wikipedia.org/wiki/Benjamin_Graham_formula
 *
 */
public class IntrinsicValue {
	
	public IntrinsicValue(){
		
	}

	/**
	 * @param ticker
	 * @return
	 * @throws IOException
	 * 
	 * Retrieves Stock info using YahooFinance api, Check dependency details
	 * in pom.xml.
	 */
	public double calculateRGV(String ticker) throws IOException {
		String stockTicker = ticker;
		Jsoup jsoup = null;
		Document doc;
		
		doc = jsoup.connect("https://finance.yahoo.com/q/ae?s="+stockTicker+"+Analyst+Estimates").get();
		Elements elements = doc.getAllElements();
		Element growth = elements.get(747);

		Stock stock = YahooFinance.get(stockTicker);
		BigDecimal currentPrice = stock.getQuote().getPrice();
		BigDecimal growthBD = stock.getStats().getEps();
		double growthRate = Double.parseDouble(growth.text().substring(0, 4));
		double eps = growthBD.doubleValue();

		System.out.println("Earns per share: "+eps);
		System.out.println("Growth: " +growthRate);
		double intrinsicValue = intrinsicValue(eps, growthRate);
		System.out.println("The Intrinsic Value " +stockTicker+ " :" +intrinsicValue);
		
		double rgv;
		rgv = intrinsicValue/currentPrice.doubleValue();
		System.out.println("Relative Graham Value: " +rgv);
		
		return rgv;

	}
	
	/**
	 * @param eps
	 * @param growth
	 * @return
	 */
	public static double intrinsicValue(double eps, double growth){
		double value = (eps*(7+2*growth)*4.4)/4.37;

		return value;
	}

}
