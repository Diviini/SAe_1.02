import iut.algo.Clavier;

public class SerpentNumerique1 {
    public static void main(String[] argv) {
        char mode;
        int[] score = new int[20];

        System.out.print("Choisissez un mode de jeu \nA = Avance\nN = Normal\nQ = Quitter\n: ");
        mode = Clavier.lire_char();

        while (true) {
            if (mode == 'N' || mode == 'n') {
                // Mode normal
                score = creerGrille();
                break;
            } else if (mode == 'A' || mode == 'a') {
                // Mode avancé
                score = creerGrille();
                score[5] = 3;
                score[11] = 20;
                score[16] = 50;
                break;

            } else if (mode == 'Q' || mode == 'q') {
                // Quitter le jeu
                System.out.println("Au revoir ;) ");
                break;
            } else {
                System.out.print("Choisissez un mode de jeu \nA = Avancé\nN = Normal\nQ = Quitter\n ");
                mode = Clavier.lire_char();
            }
        }
    }

    public static int[] creerGrille() {
        return new int[] {0, 1, 3, 5, 7, 9, 11, 15, 20, 25, 30, 35, 40, 50, 60, 70, 85, 100, 150, 300};
    }
}
