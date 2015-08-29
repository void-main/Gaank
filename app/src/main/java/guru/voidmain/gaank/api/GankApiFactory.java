package guru.voidmain.gaank.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import guru.voidmain.gaank.api.service.GankDataService;
import guru.voidmain.gaank.api.service.GankDayDataService;
import guru.voidmain.gaank.api.service.GankRandomDataService;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * 调用Gank Api
 * Created by voidmain on 8/30/15.
 */
public class GankApiFactory {
    protected static GankApiFactory sSharedAdapter = null;

    protected static final String GANK_API_ENDPOINT = "http://gank.avosapps.com";
    protected static final String GANK_DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    protected static final int DEFAULT_PAGE_SIZE = 10;

    // Lazy Initialization (If required then only)
    public static GankApiFactory getInstance() {
        if (sSharedAdapter == null) {
            // Thread Safe. Might be costly operation in some case
            synchronized (GankApiFactory.class) {
                if (sSharedAdapter == null) {
                    sSharedAdapter = new GankApiFactory();
                }
            }
        }
        return sSharedAdapter;
    }

    protected RestAdapter mRestAdapter;

    protected GankApiFactory() {
        Gson gson = new GsonBuilder()
                .setDateFormat(GANK_DATE_FORMAT_PATTERN)
                .serializeNulls()
                .create();

        mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(GANK_API_ENDPOINT)
                .setConverter(new GsonConverter(gson))
                .build();
    }

    // 获取分类数据service
    public GankDataService createDataService() {
        return mRestAdapter.create(GankDataService.class);
    }

    // 获取分类随机数据service
    public GankRandomDataService createRandomDataService() {
        return mRestAdapter.create(GankRandomDataService.class);
    }

    // 获取每日数据service
    public GankDayDataService createDayDataService() {
        return mRestAdapter.create(GankDayDataService.class);
    }
}
