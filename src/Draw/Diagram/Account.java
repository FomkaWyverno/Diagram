package Draw.Diagram;

public class Account {

    private static final int modifyXP = 107;

    public static int getNeedXP(int lvl) { // Геттер сколько нужно опыта до следующего уровня.
        if (lvl == 0) {
            return 1000;
        }
        return 1000+(modifyXP*lvl);
    }

    /*public static int getAllXP(int lvl) { // Геттер всего накопленного опыта.
        if (lvl == 0) {
            return 0;
        }
        int allXP = 0;
        for (int i = 0; i < lvl; i++) {
            allXP += getNeedXP(i);
        }

        return allXP;
    }*/
}
