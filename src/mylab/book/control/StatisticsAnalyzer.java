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
        if (p instanceof Novel) return "소설";
        if (p instanceof Magazine) return "잡지";
        if (p instanceof ReferenceBook) return "참고서";
        return "기타";
    }

    public void printStatistics(Publication[] pubs) {
        DecimalFormat df = new DecimalFormat("#,###.##");

        System.out.println("1. 타입별 평균 가격:");
        Map<String, Double> avg = calculateAveragePriceByType(pubs);
        avg.forEach((t, v) -> System.out.printf("   - %s: %s원%n", t, df.format(v)));

        System.out.println("\n2. 출판물 유형 분포:");
        Map<String, Double> dist = calculatePublicationDistribution(pubs);
        dist.forEach((t, v) -> System.out.printf("   - %s: %.2f%%%n", t, v));

        System.out.println("\n3. 2007년에 출판된 출판물 비율: "
                + String.format("%.2f%%", calculatePublicationRatioByYear(pubs, "2007")));
    }
}
