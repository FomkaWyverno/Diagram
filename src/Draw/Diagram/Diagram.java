package Draw.Diagram;

import Draw.Arrows.DrawArrows;
import Draw.Figure.Arrow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Diagram {
    private static final int WIDTH = 1920; // Статические данные
    private static final int HEIGHT = 1080;
    private static final int SIZE_RANKS = 31; // Количество рангов
    private static final int START_POINT_X = 100;
    private static final int START_POINT_Y = 1020;
    private static final int RATE_STEP = 55;

    private final List<DrawArrows> arrows = new ArrayList<>();

    private BufferedImage diagram;


    public Diagram(DrawArrows ... arrows) {
        this.arrows.addAll(Arrays.asList(arrows));
        drawDefaultDiagram();
    }

    public BufferedImage getDiagram() {
        return diagram;
    }

    private void drawDefaultDiagram() {
        this.diagram = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = this.diagram.createGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0,0,WIDTH,HEIGHT);
        graphics.setColor(Color.black);
        graphics.fillRect(START_POINT_X,20,12,1000); // Отрисовка вертикальной и горизотальной линии
        graphics.fillRect(START_POINT_X,START_POINT_Y,1800,12);

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics.fillPolygon(new Arrow(95,5,1.1,0)); // Отрисовка стрелок
        graphics.fillPolygon(new Arrow(1890,1015,1.1,1));

        graphics.setColor(Color.BLUE); //Отрисовка названий стрелок
        graphics.setFont(new Font("Minecraft Rus",Font.PLAIN,20));
        graphics.drawString("Time",START_POINT_X+25,25);
        graphics.drawString("Ranks",1820,1010);

        graphics.setColor(Color.black);
        drawBorderLineRank(graphics); // Отрисовка для каждого ранга


        for (DrawArrows a : arrows) {
            graphics.drawImage(a.getDiagram(),0,0,null);
        }

        drawInfo(graphics);
    }

    private void drawBorderLineRank(Graphics2D g) {
        int point = START_POINT_X+50;
        for (int i=0; i < SIZE_RANKS; i++) {
            g.fillRect(point,START_POINT_Y-20,5,20);
            point += RATE_STEP;
        }
    }

    private void drawInfo(Graphics2D g) {
        int xRect = 125;
        int yRect = 75;
        int xText = 150;
        int yText = 95;
        for (int i = 0; i < arrows.size(); i++) {
            g.setColor(arrows.get(i).getColor());
            g.fillRect(xRect,yRect,25,25);
            g.setColor(Color.MAGENTA);
            g.drawString(" - " + arrows.get(i).getLine(),xText,yText);
            yRect+=40;
            yText+=40;

        }
    }

}
