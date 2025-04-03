package flappybird;

import javafx.scene.image.ImageView;

public class Pillars{
    private final int speed_px = -2;
    private final double gap = 66.5; //66.5
    private double offsetY;
    private double sceneWidth;
    private ImageView topPillar;
    private ImageView bottomPillar;
    private final double start_layoutX;
    private boolean hasScored = false;


    public Pillars(ImageView topPillar, ImageView bottomPillar){
        this.topPillar = topPillar;
        this.bottomPillar = bottomPillar;
        this.start_layoutX = topPillar.getLayoutX();
        this.offsetY = Math.random() * 100;
        randomOffsetY();
        applyOffsetY();
    }  

    public void randomOffsetY(){
        this.offsetY = Math.random() * 100 * (Math.random() < 0.5 ? -1 : 1);
    }
    public void applyOffsetY(){
        //adds a random offset to the top pillar so we get random gaps
        double pipeHeigth = topPillar.getFitHeight() * topPillar.getScaleY();
        double originalTop = -63;
        topPillar.setLayoutY(originalTop + offsetY);
        bottomPillar.setLayoutY(topPillar.getLayoutY() + pipeHeigth + gap);
    }
    public void setSceneWidth(double sceneWidth){
        this.sceneWidth = sceneWidth;
    }
    public void move(){
        if (topPillar.getLayoutX() + topPillar.getFitWidth() < 0){
            randomOffsetY();
            applyOffsetY();
            topPillar.setLayoutX(sceneWidth + 50);
            bottomPillar.setLayoutX(sceneWidth + 50);
            this.hasScored = false;
        }
        topPillar.setLayoutX(topPillar.getLayoutX() + speed_px);
        bottomPillar.setLayoutX(bottomPillar.getLayoutX() + speed_px);
    }
    //resets pillars to start position
    public void reset(){
        topPillar.setLayoutX(start_layoutX);
        bottomPillar.setLayoutX(start_layoutX);
        hasScored = false;
    }

    public boolean birdCollides(Bird bird){
        ImageView birdView = bird.getImageView();
        //adding a margin of 50 pixels to each side of the pillar, because the imageview have a lot of transparent space
        double pillarX = topPillar.getLayoutX() + 50;
        double pillarWidth = topPillar.getFitWidth() - 100;

        double pillarHeight = topPillar.getFitHeight() * topPillar.getScaleY();
        // makes sure that the LayoutY is correct since i have scaled the pipe by 2.5
        double halfExtraHeight = (topPillar.getFitHeight() * (topPillar.getScaleY() - 1)) / 2.0;
        double topPillarY = topPillar.getLayoutY() - halfExtraHeight;
        double bottomPillarY = bottomPillar.getLayoutY() - halfExtraHeight;

        //adding a margin of 5 pixels to the bird from every side
        //first pushes it 5px to the right and down, then substracting 10 from its width and height
        double birdX = birdView.getLayoutX() + 5;
        double birdY = birdView.getLayoutY() + 5;
        double birdWidth = birdView.getFitWidth() - 10;
        double birdHeight = birdView.getFitHeight() - 10;
        
        //returns true if bird overlaps with x values of pillar
        boolean horizontalOverlap = birdX + birdWidth >= pillarX  && 
                                    birdX <= pillarX + pillarWidth;

        // return true if bird overlaps with either top or bottom pillar
        boolean verticalOverlap = birdY <= topPillarY + pillarHeight - 28 || 
                                  birdY + birdHeight >= bottomPillarY + 10;
                                  
        return horizontalOverlap && verticalOverlap;
    }

    public boolean canScore(Bird bird){
        //checks if the bird has passed the pillar
        if (bird.getLayoutX() > topPillar.getLayoutX() + topPillar.getFitWidth() - 50 ){
            this.hasScored = true;
            return true;
        }
        return false;
    }
    //returns true if the pillar has "scored", meaning the bird has passed it
    public boolean getHasScored(){
        return this.hasScored;
    }
}


