package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class CheckingAccount extends Account {
    private final double withdrawalLimit;

    public CheckingAccount(String accountNumber, String ownerName,
                           double balance, double withdrawalLimit) {
        super(accountNumber, ownerName, balance);
        if (withdrawalLimit <= 0) {
            throw new IllegalArgumentException("출금 한도는 0보다 커야 합니다.");
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
        return String.format("%s, 출금 한도: %.1f원", commonString(), withdrawalLimit);
    }
}
