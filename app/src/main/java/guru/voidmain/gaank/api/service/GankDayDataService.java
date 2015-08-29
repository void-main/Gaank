package guru.voidmain.gaank.api.service;

import guru.voidmain.gaank.model.response.GankCategoryResponse;
import guru.voidmain.gaank.model.response.GankDayResponse;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * 获取每日数据
 * Created by voidmain on 8/30/15.
 */
public interface GankDayDataService {
    @GET("/api/day/{year}/{month}/{day}")
    GankDayResponse getGankDayData(@Path("year") int year,
                                   @Path("month") int month,
                                   @Path("day") int day);
}
