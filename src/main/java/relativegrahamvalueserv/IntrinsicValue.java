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
	public StockInfo calculateRGV(StockInfo stockEval) throws IOException {
		Stock stock = YahooFinance.get(stockEval.getTicker());
		
		stockEval.setPrice(stock.getQuote().getPrice().doubleValue());
		stockEval.setEps(stock.getStats().getEps().doubleValue());
		stockEval.setGrowth(getNext5YearGrowth(stockEval.getTicker()));
		stockEval.setIntrinsicValue(calculateIntrinsicValue(stockEval.getEps(), stockEval.getGrowth()));
		stockEval.setRgv(calculateRGV(stockEval.getIntrinsicValue(), stockEval.getPrice()));
		
		System.out.println("The Intrinsic Value " +stockEval.getTicker()+ " :" +stockEval.getIntrinsicValue());
		System.out.println("Relative Graham Value: " +stockEval.getRgv());
		
		return stockEval;
	}
	
	/**
	 * @param eps
	 * @param growth
	 * @return
	 */
	public static double calculateIntrinsicValue(double eps, double growth){
		double value = (eps*(7+2*growth)*4.4)/4.37;

		return value;
	}
	
	public static double calculateRGV(double intrinsicValue, double currentPrice){
		return intrinsicValue/currentPrice;
	}
	
	public static double getNext5YearGrowth(String stockTicker) throws IOException{
		double growth =0.0;
		Document doc;
		Jsoup jsoup = null;
		
		if(stockTicker != null){
			doc = jsoup.connect("https://finance.yahoo.com/q/ae?s="+stockTicker+"+Analyst+Estimates").get();
			Elements elements = doc.getAllElements();
			for(int x = 0; x < elements.size();x++){
				if(elements.get(x).text().contentEquals("Next 5 Years (per annum)")){
					System.out.println("Element: "+elements.get(x).text());
					growth = Double.parseDouble(elements.get(x+1).text().substring(0, 4));
				}
			}
		}else{
			System.out.println("StockEvaluation object is Null");
		}
		
		return growth;
	}

}
