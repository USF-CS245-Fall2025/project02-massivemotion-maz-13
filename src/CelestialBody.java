import java.awt.Color;

/**
 * Represents a celestial body (star or comet) in the simulation.
 * Contains position, velocity, size, and color information.
 */
public class CelestialBody {
    private int x;
    private int y;
    private int velocityX;
    private int velocityY;
    private int size;
    private Color color;
    private boolean isStar;
    
    /**
     * Constructs a new CelestialBody with the specified parameters.
     * @param x the x-coordinate of the celestial body
     * @param y the y-coordinate of the celestial body
     * @param velocityX the x-velocity of the celestial body
     * @param velocityY the y-velocity of the celestial body
     * @param size the radius of the celestial body
     * @param isStar true if this is a star, false if it's a comet
     */
    public CelestialBody(int x, int y, int velocityX, int velocityY, int size, boolean isStar) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.size = size;
        this.isStar = isStar;
        this.color = isStar ? Color.RED : Color.BLACK;
    }
    
    /**
     * Updates the position of the celestial body based on its velocity.
     */
    public void updatePosition() {
        this.x += velocityX;
        this.y += velocityY;
    }
    
    /**
     * Checks if the celestial body is outside the given boundaries.
     * @param maxX the maximum x-coordinate (window width)
     * @param maxY the maximum y-coordinate (window height)
     * @return true if the body is outside the boundaries, false otherwise
     */
    public boolean isOutOfBounds(int maxX, int maxY) {
        return x < -size || x > maxX + size || y < -size || y > maxY + size;
    }
    
    /**
     * Gets the x-coordinate of the celestial body.
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Gets the y-coordinate of the celestial body.
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }
    
    /**
     * Gets the x-velocity of the celestial body.
     * @return the x-velocity
     */
    public int getVelocityX() {
        return velocityX;
    }
    
    /**
     * Gets the y-velocity of the celestial body.
     * @return the y-velocity
     */
    public int getVelocityY() {
        return velocityY;
    }
    
    /**
     * Gets the size (radius) of the celestial body.
     * @return the size
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Gets the color of the celestial body.
     * @return the color
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Checks if this celestial body is a star.
     * @return true if this is a star, false if it's a comet
     */
    public boolean isStar() {
        return isStar;
    }
    
    /**
     * Sets the x-coordinate of the celestial body.
     * @param x the new x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Sets the y-coordinate of the celestial body.
     * @param y the new y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Sets the x-velocity of the celestial body.
     * @param velocityX the new x-velocity
     */
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }
    
    /**
     * Sets the y-velocity of the celestial body.
     * @param velocityY the new y-velocity
     */
    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
}
