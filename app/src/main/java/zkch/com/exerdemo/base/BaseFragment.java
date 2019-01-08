package zkch.com.exerdemo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import butterknife.Unbinder;

/**BaseFragment
 */

public abstract class BaseFragment extends Fragment {

   protected View rootView;
    Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(getLayoutResID(), container, false);
        bind = ButterKnife.bind(this, rootView);
        return rootView;
    }

    public abstract int getLayoutResID();

    @Override
    public void onDestroy() {
        super.onDestroy();

        bind.unbind();
    }

}
