package mylab.bank.control;

import mylab.bank.entity.*;
import mylab.bank.exception.*;

public class BankDemo {
    public static void main(String[] args) throws AccountNotFoundException {
        Bank bank = new Bank();

        System.out.println("=== ���� ���� ===");
        String ac1 = bank.createSavingsAccount("ȫ�浿", 10000.0, 0.03);
        System.out.printf("Saving(����) ���°� �����Ǿ����ϴ�: %s%n", bank.findAccount(ac1));
        String ac2 = bank.createCheckingAccount("��ö��", 20000.0, 5000.0);
        System.out.printf("üŷ ���°� �����Ǿ����ϴ�: %s%n", bank.findAccount(ac2));
        String ac3 = bank.createSavingsAccount("�̿���", 30000.0, 0.02);
        System.out.printf("���� ���°� �����Ǿ����ϴ�: %s%n", bank.findAccount(ac3));
        System.out.println();

        System.out.println("=== ��� ���� ��� ===");
        bank.printAllAccounts();

        System.out.println("\n=== �Ա�/��� �׽�Ʈ ===");
        try {
            bank.deposit(ac1, 5000.0);
            bank.withdraw(ac2, 3000.0);
        } catch (Exception e) {
            System.out.println("���� �߻�: " + e.getMessage());
        }

        System.out.println("\n=== ���� ���� �׽�Ʈ ===");
        try {
            SavingsAccount sa = (SavingsAccount) bank.findAccount(ac1);
            double interest = sa.getBalance() * sa.getInterestRate();
            bank.deposit(ac1, interest);
            System.out.printf("���� %.1f���� ����Ǿ����ϴ�. ���� �ܾ�: %.1f��%n",
                    interest, sa.getBalance());
        } catch (Exception e) {
            System.out.println("���� �߻�: " + e.getMessage());
        }

        System.out.println("\n=== ���� ��ü �׽�Ʈ ===");
        try {
            bank.transfer(ac3, ac2, 5000.0);
        } catch (Exception e) {
            System.out.println("���� �߻�: " + e.getMessage());
        }

        System.out.println("\n=== ��� ���� ��� ===");
        bank.printAllAccounts();

        try {
            bank.withdraw(ac2, 6000.0); 
        } catch (Exception e) {
            System.out.println("���� �߻�: " + e.getMessage());
        }
        try {
            bank.transfer(ac2, ac1, 6000.0);
        } catch (Exception e) {
            System.out.println("���� �߻�: " + e.getMessage());
        }
        try {
            bank.deposit("AC9999", 1000.0); 
        } catch (Exception e) {
            System.out.println("���� �߻�: " + e.getMessage());
        }
    }
}

