import java.util.Scanner;

public class JeuDeLaVie {

    //  _____                _   _                    __           __       _ _
    // |  ___|__  _ __   ___| |_(_) ___  _ __  ___    \_\_   _ __ /_/  __ _| (_)___  ___ _ __
    // | |_ / _ \| '_ \ / __| __| |/ _ \| '_ \/ __|  / _` | | '__/ _ \/ _` | | / __|/ _ \ '__|
    // |  _| (_) | | | | (__| |_| | (_) | | | \__ \ | (_| | | | |  __/ (_| | | \__ \  __/ |
    // |_|  \___/|_| |_|\___|\__|_|\___/|_| |_|___/  \__,_| |_|  \___|\__,_|_|_|___/\___|_|
    //

    // met toutes les cases du damier à “blanc"
    public static void initBlanc(String[][] damier){
        for (int y = 0; y< damier.length;y++){
            for (int x = 0; x<damier[y].length;x++){
                damier[y][x]=".";
            }
        }
    }

    // permet d’entrer les coordonnées des cellules vivantes
    // et d’initialiser le damier en conséquence.
    public static void initCoordonnées(String[][] damier){
        Scanner input = new Scanner(System.in);
        boolean finInit = false;
        Integer x = 0;
        Integer y = 0;
        initBlanc(damier);

        while (!finInit){
            System.out.println("Saisir la coordonnée de x (entre 0 et "+(damier.length-1) +")");
            x = saisirXY(damier);
            System.out.println("Saisir la coordonnée de y (entre 0 et "+(damier.length-1) +")");
            y = saisirXY(damier);
            damier[x][y] = "o";
            stop();
        }
    }

    // sort le contenu de damier à la manière d’un affichage écran
    public static void sortirDamier(String[][] damier) {
        System.out.println("+---------------+");
        for (int y = 0; y< damier.length;y++){
            System.out.print("|");
            for (int x = 0; x<damier.length;x++){
                System.out.print(damier[x][y]);
            }
        }
        System.out.println("+---------------+");
    }

    // retourne le nombre de voisins vivants de la cellule damier[x][y]
    public static Integer nombreVoisin(String[][] damier, Integer x, Integer y){
        Integer nbvoisin = 0;
            //coté haut à gauche
        if (y==0 && x==0){
            if (damier[y][x+1].equals("o")){
                nbvoisin++;
            }
            if (damier[y+1][x].equals("o")){
                nbvoisin++;
            }
            if (damier[y+1][x+1].equals("o")){
                nbvoisin++;
            }
            //coté haut à droite
        } else if (y==0 && x==damier.length){
            if (damier[y][x-1].equals("o")){
                nbvoisin++;
            }
            if (damier[y+1][x].equals("o")){
                nbvoisin++;
            }
            if (damier[y+1][x-1].equals("o")){
                nbvoisin++;
            }
            //coté bas à gauche
        } else if (y==damier.length && x==0){
            if (damier[y-1][x].equals("o")){
                nbvoisin++;
            }
            if (damier[y][x+1].equals("o")){
                nbvoisin++;
            }
            if (damier[y-1][x+1].equals("o")){
                nbvoisin++;
            }
            //coté bas à droite
        } else if (y==damier.length && x== damier.length){
            if (damier[y-1][x].equals("o")){
                nbvoisin++;
            }
            if (damier[y][x-1].equals("o")){
                nbvoisin++;
            }
            if (damier[y-1][x-1].equals("o")){
                nbvoisin++;
            }
            //barre de haut
        } else if (y==damier.length && x== damier.length){
            if (damier[y-1][x].equals("o")){
                nbvoisin++;
            }
            if (damier[y][x-1].equals("o")){
                nbvoisin++;
            }
            if (damier[y-1][x-1].equals("o")){
                nbvoisin++;
            }
        }
        return nbvoisin;
    }

    // recopie damier1 dans damier2, case par case
    public static void transfer(String[][] damier1, String[][] damier2){
        for (int y = 0; y< damier2.length;y++){
            for (int x=0; x<damier2[y].length;x++){
                damier2[y][x]=damier1[y][x];
            }
        }
    }

    // calcule, dans damier, la génération suivante du jeu de la vie.
    // Cette fonction peut nécessiter l’utilisation d’un damier temporaire.
    public static void générationSuivante(String[][] damier){
       String[][] temp = new String[10][10];
       transfer(damier, temp);
       for (int y = 0; y==temp.length;y++){
           for(int x = 0; x<temp.length;x++){
               if (temp[y][x].equals("o")){
                   if (nombreVoisin(temp, x, y) == 2 || nombreVoisin(temp,x,y){
                       damier[y][x]="o";
                   } else{
                       damier[y][x]=".";
                   }
               }else {

               }
           }
       }
    }


    //   _____                _   _                          _            _             _
    // |  ___|__  _ __   ___| |_(_) ___  _ __    _ __  _ __(_)_ __   ___(_)_ __   __ _| | ___
    // | |_ / _ \| '_ \ / __| __| |/ _ \| '_ \  | '_ \| '__| | '_ \ / __| | '_ \ / _` | |/ _ \
    // |  _| (_) | | | | (__| |_| | (_) | | | | | |_) | |  | | | | | (__| | |_) | (_| | |  __/
    // |_|  \___/|_| |_|\___|\__|_|\___/|_| |_| | .__/|_|  |_|_| |_|\___|_| .__/ \__,_|_|\___|
    //                                          |_|                       |_|

    public static void main(String[] args) throws InterruptedException {
        String[][] monDamier = new String[10][10];
        afficheTitre();
        choixInit(monDamier);
        System.out.println("Génération 0");
        sortirDamier(monDamier);
        générationN(monDamier, saisirGénération());
    }


    //  _____                _   _                                         _   __                      _        _
    // |  ___|__  _ __   ___| |_(_) ___  _ __  ___   ___ _   _ _ __  _ __ | | /_/ _ __ ___   ___ _ __ | |_ __ _(_)_ __ ___  ___
    // | |_ / _ \| '_ \ / __| __| |/ _ \| '_ \/ __| / __| | | | '_ \| '_ \| |/ _ \ '_ ` _ \ / _ \ '_ \| __/ _` | | '__/ _ \/ __|
    // |  _| (_) | | | | (__| |_| | (_) | | | \__ \ \__ \ |_| | |_) | |_) | |  __/ | | | | |  __/ | | | || (_| | | | |  __/\__ \
    // |_|  \___/|_| |_|\___|\__|_|\___/|_| |_|___/ |___/\__,_| .__/| .__/|_|\___|_| |_| |_|\___|_| |_|\__\__,_|_|_|  \___||___/
    //                                                        |_|   |_|


    // Fonction pour afficher le Titre du programme
    private static void afficheTitre() {
        // http://patorjk.com/software/taag/

        System.out.println("  _             _                  _        _               _      \n" +
                " | |    ___    (_) ___ _   _    __| | ___  | | __ _  __   _(_) ___ \n" +
                " | |   / _ \\   | |/ _ \\ | | |  / _` |/ _ \\ | |/ _` | \\ \\ / / |/ _ \\\n" +
                " | |__|  __/   | |  __/ |_| | | (_| |  __/ | | (_| |  \\ V /| |  __/\n" +
                " |_____\\___|  _/ |\\___|\\__,_|  \\__,_|\\___| |_|\\__,_|   \\_/ |_|\\___|\n" +
                "             |__/                                                 ");
    }

    // fonction pour demander le type d'initialisation
    public static Integer saisirChoix() {
        Scanner input = new Scanner(System.in);
        Integer i;
        try {
            System.out.println("Comment voulez vous initialiser le damier ?");
            System.out.println("1 - Manuellement.");
            System.out.println("2 - Avec un planeur.");
            System.out.println("3 - Avec une grille \"Test\".");
            i = input.nextInt();
            if(!(i==1||i==2||i==3)){
                System.out.println("Erreur dans la saisie. Recommencez");
                i = saisirChoix();
            }

        } catch (Exception e) {
            System.out.println("Erreur dans la saisie. Recommencez");
            i = saisirChoix();
        }
        return i;
    }

    //fonction pour initialiser le damier selon le type d'initialisation
    private static void choixInit(String[][] damier) {
        Integer choix;

        choix=saisirChoix();

        if (choix==1){
            initCoordonnées(damier);
        }else if(choix==2){
            initPlaneur(damier);
        }else if(choix==3){
            initTest(damier);
        }else{
            System.out.println("Erreur dans la saisie.");
            choixInit(damier);
        }
    }

    // Fonction pour saisir X ou Y, en fonction du tableau
    public static Integer saisirXY(String[][] damier) {
        Scanner input = new Scanner(System.in);
        Integer i;
        try {
            i = input.nextInt();
            if(i<0 || i>=damier.length){
                System.out.println("Vous devez saisir une valeur entre 0 et "+(damier.length-1)+". Recommencez");
                i = saisirXY(damier);
            }
        } catch (Exception e) {
            System.out.println("Erreur dans la saisie. Recommencez.");
            i = saisirXY(damier);
        }
        return i;
    }

    // fonction pour arreter l'initialisatoin manuelle
    public static Boolean stop(){
        Scanner input = new Scanner(System.in);
        String fin;
        Boolean finInit;
        System.out.println("Voulez-vous continuer ? (o/n)");
        fin = input.next();
        if(fin.equals("o")){
            finInit=false;
        }else if (fin.equals("n")){
            finInit=true;
        }else {
            System.out.println("Erreur dans la réponse");
            finInit = stop();
        }
        return finInit;
    }

    // fonction pour initialiser avec une grille de test
    public static void initTest(String[][] damier){
        String[][] test = {
                {".",".",".",".",".",".",".",".",".","."},
                {".",".",".",".","o","o",".",".",".","."},
                {".",".",".","o","o",".","o",".",".","."},
                {".",".",".","o",".",".",".","o",".","."},
                {".",".",".","o","o",".","o","o",".","."},
                {".",".","o","o","o","o","o",".",".","."},
                {".",".","o","o",".",".",".",".",".","."},
                {".","o","o",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".",".",".","."}
        };
        for(int y = 0; y<damier.length; y++){
            for(int x = 0; x<damier.length; x++){
                damier[y][x]=test[y][x];
            }
        }
    }

    // fonction pour initialiser avec un planeur
    public static void initPlaneur(String[][] damier){
        String[][] test = {
                {".","o",".",".",".",".",".",".",".","."},
                {".",".","o",".",".",".",".",".",".","."},
                {"o","o","o",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".",".",".","."},
                {".",".",".",".",".",".",".",".",".","."}
        };
        for(int y = 0; y<damier.length; y++){
            for(int x = 0; x<damier.length; x++){
                damier[y][x]=test[y][x];
            }
        }
    }

    // fonction pour demander le nombre de génération a afficher
    public static Integer saisirGénération() {
        Scanner input = new Scanner(System.in);
        Integer i;
        try {
            System.out.println("Combien voulez-vous de génération ? (minimum 1)");
            i = input.nextInt();
            if(i<1){
                System.out.println("Erreur dans la saisie. Recommencez");
                i = saisirGénération();
            }

        } catch (Exception e) {
            System.out.println("Erreur dans la saisie. Recommencez");
            i = saisirGénération();
        }
        return i;
    }

    // fonction qui fait vivre N génération
    public static void générationN(String[][] damier, int n)  throws InterruptedException{
        for(int i =1; i<=n; i++){
            System.out.println("Génération "+i);
            générationSuivante(damier);
            sortirDamier(damier);
            //Pause for 1 seconds
            Thread.sleep(500);
        }
    }

    // foncion qui retourne un caractère spécifique selon vivant ou mort
    public static String forme(String[][] damier, Integer x, Integer y){
        //String forme = ".";
        String forme = ""+'\u26AA';
        if(damier[y][x].equals("o")){
            forme=""+'\u26AB';
        }
        return forme;
    }
}
