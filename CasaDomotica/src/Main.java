import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void loadLamps(String src, CasaGrafica casa) {
        try{
            FileReader fr = new FileReader(src);
            BufferedReader br = new BufferedReader(fr);

            while((br.readLine()) != null){
                String line = br.readLine();
                casa.getGestore().getGestore().getTutte().add(new Lampadina(line));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File con info lampadine non trovato. " + e);
        } catch (IOException e) {
            throw new RuntimeException("Errore nella lettura nel file" + e);
        }
    }

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
            System.out.println("4) Seleziona lampadina");
            System.out.println("5) Chiudi menu grafico");
            System.out.println("6) Esci");
            System.out.print("Scelta: ");

            int scelta = in.nextInt();
            switch (scelta) {

                case 1 -> {
                    try {
                        System.out.print("\nNome lampadina: ");
                        String nome = in.next();

                        ArrayList<Posizione> pos = null;
                        String stanza = "";
                        pos = loadPos();
                        Posizione posTrovata = null;
                        boolean trovato = false;
                        do {
                            System.out.println("\n==Stanze disponibili==");
                            for (Posizione p : pos) {
                                System.out.println(p.getStanza());
                            }
                            System.out.print("\nInserisci nome stanza: ");
                            stanza = in.next();
                            for (Posizione p : pos) {
                                if (p.getStanza().equals(stanza)) {
                                    trovato = true;
                                    posTrovata = p;
                                    break;
                                }
                            }
                        }
                        while (!trovato);

                        System.out.print("\nColore (yellow, blue, green, red): ");
                        String colore = in.next().toUpperCase();

                        System.out.print("\nPotenza massima lampadina: ");
                        double potenza = in.nextDouble();

                        casa.addLamp(potenza, posTrovata, nome, 50, colore);

                        System.out.println("Lampadina aggiunta con successo.");
                    } catch (LampadinaDuplicataException e) {
                        System.out.println("Esiste già una lampadina in questo luogo!");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
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
                    System.out.print("Inserisci numero lampadina:");
                    long id = in.nextLong();
                    casa.apriMenu();
                    try {
                        casa.selectLamp(id);
                    } catch (LampadinaNonTrovataException e) {
                        System.out.println("Lampadina non trovata");
                        return;
                    }

                    System.out.println("\nScegli cosa fare:");
                    System.out.println("1) Modifica lampadina");
                    System.out.println("2) Esci");
                    System.out.print("Scelta:");
                    scelta = in.nextInt();

                    if (scelta == 1) {
                        while (scelta != 5) {
                            System.out.println("\nMenu modifica lampadina:");
                            System.out.println("1) Cambia colore");
                            System.out.println("2) Accendi lampadina");
                            System.out.println("3) Spegni lampadina");
                            System.out.println("4) Cambia intensità");
                            System.out.println("5) Esci");
                            System.out.print("Scelta: ");

                            scelta = in.nextInt();
                            try {
                                switch (scelta) {
                                    case 1:
                                        System.out.print("Nuovo colore: ");
                                        String colore = in.next();

                                        System.out.println("Colore aggiornato!");
                                        break;
                                    case 2:
                                        casa.getGestore().accediLampadina(id);
                                        System.out.println("Lampadina accesa!");
                                        break;
                                    case 3:
                                        casa.getGestore().spegnilampadina(id);
                                        System.out.println("Lampadina spenta!");
                                        break;
                                    case 4:
                                        System.out.print("Nuova intensità (0-100): ");
                                        int intensita = in.nextInt();

                                        System.out.println("Intensità aggiornata!");
                                        break;
                                    case 5:
                                        System.out.println("Uscita dal menu lampadina.");
                                        break;
                                    default:
                                        System.out.println("Scelta non valida, riprova.");
                                }
                            }
                            catch (LampadinaNonTrovataException e) {
                                System.out.println("Errore con la lampadina");
                                return;
                            }
                        }
                    } else {
                        System.out.println("Uscita dal menu.");
                    }
                    casa.deselectLamps();
                }

                case 5 -> {
                    casa.chiudiMenu();
                    System.out.println("Menu grafico chiuso.");
                }

                case 6 -> {
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

    public static ArrayList<Posizione> loadPos() throws IOException {
        ArrayList<Posizione> pos = new ArrayList<>();
        FileReader fr = null;
        try {
            fr = new FileReader("pos.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Errore in lettura delle posizioni");
            throw new IOException();
        }

        BufferedReader br = new BufferedReader(fr);
        Posizione posizione = null;
        String riga;
        while ((riga = br.readLine()) != null) {
            String[] s = riga.split(":");;
            posizione = new Posizione(Integer. parseInt(s[1]), Integer. parseInt(s[2]), s[0]);
            pos.add(posizione);
        }
        return pos;
    }
}