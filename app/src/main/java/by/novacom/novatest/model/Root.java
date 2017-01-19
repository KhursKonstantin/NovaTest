package by.novacom.novatest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by al-ev on 19.01.2017.
 */

public class Root {

    @SerializedName("data")
    @Expose
    private List<Datas> data = new ArrayList<Datas>();

    /**
     * @return The data
     */
    public List<Datas> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<Datas> data) {
        this.data = data;
    }

}