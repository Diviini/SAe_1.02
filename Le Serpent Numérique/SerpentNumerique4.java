import iut.algo.Clavier;

public class SerpentNumerique4
{
    public static void main(String[] argv)
    {
        // Constante
        final int[] score = {0, 1, 3, 5, 7, 9, 11, 15, 20, 25, 30, 35, 40, 50, 60, 70, 85, 100, 150, 300};
        final int NB_SERIE = 20;


        // Variables
        
        int nb, nbPrecedent, nbSequence, scoreTot;

        // Début

        nbSequence = 1;
        nbPrecedent = 0;
        scoreTot = 0;

        for ( int i = 0; i < NB_SERIE; i++ )
        {
            nb = Clavier.lire_int();
            
            if ( nb > nbPrecedent )
            {
                nbSequence++;
            };
            if ( nb < nbPrecedent )
            {
                scoreTot += score[nbSequence];
                nbSequence = 0;
            }
            nbPrecedent = nb;
        }

        System.out.print(" Max serie : " + nbSequence);

    }
}