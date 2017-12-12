package businesslogic;
/**
 * The class {@code SolidsFactory} generates {@code IChemicalIObserver} objects
 * using data from file.
 */
import dataclasses.Marker;
import dataclasses.Substance;
import interfaces.IChemicalIObserver;
import interfaces.IFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SolidsFactory implements IFactory {
    /** a cache for chemicals*/
    private static Map<String,Substance> cache=new HashMap<>();
    /**
     * Returns the {@code IChemicalIObserver} object.
     */
    public IChemicalIObserver getChemical(Object... args) {
        String s=(String) args[0];
        if (cache.get(s) == null) {
            Substance substance = null;
            try (Scanner scanner = new Scanner(new File("substances.txt"))) {
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if (line.startsWith(s)) {
                        String marker = line.split(" ")[1];
                        substance = new Substance(s, Marker.valueOf(marker));
                        cache.put(s, substance);
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Problem with connection to library of chemicals");;
            }
        }
        return new Substance(cache.get(s));
    }
}
