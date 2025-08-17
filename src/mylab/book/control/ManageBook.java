package mylab.book.control;

import mylab.book.entity.*;

public class ManageBook {
    public static void main(String[] args) {
        Publication[] publications = {
                new Magazine("����ũ�μ���Ʈ", "2007-10-01", 328, 9900, "�ſ�"),
                new Magazine("�濵����ǻ��", "2007-10-03", 316, 9000, "�ſ�"),
                new Novel("���߿�", "2007-07-01", 396, 9800, "����������������", "����Ҽ�"),
                new Novel("���ѻ꼺", "2007-04-14", 383, 11000, "����", "���ϼҼ�"),
                new ReferenceBook("�ǿ��������α׷���", "2007-01-14", 496, 25000, "����Ʈ�������"),
                new Novel("�ҳ��̿´�", "2014-05-01", 216, 15000, "�Ѱ�", "����Ҽ�"),
                new Novel("�ۺ������ʴ´�", "2021-09-09", 332, 15120, "�Ѱ�", "����Ҽ�")
        };

        System.out.println("==== ���� ���� ��� ====");
        for (int i = 0; i < publications.length; i++) {
            System.out.printf("%d. %s%n", i + 1, publications[i]);
        }

        System.out.println("\n==== ���� ���� ====");
        Publication target = publications[6]; 
        int before = target.getPrice();
        System.out.printf("%s ���� �� ����: %d��%n", target.getTitle(), before);

        modifyPrice(target);
        int after = target.getPrice();
        System.out.printf("%s ���� �� ����: %d��%n", target.getTitle(), after);
        System.out.printf("����: %d��%n", before - after);

        System.out.println("\n===== ���ǹ� ��� �м� =====");
        new StatisticsAnalyzer().printStatistics(publications);
    }

    public static void modifyPrice(Publication pub) {
        int currentPrice = pub.getPrice();
        if (pub instanceof Magazine) {
            pub.setPrice((int) (currentPrice * 0.6));
        } else if (pub instanceof Novel) {
            pub.setPrice((int) (currentPrice * 0.8));
        } else if (pub instanceof ReferenceBook) {
            pub.setPrice((int) (currentPrice * 0.9));
        }
    }
}
