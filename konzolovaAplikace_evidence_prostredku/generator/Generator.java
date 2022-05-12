/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.Random;
import kolekce.LinSeznam;
import kolekce.Seznam;
import prostredky.Autobus;
import prostredky.DopravniProstredek;
import prostredky.Letadlo;
import prostredky.Motorka;
import prostredky.NakladniAuto;
import prostredky.OsobniAuto;
import prostredky.ProstredekTyp;
import util.Barva;

/**
 *
 * @author Admin
 */
public final class Generator {

    private static final Random random = new Random();

    private Generator() {
    }
    
    public static Seznam<DopravniProstredek> generuj(int pocetProstredku){
        Seznam<DopravniProstredek> seznam = new LinSeznam<>();
        for(int i = 0; i < pocetProstredku;i++){
            seznam.vlozNaKonec(generujProstredek());
        }
        return seznam;
    }

    private static DopravniProstredek generujProstredek() {
        DopravniProstredek prostredek = null;
        int typ = random.nextInt(ProstredekTyp.getProstredky().length);
        switch(ProstredekTyp.values()[typ]){
            case MOTORKA:
                prostredek = new Motorka(novaSpz(),novyHmotnost(), novaBarva(), novyPocetKW(), novyObjem());
                break;
            case AUTOBUS:
                prostredek = new Autobus(novaSpz(),novyHmotnost(),novyPocetSedadelAutobus(), novyPocetNaprav());
                break;
            case LETADLO:
                prostredek = new Letadlo(novaSpz(),novyHmotnost(),novyPocetSedadelLetadlo(), novyDolet(), novyPocetMoturu());
                break;
            case NAKLADNI_AUTO:
                prostredek = new NakladniAuto(novaSpz(),novyHmotnost(),novyNosnost(), novyPocetNaprav());
                break;
            case OSOBNI_AUTO:
                prostredek = new OsobniAuto(novaSpz(),novyHmotnost(), novaBarva(), novyPocetSedadelAuto(), novyPocetKW());
                break;
        }
        return prostredek;
    }

    public static String novaSpz() {
        StringBuilder spz = new StringBuilder();
        spz.append(random.nextInt(10))
                .append((char) ('A' + random.nextInt(27)))
                .append((char) ('0' + random.nextInt(10)))
                //  .append(" ")
                .append((char) ('0' + random.nextInt(10)))
                .append((char) ('0' + random.nextInt(10)))
                //                .append("-")
                .append((char) ('0' + random.nextInt(10)))
                .append((char) ('0' + random.nextInt(10)));

        return spz.toString();
    }

    public static Barva novaBarva() {
        return Barva.values()[random.nextInt(Barva.values().length)];
    }

    public static int novyPocetSedadelAuto() {
        return random.nextInt(8) + 2;
    }

    public static int novyPocetSedadelLetadlo() {
        return random.nextInt(500) + 50;
    }

    public static int novyPocetSedadelAutobus() {
        return random.nextInt(100) + 20;
    }

    public static int novyPocetNaprav() {
        return random.nextInt(6) + 2;
    }

    public static int novyPocetMoturu() {
        return random.nextInt(6) + 2;
    }

    public static int novyDolet() {
        return random.nextInt(5000) + 1000;
    }

    public static int novyPocetKW() {
        return random.nextInt(150) + 50;
    }

    public static int novyObjem() {
        return random.nextInt(100) + 50;
    }

    public static int novyNosnost() {
        return random.nextInt(2000) + 500;
    }
    public static int novyHmotnost(){
        return random.nextInt(5000) + 500;
    }

}
