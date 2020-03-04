package edu.lewisu.cs.antonioocasio.ratingapp;

public class GameRating {
    private String gameName, gameType,consoleType, comment;
    private float rating;

    public GameRating(){
        gameName = "";
        consoleType = "\n";
        comment = "\n \n";
        rating = 0;

    }

    public String getGameType() {
        return gameName;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getConsoleType() {
        return consoleType;
    }

    public void setConsoleType(String consoleType) {
        this.consoleType = consoleType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "GameRating:" +
                "\n\tGame Name = " + gameName +
                "\n\tGame Type = " + gameType +
                "\n\tComment = " + comment +
                "\n\tConsoleT ype = " + consoleType +
                "\n\tRating = " + rating;
    }



}
