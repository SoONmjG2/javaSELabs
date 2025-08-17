package mylab.bank.entity;

import mylab.bank.exception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bank {
    private final List<Account> accounts = new ArrayList<>();
    private int seq = 1000; 

    public Bank() {}

    private String nextAccountNumber() {
        return "AC" + (seq++);
    }

    public String createSavingsAccount(String owner, double initialBalance, double interestRate) {
        String no = nextAccountNumber();
        accounts.add(new SavingsAccount(no, owner, initialBalance, interestRate));
        return no;
    }

    public String createCheckingAccount(String owner, double initialBalance, double withdrawalLimit) {
        String no = nextAccountNumber();
        accounts.add(new CheckingAccount(no, owner, initialBalance, withdrawalLimit));
        return no;
    }

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        Optional<Account> found = accounts.stream()
                .filter(a -> a.getAccountNumber().equals(accountNumber))
                .findFirst();
        if (!found.isPresent()) {   
            throw new AccountNotFoundException(accountNumber);
        }
        return found.get();
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        Account acc = findAccount(accountNumber);
        acc.deposit(amount);
        System.out.printf("%.1f원이 입금되었습니다. 현재 잔액: %.1f원%n",
                amount, acc.getBalance());
    }

    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        Account acc = findAccount(accountNumber);
        acc.withdraw(amount);
        System.out.printf("%.1f원이 출금되었습니다. 현재 잔액: %.1f원%n",
                amount, acc.getBalance());
    }

    public void transfer(String from, String to, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        Account src = findAccount(from);
        Account dst = findAccount(to);
        src.withdraw(amount);
        System.out.printf("%.1f원이 출금되었습니다. 현재 잔액: %.1f원%n",
                amount, src.getBalance());
        dst.deposit(amount);
        System.out.printf("%.1f원이 입금되었습니다. 현재 잔액: %.1f원%n",
                amount, dst.getBalance());
        System.out.printf("%.1f원이 %s에서 %s로 송금되었습니다.%n", amount, from, to);
    }

    public void printAllAccounts() {
        accounts.forEach(acc -> System.out.println(acc.toString()));
        System.out.println("===================");
    }

    public List<Account> getAccountsView() {
        return new ArrayList<>(accounts); // Java 8 호환 (List.copyOf 없음)
    }
}