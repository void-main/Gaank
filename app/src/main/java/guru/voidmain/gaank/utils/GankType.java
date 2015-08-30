package guru.voidmain.gaank.utils;

import android.content.Context;

import guru.voidmain.gaank.R;
import guru.voidmain.gaank.exception.GankTypeNotFoundException;

/**
 * Created by voidmain on 8/30/15.
 */
public class GankType {
    private static String[] sGankTypes;

    public static String[] getGankTypes(Context context) {
        if (sGankTypes == null) {
            sGankTypes = context.getResources().getStringArray(R.array.gank_type_array);
        }

        return sGankTypes;
    }

    public static String getGankType(Context context, int position) throws GankTypeNotFoundException {
        String[] gankArr = getGankTypes(context);
        if (position >= 0 && position < gankArr.length) {
            return gankArr[position];
        }

        throw new GankTypeNotFoundException();
    }

    public static String getGankRequestName(Context context, int position) throws GankTypeNotFoundException {
        if (position == 0) {
            return context.getResources().getString(R.string.gank_type_all);
        } else {
            return getGankType(context, position);
        }
    }

}
