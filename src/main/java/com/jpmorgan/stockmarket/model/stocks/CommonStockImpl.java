package com.jpmorgan.stockmarket.model.stocks;

import com.jpmorgan.stockmarket.model.Trade;
import com.jpmorgan.stockmarket.enums.TradeType;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by David Afonso on 06/02/2016.
 */
public class CommonStockImpl implements Stock {

    private String symbol;
    private Double lastDividend;
    private Double fixedDividend;
    private Double parValue;
    private TreeMap<Date, Trade> trades;

    public CommonStockImpl(String symbol, double lastDividend, double fixedDividend, double parValue) {
        this.symbol = symbol;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
        this.trades = new TreeMap<Date, Trade>();
    }

    public CommonStockImpl() {

    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getLastDividend() {
        return lastDividend;
    }

    public void setLastDividend(Double lastDividend) {
        this.lastDividend = lastDividend;
    }

    public Double getFixedDividend() {
        return fixedDividend;
    }

    public void setFixedDividend(Double fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    public Double getParValue() {
        return parValue;
    }

    public void setParValue(Double parValue) {
        this.parValue = parValue;
    }

    public TreeMap<Date, Trade> getTrades() {
        return trades;
    }

    public void setTrades(TreeMap<Date, Trade> trades) {
        this.trades = trades;
    }

    @Override
    public Double calcDividend(Double tickerPrice) {
        return this.getLastDividend() / tickerPrice;
    }

    @Override
    public Double calcPERatio(Double tickerPrice) {
        return tickerPrice / this.getLastDividend();
    }

    @Override
    public void buy(Integer quantity, Double price) {
        Trade trade = new Trade(TradeType.BUY, quantity, price);
        this.trades.put(new Date(), trade);
    }

    @Override
    public void sell(Integer quantity, Double price) {
        Trade trade = new Trade(TradeType.SELL, quantity, price);
        this.trades.put(new Date(), trade);
    }

    @Override
    public Double getPrice() {
        if (this.trades.size() > 0) {
            return this.trades.lastEntry().getValue().getPrice();
        } else {
            return 0.0;
        }
    }

    @Override
    public Double calculateStockPrice() {
        Double summatoryPriceQty = 0.0;
        Integer summatoryQty = 0;
        Date now = new Date();
        Date startTime = new DateTime().minusMinutes(1).toDate();
        SortedMap<Date, Trade> trades = this.trades.tailMap(startTime);

        for (Trade trade : trades.values()) {
            summatoryQty += trade.getQuantity();
            summatoryPriceQty += trade.getPrice() * trade.getQuantity();
        }
        return summatoryPriceQty / summatoryQty;
    }
}
