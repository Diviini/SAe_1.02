import iut.algo.Clavier;

public class SerpentNumerique6 {
    // Fonction principale
    public static void main(String[] argv) {

        /*--------------*/
        /*  CONSTANTES  */
        /*--------------*/

        final int TAILLE_SERPENT = 20;
        final int TAILLE_SCORE = 20;
        final int TAILLE_PIOCHE = 40;

        /*-------------*/
        /*  Variables  */
        /*-------------*/

        char mode;
        int[] score = new int[TAILLE_SCORE];
        int[] pioche = new int[TAILLE_PIOCHE];
        int[] serpentJ1 = new int[TAILLE_SERPENT];
        int[] serpentJ2 = new int[TAILLE_SERPENT];
        boolean modeTest = false;
        boolean multijoueur = false;
        boolean running = true;
        int nbJoueur;
        int cptSerpent = 0;

        /*----------------*/
        /*  Instructions  */
        /*----------------*/

        // Gestion du mode TEST
        if (argv.length != 0 && argv[0].length() == 40) {
            modeTest = true; // On active le mode test
            for (int i = 0; i < TAILLE_SERPENT; i++) {
                String element = argv[0].substring(i * 2, (i + 1) * 2);
                if (element.contains("?")) {
                    serpentJ1[i] = -1;
                } else {
                    serpentJ1[i] = Integer.parseInt(element);
                }
            }
            cptSerpent = 20; // Le serpent est remplie
        } else if ( argv.length != 0 && argv[0].equals("TEST") ){
            modeTest = true; // On active le mode test
            for ( int i = 1; i <  TAILLE_SERPENT+1; i++){
                System.out.print("Entrez la valeur de la case " + i + " ");
                String valeur = Clavier.lireString();
                if (valeur.contains("?")) {
                    serpentJ1[i - 1] = -1;
                } else {
                    serpentJ1[i - 1] = Integer.parseInt(valeur);
                }
            }
            cptSerpent = 20; // Le serpent est remplie
        }

        // Si on n'est pas en mode test, on demande le nombre de joueur
        if (!modeTest) {
            System.out.print("Nombre de joueurs (2 joueurs max) : ");
            nbJoueur = Clavier.lire_int();

            // L'utilisateur ne peux pas mettre moins d'1 joueur ou plus de 2 joueurs
            while (nbJoueur > 2 || nbJoueur < 1) {
                if (nbJoueur > 2) {
                    System.out.println("Le nombre de joueurs est limité à 2 joueurs !");
                } else if (nbJoueur < 1) {
                    System.out.println("Il faut au moins 1 joueur !");
                }
                System.out.print("Nombre de joueurs (2 joueurs max) : ");
                nbJoueur = Clavier.lire_int();
            }

            // On active le multijoueur si il y as plusieurs joueur
            if (nbJoueur == 2) {
                multijoueur = true;
            }
        }

        // Choix du mode de jeu
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
                running = false;
                break;
            } else {
                System.out.print("Choisissez un mode de jeu \nA = Avancé\nN = Normal\nQ = Quitter\n ");
                mode = Clavier.lire_char();
            }
        }
        // Debut du jeu
        if (running) {
            pioche = creerPioche();


            while (cptSerpent < 20) {
                if (!modeTest) {
                    int nombre, indice;

                    nombre = piocher(pioche);
                    dessinerSerpent(serpentJ1);

                    // On vérifie si on pioche le joker
                    if (nombre == -1) {
                        System.out.println("Vous avez pioché le joker !");
                    } else {
                        System.out.println("Vous avez pioché l'écaille " + nombre);
                    }

                    // Choix de la case par l'utilisateur
                    while (true) {
                        System.out.print("Joueur 1, choisissez une case : ");
                        indice = Clavier.lire_int();
                        if (indice > TAILLE_SERPENT || indice < 1) { // On vérifie si la case existe
                            System.out.println("Cette case n'existe pas !");
                        } else if (serpentJ1[indice - 1] != 0) { // On vérifie si la case contient déjà quelque chose
                            System.out.println("Cette case est déjà utilisée !");
                        } else {
                            break;
                        }
                    }

                    // On change le contenu de la case
                    if (serpentJ1[indice - 1] == 0) {
                        serpentJ1[indice - 1] = nombre;
                        cptSerpent++;
                    } else {
                        System.out.println("Cette case est déjà utilisée !");
                    }

                    // Si on est en multijoueur on gère aussi le joueur 2
                    if (multijoueur) {
                        dessinerSerpent(serpentJ2);
                        while (true) {
                            System.out.print("Joueur 2, choisissez une case : ");
                            indice = Clavier.lire_int();
                            if (indice > TAILLE_SERPENT || indice < 1) {
                                System.out.println("Cette case n'existe pas !");
                            }

                            if (serpentJ2[indice - 1] != 0) {
                                System.out.println("Cette case est déjà utilisée !");
                            } else {
                                break;
                            }
                        }

                        if (serpentJ2[indice - 1] == 0) {
                            serpentJ2[indice - 1] = nombre;
                        } else {
                            System.out.println("Cette case est déjà utilisée !");
                        }
                    }
                }
                // Calcul du/des score(s)
                System.out.println("Joueur 1, votre score est de " + calculScore(serpentJ1, score) + " pts !");
                if (multijoueur) {
                    System.out.println("Joueur 2, votre score est de " + calculScore(serpentJ2, score) + " pts !");
                }
            }
            // On ne rentre pas dans la boucle en mode test, on affiche le serpent et calcul donc les scores à ce moment là
            if (modeTest) {
                dessinerSerpent(serpentJ1);
                System.out.println("Joueur 1, votre score est de " + calculScore(serpentJ1, score) + " pts !");
            }
        }
    }

    public static int[] creerGrille() {
        return new int[] {0, 1, 3, 5, 7, 9, 11, 15, 20, 25, 30, 35, 40, 50, 60, 70, 85, 100, 150, 300};
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

    public static int calculScore(int[] serpent, int[] score) {
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

        return scoreTot;
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
