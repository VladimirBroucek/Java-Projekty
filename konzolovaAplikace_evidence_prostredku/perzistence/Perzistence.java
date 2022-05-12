/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perzistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;
import kolekce.Seznam;
import prostredky.DopravniProstredek;

/**
 *
 * @author Admin
 */
public final class Perzistence extends MapovaniProstredku {
    
    private Perzistence() {
    }
    
    public static <T> void uloz(String jmenoSouboru, Seznam<T> seznam) throws IOException {
        try {
            Objects.requireNonNull(seznam);
            
            ObjectOutputStream vystup = new ObjectOutputStream(new FileOutputStream(jmenoSouboru));
            vystup.writeInt(seznam.size());
            
            Iterator<T> it = seznam.iterator();
            while (it.hasNext()) {
                vystup.writeObject(it.next());
            }
            vystup.close();
            
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }
    
    public static <T> Seznam<T> nacti(String jmenoSouboru, Seznam<T> seznam) throws IOException, ClassNotFoundException {
        try {
            Objects.requireNonNull(seznam);
            try (ObjectInputStream vstup = new ObjectInputStream(new FileInputStream(jmenoSouboru))) {
                seznam.zrus();
                
                int pocet = vstup.readInt();
                for (int i = 0; i < pocet; i++) {
                    seznam.vlozNaKonec((T) vstup.readObject());
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            throw new IOException(ex);
        } finally {
        }
        return seznam;
    }
    
    public static Stream<DopravniProstredek> cteni(String nameFile) throws IOException {
        return Files.lines(Paths.get(nameFile), StandardCharsets.UTF_8)
                .filter(t -> t != null)
                .map(mapVstup);
    }
    
    public static void zapis(Stream<DopravniProstredek> stream, String nameFile) throws FileNotFoundException, UnsupportedEncodingException {
        try (PrintWriter zapsani = new PrintWriter(nameFile, "UTF-8")) {
            stream
                    .map(mapVystup)
                    .forEach(zapsani::println);
            
        }
    }
    
}
