package zkch.com.exerdemo.cniaow.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.common.ACache;
import zkch.com.exerdemo.common.constant.Constant;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.pathView)
    PathView pathView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        pathView.getPathAnimator().delay(10).duration(5000).listenerStart(() -> Log.v("AppStore", "start")).listenerEnd(() -> {
            Log.v("AppStore", "End");
            jumpToMain();
        }).interpolator(new AccelerateDecelerateInterpolator()).start();
    }

    private void jumpToMain() {
        String isShowGuid = ACache.get(this).getAsString(Constant.IS_SHOW_GUIDE);
        if (isShowGuid == null) {
            startActivity(new Intent(this, GuideActivity.class));
        } else {
            startActivity(new Intent(this, SimpleActivity.class));
        }
        this.finish();
    }

}
