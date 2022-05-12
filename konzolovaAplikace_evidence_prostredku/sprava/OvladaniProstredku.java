/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprava;

import generator.Generator;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import kolekce.KolekceException;
import kolekce.Seznam;
import prostredky.DopravniProstredek;

/**
 *
 * @author Admin
 */
public class OvladaniProstredku implements Ovladani<DopravniProstredek> {

    private Seznam<DopravniProstredek> seznam;
    private Consumer<String> errorLog;
    private Comparator<? super DopravniProstredek> komparator;

    private OvladaniProstredku(Consumer<String> errorLog) {
        this.errorLog = errorLog;
    }

    public static OvladaniProstredku vytvorSpravce(
            Consumer<String> errorLog, Supplier<Seznam<DopravniProstredek>> creator) {
        OvladaniProstredku spravce = new OvladaniProstredku(errorLog);
        spravce.vytvorSeznam(creator);
        return spravce;
    }

    @Override
    public void vytvorSeznam(Supplier<Seznam<DopravniProstredek>> creator) {
        Objects.requireNonNull(creator);
        seznam = creator.get();
    }

    @Override
    public void vytvorSeznam(Function<Integer, Seznam<DopravniProstredek>> creator, int size) throws KolekceException {
        Objects.requireNonNull(creator);
        seznam = creator.apply(size);
        if (seznam == null) {
            throw new KolekceException();
        }
    }

    @Override
    public void nastavKomparator(Comparator<? super DopravniProstredek> comparator) {
        Objects.nonNull(comparator);
        this.komparator = comparator;
    }

    @Override
    public void nastavErrorLog(Consumer<String> errorLog) {
        this.errorLog = errorLog;
    }

    @Override
    public void vlozPolozku(DopravniProstredek data) throws NullPointerException {
        seznam.vlozNaKonec(data);
    }

    @Override
    public DopravniProstredek najdiPolozku(DopravniProstredek klic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void prejdiNaPrvniPolozku() {
        try {
            seznam.nastavPrvni();
        } catch (KolekceException ex) {
            errorMessage(ex);
        }
    }

    @Override
    public void prejdiNaPosledniPolozku() {
        try {
            seznam.nastavPosledni();
        } catch (KolekceException ex) {
            errorMessage(ex);
        }
    }

    @Override
    public void prejdiNaDalsiPolozku() {
        try {
            seznam.dalsi();
        } catch (KolekceException ex) {
            errorMessage(ex);
        }
    }

    @Override
    public void prejdiNaPredchoziPolozku() {
        try {
            seznam.nastavPrvni();
        } catch (KolekceException ex) {
            errorMessage(ex);
        }
    }

    @Override
    public DopravniProstredek nastavAktualniPolozku(DopravniProstredek klic) {
        try {
            if(!seznam.jePrazdny()){
                seznam.nastavPrvni();
                
                do {                    
                    DopravniProstredek prvek = seznam.dejAktualni();
                    if(komparator.compare(prvek, klic) == 0){
                        return prvek;
                    }
                } while (true);
                
            }else{
                return null;
            }
        } catch (KolekceException ex) {
            errorMessage(ex);
        }
        return null;
    }

    @Override
    public DopravniProstredek vyjmiAktualniPolozku() {
        try {
            seznam.odeberAktualni();
        } catch (KolekceException ex) {
            errorMessage(ex);
        }
        return null;
    }

    @Override
    public DopravniProstredek dejKopiiAktualniPolozky() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editujAktualniPolozku(Function<DopravniProstredek, DopravniProstredek> editor) {
        try {
            editor.apply(seznam.dejAktualni());
        } catch (KolekceException ex) {
            errorMessage(ex);
        }
    }

    @Override
    public void ulozDoSouboru(String soubor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nactiZeSouboru(String soubor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void vypis(Consumer<DopravniProstredek> writer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nactiTextSoubor(String soubor, Function<String, DopravniProstredek> mapper) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ulozTextSoubor(String soubor, Function<DopravniProstredek, String> mapper) {
        try(PrintWriter printWrite = new PrintWriter(soubor, "UTF-8")){
            seznam.stream().map(mapper).forEachOrdered(printWrite::println);
        }catch(FileNotFoundException | UnsupportedEncodingException ex){
            errorMessage(ex);
        }
    }

    @Override
    public void generujData(int pocetProstredku) {
        Generator.generuj(pocetProstredku)
                .forEach((t) -> {
                    seznam.vlozNaKonec((DopravniProstredek) t);
                });
    }

    @Override
    public int dejAktualniPocetPolozek() {
        return seznam.size();
    }

    @Override
    public void zrus() {
        seznam.zrus();
    }

    @Override
    public Iterator<DopravniProstredek> iterator() {
        return seznam.iterator();
    }

    private void errorMessage(Exception ex) {
        if (errorLog == null) {
            Logger.getLogger(OvladaniProstredku.class.getName()).log(Level.SEVERE, null, ex);
        } else {
            errorLog.accept((OvladaniProstredku.class.getName()) + " " + ex);
        }
    }
    
    @Override
    public Stream<DopravniProstredek> stream(){
        return seznam.stream();
    }
    
}
