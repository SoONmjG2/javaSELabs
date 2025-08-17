package mylab.book.control;

import mylab.book.entity.*;
import java.text.DecimalFormat;
import java.util.*;

public class StatisticsAnalyzer {

    public Map<String, Double> calculateAveragePriceByType(Publication[] pubs) {
        Map<String, Integer> sum = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        for (Publication p : pubs) {
            String type = getPublicationType(p);
            sum.put(type, sum.getOrDefault(type, 0) + p.getPrice());
            count.put(type, count.getOrDefault(type, 0) + 1);
        }

        Map<String, Double> result = new HashMap<>();
        for (String type : sum.keySet()) {
            result.put(type, sum.get(type) / (double) count.get(type));
        }
        return result;
    }

    public Map<String, Double> calculatePublicationDistribution(Publication[] pubs) {
        Map<String, Integer> count = new HashMap<>();
        for (Publication p : pubs) {
            String type = getPublicationType(p);
            count.put(type, count.getOrDefault(type, 0) + 1);
        }

        Map<String, Double> result = new HashMap<>();
        for (String type : count.keySet()) {
            result.put(type, count.get(type) * 100.0 / pubs.length);
        }
        return result;
    }

    public double calculatePublicationRatioByYear(Publication[] pubs, String year) {
        long cnt = Arrays.stream(pubs)
                .filter(p -> p.getPublishDate().startsWith(year))
                .count();
        return cnt * 100.0 / pubs.length;
    }

    private String getPublicationType(Publication p) {
        if (p instanceof Novel) return "�Ҽ�";
        if (p instanceof Magazine) return "����";
        if (p instanceof ReferenceBook) return "����";
        return "��Ÿ";
    }

    public void printStatistics(Publication[] pubs) {
        DecimalFormat df = new DecimalFormat("#,###.##");

        System.out.println("1. Ÿ�Ժ� ��� ����:");
        Map<String, Double> avg = calculateAveragePriceByType(pubs);
        avg.forEach((t, v) -> System.out.printf("   - %s: %s��%n", t, df.format(v)));

        System.out.println("\n2. ���ǹ� ���� ����:");
        Map<String, Double> dist = calculatePublicationDistribution(pubs);
        dist.forEach((t, v) -> System.out.printf("   - %s: %.2f%%%n", t, v));

        System.out.println("\n3. 2007�⿡ ���ǵ� ���ǹ� ����: "
                + String.format("%.2f%%", calculatePublicationRatioByYear(pubs, "2007")));
    }
}
