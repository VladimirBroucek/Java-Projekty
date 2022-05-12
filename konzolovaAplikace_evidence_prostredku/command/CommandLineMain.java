package command;

import generator.Generator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.Stream;
import kolekce.KolekceException;
import kolekce.Seznam;
import prostredky.*;

public class CommandLineMain {

    private static final String ZALOHA_FILE_NAME = "zaloha.bin";
    private static Seznam<DopravniProstredek> seznam;
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws KolekceException, IOException, ClassNotFoundException {
        //seznam = new LinSeznam<>();

        run();
        System.out.println("Konec programu");
    }

    private static void run() throws KolekceException, IOException, ClassNotFoundException {
        String cmd;
        do {

            cmd = Keyboard.getStringItem("Zadej příkaz: ");
            switch (cmd) {
                case "help":
                case "h":
                    vypisHelp();
                    break;
                case "dej":
                case "de":
                    System.out.println("Aktuální prvek: " + seznam.dejAktualni());
                    break;
                case "vyjmi":
                    prikazVyjmi();
                    break;
                case "prvni":
                case "pr":
                    seznam.nastavPrvni();
                    System.out.println("Byl nastaven prvni prvek");
                    break;
                case "exit":
                    System.exit(0);
                    break;
                case "generuj":
                case "g":
                    prikazGeneruj();
                    break;
                case "pocet":
                    prikazPocet();
                    break;
                case "dalsi":
                case "da":
                    seznam.dalsi();
                    System.out.println("Přechod na další prvek");
                    break;
                case "posledni":
                case "po":
                    seznam.nastavPosledni();
                    System.out.println("Byl nastaven poslední prvek");
                    break;
                case "zrus":
                    seznam.zrus();
                    break;
                case "vypis":
                case "v":
                    prikazVypis();
                    break;
                case "novy":
                case "no":
                    prikazNovy();
                    break;

                case "obnov":
                    prikazObnov();
                    break;
                case "zalohuj":
                    prikazZalohuj();
                    break;
                case "nactitext":
                case "nt":
                    //TODO
                    break;
                case "uloztext":
                case "ut":
                    //TODO
                    break;
                case "najdi":
                case "na":
                case "n":
                    prikazNajdi();
                    break;

                default:
                    System.out.println("Neznamy prikaz:" + cmd.length());
            }
        } while (true);
    }

    private static void vypisHelp() {
        System.out.println(""
                + "help, h     - výpis příkazů\n"
                + "novy, no    - vytvoř novou instanci a vlož dopravní prostředek na konec\n"
                + "najdi, na,n - najdi v seznamu dopravní prostředek podle státní poznavací značky\n" 
                + "dej,de      - zobraz aktuální dopravní prostředk v seznamu\n"
                + "edituj,edit - edituj aktuální dopravní prostředek v seznamu\n" //TODO
                + "vyjmi       - vyjmi aktuální dopravní prostředek ze seznamu\n"
                + "prvni,pr    - nastav jako aktuální první dopravní prostředek v seznamu\n"
                + "dalsi,da    - přejdi na další dopravni prostředek\n"
                + "posledni,po - přejdi na poslední dopravní prostředek\n"
                + "pocet       - zobraz počet položek v seznamu\n"
                + "obnov       - obnov seznam dopravních prostředků z binárního souboru\n" 
                + "zalohuj     - zalohuj seznam dopravních prostředků do binárního souboru\n" 
                + "vypis,v     - zobraz seznam dopravních prostředků\n"
                + "nactitext,nt- načti seznam dopravních prostředků z textového souboru\n" //TODO
                + "uloztext,ut - ulož seznam dopravních prostředků do textového souboru\n" //TODO
                + "generuj,g   - generuj náhodně dopravní prostředky pro testování\n"
                + "zrus        - zruš všechny dopravní prostředky v seznamu\n"
                + "exit        - ukončení programu"
        );
    }

    private static void prikazGeneruj() {
        System.out.println("Zadejte pocet prostredku: ");
        int pocetProstredku = input.nextInt();
        seznam = Generator.generuj(pocetProstredku);
    }

    private static void prikazPocet() {
        int pocet = seznam.size();
        System.out.println("Pocet vozidel je " + pocet);
    }

    private static void prikazVyjmi() throws KolekceException {
        seznam.odeberAktualni();
        System.out.println("Byl odebrán aktuální prvek");
    }

    private static void prikazVypis() throws KolekceException {
        Iterator<DopravniProstredek> prostredek = seznam.iterator();

        for (int i = 0; i < seznam.size(); i++) {
            System.out.println(prostredek.next());
        }
    }

    private static void prikazNovy() {
        seznam = Generator.generuj(1);
        System.out.println("Byl přidán nový prostředek");
    }

    private static void prikazObnov() throws IOException, ClassNotFoundException {
        String nazevSouboru = "zaloha.bin";
        perzistence.Perzistence.nacti(nazevSouboru, seznam);
        System.out.println("Seznam byl obnoven");

    }

    private static void prikazZalohuj() throws IOException {
        String nazevSouboru = "zaloha.bin";
        perzistence.Perzistence.uloz(nazevSouboru, seznam);
        System.out.println("seznam byl zalohovan");
    }

//    private static void prikazZapisText() throws FileNotFoundException, UnsupportedEncodingException{
//        String nazevSouboru = "prostredky.txt";
//        perzistence.Perzistence.zapis(stream, nazevSouboru);
//    }
    private static void prikazNajdi() {
        String hledanaSpz = Keyboard.getStringItem("Zadejte čast hledane SPZ: ").toUpperCase();
        Iterator<DopravniProstredek> iterator = seznam.iterator();
        while (iterator.hasNext()) {
            DopravniProstredek prostredek = iterator.next();
            if (prostredek.getSpz().contains(hledanaSpz)) {
                System.out.println("Vaše hledané vozidlo: " + prostredek);
            }

        }
    }

}
