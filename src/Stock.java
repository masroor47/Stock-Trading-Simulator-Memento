import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Stock {

    private ArrayList<Double> priceList;

    public Stock(String filePath) {
        priceList = loadPrices(filePath);
    }

    public ArrayList<Double> loadPrices(String filePath) {
        ArrayList<Double> openingPrices = new ArrayList<Double>();
        try {
            Scanner sc = new Scanner(new File(filePath));
            // remove first row of column names
            String[] currLine = sc.next().split(",");
            while (sc.hasNext()) {
                currLine = sc.next().split(",");
                openingPrices.add(Double.parseDouble(currLine[1]));
            }

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return openingPrices;
    }

    public double getStockPriceAt(int day) {
        return priceList.get(day);
    }
}