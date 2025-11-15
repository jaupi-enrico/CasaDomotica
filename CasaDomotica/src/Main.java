public class Main {
    public static void main(String[] args) {

        GestoreLampadine gestore = new GestoreLampadine();

        try {
            gestore.aggiungiLampadina(new Lampadina(1, 50));

            gestore.aggiungiLampadina(new Lampadina(2, 100));

        } catch (LampadinaDuplicataException e) {
            System.out.println(e.getMessage());
        }

        CasaIntelligente casa = new CasaIntelligente(gestore);

        System.out.println("Stato iniziale:");
        System.out.println(casa.riepilogoCasa());

        try {
            gestore.accendi(1);
        } catch (LampadinaNonTrovataException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nDopo aver acceso la lampadina 1:");
        System.out.println(casa.riepilogoCasa());

        casa.accendiStanza("Camera");

        System.out.println("\nDopo aver acceso tutte le lampadine in 'Camera':");
        System.out.println(casa.riepilogoCasa());

        casa.spegniStanza("Soggiorno");

        System.out.println("\nDopo aver spento 'Soggiorno':");
        System.out.println(casa.riepilogoCasa());

        try {
            gestore.accendi(99);
        } catch (LampadinaNonTrovataException e) {
            System.out.println("\nErrore catturato correttamente:");
            System.out.println(e.getMessage());
        }

        System.out.println("\nProgramma terminato.");
    }
}
