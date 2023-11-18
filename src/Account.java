import java.text.NumberFormat;

public class Account {
    private double cashBalance;
    private String ticker;
    private int stockCount;
    private double currStockPrice;
    private double avgPrice;

    public Account() {
        cashBalance = 100000;
        ticker = "STOCK";
        stockCount = 0;
    }
    public Account(double balance, String ticker) {
        this.cashBalance = balance;
        this.ticker = ticker;
        this.stockCount = 0;
        avgPrice = 0;
    }

    public void updateDashboard(double currStockPrice) {
        this.currStockPrice = currStockPrice;
    }

    public double getTotalValue() {
        return cashBalance + stockCount * avgPrice;
    }

    @Override
    public String toString() {
        NumberFormat fmt = NumberFormat.getInstance();
        fmt.setGroupingUsed(true);

        return "Your Cash Balance: " + fmt.format(cashBalance) +"\n" +
                "Your Position:\n" +
                ticker + ": " + stockCount + " x " + fmt.format(avgPrice) + " = " + fmt.format((stockCount * avgPrice)) + "\n" +
                "Total account value: " + fmt.format(cashBalance + (stockCount * currStockPrice)) + "\n" +
                "Today's " + ticker + " opening price: " + fmt.format(currStockPrice);
    }

    public AccountSnapshot getAccountSnapshot() {
        return new AccountSnapshot(this);
    }
    public void restore(AccountSnapshot snapshot) {
        cashBalance = snapshot.cashBalance;
        stockCount = snapshot.stockCount;
        ticker = snapshot.ticker;
        avgPrice = snapshot.avgPrice;
    }

    public static class AccountSnapshot {
        private final double cashBalance;
        private final String ticker;
        private final int stockCount;
        private final double avgPrice;
        private AccountSnapshot(Account currAccount) {
            cashBalance = currAccount.cashBalance;
            ticker = currAccount.ticker;
            stockCount = currAccount.stockCount;
            avgPrice = currAccount.avgPrice;
        }

        public double getCashBalance() {
            return cashBalance;
        }
        public String getTicker() {
            return ticker;
        }
        public int getStockCount() {
            return stockCount;
        }
        public double getAvgPrice() {
            return avgPrice;
        }
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void setCurrStockPrice(double currStockPrice) {
        this.currStockPrice = currStockPrice;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public double getAvgPrice() {
        return avgPrice;
    }
}
