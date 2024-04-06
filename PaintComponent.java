import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class PaintComponent extends JComponent implements MouseMotionListener , MouseListener{
    PaintFrame frame;
    ColorPicker colorPicker;
    BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    int paintBrushSize = 1;
    public PaintComponent(PaintFrame frame, ColorPicker colorPicker){
        addMouseMotionListener(this);
        addMouseListener(this);
        this.colorPicker = colorPicker;
        colorPicker.setPaintComponent(this);
        this.frame = frame;
    }
    public PaintComponent(BufferedImage image, PaintFrame frame, ColorPicker colorPicker){
        addMouseMotionListener(this);
        addMouseListener(this);
        this.frame = frame;
        this.colorPicker = colorPicker;
        colorPicker.setPaintComponent(this);
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawAtPosition(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        drawAtPosition(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


    private void drawAtPosition(int x, int y){
        for(int i = 0; i <= paintBrushSize*2; i++){
            for(int j = 0; j <= paintBrushSize*2; j++)
                placePixel(x-paintBrushSize + i, y - paintBrushSize + j);
        }
        frame.repaint();
    }
    private void placePixel(int x, int y){
        try {
            image.setRGB(x, y, colorPicker.getColor().getRGB());
        }
        catch(Exception ignored){}

    }
    public void setBrushSize(int n){
        paintBrushSize = n;
    }


}
