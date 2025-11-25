import graphics.Canvas;
import graphics.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static void loadLamps(String src, CasaGrafica casa) {
        try (BufferedReader br = new BufferedReader(new FileReader(src))) {
            String line;
            while ((line = br.readLine()) != null) {
                Lampadina l = new Lampadina(line);
                casa.addLamp(l);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File con info lampadine non trovato. " + e);
        } catch (IOException e) {
            throw new RuntimeException("Errore nella lettura nel file: " + e);
        } catch (LampadinaDuplicataException e) {
            throw new RuntimeException(e);
        }
    }


    private static ArrayList<Posizione> loadPos() throws IOException {
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
            System.out.println("2) Toggle tutte le lampadine");
            System.out.println("3) Apri menu grafico");
            System.out.println("4) Seleziona lampadina");
            System.out.println("5) Chiudi menu grafico");
            System.out.println("6) Esci");
            System.out.println("7) Carica salvataggio");
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

                        System.out.println("\nOpzioni colore:");
                        System.out.println("1) Lista colori");
                        System.out.println("2) Personalizzato");
                        System.out.print("Scelta: ");
                        scelta = in.nextInt();
                        Color colore = Color.YELLOW;
                        if (scelta == 2) {
                            System.out.print("\nColore personalizzato:");
                            System.out.print("Red (0-255):");
                            int red = in.nextInt();
                            System.out.print("Green (0-255):");
                            int green = in.nextInt();
                            System.out.print("Blue (0-255):");
                            int blue = in.nextInt();
                            colore = Color.getColor(red, green, blue);
                        }
                        else {
                            System.out.println("\nLista colori:");
                            ArrayList<String> colori = Color.getColorList();
                            for (int i = 0; i < colori.size(); i++) {
                                System.out.println((i + 1) + ") " + colori.get(i));
                            }
                            System.out.print("\nInserisci numero colore:");
                            scelta = in.nextInt();
                            try {
                                colore = Color.getColor(colori.get(scelta - 1));
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Opzione non disponibile");
                                continue;
                            }
                        }

                        System.out.print("\nPotenza massima lampadina: ");
                        double potenza = in.nextDouble();

                        casa.addLamp(potenza, posTrovata, nome, 50, colore);
                        if(casa.isMenuOpen()) {
                            casa.disegnaLampadineWithId();
                        }
                        else {
                            casa.disegnaLampadine();
                        }
                        System.out.println("Lampadina aggiunta con successo.");
                    } catch (LampadinaDuplicataException e) {
                        System.out.println("Esiste già una lampadina in questo luogo!");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                case 2 -> {
                    casa.toggleLampadine();
                    System.out.println("Stato lampadine invertito.");
                }

                case 3 -> {
                    casa.apriMenu();
                    casa.togliDisegnoLampadine();
                    casa.disegnaLampadineWithId();
                    System.out.println("Menu grafico aperto.");
                }

                case 4 -> {
                    System.out.print("Inserisci numero lampadina:");
                    long id = in.nextLong();
                    casa.togliDisegnoLampadine();
                    casa.apriMenu();
                    try {
                        casa.selectLamp(id);
                        casa.disegnaLampadina(id);
                    } catch (LampadinaNonTrovataException e) {
                        System.out.println("Lampadina non trovata");
                        continue;
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
                            System.out.println("5) Rimuovi lampadina");
                            System.out.println("6) Esci");
                            System.out.print("Scelta: ");

                            scelta = in.nextInt();
                            try {
                                switch (scelta) {
                                    case 1:
                                        System.out.println("\nOpzioni colore:");
                                        System.out.println("1) Lista colori");
                                        System.out.println("2) Personalizzato");
                                        System.out.print("Scelta: ");
                                        scelta = in.nextInt();
                                        Color colore = Color.YELLOW;
                                        if (scelta == 2) {
                                            System.out.print("\nColore personalizzato:");
                                            System.out.print("Red (0-255):");
                                            int red = in.nextInt();
                                            System.out.print("Green (0-255):");
                                            int green = in.nextInt();
                                            System.out.print("Blue (0-255):");
                                            int blue = in.nextInt();
                                            colore = Color.getColor(red, green, blue);
                                        }
                                        else {
                                            System.out.println("\nLista colori:");
                                            ArrayList<String> colori = Color.getColorList();
                                            for (int i = 0; i < colori.size(); i++) {
                                                System.out.println((i + 1) + ") " + colori.get(i));
                                            }
                                            System.out.print("\nInserisci numero colore:");
                                            scelta = in.nextInt();
                                            try {
                                                colore = Color.getColor(colori.get(scelta - 1));
                                            } catch (ArrayIndexOutOfBoundsException e) {
                                                System.out.println("Opzione non disponibile");
                                                continue;
                                            }
                                        }
                                        casa.getGestore().cambiaColore(id, colore);
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
                                        double intensita = in.nextDouble();
                                        casa.getGestore().cambiaIntensita(id, intensita);
                                        System.out.println("Intensità aggiornata!");
                                        break;
                                    case 5:
                                        casa.rimuoviLampadina(id);
                                        System.out.println("Lampadina rimossa");
                                        break;
                                    case 6:
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
                    try {
                        casa.togliDisegnoLampadina(id);
                    } catch (LampadinaNonTrovataException e) {
                        System.out.println("Lampadina non trovata");
                    }
                    if(casa.isMenuOpen()) {
                        casa.disegnaLampadineWithId();
                    }
                    else {
                        casa.disegnaLampadine();
                    }
                }

                case 5 -> {
                    casa.chiudiMenu();
                    casa.togliDisegnoLampadineWithId();
                    casa.disegnaLampadine();
                    System.out.println("Menu grafico chiuso.");
                }

                case 6 -> {
                    System.out.println("\nVuoi salvare lo stato della casa:");
                    System.out.println("1) Salva su file");
                    System.out.println("2) Non salvare");
                    scelta = in.nextInt();

                    if (scelta == 1) {
                        System.out.println("Salvataggio in corso...");
                        ArrayList<Lampadina> lamp = casa.getGestore().getGestore().getTutte();
                        try (BufferedWriter bw = new BufferedWriter(new FileWriter("lamp.txt", false))) {
                            for (Lampadina l : lamp) {
                                String s = l.toString();
                                System.out.println(s);
                                bw.write(s);
                                bw.newLine();
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    running = false;
                    System.out.println("Uscita dal programma...");
                    Canvas.getInstance().close();
                }

                case 7 -> {
                    loadLamps("lamp.txt", casa);
                    casa.disegnaLampadine();
                }

                default -> {
                    System.out.println("Scelta non valida.");
                }
            }
        }

        in.close();
    }
}