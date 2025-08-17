package mylab.book.control;

import mylab.book.entity.*;
import java.util.*;

public class ShoppingCart {
    private final List<Publication> items = new ArrayList<>();

    public void addItem(Publication item) {
        items.add(item);
        System.out.println(item.getTitle() + "��(��) ��ٱ��Ͽ� �߰��Ǿ����ϴ�.");
    }

    public boolean removeItem(String title) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTitle().equals(title)) {
                Publication removed = items.remove(i);
                System.out.println(removed.getTitle() + "��(��) ��ٱ��Ͽ��� ���ŵǾ����ϴ�.");
                return true;
            }
        }
        System.out.println("�ش� ������ ���ǹ��� ã�� �� �����ϴ�.");
        return false;
    }

    public void displayCart() {
        System.out.println("====== ��ٱ��� ���� ======");
        for (int i = 0; i < items.size(); i++) {
            Publication p = items.get(i);
            System.out.printf("%d. %s - %,d��%n", i + 1, p.getTitle(), p.getPrice());
        }
        System.out.printf("�� ����: %,d��%n", calculateTotalPrice());
        System.out.printf("���� ���� ����: %,d��%n", calculateDiscountedPrice());
    }

    public int calculateTotalPrice() {
        return items.stream().mapToInt(Publication::getPrice).sum();
    }

    public int calculateDiscountedPrice() {
        int total = 0;
        for (Publication p : items) {
            if (p instanceof Magazine) total += p.getPrice() * 0.9;
            else if (p instanceof Novel) total += p.getPrice() * 0.85;
            else if (p instanceof ReferenceBook) total += p.getPrice() * 0.8;
            else total += p.getPrice();
        }
        return total;
    }

    public void printStatistics() {
        int m = 0, n = 0, r = 0;
        for (Publication p : items) {
            if (p instanceof Magazine) m++;
            else if (p instanceof Novel) n++;
            else if (p instanceof ReferenceBook) r++;
        }
        System.out.println("====== ��ٱ��� ��� ======");
        System.out.printf("����: %d��%n", m);
        System.out.printf("�Ҽ�: %d��%n", n);
        System.out.printf("����: %d��%n", r);
        System.out.printf("�� ���ǹ�: %d��%n", items.size());
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Magazine("����ũ�μ���Ʈ", "2007-10-01", 328, 9900, "�ſ�"));
        cart.addItem(new Magazine("�濵����ǻ��", "2007-10-03", 316, 9000, "�ſ�"));
        cart.addItem(new Novel("���߿�", "2007-07-01", 396, 9800, "����������������", "����Ҽ�"));
        cart.addItem(new Novel("���ѻ꼺", "2007-04-14", 383, 11000, "����", "���ϼҼ�"));
        cart.addItem(new ReferenceBook("�ǿ��������α׷���", "2007-01-14", 496, 25000, "����Ʈ�������"));

        cart.displayCart();
        cart.printStatistics();

        cart.removeItem("���߿�");
        cart.displayCart();
    }
}
