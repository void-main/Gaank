package guru.voidmain.gaank.model.response;

import com.google.gson.Gson;

import java.util.List;

import guru.voidmain.gaank.model.GankItem;

/**
 * Created by voidmain on 8/29/15.
 */
public class GankCategoryResponse {
    List<GankItem> results;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
