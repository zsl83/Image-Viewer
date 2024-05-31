import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;
import java.awt.image.RescaleOp;

public class ImageViewerGUI extends JFrame implements ActionListener {
    JButton selectFileButton;
    JButton showImageButton;
    JButton resizeButton;
    JButton grayscaleButton;
    JButton brightnessButton;
    JButton closeButton;
    JButton showResizeButton;
    JButton showBrightnessButton;
    JButton backButton;
    JTextField widthTextField;
    JTextField heightTextField;
    JTextField brightnessTextField;

    String filePath = "C:\\Users\\eslam\\Downloads\\picture";
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 900;
    int w = 1200;
    float brightenFactor = 1;
    Font font1 = new Font("Calibri", Font.BOLD, 23);
    Font font2 = new Font("Calibri", Font.BOLD, 18);

    ImageViewerGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 400);
        this.setVisible(true);
        this.setResizable(true);

        mainPanel();
    }

    public void mainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setSize(700, 400);
        JLabel label1 = new JLabel("Image Viewer");
        label1.setFont(font1);
        label1.setBounds(270, 50, 200, 70);
        mainPanel.add(label1);

        JPanel buttonsPanel = new JPanel(new GridLayout(3, 2));
        buttonsPanel.setBounds(150, 150, 400, 150);

        selectFileButton = new JButton("Choose Image");
        showImageButton = new JButton("Show Image");
        brightnessButton = new JButton("Brightness");
        grayscaleButton = new JButton("Gray scale");
        resizeButton = new JButton("Resize");
        closeButton = new JButton("Exit");

        selectFileButton.setFont(font1);
        showImageButton.setFont(font1);
        brightnessButton.setFont(font1);
        grayscaleButton.setFont(font1);
        resizeButton.setFont(font1);
        closeButton.setFont(font1);

        selectFileButton.setFocusable(false);
        showImageButton.setFocusable(false);
        grayscaleButton.setFocusable(false);
        brightnessButton.setFocusable(false);
        resizeButton.setFocusable(false);
        closeButton.setFocusable(false);

        selectFileButton.addActionListener(this);
        showImageButton.addActionListener(this);
        brightnessButton.addActionListener(this);
        grayscaleButton.addActionListener(this);
        resizeButton.addActionListener(this);
        closeButton.addActionListener(this);

        buttonsPanel.add(selectFileButton);
        buttonsPanel.add(showImageButton);
        buttonsPanel.add(brightnessButton);
        buttonsPanel.add(grayscaleButton);
        buttonsPanel.add(resizeButton);
        buttonsPanel.add(closeButton);

        mainPanel.add(buttonsPanel);
        this.add(mainPanel);
    }

    public void resizePanel() {
        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(null);
        resizePanel.setSize(700, 400);

        JLabel widthLabel = new JLabel("Width:");
        widthLabel.setBounds(100, 115, 150, 20);
        widthLabel.setFont(font1);
        resizePanel.add(widthLabel);

        JLabel heightLabel = new JLabel("Height:");
        heightLabel.setBounds(100, 195, 150, 20);
        heightLabel.setFont(font1);
        resizePanel.add(heightLabel);

        JLabel resizeSection = new JLabel("Resize Section");
        resizeSection.setBounds(340, 40, 150, 20);
        resizeSection.setFont(font1);
        resizePanel.add(resizeSection);

        widthTextField = new JTextField();
        heightTextField = new JTextField();

        widthTextField.setBounds(300, 100, 200, 50);
        heightTextField.setBounds(300, 180, 200, 50);


        widthTextField.setFont(font1);
        heightTextField.setFont(font1);

        resizePanel.add(heightTextField);
        resizePanel.add(widthTextField);

        backButton = new JButton("Back");
        backButton.setBounds(70, 280, 200, 50);
        backButton.setFont(font1);
        backButton.addActionListener(this);
        resizePanel.add(backButton);

        showResizeButton = new JButton("Show Result");
        showResizeButton.setBounds(430, 280, 200, 50);
        showResizeButton.setFont(font1);
        showResizeButton.addActionListener(this);
        resizePanel.add(showResizeButton);

        this.add(resizePanel);
    }

    public void brightnessPanel() {
        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setLayout(null);
        brightnessPanel.setSize(700, 400);

        brightnessTextField = new JTextField();
        brightnessTextField.setFont(font1);
        brightnessTextField.setBounds(390, 120, 180, 50);
        brightnessPanel.add(brightnessTextField);

        JLabel label1 = new JLabel("Enter f ");
        label1.setFont(font1);
        label1.setBounds(100, 100, 300, 80);
        brightnessPanel.add(label1);

        JLabel label2 = new JLabel("(must be between 0 and 1) ");
        label2.setFont(font1);
        label2.setBounds(100, 140, 300, 80);
        brightnessPanel.add(label2);

        showBrightnessButton = new JButton("Result");
        showBrightnessButton.setBounds(430, 280, 200, 50);
        showBrightnessButton.setFont(font1);
        showBrightnessButton.addActionListener(this);
        brightnessPanel.add(showBrightnessButton);

        backButton = new JButton("Back");
        backButton.setBounds(70, 280, 200, 50);
        backButton.setFont(font1);
        backButton.addActionListener(this);
        brightnessPanel.add(backButton);

        this.add(brightnessPanel);
    }

    public void chooseFileImage() {
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
    }

    public void showOriginalImage() {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        try {
            BufferedImage image = ImageIO.read(file);
            JLabel label3 = new JLabel(new ImageIcon(image));
            tempPanel.add(label3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    public void grayScaleImage() {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        try {
            BufferedImage originalImage = ImageIO.read(file);
            BufferedImage grayscaleImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
            ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
            op.filter(originalImage, grayscaleImage);
            tempPanel.add(new JLabel(new ImageIcon(grayscaleImage)));
            tempFrame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    public void showResizeImage(int w, int h) {
        try {
            BufferedImage originalImage = ImageIO.read(file);
            Image resizedImage = originalImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
            BufferedImage resizedBufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            resizedBufferedImage.getGraphics().drawImage(resizedImage, 0, 0, null);


            JFrame tempFrame = new JFrame();
            JPanel tempPanel = new JPanel();
            JLabel imageLabel = new JLabel(new ImageIcon(resizedBufferedImage));
            tempPanel.add(imageLabel);

            int imgWidth = resizedBufferedImage.getWidth();
            int imgHeight = resizedBufferedImage.getHeight();
            tempPanel.setSize(imgWidth, imgHeight);
            tempFrame.setSize(imgWidth + 20, imgHeight + 40);
            tempFrame.setTitle("Resized Image");
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempFrame.add(tempPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showBrightnessImage(float f) {
        try {
            BufferedImage originalImage = ImageIO.read(file);
            RescaleOp rescaleOp = new RescaleOp(f, 0, null);
            BufferedImage brighterImage = rescaleOp.filter(originalImage, null);

            JFrame tempFrame = new JFrame();
            JPanel tempPanel = new JPanel();
            JLabel imageLabel = new JLabel(new ImageIcon(brighterImage));
            tempPanel.add(imageLabel);

            int imgWidth = brighterImage.getWidth();
            int imgHeight = brighterImage.getHeight();
            tempPanel.setSize(imgWidth, imgHeight);
            tempFrame.setSize(imgWidth + 20, imgHeight + 40);
            tempFrame.setTitle("Brighter Image");
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempFrame.add(tempPanel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resizeButton) {
            this.getContentPane().removeAll();
            this.resizePanel();
            this.revalidate();
            this.repaint();
        } else if (e.getSource() == showImageButton) {
            this.showOriginalImage();
            this.revalidate();
            this.repaint();
        } else if (e.getSource() == grayscaleButton) {
            this.grayScaleImage();
            this.revalidate();
            this.repaint();
        } else if (e.getSource() == showResizeButton) {
            try {
                int w = Integer.parseInt(widthTextField.getText());
                int h = Integer.parseInt(heightTextField.getText());
                this.showResizeImage(w, h);
            } catch (NumberFormatException ex) {
                // Handle invalid input
            }
        } else if (e.getSource() == brightnessButton) {
            this.getContentPane().removeAll();
            this.brightnessPanel();
            this.revalidate();
            this.repaint();
        } else if (e.getSource() == showBrightnessButton) {
            try {
                float brightnessFactor = Float.parseFloat(brightnessTextField.getText());
                this.showBrightnessImage(brightnessFactor);
            } catch (NumberFormatException ex) {
                // Handle invalid input
            }
        } else if (e.getSource() == selectFileButton) {
            this.chooseFileImage();
            this.revalidate();
            this.repaint();
        } else if (e.getSource() == closeButton) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (e.getSource() == backButton) {
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }
}
