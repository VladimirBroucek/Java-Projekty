/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prostredky;

/**
 *
 * @author Admin
 */
public class Autobus extends DopravniProstredek{
    
    private int pocetSedadel;
    private int pocetNaprav;
    
    public Autobus(float hmotnost, String spz){
        super(ProstredekTyp.AUTOBUS, spz, hmotnost);
    }
    public Autobus(String spz, float hmotnost, int pocetSedadel, int pocetNaprav){
        this(hmotnost, spz);
        this.pocetNaprav = pocetNaprav;
        this.pocetSedadel = pocetSedadel;
    }

    public int getPocetSedadel() {
        return pocetSedadel;
    }

    public void setPocetSedadel(int pocetSedadel) {
        this.pocetSedadel = pocetSedadel;
    }

    public int getPocetNaprav() {
        return pocetNaprav;
    }

    public void setPocetNaprav(int pocetNaprav) {
        this.pocetNaprav = pocetNaprav;
    }
    
    @Override
    public String toString(){
        return super.toString() + ", pocetSedadel=" + pocetSedadel + ", pocetNaprav=" + pocetNaprav;
    }
    
    
}
