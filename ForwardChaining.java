import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForwardChaining {

    private static final Map<String, List<String>> rules = new HashMap<>();
    private static final List<String> facts = new ArrayList<>();

    public static void main(String[] args) {
        initRules();
        initFacts();

        while (true) {
            boolean ruleFired = false;

            for (Map.Entry<String, List<String>> rule : rules.entrySet()) {
                String conclusion = rule.getKey();
                List<String> premises = rule.getValue();

                // Memeriksa apakah semua premis terpenuhi
                boolean allPremisesSatisfied = true;
                for (String premise : premises) {
                    if (!facts.contains(premise)) {
                        allPremisesSatisfied = false;
                        break;
                    }
                }

             
                if (allPremisesSatisfied) {
                    facts.add(conclusion);
                    System.out.println("Menambahkan fakta: " + conclusion);
                    ruleFired = true;

           
                    if (conclusion.equals("K")) {
                        System.out.println("Kesimpulan: K bernilai benar");
                        return;
                    }
                }
            }


            if (!ruleFired) {
                break;
            }
        }

        System.out.println("Kesimpulan: K tidak dapat dipastikan nilainya");
    }

    private static void initRules() {
        rules.put("C", List.of("A", "B"));
        rules.put("D", List.of("C"));
        rules.put("F", List.of("A", "E"));
        rules.put("G", List.of("A"));
        rules.put("D", List.of("F", "G"));
        rules.put("H", List.of("G", "E"));
        rules.put("I", List.of("C", "H"));
        rules.put("J", List.of("I", "A"));
        rules.put("J", List.of("G"));
        rules.put("K", List.of("J"));
    }

    private static void initFacts() {
        facts.add("A");
        facts.add("E");
    }
}