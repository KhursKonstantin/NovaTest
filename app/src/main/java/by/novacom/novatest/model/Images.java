package by.novacom.novatest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by al-ev on 19.01.2017.
 */
public class Images {


        @SerializedName("fixed_height_still")
        @Expose
        private FixedHeightStill fixedHeightStill;

        @SerializedName("original")
        @Expose
        private Original original;

        /**
         *
         * @return
         *     The fixedHeightStill
         */
        public FixedHeightStill getFixedHeightStill() {
                return fixedHeightStill;
        }

        /**
         *
         * @param fixedHeightStill
         *     The fixed_height_still
         */
        public void setFixedHeightStill(FixedHeightStill fixedHeightStill) {
                this.fixedHeightStill = fixedHeightStill;
        }

        /**
         *
         * @return
         *     The original
         */
        public Original getOriginal() {
            return original;
        }

        /**
         *
         * @param original
         *     The original
         */
        public void setOriginal(Original original) {
            this.original = original;
        }


}
