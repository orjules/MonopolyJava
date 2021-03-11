package programm.system;

import java.util.LinkedList;

public class Tabelle {
    int gesetzteSpaltenZahl = 0;
    LinkedList<String[]> daten = new LinkedList<>();
    String ausgabe = "";

    // Jetzt definiert, können später geändert werden
    char seitenRand = '|';
    char obenUntenRand = '=';
    char kopfTrennung = '-';

    public Tabelle(String[] kopfZeile) {
        gesetzteSpaltenZahl = kopfZeile.length;
        daten.add(kopfZeile);
    }

    public void addZeile(String[] zeile){
        if(zeile.length != gesetzteSpaltenZahl){
            throw new IllegalArgumentException("Die Anzahl von Spalten wird bei der Initalisierung festgelegt " +
                    "und muss eingehalten werden");
        }else{
            daten.add(zeile);
        }
    }

    private void tabelleErstellen(){
        // 1. Pro Spalte die maximale Länge herausfinden
        int[] breiteProSpalte = new int[gesetzteSpaltenZahl];
        for (int i = 0; i < gesetzteSpaltenZahl; i++) {
            int maxVonI = 0;
            for (int j = 0; j < daten.size(); j++) {
                if (daten.get(j)[i].length() > maxVonI) {
                    maxVonI = daten.get(j)[i].length();
                }
                breiteProSpalte[i] = maxVonI;
            }
        }

        // 2. Rand Oben und unten bestimmen
        int breiteGanzeTabelle = 2 + (gesetzteSpaltenZahl - 1) + 1; // für die Ränder + mitteltrenner + 1 keine Ahnung warum
        for (int i = 0; i < gesetzteSpaltenZahl; i++){
            breiteGanzeTabelle += breiteProSpalte[i] + 2; // eigentliches Wort + 2 padding
        }
        for (int i = 0; i <= breiteGanzeTabelle; i++ ){
            ausgabe += obenUntenRand;
        }
        ausgabe += "\n";

        // 3. Kopfzeile
        printZeile(0, breiteProSpalte);
        ausgabe += seitenRand;

        for (int i = 0; i <= breiteGanzeTabelle-2; i++ ){
            ausgabe += kopfTrennung;
        }
        ausgabe += seitenRand + "\n";

        // 4. alle weiteren Zeilen
        for (int d = 1; d < daten.size(); d++){
            printZeile(d, breiteProSpalte);
        }

        // 5. Unteres Ende
        for (int i = 0; i <= breiteGanzeTabelle; i++ ){
            ausgabe += obenUntenRand;
        }
        ausgabe += "\n";
    }

    private void printZeile(int nummer, int[] breiteProSpalte){
        ausgabe += seitenRand;
        for (int i = 0; i < gesetzteSpaltenZahl; i++){
            int wortlänge = daten.get(nummer)[i].length();
            int anzahlLeerzeichen = breiteProSpalte[i] - wortlänge;
            for (int j = 0; j < (anzahlLeerzeichen/2) + 1; j++){        // +1 padding
                ausgabe += " ";
            }
            ausgabe += daten.get(nummer)[i];
            if (anzahlLeerzeichen%2 == 0){
                for (int j = 0; j <= (anzahlLeerzeichen/2) + 1; j++){
                    ausgabe += " ";
                }
            }else {
                for (int j = 0; j <= (anzahlLeerzeichen/2) + 2; j++){
                    ausgabe += " ";
                }
            }
            ausgabe += seitenRand;
        }
        ausgabe += "\n";
    }

    @Override
    public String toString(){
        tabelleErstellen();
        return ausgabe;
    }
}
