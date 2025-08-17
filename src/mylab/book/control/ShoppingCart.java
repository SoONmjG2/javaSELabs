package mylab.book.control;

import mylab.book.entity.*;
import java.util.*;

public class ShoppingCart {
    private final List<Publication> items = new ArrayList<>();

    public void addItem(Publication item) {
        items.add(item);
        System.out.println(item.getTitle() + "이(가) 장바구니에 추가되었습니다.");
    }

    public boolean removeItem(String title) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTitle().equals(title)) {
                Publication removed = items.remove(i);
                System.out.println(removed.getTitle() + "이(가) 장바구니에서 제거되었습니다.");
                return true;
            }
        }
        System.out.println("해당 제목의 출판물을 찾을 수 없습니다.");
        return false;
    }

    public void displayCart() {
        System.out.println("====== 장바구니 내용 ======");
        for (int i = 0; i < items.size(); i++) {
            Publication p = items.get(i);
            System.out.printf("%d. %s - %,d원%n", i + 1, p.getTitle(), p.getPrice());
        }
        System.out.printf("총 가격: %,d원%n", calculateTotalPrice());
        System.out.printf("할인 적용 가격: %,d원%n", calculateDiscountedPrice());
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
        System.out.println("====== 장바구니 통계 ======");
        System.out.printf("잡지: %d권%n", m);
        System.out.printf("소설: %d권%n", n);
        System.out.printf("참고서: %d권%n", r);
        System.out.printf("총 출판물: %d권%n", items.size());
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Magazine("마이크로소프트", "2007-10-01", 328, 9900, "매월"));
        cart.addItem(new Magazine("경영과컴퓨터", "2007-10-03", 316, 9000, "매월"));
        cart.addItem(new Novel("빠삐용", "2007-07-01", 396, 9800, "베르나르베르베르", "현대소설"));
        cart.addItem(new Novel("남한산성", "2007-04-14", 383, 11000, "김훈", "대하소설"));
        cart.addItem(new ReferenceBook("실용주의프로그래머", "2007-01-14", 496, 25000, "소프트웨어공학"));

        cart.displayCart();
        cart.printStatistics();

        cart.removeItem("빠삐용");
        cart.displayCart();
    }
}
