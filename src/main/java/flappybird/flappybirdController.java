package flappybird;

import javafx.util.Duration;

import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class flappybirdController {
    @FXML private ImageView myBackground;
    @FXML private Button startButton;
    @FXML private Label scoreLabel;
    @FXML private Label lastScore;
    @FXML private ImageView top1;
    @FXML private ImageView bottom1;
    @FXML private ImageView top2;
    @FXML private ImageView bottom2;
    @FXML private ImageView top3;
    @FXML private ImageView bottom3;
    @FXML private ImageView top4;
    @FXML private ImageView bottom4;
    @FXML private ImageView top5;
    @FXML private ImageView bottom5;
    @FXML private ImageView myBird;
    
    private List<Pillars> pillarPairs = new ArrayList<>();

    private Timeline gameLoop;
    
    private Bird bird;

    private ScoreManager scoreManager;

    private final String txt_file = "./src/main/java/flappybird/highscore.txt";

    @FXML
    public void initialize(){
       lastScore.setVisible(false);
       startButton.setVisible(true);
       scoreLabel.setVisible(true);
       this.scoreManager = new ScoreManager();
       this.scoreLabel.setText("Highscore: " + scoreManager.readHighScore(txt_file));
    }


    @FXML
    public void startGame(){
        scoreManager.resetScore();
        scoreLabel.setText("Score: 0");
        lastScore.setVisible(false);
    
        double height = myBackground.getFitHeight();
        this.bird = new Bird(myBird);  
        // Setter opp søyleparene
        Pillars pillarPair1 = new Pillars(top1, bottom1);
        Pillars pillarPair2 = new Pillars(top2, bottom2);
        Pillars pillarPair3 = new Pillars(top3, bottom3);
        Pillars pillarPair4 = new Pillars(top4, bottom4);
        Pillars pillarPair5 = new Pillars(top5, bottom5);
        // Setter sceneWidth for alle søylene
        double sceneWidth = myBackground.getFitWidth();
        pillarPair1.setSceneWidth(sceneWidth);
        pillarPair2.setSceneWidth(sceneWidth);
        pillarPair3.setSceneWidth(sceneWidth);
        pillarPair4.setSceneWidth(sceneWidth);
        pillarPair5.setSceneWidth(sceneWidth);
        pillarPairs.clear();
        pillarPairs.add(pillarPair1);
        pillarPairs.add(pillarPair2);
        pillarPairs.add(pillarPair3);
        pillarPairs.add(pillarPair4);
        pillarPairs.add(pillarPair5);
        

        gameLoop = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            bird.update();
            //sjekke om fuglen kolliderer med bakken eller treffer toppen av skjermen
            if (!bird.checkBottomAndTop(myBackground.getLayoutY(), height)){
                myBird.setLayoutY(myBackground.getFitHeight() / 2);
                gameLoop.stop();
                scoreManager.writeHighScore(txt_file);
                scoreLabel.setText("Highscore: " + scoreManager.getHighScore());
                lastScore.setText("Last score: " + scoreManager.getCurrentScore());
                lastScore.setVisible(true);
                startButton.setVisible(true);
                for (Pillars pillarPair : pillarPairs){
                    pillarPair.reset();
                }
            }
            //beveger søylene og sjekker om fuglen kolliderer med dem
            for (Pillars pillarPair : pillarPairs){
                pillarPair.move();
                if (pillarPair.birdCollides(bird)){
                    myBird.setLayoutY(myBackground.getFitHeight() / 2);
                    gameLoop.stop();
                    scoreManager.writeHighScore(txt_file);
                    scoreLabel.setText("Highscore: " + scoreManager.getHighScore());
                    lastScore.setText("Last score: " + scoreManager.getCurrentScore());
                    lastScore.setVisible(true);
                    startButton.setVisible(true);

                    for (Pillars p : pillarPairs){
                        p.reset();
                    }
                    break;
                }
                //sjekker om søyleparet har passert fuglen fra før eller ikke
                //hvis ikke -> sjekker om vi kan få poeng
                else if (!pillarPair.getHasScored()){
                    if (pillarPair.canScore(bird)){
                        scoreManager.addPoint();
                        scoreLabel.setText("Score: " + scoreManager.getCurrentScore());
                    }
                }
            }
        }));

        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.play();
        startButton.setVisible(false);
        myBird.getParent().requestFocus();
        myBird.getParent().setOnKeyPressed(event -> {
        if (event.getCode().toString().equals("SPACE")) {
            bird.jump();
        }
        });
    }    
}
