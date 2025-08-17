package mylab.bank.entity;

public class SavingsAccount extends Account {
    private final double interestRate; 

    public SavingsAccount(String accountNumber, String ownerName, double balance, double interestRate) {
        super(accountNumber, ownerName, balance);
        if (interestRate < 0) throw new IllegalArgumentException("이자율은 음수가 될 수 없습니다.");
        this.interestRate = interestRate;
    }

    public double getInterestRate() { return interestRate; }

    @Override
    public String toString() {
        return String.format("%s, 이자율: %.1f%%", commonString(), interestRate * 100);
    }
}
