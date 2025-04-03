package flappybird;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.scene.image.ImageView;
import java.io.IOException;


public class GameTests {
    private ImageView top1;
    private ImageView bottom1;
    private ImageView imageBird;
    private Bird bird;
    private Pillars pillars;
    private ScoreManager scoreManager;  // if needed

    @BeforeEach
    public void setUp() {
        // Manually create and position the Bird's ImageView
        imageBird = new ImageView();
        // The tests assume Bird starts at Y=164 (as in FXML file)
        imageBird.setLayoutY(164);

        // Manually create and position top/bottom pillars
        top1 = new ImageView();
        top1.setLayoutX(650);   // Tests expect pillars to start at X=650
        
        bottom1 = new ImageView();
        bottom1.setLayoutX(650);
    
        // Create the Bird and Pillars using these ImageView instances
        bird = new Bird(imageBird);
        pillars = new Pillars(top1, bottom1);

        this.scoreManager = new ScoreManager();
    }

    
    // Bird tests
    @Test
    public void testBirdInitialPosition() {
        // We set it up at 164 in setUp()
        assertEquals(164, bird.getImageView().getLayoutY(), 
                     "Bird should start at Y position 164");
    }

    @Test
    public void testBirdMovement() {
        // Simulate a jump or upward movement
        bird.changeVelocity(-10);
        bird.update();
        bird.update();
        // bird should be at 
        assertTrue(161.65 < bird.getImageView().getLayoutY() && bird.getImageView().getLayoutY() < 161.66,
                   "Bird should be at approximately 161.654286... after two updates and velocity change, but was at" 
                   + bird.getImageView().getLayoutY());
    }

    // Pillars tests    
    @Test
    public void testPillarsMove() {
        // layoutX should start at 650
        // Typically, Pillars move by -2 each time, so after 2 moves it should be 646
        pillars.move();
        pillars.move();
        assertEquals(646, top1.getLayoutX(),
                     "Pillars should move left by 4 units total");
        assertEquals(646, bottom1.getLayoutX(),
                     "Pillars should move left by 4 units total");
    }

    @Test
    public void testPillarsReset() {
        pillars.reset();
        // The reset method presumably sets them back to X=650
        assertEquals(650, top1.getLayoutX(),
                     "Pillars should reset to starting X position");
        assertEquals(650, bottom1.getLayoutX(),
                     "Pillars should reset to starting X position");
    }

    
    // ScoreManager tests
    @Test
    public void testWriteAndReadHighScore() throws IOException {
        scoreManager.addPoint();
        scoreManager.addPoint();
        scoreManager.addPoint();
        //highscore should now be 3
        scoreManager.writeHighScore("nothing.txt");
        assertEquals(3, scoreManager.readHighScore("nothing.txt"), "High score should be read correctly after saving");
    }

    @Test
    public void testResetScore() {
        scoreManager.addPoint();
        scoreManager.resetScore();
        assertEquals(0, scoreManager.getCurrentScore(), "Current score should be reset to 0");
    }
}
