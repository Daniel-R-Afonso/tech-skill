package com.jpmorgan.stockmarket.model.stocks;

import com.jpmorgan.stockmarket.model.Trade;

import java.util.Date;
import java.util.TreeMap;

public interface Stock {

	String getSymbol();

	void setSymbol(String symbol);

	Double getLastDividend();

	void setLastDividend(Double lastDividend);

	Double getFixedDividend();

	void setFixedDividend(Double fixedDividend);

	Double getParValue();

	void setParValue(Double parValue);

	Double calcDividend(Double tickerPrice);

	Double calcPERatio(Double tickerPrice);

	void buy(Integer quantity, Double price);

	void sell(Integer quantity, Double price);

	Double getPrice();

	Double calculateStockPrice();

	TreeMap<Date, Trade> getTrades();
}
