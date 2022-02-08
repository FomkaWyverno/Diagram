package Run;

import Draw.Arrows.DrawArrows;
import Draw.Diagram.Diagram;
import Clipboard.TransferableImage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Diagram diagram = new Diagram(new DrawArrows("1.cfg","Первая ранговая система.",1.0, Color.red),
                new DrawArrows("2.cfg","Вторая ранговая система.",1.0, Color.cyan),
                new DrawArrows("3.cfg","Третья ранговая система.",2.5, Color.green),
                new DrawArrows("3.cfg","Работующая сейчас ранговая система",1.75,new Color(171, 68, 239)));
        /*File file = new File("diagram.jpg");*/
        /*ByteArrayOutputStream image = new ByteArrayOutputStream();
        ImageIO.write(diagram.getDiagram(),"jpg",image);*/

        TransferableImage trans = new TransferableImage(diagram.getDiagram()/*BufferedImage*/); // Кастомный класс имеющий интерфейс Transferable для установки контета в Буфер Обмена
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); // Системный буффер обмена
        clipboard.setContents(trans,null); // Установка контента в Буффер Обмена

    }
}
