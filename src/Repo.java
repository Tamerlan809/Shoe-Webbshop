import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Repo {
    public static void main(String[] args) throws IOException {
        while (true){
            System.out.println("Välkommen till Tamerlans Webbshop!!!");
            System.out.println("Om du vill göra beställning skriv in 1 om du vill kolla på rapporter skriv in 2: ");
            Scanner sc = new Scanner(System.in);
            int val = Integer.parseInt(sc.nextLine());

            if(val == 1){
                int kundId = 0;
                int skorId = 0;


                ////Logga in
                Repository r = new Repository();
                System.out.println("INLOGGNING");
                System.out.println("Namn: ");
                String namn = sc.nextLine();
                System.out.println("Lösenord: ");
                String lösenord = sc.nextLine();

                try {
                    List<Kund> hittaKund = r.loggaIn(namn, lösenord);
                    if (!hittaKund.isEmpty()) {
                        Kund kund = hittaKund.getFirst();
                        System.out.println("Inloggning lyckades");
                        //////Hämta kund Id
                        kundId = kund.getId();
                        System.out.println(kundId);
                    } else {
                        System.out.println("Inlogning misslyckades");
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //////Visar skor katalog
                System.out.println("SKO KATALOGEN");
                List<Skor> allaSkor = r.skor();
                allaSkor.forEach(skor -> System.out.println(skor.getSkorByAllData()));

                ///////Välj en färg
                System.out.println("Välj en färg: ");
                String färg = sc.nextLine();

                List<Skor> visaEfterFärg = allaSkor.stream()
                        .filter(skor -> skor.getFärg().equalsIgnoreCase(färg))
                        .toList();
                visaEfterFärg.forEach(skor -> System.out.println(skor.getSkorByAllData()));

                ///////Välja en storlek
                System.out.println("Skriv in en storlek: ");
                String storlek = sc.nextLine();

                List<Skor> visaEfterStorlek = visaEfterFärg.stream()
                        .filter(skor -> skor.getStorlek().equalsIgnoreCase(storlek))
                        .toList();
                visaEfterStorlek.forEach(skor -> System.out.println(skor.getSkorByAllData()));

                //Ta reda på skor_id
                skorId = visaEfterStorlek.get(0).getId();

                //////Lägga beställning
                AddToCart a = new AddToCart();

                int kund_id = kundId;
                int beställning_id = 1000; ///Det ska läggas in nytt beställning varje gång man lägger en order.
                int skor_id = skorId;

                a.AddToCart(kund_id, beställning_id, skor_id);
            } else if (val == 2){
                RapportNrOne r1 = new RapportNrOne();
                RapportNrTwo r2 = new RapportNrTwo();
                RapportNrThree r3 = new RapportNrThree();
                System.out.println("RAPPORTER");
                System.out.println("Välj in en siffra mellan 1 och 3: ");
                int valdRapportSiffra = Integer.parseInt(sc.nextLine());

                if(valdRapportSiffra == 1){
                    r1.rapportNr1();
                } else if (valdRapportSiffra == 2) {
                    r2.rapportNr2();
                } else if (valdRapportSiffra == 3) {
                    r3.rapportNr3();
                } else{
                    System.out.println("Du valde inte en siffra mellan 1 och 3!!!");
                }
            } else {
                System.out.println("Du måste göra ett val mellan bara 1 och 2!!!!");
            }
        }
    }
}