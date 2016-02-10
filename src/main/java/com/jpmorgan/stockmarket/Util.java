package com.jpmorgan.stockmarket;

import com.jpmorgan.stockmarket.model.stocks.Stock;

import java.io.DataOutput;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Random;

public class Util {

	public static Double calculateGBCE(Map<String, Stock> stocks) {
		Double sum = 0.0;
		for(Stock stock: stocks.values()) {
			sum+=stock.getPrice();
		}
		return Math.pow(sum, 1.0 / stocks.size());
	}
	public static String roundNumber(Double number){
		DecimalFormat numberFormat = new DecimalFormat("#.00");

        if(number.isInfinite()){
            return number.toString();
        }
        return Double.valueOf(numberFormat.format(number).replace(",",".")).toString();

	}

    public static Double getRandomDouble(){
        Random r = new Random();
        int Low = 120;
        int High = 1200;
        return Double.valueOf((r.nextInt(High-Low)+Low)/100);

    }

}
