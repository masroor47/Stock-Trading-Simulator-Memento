public class Main {

    public static void main(String[] args) {
        String stockPriceFilePath = "./data/SPY.csv";
        TradingPlatform tradingPlatform = new TradingPlatform(stockPriceFilePath);

        while (tradingPlatform.canTrade()) {
            tradingPlatform.makeMove();
        }
    }
}
