package guru.voidmain.gaank.fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import guru.voidmain.gaank.MainActivity;
import guru.voidmain.gaank.R;
import guru.voidmain.gaank.exception.GankTypeNotFoundException;
import guru.voidmain.gaank.utils.GankType;

/**
 * Created by voidmain on 8/30/15.
 */
public class GankFragment extends Fragment {
    /**
     * 代表当前fragment对应的类型，比如福利，iOS等等
     */
    private static final String ARG_SECTION_TYPE = "section_type";

    public static GankFragment newInstance(int sectionType) {
        GankFragment fragment = new GankFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_TYPE, sectionType);
        fragment.setArguments(args);
        return fragment;
    }

    public GankFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gank, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        if (context instanceof MainActivity) {
            int sectionType = getArguments().getInt(ARG_SECTION_TYPE);
            try {
                ((MainActivity) context).onSectionAttached(GankType.getGankType(getContext(), sectionType));
            } catch (GankTypeNotFoundException e) {
                Toast.makeText(getContext(), R.string.exception_gank_type_not_found, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}
