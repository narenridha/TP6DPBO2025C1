import java.awt.*;

public class Player {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private Image Image;
    private int velocityY;

    public Player(int posX, int posY, int width, int height, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.Image = image;

        this.velocityY = -1; // hanya untuk testing
    }

    // Getter
    public int getPosX() {return posX;}
    public int getPosY() {return posY;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public Image getImage() {return Image;}
    public int getVelocityY() {return velocityY;}

    // Setter
    public void setPosX(int posX) {this.posX = posX;}
    public void setPosY(int posY) {this.posY = posY;}
    public void setWidth(int width) {this.width = width;}
    public void setHeight(int height) {this.height = height;}
    public void setImage(Image image) {this.Image = image;}
    public void setVelocityY(int velocityY) {this.velocityY = velocityY;}
}
