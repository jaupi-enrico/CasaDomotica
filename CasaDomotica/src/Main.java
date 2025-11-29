import graphics.*;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static void loadLamps(CasaGrafica casa) {
        try (BufferedReader br = new BufferedReader(new FileReader("lamp.txt"))) {

            String line;
            int nLamp = 0;

            while ((line = br.readLine()) != null) {
                try {
                    Lampadina l = new Lampadina(line);
                    casa.addLamp(l);
                    nLamp++;
                } catch (LampadinaDuplicataException e) {
                    System.out.println("Lampadina duplicata: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Errore nella riga: \"" + line + "\" → " + e.getMessage());
                }
            }

            System.out.println("Sono state aggiunte " + nLamp + " lampadine.");

        } catch (FileNotFoundException e) {
            System.out.println("File lamp.txt non trovato: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Errore nella lettura di lamp.txt: " + e.getMessage());
        }
    }



    private static ArrayList<Posizione> loadPos() throws IOException {
        ArrayList<Posizione> pos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("pos.txt"))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] s = riga.split(":");
                if (s.length < 3) {
                    System.out.println("Riga pos.txt malformata (atteso formato 'stanza:x:y'): " + riga);
                    throw new IOException("riga malformata: " + riga);
                }
                try {
                    int x = Integer.parseInt(s[1].trim());
                    int y = Integer.parseInt(s[2].trim());
                    Posizione posizione = new Posizione(x, y, s[0].trim());
                    pos.add(posizione);
                } catch (NumberFormatException nfe) {
                    System.out.println("Coordinata non numerica nella riga: " + riga);
                }
            }
            return pos;
        } catch (FileNotFoundException e) {
            System.out.println("File pos.txt non trovato: " + e.getMessage());
            throw new IOException("File pos.txt non trovato", e);
        }
    }

    private static Color inserisciColore(Scanner in) {
        Color colore = null;

        while (true) {
            try {
                System.out.println("\nOpzioni colore:");
                System.out.println("1) Lista colori");
                System.out.println("2) Personalizzato");
                int scelta = leggiInt(in, "Scelta: ");

                if (scelta == 1) {
                    ArrayList<String> colori = Color.getColorList();
                    System.out.println("\nLista colori:");
                    for (int i = 0; i < colori.size(); i++) {
                        System.out.println((i + 1) + ") " + colori.get(i));
                    }
                    int index = leggiInt(in, "\nInserisci numero colore: ");
                    if (index < 1 || index > colori.size()) {
                        System.out.println("Opzione non disponibile.");
                        continue;
                    }
                    colore = Color.getColor(colori.get(index - 1));
                    break;
                } else if (scelta == 2) {
                    System.out.println("\nColore personalizzato:");
                    int red = leggiInt(in, "Red (0-255): ");
                    int green = leggiInt(in, "Green (0-255): ");
                    int blue = leggiInt(in, "Blue (0-255): ");

                    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
                        System.out.println("Valori RGB devono essere compresi tra 0 e 255. Riprova.");
                        continue;
                    }

                    colore = Color.getColor(red, green, blue);
                    break;
                } else {
                    System.out.println("Scelta non valida.");
                }

            } catch (Exception e) {
                System.out.println("Errore nell'input: " + e);
                in.nextLine();
            }
        }

        return colore;
    }

    public static int leggiInt(Scanner in, String msg) {
        while (true) {
            System.out.print(msg);
            String s = in.nextLine();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Inserisci un numero valido.");
            }
        }
    }

    public static long leggiLong(Scanner in, String msg) {
        while (true) {
            System.out.print(msg);
            String s = in.nextLine();
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException e) {
                System.out.println("Inserisci un numero valido.");
            }
        }
    }

    public static double leggiDouble(Scanner in, String msg) {
        while (true) {
            System.out.print(msg);
            String s = in.nextLine();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Inserisci un valore numerico valido.");
            }
        }
    }

    public static String leggiString(Scanner in, String msg) {
        System.out.print(msg);
        return in.nextLine().trim();
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
            System.out.println("\n***** MENU CASA DOMOTICA *****");
            System.out.println("1) Aggiungi lampadina");
            System.out.println("2) Toggle tutte le lampadine");
            System.out.println("3) Apri menu grafico");
            System.out.println("4) Seleziona lampadina");
            System.out.println("5) Chiudi menu grafico");
            System.out.println("6) Esci");
            System.out.println("7) Carica salvataggio");
            System.out.println("8) API");
            int scelta = leggiInt(in, "Scelta: ");

            switch (scelta) {
                case 1 -> {
                    try {
                        String nome = leggiString(in, "\nNome lampadina: ");
                        ArrayList<Posizione> pos = loadPos();
                        String stanza = "";
                        Posizione posTrovata = null;
                        boolean trovato = false;
                        do {
                            System.out.println("\n***** ELENCO STANZE *****");
                            for (Posizione p : pos) {
                                System.out.println(p.getStanza());
                            }
                            stanza = leggiString(in, "\nInserisci nome stanza: ");
                            for (Posizione p : pos) {
                                if (p.getStanza().trim().equalsIgnoreCase(stanza.trim())) {
                                    trovato = true;
                                    posTrovata = p;
                                    break;
                                }
                            }

                            if(!trovato){
                                System.out.println("Stanza non trovata. Riprova.");
                            }
                        }
                        while (!trovato);

                        Color colore = inserisciColore(in);

                        double potenza = leggiDouble(in, "\nPotenza massima lampadina: ");

                        casa.addLamp(potenza, posTrovata, nome, 50, colore);
                        if(casa.isMenuOpen()) {
                            casa.disegnaLampadineWithId();
                        }
                        else {
                            casa.disegnaLampadine();
                        }
                        System.out.println("Lampadina aggiunta con successo. \n");
                    } catch (LampadinaDuplicataException e) {
                        System.out.println("Esiste già una lampadina in questo luogo!");
                    } catch (IOException e) {
                        System.out.println("Errore durante la lettura: " + e);
                    }
                }

                case 2 -> {
                    casa.toggleLampadine();
                    System.out.println("Stato lampadine invertito. \n");
                }

                case 3 -> {
                    casa.apriMenu();
                    casa.togliDisegnoLampadineWithId();
                    casa.disegnaLampadineWithId();
                    System.out.println("Menu grafico aperto.");
                }

                case 4 -> {
                    long id = leggiLong(in, "Inserisci numero lampadina: ");
                    casa.togliDisegnoLampadineWithId();
                    casa.apriMenu();
                    try {
                        casa.selectLamp(id);
                        casa.disegnaLampadinaWithId(id);
                    } catch (LampadinaNonTrovataException e) {
                        System.out.println("Lampadina non trovata" + e);
                        continue;
                    }
                    System.out.println("\nVuoi modificare la lampadina selezionata?");
                    System.out.println("1) Modifica lampadina");
                    System.out.println("2) API");
                    System.out.println("3) Esci");
                    scelta = leggiInt(in, "Scelta: ");

                    boolean lampRemoved = false;
                    if (scelta == 1) {
                        casa.casaImg.remove();
                        casa.togliDisegnoLampadineWithId();
                        MenuLampadina menu;
                        try {
                            menu = new MenuLampadina(casa.getGestore().getGestore().getLampadina(id), casa.casaImg.getWidth());
                        }
                        catch (LampadinaNonTrovataException e) {
                            continue;
                        }
                        menu.show();
                        while (scelta != 6 && scelta != 5) {
                            System.out.println("\n***** MENU MODIFICA LAMPADINA *****");
                            System.out.println("1) Cambia colore");
                            System.out.println("2) Accendi lampadina");
                            System.out.println("3) Spegni lampadina");
                            System.out.println("4) Cambia intensità");
                            System.out.println("5) Rimuovi lampadina");
                            System.out.println("6) Esci");
                            scelta = leggiInt(in, "Scelta: ");

                            try {
                                switch (scelta) {
                                    case 1:
                                        Color colore = inserisciColore(in);
                                        casa.getGestore().cambiaColore(id, colore);
                                        casa.togliDisegnoLampadineWithId();
                                        menu.cambiaColore(colore);
                                        System.out.println("Colore aggiornato!");
                                        break;
                                    case 2:
                                        casa.getGestore().accediLampadina(id);
                                        casa.togliDisegnoLampadineWithId();
                                        menu.accendi();
                                        System.out.println("Lampadina accesa!");
                                        break;
                                    case 3:
                                        casa.getGestore().spegnilampadina(id);
                                        casa.togliDisegnoLampadineWithId();
                                        menu.spegni();
                                        System.out.println("Lampadina spenta!");
                                        break;
                                    case 4:
                                        double intensita;
                                        do {
                                            intensita = leggiDouble(in, "Nuova intensità (0-100): ");
                                            if (intensita < 0 || intensita > 100) {
                                                System.out.println("Errore: inserisci un valore tra 0 e 100.");
                                            }
                                        } while (intensita < 0 || intensita > 100);

                                        casa.getGestore().cambiaIntensita(id, intensita);
                                        casa.togliDisegnoLampadineWithId();
                                        menu.cambiaIntensita(intensita);
                                        System.out.println("Intensità aggiornata!");
                                        break;
                                    case 5:
                                        casa.rimuoviLampadina(id);
                                        System.out.println("Lampadina rimossa");
                                        lampRemoved = true;
                                        break;
                                    case 6:
                                        System.out.println("Uscita dal menu lampadina.");
                                        break;
                                    default:
                                        System.out.println("Scelta non valida, riprova.");
                                }
                            }
                            catch (LampadinaNonTrovataException e) {
                                System.out.println("Lampadina non trovata: " + e);
                                return;
                            }
                        }
                        menu.remove();
                        casa.casaImg.draw();
                    } else if (scelta == 2) {
                        try {
                            System.out.println(casa.getGestore().getGestore().getLampadina(id).toAPI());
                        }
                        catch (LampadinaNonTrovataException e) {
                            System.out.println("Lampadina non trovata: " + e);
                        }
                    } else {
                        System.out.println("Uscita dal menu.");
                    }
                    if (lampRemoved) {
                        casa.deselectLamps();
                        try {
                            casa.togliDisegnoLampadina(id);
                        } catch (LampadinaNonTrovataException e) {
                            System.out.println("Lampadina non trovata: " + e);
                        }
                        if (casa.isMenuOpen()) {
                            casa.disegnaLampadineWithId();
                        } else {
                            casa.disegnaLampadine();
                        }
                        continue;
                    }
                    casa.deselectLamps();
                    casa.apriMenu();
                    casa.disegnaLampadineWithId();
                }

                case 5 -> {
                    casa.chiudiMenu();
                    casa.togliDisegnoLampadineWithId();
                    casa.disegnaLampadine();
                    System.out.println("Menu grafico chiuso.");
                }

                case 6 -> {
                    System.out.println("\nVuoi salvare lo stato della casa?");
                    System.out.println("1) Salva su file");
                    System.out.println("2) Non salvare");
                    scelta = leggiInt(in, "Scelta: ");

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
                    loadLamps(casa);
                    if (casa.isMenuOpen()) {
                        casa.disegnaLampadineWithId();
                    } else {
                        casa.disegnaLampadine();
                    }
                }

                case 8 -> {
                    System.out.println(casa.getGestore().getGestore().toAPI());
                }

                default -> {
                    System.out.println("Scelta non valida.");
                }
            }
        }

        in.close();
    }
}