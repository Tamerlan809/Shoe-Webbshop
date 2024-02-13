import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//Main class for webbshop.
public class Repo {
    public static void main(String[] args) throws IOException {

        //Loop for presenting the user with options.
        while (true){
            System.out.println("Välkommen till Tamerlans Webbshop!!!");
            System.out.println("Om du vill göra beställning skriv in 1 om du vill kolla på rapporter skriv in 2: ");
            Scanner sc = new Scanner(System.in);
            int val = Integer.parseInt(sc.nextLine()); //Reads the user answer.

            //If user chooses to place an order
            if(val == 1){
                int kundId = 0;
                int skorId = 0;


                ////Login
                Repository r = new Repository();
                System.out.println("INLOGGNING");
                System.out.println("Namn: ");
                String namn = sc.nextLine(); //Name input
                System.out.println("Lösenord: ");
                String lösenord = sc.nextLine(); //Password input

                try {
                    //Tries to login with given inputs.
                    List<Kund> hittaKund = r.loggaIn(namn, lösenord);
                    if (!hittaKund.isEmpty()) {
                        Kund kund = hittaKund.getFirst();
                        System.out.println("Inloggning lyckades");
                        //////Get the kund_id
                        kundId = kund.getId();
                        System.out.println(kundId);
                    } else {
                        //if login fails it exits the order process.
                        System.out.println("Inlogning misslyckades");
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //////Show the shoe catalog
                System.out.println("SKO KATALOGEN");
                List<Skor> allaSkor = r.skor();
                //Prints each shoe's details.
                allaSkor.forEach(skor -> System.out.println(skor.getSkorByAllData()));

                ///////Choose a shoe by colour
                System.out.println("Välj en färg: ");
                String färg = sc.nextLine();

                List<Skor> visaEfterFärg = allaSkor.stream()
                        .filter(skor -> skor.getFärg().equalsIgnoreCase(färg))
                        .toList();
                visaEfterFärg.forEach(skor -> System.out.println(skor.getSkorByAllData()));

                ///////Choose the shoe by size
                System.out.println("Skriv in en storlek: ");
                String storlek = sc.nextLine();

                List<Skor> visaEfterStorlek = visaEfterFärg.stream()
                        .filter(skor -> skor.getStorlek().equalsIgnoreCase(storlek))
                        .toList();
                visaEfterStorlek.forEach(skor -> System.out.println(skor.getSkorByAllData()));

                //It will be only left one shoe left after filtretion thats why it gets the shoeId
                skorId = visaEfterStorlek.get(0).getId();

                //////Put the order
                AddToCart a = new AddToCart();

                //Gets the parameters for to use later in AddToCart
                int kund_id = kundId;
                int beställning_id = 1000; ///Det ska läggas in nytt beställning varje gång man lägger en order.
                int skor_id = skorId;

                //Adds the shoe into the cart
                a.AddToCart(kund_id, beställning_id, skor_id);

                //Run the rapports page if user's input is 2.
            } else if (val == 2){
                RapportNrOne r1 = new RapportNrOne();
                RapportNrTwo r2 = new RapportNrTwo();
                RapportNrThree r3 = new RapportNrThree();
                System.out.println("RAPPORTER");
                System.out.println("Välj in en siffra mellan 1 och 3: ");
                int valdRapportSiffra = Integer.parseInt(sc.nextLine());

                //Displays the rapport based on user's input.
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
                //If user's input is invalid it gaves info about what to do.
                System.out.println("Du måste göra ett val mellan bara 1 och 2!!!!");
            }
        }
    }
}