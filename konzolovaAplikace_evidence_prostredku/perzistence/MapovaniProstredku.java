/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perzistence;

import java.util.function.Function;
import prostredky.Autobus;
import prostredky.DopravniProstredek;
import prostredky.Letadlo;
import prostredky.Motorka;
import prostredky.NakladniAuto;
import prostredky.OsobniAuto;
import util.Barva;

/**
 *
 * @author Admin
 */
public class MapovaniProstredku {

    public MapovaniProstredku() {
    }

    static Function<String, DopravniProstredek> mapVstup = (radek) -> {
        DopravniProstredek prostredek = null;

        if (radek.length() == 0) {
            return prostredek;
        }
        String[] values = radek.split(",");
        String zkratkaProstrekdu = values[0].trim().toLowerCase();
        String spz = values[1].trim();
        float hmotnost = Float.valueOf(values[2]);

        switch (zkratkaProstrekdu) {
            case "au":
                int pocetSedadel = Integer.valueOf(values[3].trim());
                int pocetNaprav = Integer.valueOf(values[4].trim());
                prostredek = new Autobus(spz, hmotnost, pocetSedadel, pocetNaprav);
                break;
            case "le":
                int pocetSedadelLetadlo = Integer.valueOf(values[3].trim());
                int dolet = Integer.valueOf(values[4].trim());
                int pocetMotoru = Integer.valueOf(values[5].trim());
                prostredek = new Letadlo(spz, hmotnost, pocetSedadelLetadlo, dolet, pocetMotoru);
                break;
            case "mo":
                Barva barva = Barva.decode(values[3].trim());
                int pocetKW = Integer.valueOf(values[4].trim());
                float objem = Float.valueOf(values[5].trim());
                prostredek = new Motorka(spz, hmotnost, barva, pocetKW, objem);
                break;
            case "na":
                int nosnost = Integer.valueOf(values[3].trim());
                int pocetNapravNa = Integer.valueOf(values[4].trim());
                prostredek = new NakladniAuto(spz, hmotnost, nosnost, pocetNapravNa);
                break;
            case "os":
                Barva barvaAuta = Barva.decode(values[3].trim());
                int pocetSedadelAuto = Integer.valueOf(values[4].trim());
                int pocetKWAuto = Integer.valueOf(values[5].trim());
                prostredek = new OsobniAuto(spz, hmotnost, barvaAuta, pocetSedadelAuto, pocetKWAuto);
                break;
        }

        return prostredek;

    };

    static Function<DopravniProstredek, String> mapVystup = (prostredek) -> {
        switch (prostredek.getType()) {
            case AUTOBUS:
                return String.format("au, %s, %5.0f, %d, %d", prostredek.getSpz(), prostredek.getHmotnost(), ((Autobus) prostredek).getPocetSedadel(), ((Autobus) prostredek).getPocetNaprav());
            case LETADLO:
                return String.format("le, %s, %5.0f,%d,%d,%d", prostredek.getSpz(), prostredek.getHmotnost(), ((Letadlo) prostredek).getPocetSedadel(), ((Letadlo) prostredek).getDolet(),
                        ((Letadlo) prostredek).getPocetMotoru());
            case MOTORKA:
                return String.format("mo, %s, %5.0f,%s,%d,%5.0f", prostredek.getSpz(), prostredek.getHmotnost(),
                        ((Motorka) prostredek).getBarva().getNazev(), ((Motorka) prostredek).getPocetKW(), ((Motorka) prostredek).getObjem());
            case NAKLADNI_AUTO:
                return String.format("na, %s, %5.0f,%d,%d", prostredek.getSpz(), prostredek.getHmotnost(),
                ((NakladniAuto)prostredek).getNosnost(),((NakladniAuto)prostredek).getPocetNaprav());
            case OSOBNI_AUTO:
                return String.format("os,%s,%5.0f,%s,%d,%d", prostredek.getSpz(),prostredek.getHmotnost(),
                        ((OsobniAuto)prostredek).getBarva().getNazev(),((OsobniAuto)prostredek).getPocetSedadel(),((OsobniAuto)prostredek).getPocetKW());
        }
        return null;
    };
}
