package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class CheckingAccount extends Account {
    private final double withdrawalLimit;

    public CheckingAccount(String accountNumber, String ownerName,
                           double balance, double withdrawalLimit) {
        super(accountNumber, ownerName, balance);
        if (withdrawalLimit <= 0) {
            throw new IllegalArgumentException("��� �ѵ��� 0���� Ŀ�� �մϴ�.");
        }
        this.withdrawalLimit = withdrawalLimit;
    }

    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > withdrawalLimit) {
            throw new WithdrawalLimitExceededException(withdrawalLimit);
        }
        super.withdraw(amount);
    }

    @Override
    public String toString() {
        return String.format("%s, ��� �ѵ�: %.1f��", commonString(), withdrawalLimit);
    }
}
