package flappybird;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class Bird extends ImageView{
    private double velocity = 0;
    private final double gravity = 1.2;
    private ImageView bird;
    private final int jumpPower = -33;
 
    public Bird(ImageView myBird){
        this.bird = myBird;
    }

    public void changeVelocity(double jumpPower){
        this.velocity = jumpPower;
    }

    public void update(){
        //adds gravity to the velocity, why I divided by 7 is just to make the game feel smoother
        //I just tried random values and found out what felt good
        velocity += gravity;
        bird.setLayoutY(bird.getLayoutY() + velocity / 7);
    }
    
    public ImageView getImageView(){
        return bird;
    }

    public boolean checkBottomAndTop(double layoutY, double height){
        //checks if the bird is below the bottom of the screen
        if (bird.getLayoutY() > layoutY + height){
            return false;
        }
        //checks if the bird is above the top of the screen
        else if(bird.getLayoutY() < layoutY){
            bird.setLayoutY(-bird.getLayoutY());
            changeVelocity(0);
        }
        return true;
    }
    
    @FXML
    public void jump(){
        changeVelocity(jumpPower);
    }
}
