package by.novacom.novatest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by al-ev on 19.01.2017.
 */
public class Original {


    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("mp4")
    @Expose
    private String mp4;

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

    /**
     *
     * @return
     *     The mp4
     */
    public String getMp4() {
        return mp4;
    }

    /**
     *
     * @param mp4
     *     The mp4
     */
    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }
}
