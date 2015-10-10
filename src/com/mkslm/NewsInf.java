package com.mkslm;

public class NewsInf {
	String currencyPair;
	long valueTime;
	double impact;
	int windowMinutes;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((currencyPair == null) ? 0 : currencyPair.hashCode());
		long temp;
		temp = Double.doubleToLongBits(impact);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (valueTime ^ (valueTime >>> 32));
		result = prime * result + windowMinutes;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewsInf other = (NewsInf) obj;
		if (currencyPair == null) {
			if (other.currencyPair != null)
				return false;
		} else if (!currencyPair.equals(other.currencyPair))
			return false;
		if (Double.doubleToLongBits(impact) != Double
				.doubleToLongBits(other.impact))
			return false;
		if (valueTime != other.valueTime)
			return false;
		if (windowMinutes != other.windowMinutes)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NewsInf [currencyPair=" + currencyPair + ", valueTime="
				+ valueTime + ", impact=" + impact + ", windowMinutes="
				+ windowMinutes + "]";
	}
	
	
}
