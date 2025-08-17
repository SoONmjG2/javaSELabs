package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

public abstract class Account {
    private final String accountNumber;
    private final String ownerName;
    protected double balance;

    public Account(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("입금액은 0보다 커야 합니다.");
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) throw new IllegalArgumentException("출금액은 0보다 커야 합니다.");
        if (balance < amount) {
            throw new InsufficientBalanceException(
                String.format("잔액 부족 (현재 잔액: %.1f원, 요청 금액: %.1f원)", balance, amount)
            );
        }
        balance -= amount;
    }

    protected String commonString() {
        return String.format("계좌번호: %s, 소유자: %s, 잔액: %.1f원", accountNumber, ownerName, balance);
    }

    @Override
    public String toString() {
        return commonString();
    }
}
