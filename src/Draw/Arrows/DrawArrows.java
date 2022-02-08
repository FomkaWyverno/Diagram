package Draw.Arrows;

import Draw.Diagram.Account;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DrawArrows {
    private final List<Integer> listLVL;
    private BufferedImage diagram;
    private Graphics2D g;
    private String line;
    private Color color;
    private double gainXP;

    private static final int SIZE_RANKS = 31; // Количество рангов
    private static final int START_POINT_X = 100;
    private static final int START_POINT_Y = 1020;
    private static final int RATE_STEP = 55;

    public DrawArrows(String path,String line,double gainXP, Color color) throws IOException {
        this.line = line;
        this.color = color;
        this.gainXP = gainXP;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                path
                        )
                )
        );
        listLVL = new ArrayList<>();
        while (reader.ready()) {
            listLVL.add(Integer.parseInt(reader.readLine().trim()));
        }
        diagram = new BufferedImage(1920,1080,BufferedImage.TYPE_INT_ARGB);
        g = diagram.createGraphics();
        g.setColor(color);
        drawDiagram();

    }

    public String getLine() {
        return line;
    }

    public Color getColor() {
        return color;
    }

    public BufferedImage getDiagram() {

        return diagram;
    }

    private void drawDiagram() {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        drawLineLVLDiagram();
    }

    private void drawLineX8(int x1,int y1, int x2,int y2) {
        for (int i = 0; i < 8; i++) {
            drawLine(x1,y1,x2,y2);
        }
    }

    private void drawLine(int x1,int y1, int x2,int y2) {
        for (int i = 0; i < 4; i++,y1++,y2++) {
            g.drawLine(x1,y1,x2,y2);
        }
    }

    private void drawLineLVLDiagram() {
        int startX = START_POINT_X+12;
        int startY = START_POINT_Y-7;
        int endX = START_POINT_X+RATE_STEP;
        int endY = START_POINT_Y-7;

        try {
            g.drawImage(getRankImage("/resources/rank/1.png"),startX+5,START_POINT_Y+10,null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = listLVL.size()-1; i >-1; i--) {


            int xp = Account.getNeedXP(listLVL.get(i));
            endX+=RATE_STEP;
            endY-= xp/(500*gainXP);
            drawLineX8(startX,startY,endX,endY);
            startX=endX;
            startY=endY;

            String path = "/resources/rank/"+(Math.abs(i-listLVL.size())+1)+".png";
            try {
                g.drawImage(getRankImage(path),startX-32,START_POINT_Y+10,null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private BufferedImage getRankImage(String path) throws IOException {
        BufferedImage image = new BufferedImage(100,96,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(ImageIO.read(getClass().getResource(path)),0,0,60,58,null);

        g.dispose();

        return image;
    }
}
