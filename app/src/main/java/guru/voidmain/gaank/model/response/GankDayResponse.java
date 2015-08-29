package guru.voidmain.gaank.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import guru.voidmain.gaank.model.GankItem;

/**
 * GankDayResponse - model for api response
 * Created by voidmain on 8/29/15.
 */
public class GankDayResponse extends GankBaseResponse {
    public Result results;
    public List<String> category;

    public class Result {
        @SerializedName("Android") public
        List<GankItem> androidList;

        @SerializedName("休息视频")
        public List<GankItem> videoList;

        @SerializedName("iOS")
        public List<GankItem> iOSList;

        @SerializedName("福利")
        public List<GankItem> benefitsList;

        @SerializedName("拓展资源")
        public List<GankItem> extraResourceList;

        @SerializedName("瞎推荐")
        public List<GankItem> casualRecommendList;
    }
}
