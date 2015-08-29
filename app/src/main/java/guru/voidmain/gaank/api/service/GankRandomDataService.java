package guru.voidmain.gaank.api.service;

import guru.voidmain.gaank.model.response.GankCategoryResponse;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * 获取随即分类数据
 * Created by voidmain on 8/30/15.
 */
public interface GankRandomDataService {
    @GET("/api/random/data/{category}/{pageSize}/{pageNumber}")
    GankCategoryResponse getRandomGankData(@Path("category") String category,
                                           @Path("pageSize") int pageSize,
                                           @Path("pageNumber") int pageNumber);
}
