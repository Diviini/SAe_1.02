import iut.algo.Clavier;

public class SerpentNumerique5 {

    public static void main(String[] argv) {
        final int TAILLE_SERPENT = 20;

        int[] serpent = new int[TAILLE_SERPENT];
        int nombre, indice;

        dessinerSerpent(serpent);
        while (true) {
            System.out.print("Choisir un nombre : ");
            nombre = Clavier.lire_int();

            System.out.print("Choisir une case : ");
            indice = Clavier.lire_int();

            if (indice > TAILLE_SERPENT) {
                System.out.println("Cette case n'existe pas !");
            } else if (serpent[indice - 1] == 0) {
                serpent[indice - 1] = nombre;
            } else {
                System.out.println("Cette case est déjà utilisée !");
            }

            dessinerSerpent(serpent);
        }
    }

    public static void dessinerSerpent(int[] listeSerpent) {
        // 1ere ligne de cases
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (i + (j * 8) + 1 < 10) {
                    System.out.print("┌-0" + (i + (j * 8) + 1) + "-┐ ");
                } else {
                    System.out.print("┌-" + (i + (j * 8) + 1) + "-┐ ");
                }
            }
            System.out.print("       ");
        }
        System.out.println();
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (listeSerpent[i + (j * 8)] == -1) {
                    System.out.print("| " + "??" + " | ");
                } else if (listeSerpent[i + (j * 8)] < 10) {
                    System.out.print("| " + "0" + listeSerpent[i + (j * 8)] + " | ");
                } else {
                    System.out.print("| " + listeSerpent[i + (j * 8)] + " | ");
                }
            }
            System.out.print("       ");
        }
        System.out.println();
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                System.out.print("└----┘ ");
            }
            System.out.print("       ");
        }

        // 2eme ligne de cases
        System.out.println();
        System.out.print("              ");
        for (int i = 0; i < 5; i++) {
            if (3 + i * 4 + 1 < 10) {
                System.out.print("┌-0" + (3 + i * 4 + 1) + "-┐ ");
            } else {
                System.out.print("┌-" + (3 + i * 4 + 1) + "-┐ ");
            }
            System.out.print("       ");
        }
        System.out.println();
        System.out.print("              ");
        for (int i = 0; i < 5; i++) {
            if (listeSerpent[3 + i * 4] == -1) {
                System.out.print("| " + "??" + " | ");
            } else if (listeSerpent[3 + i * 4] < 10) {
                System.out.print("| " + "0" + listeSerpent[3 + i * 4] + " | ");
            } else {
                System.out.print("| " + listeSerpent[3 + i * 4] + " | ");
            }
            System.out.print("       ");
        }
        System.out.println();
        System.out.print("              ");
        for (int i = 0; i < 5; i++) {
            System.out.print("└----┘ ");
            System.out.print("       ");
        }

        // 3eme ligne de cases
        System.out.println();
        System.out.print("              ");
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 3; i++) {
                if (4 + i + 8 * j + 1 < 10) {
                    System.out.print("┌-0" + (4 + i + 8 * j + 1) + "-┐ ");
                } else {
                    System.out.print("┌-" + (4 + i + 8 * j + 1) + "-┐ ");
                }
            }
            System.out.print("       ");
        }
        System.out.println();
        System.out.print("              ");
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 3; i++) {
                if (listeSerpent[4 + i + 8 * j] == -1) {
                    System.out.print("| " + "??" + " | ");
                } else if (listeSerpent[4 + i + 8 * j] < 10) {
                    System.out.print("| " + "0"+listeSerpent[4 + i + 8 * j] + " | ");
                } else {
                    System.out.print("| " + listeSerpent[4 + i + 8 * j] + " | ");
                }
            }
            System.out.print("       ");
        }
        System.out.println();
        System.out.print("              ");
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 3; i++) {
                System.out.print("└----┘ ");
            }
            System.out.print("       ");
        }
        System.out.println();
    }
}