# Stock-Trading-Simulator-Memento

This is a project I made for my CS 370 - Software Engineering class.

## Memento pattern
For this project I had to implement the Memento design pattern.
Memento  is a design pattern where you periodically save snapshots of your object's state. This allows you to 'undo' your changes and revert to a previous state for example.

## The app
I created this stock trading simulator to implement the memento pattern. The simulator lets you buy, sell, hold and backtrack every trading day. Initially you start with $100,000.00 and 0 $SPY stocks. The $SPY daily opening prices were taken from a Yahoo CSV and they contain prices over the past year.

The app prints the following dashboard:
```
DAY 5
Your Cash Balance: 80,267.999
Your Position:
SPY: 50 x 394.64 = 19,732.001
Total account value: 100,222.499
Today's SPY opening price: 399.09
1) Buy  2) Sell  3) Hold  4) Backtrack
Your choice: _
```
If you choose 1 for example, you get prompted:
```
How many stocks do you want to buy (0 to 201): 50_
```
Then:
```
ORDER REVIEW:
BUY 50 of SPY at 399.089996 for 19954.499799999998
CONFIRM (y/n): y_
```
After you confirm, the day is incremented and you get the dashboard with the updated today's stock price. 

If you are dissatisfied with your life choices, you can choose option 4 to backtrack, which prompts:
```
How many days back do you want to go: 3_
```
After which the app loads your account's state three days ago.

## To Run
You can just run it in my replit environment [heeeere](https://replit.com/@masroork/CSCI370Memento-Pattern#Stock.java).

Or you can clone this repo and open with IntelliJ. I know, who still uses Java, but it was a fun experience.