package com.jpmorgan.stockmarket;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;

import com.jpmorgan.stockmarket.enums.TradeType;
import com.jpmorgan.stockmarket.model.Trade;
import com.jpmorgan.stockmarket.model.stocks.CommonStockImpl;
import com.jpmorgan.stockmarket.model.stocks.PreferredStockImpl;
import com.jpmorgan.stockmarket.model.stocks.Stock;
import org.joda.time.DateTime;
import org.junit.Test;

public class StockTest {

	@Test
	public void testDividend() {
        Stock stockALE = new CommonStockImpl("ALE", 23.0, 0.0, 60.0);
        Stock stockGIN = new PreferredStockImpl("GIN", 8.0, 0.2, 100.0);

        //Common
		Double dividendALE = stockALE.calcDividend(1.0);
		Double expectedDividendALE = stockALE.getLastDividend()/1.0;
		assertEquals(expectedDividendALE, dividendALE, 0.0);

		//Preferred
		Double dividendGIN = stockGIN.calcDividend(1.0);
		Double expectedDividendGIN = stockGIN.getFixedDividend()*stockGIN.getParValue()/1.0;
		assertEquals(expectedDividendGIN, dividendGIN, 0.0);
	}

	@Test
	public void testPERatio() {
        Stock stockALE = new CommonStockImpl("ALE", 23.0, 0.0, 60.0);
        Double peRatioALE = stockALE .calcPERatio(1.0);
        Double expectedPeRatioALE = 1.0/stockALE.getLastDividend();
        assertEquals(expectedPeRatioALE, peRatioALE, 0.0);
	}

	@Test
	public void testBuy() {
		Stock stockALE = new CommonStockImpl("ALE", 23.0, 0.0, 60.0);
		stockALE.buy(1, 10.0);
		assertEquals(10.0, stockALE.getPrice(), 0.0);
	}

	@Test
	public void testSell() {
		Stock stockALE = new CommonStockImpl("ALE", 23.0, 0.0, 60.0);
		stockALE.sell(1, 10.0);
		assertEquals(10.0, stockALE.getPrice(), 0.0);
	}

	@Test
	public void testGetPrice() {
		Stock stockALE = new CommonStockImpl("ALE", 23.0, 0.0, 60.0);
		stockALE.sell(1, 10.0);
		stockALE.buy(1, 11.0);

		//test the latest stock price
		assertEquals(11.0, stockALE.getPrice(), 0.0);
	}

	@Test
	public void testStockPrice() {
		Stock stockALE = new CommonStockImpl("ALE", 23.0, 0.0, 60.0);
		stockALE.sell(2, 10.0);
		stockALE.buy(2, 10.0);		
		Double StockPrice = stockALE.calculateStockPrice();
		assertEquals(10.0, StockPrice, 0.0);

		//last 15 minutes stocks
		Date startTime = new DateTime().minusMinutes(15).toDate();
		stockALE.getTrades().put(startTime, new Trade(TradeType.BUY, 1, 20.0));
		StockPrice = stockALE.calculateStockPrice();
		assertEquals(10.0, StockPrice, 0.0);
	}

	@Test
	public void testAllShareIndex() {

		HashMap<String, Stock> db = new HashMap<String, Stock>();
		db.put("TEA", new CommonStockImpl("TEA", 0.0, 0.0, 100.0));
		db.put("POP", new CommonStockImpl("POP", 8.0, 0.0, 100.0));
		db.put("ALE", new CommonStockImpl("ALE", 23.0, 0.0, 60.0));
		db.put("GIN", new PreferredStockImpl("GIN", 8.0, 0.2, 100.0));
		db.put("JOE", new CommonStockImpl("JOE", 13.0, 0.0, 250.0));

		//buy and sell some trades
		for (Stock stock: db.values()) {
			for (int i=1; i <= 5; i++) {
				stock.buy(1, 1.0);
				stock.sell(1, 1.2);
			}
		}
		Double GBCE = Util.calculateGBCE(db);
		assertEquals(1.4309690811052556, GBCE, 0.0);
	}

	@Test
	public void testRoundNumber() {
		String number = Util.roundNumber(11.9876575565);
		assertEquals(number,"11.99");
	}
}
