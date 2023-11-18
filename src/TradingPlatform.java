import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
public class TradingPlatform {
    private Stock stock;
    private String ticker = "SPY";
    private Account account;
    private boolean gameOn;
    private int currDay = 0;
    private double currStockPrice;
    private Scanner sc = new Scanner(System.in);

    private ArrayList<Account.AccountSnapshot> accountSnapshotList = new ArrayList<>();

    public TradingPlatform(String stockPriceFilePath) {
        stock = new Stock(stockPriceFilePath);
        account = new Account(100000, "SPY");
        gameOn = true;
    }

    public boolean canTrade() {
        if (account.getTotalValue() > 0)
            return true;
        return false;
    }


    public void makeMove() {
        this.currDay++;
        this.currStockPrice = stock.getStockPriceAt(currDay);
        account.setCurrStockPrice(currStockPrice);
        accountSnapshotList.add(account.getAccountSnapshot());
        makeChoice();
    }

    public void makeChoice() {
        System.out.println("\033[H\033[2J");
        System.out.println("DAY " + currDay);
        System.out.println(account);
        System.out.print("1) Buy  2) Sell  3) Hold  4) Backtrack\nYour choice: ");
        String userChoice = sc.nextLine();

        boolean badInput;
        do {
            badInput = false;
            switch (userChoice) {
                case "1" -> buy();
                case "2" -> sell();
                case "3" -> hold();
                case "4" -> backtrack();
                default -> {
                    badInput = true;
                    System.out.print("Only numbers 1 - 4:");
                    userChoice = sc.nextLine();
                }
            }
        } while (badInput);
    }

    public boolean confirmOrder(String action, int numStocks) {
        System.out.println("ORDER REVIEW:\n" + action + " " +
                numStocks + " of " + ticker +
                " at " + currStockPrice + " for "  + numStocks * currStockPrice);
        System.out.print("CONFIRM (y/n): ");
        String confirm = sc.nextLine();
        if (!confirm.equals("y")) return false;
        return true;
    }

    public void buy() {
        int maxStocksCanBuy = (int)(account.getCashBalance() / currStockPrice);
        System.out.print("How many stocks do you want to buy (0 to " + maxStocksCanBuy + "): ");
        int numStocksToBuy = Integer.parseInt(sc.nextLine());
        double orderCost = numStocksToBuy * currStockPrice;

        while (orderCost > account.getCashBalance()) {
            System.out.println("You don't have enough money");
            System.out.print("How many stocks do you want to buy: ");
            numStocksToBuy = Integer.parseInt(sc.nextLine());
            orderCost = numStocksToBuy * currStockPrice;
        }

        if (!confirmOrder("BUY", numStocksToBuy)) {
            makeChoice();
            return;
        }


//        reduce cash balance
        account.setCashBalance(account.getCashBalance() - orderCost);
//        change account average price
        double newStockValue = account.getStockCount() * account.getAvgPrice() + orderCost;
        account.setAvgPrice(newStockValue / (account.getStockCount() + numStocksToBuy));
//        change holding position stock count
        account.setStockCount(account.getStockCount() + numStocksToBuy);
    }
    public void sell() {
        if (account.getStockCount() == 0) makeChoice();
        System.out.print("How many stocks do you want to sell: ");
        int numStocksToSell = Integer.parseInt(sc.nextLine());
        while (numStocksToSell > account.getStockCount()) {
            System.out.println("You don't have enough stocks");
            System.out.print("How many stocks do you want to sell: ");
            numStocksToSell = Integer.parseInt(sc.nextLine());
        }

        if (!confirmOrder("SELL", numStocksToSell)) {
            makeChoice();
            return;
        }

        double orderCost = numStocksToSell * currStockPrice;

//        reduce stock count
        account.setStockCount(account.getStockCount() - numStocksToSell);
//        add to cash balance
        account.setCashBalance(account.getCashBalance() + orderCost);
    }
    public void hold() {}
    public void backtrack() {
        if (currDay == 1) {
            makeChoice();
        }
        System.out.print("How many days back do you want to go: ");
        int daysBack = Integer.parseInt(sc.nextLine());
        while (daysBack <= 0 || daysBack > currDay) {
            System.out.println("Invalid number of days back.");
            System.out.print("How many days back do you want to go: ");
            daysBack = Integer.parseInt(sc.nextLine());
        }
//        Unnecessary, but I guess I have to initialize it with something
        Account.AccountSnapshot snapshot = account.getAccountSnapshot();
        for (int i = 0; i < daysBack; i++) {
            snapshot = accountSnapshotList.remove(accountSnapshotList.size() - 1);
        }
        currDay -= daysBack;
//        subtracting extra 1 because makeMove() adds 1
        currDay--;
        account.restore(snapshot);
    }
}