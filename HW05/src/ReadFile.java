
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ReadFile {

    Integer[][] rgbArray = new Integer[1000000][3];
    int i = 0;

    public void printPixelARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
        rgbArray[i][0] = red;
        rgbArray[i][1] = green;
        rgbArray[i][2] = blue;
        ++i;
    }

    private void marchThroughImage(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        System.out.println("width, height: " + w + ", " + h);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.println("x,y: " + j + ", " + i);
                int pixel = image.getRGB(j, i);
                printPixelARGB(pixel);
                System.out.println("");
            }
        }
    }

    public ReadFile(String filename) {
        try {
            // get the BufferedImage, using the ImageIO class
            BufferedImage image = ImageIO.read(this.getClass().getResource(filename));
            marchThroughImage(image);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public void write(){
        MyQueue<Integer[]> Mqueue = new MyQueue<Integer[]>();
        Integer[] addElement = new Integer[3];
        for (int i = 0 ; i<100 ; ++i){
            addElement[0] = rgbArray[i][0];
            addElement[1] = rgbArray[i][1];
            addElement[2] = rgbArray[i][2];
            Mqueue.add(addElement);
        }



    }

    /**
     * Thread
     * @param filename
     */
    public void addHundred(String filename){

        Thread hundred = new Thread(this::write);
        hundred.start();

    }
}
