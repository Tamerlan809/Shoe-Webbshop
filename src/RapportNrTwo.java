import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RapportNrTwo {

    public void rapportNr2() throws  IOException{
        ////////Rapport nr2/////////////
        Repository r = new Repository();
        List<Beställning> beställning = r.beställning();
        System.out.println("Rapport nr2: En rapport som listar alla kunder och hur många ordrar varje kund har lagt. Skriv ut namn och sammanlagda antalet ordrar för varje kund: ");

        Map<String, Long> mMap = beställning.stream()
                .collect(Collectors.groupingBy(b -> b.getKund().getNamn(), Collectors.counting()));
        mMap.forEach((k,v) -> System.out.println(k + " har gjort " + v + " st beställning/beställningar."));
    }
}