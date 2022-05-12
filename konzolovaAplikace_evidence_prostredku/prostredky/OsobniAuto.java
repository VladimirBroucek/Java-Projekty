/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prostredky;

import util.Barva;

/**
 *
 * @author Admin
 */
public class OsobniAuto extends DopravniProstredek{
    
    private Barva barva;
    private int pocetSedadel;
    private int pocetKW;
    
    
    public OsobniAuto(float hmotnost, String spz){
        super(ProstredekTyp.OSOBNI_AUTO, spz, hmotnost);
    }
    public OsobniAuto(String spz,float hmotnost, Barva barva, int pocetSedadel, int pocetKW){
        this(hmotnost, spz);
        this.barva = barva;
        this.pocetKW = pocetKW;
        this.pocetSedadel = pocetSedadel;
    }

    public Barva getBarva() {
        return barva;
    }

    public void setBarva(Barva barva) {
        this.barva = barva;
    }

    public int getPocetSedadel() {
        return pocetSedadel;
    }

    public void setPocetSedadel(int pocetSedadel) {
        this.pocetSedadel = pocetSedadel;
    }

    public int getPocetKW() {
        return pocetKW;
    }

    public void setPocetKW(int pocetKW) {
        this.pocetKW = pocetKW;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return super.toString() + ", barva=" + barva + ", početSedadel=" + pocetSedadel + ", početKW=" + pocetKW;
    }
    
}
