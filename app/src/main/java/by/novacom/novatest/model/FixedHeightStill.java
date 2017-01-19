package by.novacom.novatest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by al-ev on 19.01.2017.
 */
public class FixedHeightStill {
    @SerializedName("url")
    @Expose
    private String url;

    /**
     *
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }



}
