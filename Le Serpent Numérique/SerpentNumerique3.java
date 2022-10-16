public class SerpentNumerique3 {
    public static void main(String[] argv) {
        final int TAILLE_SERPENT = 20;

        int[] serpent = new int[20];

        for (int i = 0; i < TAILLE_SERPENT; i++) {
            System.out.print("| " + serpent[i] + " ");
        }
    }
}
