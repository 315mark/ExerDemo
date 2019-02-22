package zkch.com.exerdemo.cniaow.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zkch.com.exerdemo.R;

public class GuideFragment extends Fragment {

    @BindView(R.id.imgView)
    ImageView mImgView;
    @BindView(R.id.text)
    TextView mText;
    @BindView(R.id.rootView)
    LinearLayout mRootView;

    public static final String IMG_ID = "IMG_ID";
    public static final String COLOR_ID = "COLOR_ID";
    public static final String TEXT_ID = "TEXT_ID";
    private View mView;

    public static GuideFragment newInstance(int imgResId, int bgColorResId, int textResId) {
        Bundle args = new Bundle();
        args.putInt(IMG_ID, imgResId);
        args.putInt(COLOR_ID, bgColorResId);
        args.putInt(TEXT_ID, textResId);
        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_guide, container, false);
        ButterKnife.bind(this, mView);
        initData();
        return mView;
    }

    private void initData() {
        Bundle bundle = getArguments();
        int colorId = bundle.getInt(COLOR_ID);
        int imgId = bundle.getInt(IMG_ID);
        int txtId = bundle.getInt(TEXT_ID);

        mRootView.setBackgroundColor(getResources().getColor(colorId));
        mImgView.setImageResource(imgId);
        mText.setText(txtId);
    }


}
