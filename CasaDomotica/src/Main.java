import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CasaGrafica casa = null;

        try {
            casa = new CasaGrafica("casaImg.png");
        } catch (FileNotFoundException e) {
            System.out.println("Immagine casa non trovata.");
            return;
        }

        Scanner in = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== MENU CASA DOMOTICA =====");
            System.out.println("1) Aggiungi lampadina");
            System.out.println("2) Disegna tutte le lampadine");
            System.out.println("3) Apri menu grafico");
            System.out.println("4) Chiudi menu grafico");
            System.out.println("5) Esci");
            System.out.print("Scelta: ");

            int scelta = in.nextInt();
            switch (scelta) {

                case 1 -> {
                    try {
                        System.out.print("ID lampadina: ");
                        int id = in.nextInt();

                        System.out.print("Nome lampadina: ");
                        String nome = in.next();

                        System.out.print("Stanza: ");
                        String stanza = in.next();

                        System.out.print("Colore (yellow, blue, green, red): ");
                        String colore = in.next().toUpperCase();

                        System.out.print("Posizione X: ");
                        int x = in.nextInt();

                        System.out.print("Posizione Y: ");
                        int y = in.nextInt();

                        Posizione p = new Posizione(x, y, stanza);
                        casa.getGestore().addLampadina(id, p, nome, 50, colore);

                        System.out.println("Lampadina aggiunta con successo.");
                    } catch (LampadinaDuplicataException e) {
                        System.out.println("Esiste già una lampadina con questo ID!");
                    }
                }

                case 2 -> {
                    casa.disegnaLampadine();
                    System.out.println("Lampadine disegnate.");
                }

                case 3 -> {
                    casa.apriMenu();
                    System.out.println("Menu grafico aperto.");
                }

                case 4 -> {
                    casa.chiudiMenu();
                    System.out.println("Menu grafico chiuso.");
                }

                case 5 -> {
                    running = false;
                    System.out.println("Uscita dal programma...");
                    System.out.println("Chiudi la finestra per spegnere");
                }

                default -> {
                    System.out.println("Scelta non valida.");
                }
            }
        }

        in.close();
    }
}