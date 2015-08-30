package guru.voidmain.gaank.api.service;

import guru.voidmain.gaank.model.response.GankCategoryResponse;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * 获取分类数据
 * Created by voidmain on 8/29/15.
 */
public interface GankDataService {
    @GET("/api/data/{category}/{pageSize}/{pageNumber}")
    Observable<GankCategoryResponse> getGankData(@Path("category") String category,
                                                 @Path("pageSize") int pageSize,
                                                 @Path("pageNumber") int pageNumber);
}
