import graphics.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static void loadLamps(CasaGrafica casa) {
        try (BufferedReader br = new BufferedReader(new FileReader("lamp.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Lampadina l = new Lampadina(line);
                try {
                    casa.addLamp(l);
                }
                catch (LampadinaDuplicataException e) {
                    System.out.println("Una Lampadina è duplicata: " + e);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File con informazioni delle lampadine non trovato: " + e);
        } catch (IOException e) {
            System.out.println("Errore nella lettura delle lampadine: " + e);
        }
    }


    private static ArrayList<Posizione> loadPos() throws IOException {
        ArrayList<Posizione> pos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("pos.txt"));){
            Posizione posizione = null;
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] s = riga.split(":");;
                posizione = new Posizione(Integer.parseInt(s[1]), Integer.parseInt(s[2]), s[0]);
                pos.add(posizione);
            }
            return pos;
        } catch (FileNotFoundException e) {
            System.out.println("Errore nella lettura delle posizioni: " + e);
            throw new IOException();
        }
    }

    private static Color inserisciColore() {
        Scanner in = new Scanner(System.in);
        Color colore = null;
        boolean inserito = false;

        while(!inserito){
            System.out.println("\nOpzioni colore:");
            System.out.println("1) Lista colori");
            System.out.println("2) Personalizzato");
            System.out.print("Scelta: ");
            int scelta = in.nextInt();
            if (scelta == 2) {
                System.out.println("\nColore personalizzato:");
                System.out.print("Red (0-255):");
                int red = in.nextInt();
                System.out.print("Green (0-255):");
                int green = in.nextInt();
                System.out.print("Blue (0-255):");
                int blue = in.nextInt();
                colore = Color.getColor(red, green, blue);
                inserito = true;
            }
            else {
                System.out.println("\nLista colori:");
                ArrayList<String> colori = Color.getColorList();
                for (int i = 0; i < colori.size(); i++) {
                    System.out.println((i + 1) + ") " + colori.get(i));
                }
                System.out.print("\nInserisci numero colore:");
                scelta = in.nextInt();
                try{
                    colore = Color.getColor(colori.get(scelta - 1));
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Opzione non disponibile");
                    continue;
                }
                inserito = true;
            }
        }
        return colore;
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

                        ArrayList<Posizione> pos = loadPos();
                        String stanza = "";
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
                            if(!trovato){
                                System.out.println("Questa posizione è già occupata! Scegline un'altra: ");
                            }
                        }
                        while (!trovato);

                        Color colore = inserisciColore();

                        System.out.print("\nPotenza massima lampadina: ");
                        double potenza = in.nextDouble();

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
                    casa.togliDisegnoLampadine();
                    casa.disegnaLampadineWithId();
                    System.out.println("Menu grafico aperto.");
                }

                case 4 -> {
                    System.out.print("Inserisci numero lampadina:");
                    long id = in.nextLong();
                    casa.togliDisegnoLampadineWithId();
                    casa.apriMenu();
                    try {
                        casa.selectLamp(id);
                        casa.disegnaLampadinaWithId(id);
                    } catch (LampadinaNonTrovataException e) {
                        System.out.println("Lampadina non trovata" + e);
                        continue;
                    }
                    System.out.println("\nVuoi modificare la lampadina selezionata?:");
                    System.out.println("1) Modifica lampadina");
                    System.out.println("2) Esci");
                    System.out.print("Scelta:");
                    scelta = in.nextInt();
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
                                        Color colore = inserisciColore();
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
                                        System.out.print("Nuova intensità (0-100): ");
                                        double intensita = in.nextDouble();
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
                                System.out.println("Errore con la lampadina");
                                return;
                            }
                        }
                        menu.remove();
                        casa.casaImg.draw();
                    } else {
                        System.out.println("Uscita dal menu.");
                    }
                    if (!lampRemoved) {
                        casa.deselectLamps();
                        try {
                            casa.togliDisegnoLampadina(id);
                        } catch (LampadinaNonTrovataException e) {
                            System.out.println("Lampadina non trovata");
                        }
                        if (casa.isMenuOpen()) {
                            casa.disegnaLampadineWithId();
                        } else {
                            casa.disegnaLampadine();
                        }
                        continue;
                    }
                    casa.apriMenu();
                }

                case 5 -> {
                    casa.chiudiMenu();
                    casa.togliDisegnoLampadineWithId();
                    casa.disegnaLampadine();
                    System.out.println("Menu grafico chiuso.");
                }

                case 6 -> {
                    System.out.println("\nVuoi salvare lo stato della casa?:");
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
                    loadLamps(casa);
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