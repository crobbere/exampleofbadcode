package be.intecbrussel.entity;

/**
 * Write a description of class be.intecbrussel.entity.Bord here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bord {
    public boolean moetNeem;
    public boolean klaar;
    public boolean geactiveerd;
    private int z;
    private int x, y;
    private boolean witAanZet;
    private Stuk[][] dambord;
    private int hor, ver;
    private int q, p;
    private int v, l;

    public Bord() {
        hor = ver = 10;
        dambord = new Stuk[20][20];
        for (int i = 0; i < 11; i++) {
            if (i % 2 == 0) {
                dambord[i][1] = new Wit();
                dambord[i][3] = new Wit();
            } else {
                dambord[i][0] = new Wit();
                dambord[i][2] = new Wit();
            }
        }
        for (int i = 0; i < 11; i++) {
            if (i % 2 == 0) {
                dambord[i][9] = new Paars();
                dambord[i][7] = new Paars();
            } else {
                dambord[i][8] = new Paars();
                dambord[i][6] = new Paars();
            }

        }

    }

    public int getHor() {
        return hor;
    }

    public int getVer() {
        return ver;
    }

    public Stuk getInhoud(int x, int y) {
        return dambord[x][y];
    }

    /**
     * Methode voor het starten van een activerings-
     * proces. De methode kijkt of de betsemming leeg is, en als dit niet zo is,
     * of het geaactiveerd kan worden.
     * een actief blokje is een blokje waar je een handeling mee kan uitvoeren.
     */
    public void activeren(int x, int y) {
        klaar = false;


        Stuk st = getInhoud(x, y);
        if (st == null) {
        } else if (st.activeerbaar()) {
            st.ActiveerHetNu();
            geactiveerd = true;

        }

    }

    /**
     * deze methode leert ons dat we op een bepaald moment moeten pakken.
     * <p>
     * Het zorgt ervoor dat enkel de objecten die een ander stuk kunnen slaan;
     * geselecteerd kunnen worden. po.stro() verwijst naar een methode die
     * het vakje dat moet pakken beschermd van de onbruikbaarheid.
     */

    public void moetPakken() {
        int len = 10;
        for (int m = 0; m < len; m++) {
            for (int n = 0; n < len; n++) {
                for (int j = 0; j < len; j++) {
                    for (int i = 0; i < len; i++) {
                        Stuk st = getInhoud(m, n);
                        Stuk po = getInhoud(i, j);
                        int posX = i + (m - i) * 2;
                        int posY = j + (n - j) * 2;

                        if ((!witAanZet && n == j - 1 && po instanceof Wit) || (!witAanZet && n == j + 1 && po instanceof Wit)) {
                            if ((m == i + 1 && st instanceof Paars) || (m == i - 1 && st instanceof Paars)) {
                                if (posX < len && posX > -1 && posY < len && posY > -1) {
                                    Stuk vrij = getInhoud(posX, posY);
                                    if (vrij == null) {
                                        if (po != null) {
                                            po.stro();
                                            moetNeem = true;
                                        }
                                    }
                                }
                            }
                        } else if ((witAanZet && n == j - 1 && po instanceof Paars) || (witAanZet && n == j + 1 && po instanceof Paars)) {
                            if ((m == i + 1 && st instanceof Wit) || (m == i - 1 && st instanceof Wit)) {
                                if (posX < len && posX > -1 && posY < len && posX > -1) {
                                    Stuk vrij = getInhoud(posX, posY);
                                    if (vrij == null) {
                                        if (po != null) {
                                            po.stro();
                                            moetNeem = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * deze methode wordt gestart om te kunnen bewegen naar een bepaalde locatie.
     * ze is opgedeeld in 2 grote gevallen:
     * -indien er een schijfje geslagen moet worden
     * -indien dit niet het geval is
     */


    public void bewegen(int x, int y) {
        geactiveerd = false;
        if (moetNeem) {

            pakken(x, y);

            moetPakken();
            Stuk st = getInhoud(x, y);

            if (moetNeem) {
                if (st != null) {
                    st.ActiveerHetNu();
                    klaar = false;
                }
            } else {
                witAanZet = !witAanZet;
            }

        } else {
            doeDeZet(x, y);

        }
    }


    /**
     * de naam is vrij evident,
     * belangrijk hier was het disactiveren en de booleans klaar en witaanzet oppereren.
     * klaar is een essentiele boolean voor de controller.
     * wit aan zet betekend dat bij het wisselen van beurt paars aan zet wordt, en dat paarse
     * schijfjes dus kunnen bewegen.
     */
    public void doeDeZet(int x, int y) {
        Stuk st = dambord[x][y];
        Stuk ac = getInhoud(q, p);
        if (y == p + 1 && x == q + 1 || y == p + 1 && x == q - 1) {
            if (st == null && ac instanceof Wit || st == null && ac.isDam) {

                if (ac.isDam) {
                    ac.Disactiveren();
                    dambord[x][y] = ac;
                } else {
                    dambord[x][y] = new Wit();
                }
                dambord[q][p] = null;
                klaar = true;
                geactiveerd = false;
                witAanZet = !ac.isDam || !(ac instanceof Paars);
            }
        } else if (y == p - 1 && x == q + 1 || y == p - 1 && x == q - 1) {
            if (st == null && ac instanceof Paars || st == null && ac.isDam) {
                ac.Disactiveren();
                dambord[x][y] = ac;
                dambord[q][p] = null;
                klaar = true;
                geactiveerd = false;
                witAanZet = ac.isDam && ac instanceof Wit;
            }
        }
    }


    //scanner
    public void scannen() {
        for (int m = 0; m < 10; m++) {
            for (int n = 0; n < 10; n++) {
                Stuk st = getInhoud(m, n);
                if (st == null) {
                } else if (st.geKlikt() && st instanceof Wit) {
                    z = 1;
                    q = m;
                    p = n;
                } else if (st.geKlikt() && st instanceof Paars) {
                    z = 2;
                    q = m;
                    p = n;
                } else if (moetNeem) {
                    st.aanZet = false;
                }
            }
        }

    }

    //beurtwissel
    public void beurtwissel() {
        for (int m = 0; m < 10; m++) {
            for (int n = 0; n < 10; n++) {
                Stuk st = getInhoud(m, n);
                if (st == null) {
                } else if (witAanZet) {

                    if (st instanceof Wit) {

                        st.aanZet = false;
                    } else {
                        st.aanZet = true;
                    }
                } else {
                    System.out.println("raheu");
                    if (st instanceof Wit) {
                        st.aanZet = true;
                    } else {
                        st.aanZet = false;
                    }

                }
            }
        }
        // pakken
    }

    public void pakken(int x, int y) {

        int u = (x + q) / 2;
        int h = (y + p) / 2;
        Stuk ac = getInhoud(q, p);
        Stuk bo = getInhoud((x + q) / 2, (y + p) / 2);
        Stuk ga = getInhoud(x, y);


        if (ac instanceof Wit && bo instanceof Paars || bo instanceof Wit && ac instanceof Paars) {
            if (ga == null) {
                if (x == 2 + q && y == 2 + p || x == 2 + q && y == p - 2 || x == q - 2 && y == 2 + p || x == q - 2 && y == p - 2) {

                    dambord[(x - q) / 2 + q][(y - p) / 2 + p] = null;
                    dambord[x][y] = ac;
                    dambord[q][p] = null;
                    klaar = true;
                    Stuk st = getInhoud(x, y);
                    st.Disactiveren();
                    moetNeem = false;
                }
            }
        }
    }                                       //checkitout

    public void checkItOut() {
        for (int m = 0; m < 10; m++) {
            for (int n = 0; n < 10; n++) {
                Stuk st = getInhoud(m, n);
                if ((n == 9 && st instanceof Wit) || (n == 0 && st instanceof Paars)) {
                    st.isDam = true;
                }
            }
        }
    }

}

