import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RapportNrOne {
    final Repository r = new Repository();
    final List<Skor> visaSkorId;
    {
        try {
            visaSkorId = r.skor();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    final Scanner sc = new Scanner(System.in);
    List<Integer> skorId = new ArrayList<>();

    SkorIdSearcher färgSearch = (skor, s) -> skor.getFärg().equalsIgnoreCase(s);
    SkorIdSearcher märkeSearch = (skor, s) -> skor.getMärke().equalsIgnoreCase(s);
    SkorIdSearcher storlekSearch = (skor, s) -> skor.getStorlek().equalsIgnoreCase(s);
    public void getSkorId(String val2, SkorIdSearcher sis){
        skorId =  visaSkorId.stream()
                .filter(skor -> sis.search(skor, val2))
                .map(Skor::getId)
                .toList();
    }

    public void rapportNr1() throws IOException {
        System.out.println("Rapport nr1: En rapport som listar alla kunder, med namn och adress, som har handlat varor i en viss storlek, av en viss färg eller av ett visst märke.");
        System.out.println("Välja vad du du vill ange(valet är antingen färg eller märke eller storlek): ");
        final String val1 = sc.nextLine();
        System.out.println("Skriv in " + val1);
        final String val2 = sc.nextLine();

        if (val1.equalsIgnoreCase("färg")){
            getSkorId(val2, färgSearch);
        } else if (val1.equalsIgnoreCase("märke")) {
            getSkorId(val2, märkeSearch);
        } else if (val1.equalsIgnoreCase("storlek")) {
            getSkorId(val2, storlekSearch);
        } else {
            System.out.println("Du måste skriva in bara färg eller märke eller storlek");
        }

        List<Order> visaBeställningId = r.order();
        List<Integer> finalSkorId = skorId;
        List<Integer> beställningId = visaBeställningId.stream()
                .filter(order -> finalSkorId.contains(order.getSkor().getId()))
                .map(Order::getBeställningId)
                .toList();

        List<Beställning> visaKundId = r.beställning();
        List<Integer> kundId = visaKundId.stream()
                .filter(beställning -> beställningId.contains(beställning.getId()))
                .map(b -> b.getKund().getId())
                .toList();

        List<Kund> taNamnOchOrt = r.kund();
        List<Kund> skrivUtRapporten = taNamnOchOrt.stream()
                .filter(kund -> kundId.contains(kund.getId()))
                .toList();

        skrivUtRapporten.forEach(kund -> System.out.println(kund.getKundDataByNameAndOrt()));
    }
}
