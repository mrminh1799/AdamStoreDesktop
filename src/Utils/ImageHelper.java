/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Hoang Tue
 */
public class ImageHelper {
       public static void save(File src) {
        File dst = new File("rs", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs(); //Tao thu muc logos neu chua ton tai
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING); //Copy file vao thu muc logos
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ImageIcon read(String fileName) {
        File path = new File("rs", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }

    public static File resizeAndSave(File file, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(file);
            File outputfile = new File("rs", file.getName().split("\\.")[0] + ".png");
            System.out.println(outputfile.getPath());
            if (!outputfile.getParentFile().exists()) {
                outputfile.getParentFile().mkdirs(); //Tao thu muc logos neu chua ton tai
            }
            ImageIO.write(resize(img, 174, 197), "png", outputfile);
            return outputfile;
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    private static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }
}
