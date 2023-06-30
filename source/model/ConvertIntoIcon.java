package source.model;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ConvertIntoIcon {
    public static ImageIcon convert() {
        String cheminImagePNG = "images/icone.png";
        String cheminIconeICO = "images/icone.ico";
        
        try {
            // Chargez l'image PNG
            Image image = ImageIO.read(new File(cheminImagePNG));
            
            // Créez une icône à partir de l'image
            ImageIcon icon = new ImageIcon(image);
            
            // Enregistrez l'icône au format ICO
            ImageIO.write((BufferedImage) image, "ICO", new File(cheminIconeICO));
            
            return icon;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
