import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RapportNrThree {

    public void rapportNr3()throws IOException{
        ///////Rapport nr5///////////
        final Repository r = new Repository();
        final List<Order> orderr = r.order();
        System.out.println("Rapport nr5: En topplista över de mest sålda produkterna, som listar varje modell och hur många ex som har sålts av den modellen. Skriv ut namn på modellen och hur många ex som sålts: ");

        Map<String, Long> mMap = orderr.stream()
                .collect(Collectors.groupingBy(o -> o.getSkor().getSkorByAllData(), Collectors.counting()));
        mMap.forEach((k,v) -> System.out.println(k + " har sålts för " + v + " ex antal gånger."));
    }
}
