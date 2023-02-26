import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length == 0){
            throw new IOException("Did not specify a file");
        }
        else if (args.length > 1){
            System.out.println("Warning: more than 1 file not supported at this point!");
        }

        FileReader reader = new FileReader(args[0]);
        Color[][] colors = reader.colors;
        double beginx = colors.length * 0.1 - 0.2;
        beginx += (colors.length % 2 != 0)? 0 : 0.1;
        beginx =  beginx;
        String[][] strings = toString(colors, beginx);

        String s = "# Defines how often the emote is looped (0 means unlimited)\n" +
                "loop: 0\n" +
                "\n" +
                "# Defines whether the emote is played from the entity's head height\n" +
                "head: true\n" +
                "\n" +
                "# Defines the pattern of the emote\n" +
                "#\n" +
                "# Available Options:\n" +
                "#\n" +
                "# particle:particle_id\t\t- the id of the particle\n" +
                "# delay:integer\t\t\t\t- the delay until the particle is played\n" +
                "# repeat:integer\t\t\t- the amount of times the particle is repeated\n" +
                "# amount:integer\t\t\t- the amount of particles displayed\n" +
                "# xoffset:number\t\t\t- the x offset of the spawn location\n" +
                "# yoffset:number\t\t\t- the y offset of the spawn location\n" +
                "# zoffset:number\t\t\t- the z offset of the spawn location\n" +
                "# extra:number\t\t\t\t- the speed or other specific parameter of the particle\n" +
                "# data:particle_data\t\t- the specific data of the particle\n" +
                "pattern:\n\n";

        for (int i = 0; i < strings.length; i++){
            for (int j = 0; j <= strings[i].length; j++){
                if (j == strings[i].length){
                    s += "\n";
                }
                else {
                    if (strings[i][j] != null){
                        s += strings[i][j];
                        s += "\n";
                    }
                }
            }
        }
        File out = new File(args[0].substring(0, args[0].lastIndexOf('.') + 1) + "gex");
        FileWriter writer = new FileWriter(out);
        writer.write(s);
        writer.close();
        System.out.println("Done!");
    }

    private static String[][] toString(Color[][] colors, double beginx){
        String[][] strings = new String[colors.length][colors[0].length];
        for (int i = 0; i < colors.length; i++){
            for (int j = 0; j < colors[0].length; j++){
                Color color = colors[i][j];
                if (color != null){
                    double xoffset = Math.round((beginx - i * 0.2) * 10);
                    xoffset /= 10;
                    double yoffset = Math.round((0.8 + j * 0.2) * 10);
                    yoffset /= 10;

                    strings[i][j] = "- \"particle:redstone delay:0 repeat:1 amount:1" +
                            " xoffset:" + xoffset +
                            " yoffset:" + yoffset +
                            " zoffset:0.0" +
                            " extra:0.0 data:" +
                            color.getRed() + ":" + color.getGreen() + ":" + color.getBlue() +
                            "\"";
                }
            }
        }
        return strings;
    }
}
