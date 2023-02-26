import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

public class FileReader {
    Color[][] colors;

    public FileReader(String path) throws IOException {
        colors = reader(path);
    }

    private Color[][] reader(String path) throws IOException {
        Raster image = ImageIO.read(new File(path)).getData();
        colors = new Color[image.getWidth()][image.getHeight()];
        for (int i = 0; i < image.getWidth(); i++){
            for (int j = image.getHeight() - 1; j >= 0; j--){
                int[] values = image.getPixel(i, j, new int[4]);
                if (values[3] != 0){
                    if (values[3] == 65535){
                        System.out.println(
                                "That was'not supposed to happen bud idk it might work ig (the Math fuckery)");
                        values[0] = (int)(values[0] * (256.0/65536));
                        values[1] = (int)(values[1] * (256.0/65536));
                        values[2] = (int)(values[2] * (256.0/65536));
                    }
                    colors[i][image.getHeight() - 1 - j] = new Color(values[0], values[1], values[2]);
                }
            }
        }
        return colors;
    }
}
