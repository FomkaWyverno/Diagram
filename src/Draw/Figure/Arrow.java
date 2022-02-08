package Draw.Figure;

import java.awt.*;

public class Arrow extends Polygon {
    private static final int[] xUp = {360, 720, 540, 540, 180, 180, 0};
    private static final int[] yUp = {0, 360, 360, 720, 720, 360, 360};
    private static final int[] xLeft = {360, 720, 360, 360, 0, 0, 360};
    private static final int[] yLeft = {0, 360, 720, 540, 540, 180, 180};

    public Arrow(int x,int y,double size, int side) {
        super(sizeXPoints(x,size,side), sizeYPoints(y,size,side),Arrow.yUp.length);
    }

    private static int[] sizeXPoints(int xPos,double size,int side){
        int[] x = null;
        switch (side) {
            case 0 -> x = copyArray(Arrow.xUp);
            case 1 -> x = copyArray(Arrow.xLeft);
        }
        try {
            for (int i = 0; i < x.length; i++) {
                int tmp = x[i];
                tmp*=0.03;
                tmp*=size;
                x[i] = tmp+xPos;
            }
        } catch (NullPointerException e) {
            System.err.println("Cause NullPointerException: side maybe [0-3]");
            e.printStackTrace();
        }

        return x;
    }
    private static int[] sizeYPoints(int yPos,double size, int side){
        int[] y = null;
        switch (side) {
            case 0 -> y = copyArray(Arrow.yUp);
            case 1 -> y = copyArray(Arrow.yLeft);
        }
        try {
            for (int i = 0; i < y.length; i++) {
                int tmp = y[i];
                tmp*=0.03;
                tmp*=size;
                y[i] = tmp+yPos;
            }
        } catch (NullPointerException e) {
            System.err.println("Cause NullPointerException: side maybe [0-3]");
            e.printStackTrace();
        }

        return y;
    }

    private static int[] copyArray(final int[] array) {
        int[] a = new int[array.length];
        System.arraycopy(array, 0, a, 0, array.length);
        return a;
    }
}
