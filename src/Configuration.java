//mshaikh52 student number 250959996


public class Configuration {

    //declare private variables
    private String gameConfiguration;
    private int gameScore;

    //declare the constructor
    public Configuration(String config, int score){
        gameConfiguration=config;
        gameScore=score;
    }

    //returns the string configuraion of the game
    public String getStringConfiguration(){
            return gameConfiguration;
    }

    //returns the game score
    public int getScore(){
        return gameScore;
    }

}
