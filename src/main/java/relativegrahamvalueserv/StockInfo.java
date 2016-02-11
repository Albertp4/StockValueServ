package relativegrahamvalueserv;

/**
 * @author Frank
 * 
 * POJO for stock that is being evaluated.
 *
 */
public class StockInfo {
	private double price;
	private double eps;
	private double growth;
	private String ticker;
	private double intrinsicValue;
	private double rgv;
	
	public StockInfo(){
		
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getEps() {
		return eps;
	}
	public void setEps(double eps) {
		this.eps = eps;
	}
	public double getGrowth() {
		return growth;
	}
	public void setGrowth(double growth) {
		this.growth = growth;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public double getIntrinsicValue() {
		return intrinsicValue;
	}
	public void setIntrinsicValue(double intrinsicValue) {
		this.intrinsicValue = intrinsicValue;
	}
	public double getRgv() {
		return rgv;
	}
	public void setRgv(double rgv) {
		this.rgv = rgv;
	}

}
