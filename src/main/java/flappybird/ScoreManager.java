package flappybird;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ScoreManager implements scoreInterface{
    private int currentScore = 0;
    private int highScore = 0;

    @Override
    public void writeHighScore(String txtFile) {
        try(FileWriter writer = new FileWriter(txtFile, false)){
                       writer.write(String.valueOf(this.highScore));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public int readHighScore(String txtFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(txtFile))){
                String line = bufferedReader.readLine();
                if (line != null && line.length() > 0){
                    this.highScore = Integer.parseInt(line);
                }        
        } catch(IOException e){
            e.printStackTrace();
        }
        return this.highScore;
    }
    public int getCurrentScore(){
        return this.currentScore;
    }

    public int getHighScore(){
        return this.highScore;
    }

    public void resetScore(){
        this.currentScore = 0;
    }

    public void addPoint(){
        this.currentScore ++;
        if (this.currentScore > this.highScore){
            this.highScore = this.currentScore;
        }
    }
}