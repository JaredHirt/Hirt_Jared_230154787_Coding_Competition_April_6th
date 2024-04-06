import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> startGUI(args)
        );
    }
    public static void startGUI(String[] args){

        PaintFrame frame = new PaintFrame();
        frame.setLayout(new BorderLayout());

        ColorPicker colorPicker = new ColorPicker(frame);
        frame.add(colorPicker, BorderLayout.NORTH);

        PaintComponent component;
        try {
            component = new PaintComponent(ImageIO.read(new File(args[0])), frame, colorPicker);
        }catch(Exception e){
            component = new PaintComponent(frame, colorPicker);
        }
        frame.add(component, BorderLayout.CENTER);


    }
}
