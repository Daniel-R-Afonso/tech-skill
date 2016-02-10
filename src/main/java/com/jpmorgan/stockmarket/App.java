package com.jpmorgan.stockmarket;

import java.util.HashMap;
import java.util.Random;

import com.jpmorgan.stockmarket.model.stocks.CommonStockImpl;
import com.jpmorgan.stockmarket.model.stocks.PreferredStockImpl;
import com.jpmorgan.stockmarket.model.stocks.Stock;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class App 
{
	private static Log log = LogFactory.getLog(App.class);

    public static void main( String[] args )
    {
            long tStart = System.currentTimeMillis();
            HashMap<String, Stock> db = new HashMap<String, Stock>();
            db.put("TEA", new CommonStockImpl("TEA", 0.0, 0.0, 100.0));
            db.put("POP", new CommonStockImpl("POP", 8.0, 0.0, 100.0));
            db.put("ALE", new CommonStockImpl("ALE", 23.0, 0.0, 60.0));
            db.put("GIN", new PreferredStockImpl("GIN", 8.0, 0.2, 100.0));
            db.put("JOE", new CommonStockImpl("JOE", 13.0, 0.0, 250.0));

            for (Stock stock: db.values()) {
            	log.info( stock.getSymbol() + " - Dividend          -           " + Util.roundNumber(stock.calcDividend(8.0)));
            	log.info( stock.getSymbol() + " - P/E Ratio         -           " + Util.roundNumber(stock .calcPERatio(7.0)));
                double val = Util.getRandomDouble();
                double val2 = 0.0;
				stock.buy(1, val);
                for (double i=0 ;i <val;i++) {
                    log.info(stock.getSymbol() + " - bought    " + 1 + "   shares for     $" + val);
                    val2 = Util.getRandomDouble();
                    stock.sell(1, val2);
                    log.info(stock.getSymbol() + " - sold      " + 1 + "   shares for     $" + val2);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        log.debug(e.getMessage());
                    }
                }
                log.info( stock.getSymbol() + " - latest stock price:          $" + stock.getPrice());

            	log.info( stock.getSymbol() + " - StockPrice from last min: $" + stock.calculateStockPrice());

                Double GBCEallShareIndex = Util.calculateGBCE(db);
                log.info( "----------------------------------");
                log.info( "GBCE All Share Index: " + Util.roundNumber(GBCEallShareIndex)+"\n");
            }
            long tEnd = System.currentTimeMillis();
            long tDelta = tEnd - tStart;
            double elapsedSeconds = tDelta / 1000.0/60;
            log.info( "Time elapsed : "+elapsedSeconds);

    }
}
