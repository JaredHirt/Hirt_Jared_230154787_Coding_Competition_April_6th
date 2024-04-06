import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ColorPicker extends JMenuBar {
    private PaintFrame frame;
    private PaintComponent paintComponent;
    private Color color = Color.black;
    public ColorPicker(PaintFrame frame){
        this.frame = frame;



        JMenuItem newImage = new JMenuItem("New");
        newImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(paintComponent);
                paintComponent = new PaintComponent(frame, ColorPicker.this);

                frame.add(paintComponent, BorderLayout.CENTER);
                frame.revalidate();
            }
        });
        add(newImage);

        JMenuItem openNewImage = new JMenuItem("Open");
        openNewImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(frame);
                frame.remove(paintComponent);
                    try {
                        paintComponent = new PaintComponent(ImageIO.read(fileChooser.getSelectedFile()), frame, ColorPicker.this);
                    } catch (Exception ignored) {
                        paintComponent = new PaintComponent(frame, ColorPicker.this);
                    }
                    frame.add(paintComponent, BorderLayout.CENTER);
                    frame.revalidate();
            }
        });
        add(openNewImage);

        /*

        JMenuItem smallBrushButton = new JMenuItem("Small");
        smallBrushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintComponent.setBrushSize(1);
            }
        });
        add(smallBrushButton);


        JMenuItem mediumBrushButton = new JMenuItem("Medium");
        mediumBrushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintComponent.setBrushSize(3);
            }
        });
        add(mediumBrushButton);


        JMenuItem largeBrushButton = new JMenuItem("Large");
        largeBrushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintComponent.setBrushSize(5);
            }
        });
        add(largeBrushButton);

         */


        JMenuBar sizePicker = new JMenuBar();
        JSlider slider = new JSlider(0, 20, 2);
        sizePicker.add(slider);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                paintComponent.setBrushSize(slider.getValue());
            }
        });

        add(sizePicker);

        JMenuItem customColor = new JMenuItem("Color");
        customColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(frame, "Choose A Color", getBackground());
                if(newColor != null){
                    color = newColor;
                }
            }
        });
        add(customColor);
    }
    public Color getColor(){
        return color;
    }

    public void setPaintComponent(PaintComponent paintComponent) {
        this.paintComponent = paintComponent;
    }
}
