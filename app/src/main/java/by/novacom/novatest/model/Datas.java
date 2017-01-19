package by.novacom.novatest.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by al-ev on 19.01.2017.
 */

public class Datas {


    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("images")
    @Expose
    private Images images;


    /**
     *
     * @return
     *     The rating
     */
    public String getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     *     The rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }


    /**
     *
     * @return
     *     The images
     */
    public Images getImages() {
        return images;
    }

    /**
     *
     * @param images
     *     The images
     */
    public void setImages(Images images) {
        this.images = images;
    }

}
