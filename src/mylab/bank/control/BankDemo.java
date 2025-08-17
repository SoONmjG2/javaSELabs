package mylab.bank.control;

import mylab.bank.entity.*;
import mylab.bank.exception.*;

public class BankDemo {
    public static void main(String[] args) throws AccountNotFoundException {
        Bank bank = new Bank();

        System.out.println("=== 계좌 생성 ===");
        String ac1 = bank.createSavingsAccount("홍길동", 10000.0, 0.03);
        System.out.printf("Saving(저축) 계좌가 생성되었습니다: %s%n", bank.findAccount(ac1));
        String ac2 = bank.createCheckingAccount("김철수", 20000.0, 5000.0);
        System.out.printf("체킹 계좌가 생성되었습니다: %s%n", bank.findAccount(ac2));
        String ac3 = bank.createSavingsAccount("이영희", 30000.0, 0.02);
        System.out.printf("저축 계좌가 생성되었습니다: %s%n", bank.findAccount(ac3));
        System.out.println();

        System.out.println("=== 모든 계좌 목록 ===");
        bank.printAllAccounts();

        System.out.println("\n=== 입금/출금 테스트 ===");
        try {
            bank.deposit(ac1, 5000.0);
            bank.withdraw(ac2, 3000.0);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        System.out.println("\n=== 이자 적용 테스트 ===");
        try {
            SavingsAccount sa = (SavingsAccount) bank.findAccount(ac1);
            double interest = sa.getBalance() * sa.getInterestRate();
            bank.deposit(ac1, interest);
            System.out.printf("이자 %.1f원이 적용되었습니다. 현재 잔액: %.1f원%n",
                    interest, sa.getBalance());
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        System.out.println("\n=== 계좌 이체 테스트 ===");
        try {
            bank.transfer(ac3, ac2, 5000.0);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        System.out.println("\n=== 모든 계좌 목록 ===");
        bank.printAllAccounts();

        try {
            bank.withdraw(ac2, 6000.0); 
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        try {
            bank.transfer(ac2, ac1, 6000.0);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
        try {
            bank.deposit("AC9999", 1000.0); 
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}

