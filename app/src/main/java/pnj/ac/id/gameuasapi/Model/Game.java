package pnj.ac.id.gameuasapi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Game {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("game_title")
    @Expose
    private String game_title;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("game_genre")
    @Expose
    private String game_genre;

    @SerializedName("developer")
    @Expose
    private String developer;

    @SerializedName("mode")
    @Expose
    private String mode;

    @SerializedName("release_year")
    @Expose
    private String release_year;

    public Game(Integer id, String game_title, String game_genre, String price, String developer, String release_year){
        this.id = id;
        this.game_title = game_title;
        this.game_genre = game_genre;
        this.price = price;
        this.developer = developer;
        this.release_year = release_year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGame_title() {
        return game_title;
    }

    public void setGame_title(String game_title) {
        this.game_title = game_title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGame_genre() {
        return game_genre;
    }

    public void setGame_genre(String game_genre) {
        this.game_genre = game_genre;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }
}
