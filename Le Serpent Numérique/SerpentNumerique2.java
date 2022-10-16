public class SerpentNumerique2 {
    public static void main(String[] argv) {
        int[] pioche = creerPioche();

        for (int i = 0; i < 10; i++) {
            System.out.println(piocher(pioche));
        }
    }

    public static int[] creerPioche() {
        int[] pioche = new int[40];
        for (int i = 0; i < 30; i++ ) {
            pioche[i] = i + 1;
        }
        for  (int i = 30; i < 39; i++) {
            pioche[i] = i - 19; // -19 pour ajouter des valeurs à partir de 11
        }
        pioche[pioche.length - 1] = -1; //

        return pioche;
    }

    public static int piocher(int[] pioche) {
        int indice = (int) (Math.random() * 40); // Il y a 40 écails
        while (pioche[indice] == -2) {
            indice = (int) (Math.random() * 40);
        }

        int tirage = pioche[indice];
        pioche[indice] = -2;

        return tirage;
    }
}
