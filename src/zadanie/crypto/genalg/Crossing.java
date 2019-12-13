package zadanie.crypto.genalg;

import sun.awt.X11.XKeyboardFocusManagerPeer;

import java.util.Random;

public class Crossing {

    private double rate = 0.85;
    private Random random;
    private int KeySize;

    public void crossing(Integer[] individual1, Integer[] individual2) {
        this.KeySize = individual1.length;
        random = new Random();
        double isCrossing;
        int index = random.nextInt(KeySize);
        while ( index == 0 || index == (KeySize - 1) ) {
            index = random.nextInt(KeySize);
        }

        isCrossing = random.nextDouble();
        if (isCrossing < rate) {
            cross(individual1, individual2, index);
        }

    }

    private void cross(Integer[] individual1, Integer[] individual2, int index) {
        Integer[] temp = new Integer[KeySize - index + 1];

        for (int i = index; i < KeySize; i++) {

        }

    }


}
