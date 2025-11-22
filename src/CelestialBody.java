import java.awt.Color;

public class CelestialBody {
    private int x;
    private int y;
    private int velocityX;
    private int velocityY;
    private int size;
    private Color color;
    private boolean isStar;
    
    public CelestialBody(int x, int y, int velocityX, int velocityY, int size, boolean isStar) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.size = size;
        this.isStar = isStar;
        this.color = isStar ? Color.RED : Color.BLACK;
    }
    
    public void updatePosition() {
        this.x += velocityX;
        this.y += velocityY;
    }
    
    public boolean isOutOfBounds(int maxX, int maxY) {
        return x < -size || x > maxX + size || y < -size || y > maxY + size;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getVelocityX() {
        return velocityX;
    }
    
    public int getVelocityY() {
        return velocityY;
    }
    
    public int getSize() {
        return size;
    }
    
    public Color getColor() {
        return color;
    }
    
    public boolean isStar() {
        return isStar;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }
    
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
}
