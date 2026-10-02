package model;

public class PlayerName {

    private String name;
    private PlayingPiece playingPiece;

    public PlayerName(String name, PlayingPiece playingPiece) {
        this.name = name;
        this.playingPiece = playingPiece;
    }

    public String getName() {
        return name;
    }

    public PlayingPiece getPlayingPiece() {
        return playingPiece;
    }

}
