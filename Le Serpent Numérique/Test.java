public class Test {
    public static void main(String[] argv) {
        int[] serpent = {1, 2, 8, 9, 14, 30, 17, 26, 17, 20, 19, 29, 15, 6, 23, 5, 14, 21, 4, 24};
        int[] score = {0, 1, 3, 5, 7, 9, 11, 15, 20, 25, 30, 35, 40, 50, 60, 70, 85, 100, 150, 300};
        calculScore(serpent, score);
    }

    public static void calculScore(int[] serpent, int[] score) {
        int nb, nbSequence, scoreTot;

        nbSequence = 0;
        scoreTot = 0;

        for ( int i = 1; i < serpent.length; i++ )
        {
            nb = serpent[i];

            if ( nb >= serpent[i - 1] || nb == -1 )
            {
                nbSequence++;
            }
            else
            {
                scoreTot += score[nbSequence];
                nbSequence = 0;
            }
        }
        scoreTot += score[nbSequence];
        System.out.println(score[nbSequence]);

        System.out.println("Votre score total est de " + scoreTot + "pt");
    }
}
