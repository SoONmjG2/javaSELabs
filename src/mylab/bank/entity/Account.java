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
        if (amount <= 0) throw new IllegalArgumentException("�Աݾ��� 0���� Ŀ�� �մϴ�.");
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) throw new IllegalArgumentException("��ݾ��� 0���� Ŀ�� �մϴ�.");
        if (balance < amount) {
            throw new InsufficientBalanceException(
                String.format("�ܾ� ���� (���� �ܾ�: %.1f��, ��û �ݾ�: %.1f��)", balance, amount)
            );
        }
        balance -= amount;
    }

    protected String commonString() {
        return String.format("���¹�ȣ: %s, ������: %s, �ܾ�: %.1f��", accountNumber, ownerName, balance);
    }

    @Override
    public String toString() {
        return commonString();
    }
}
