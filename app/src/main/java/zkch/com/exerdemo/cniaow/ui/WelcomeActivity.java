package zkch.com.exerdemo.cniaow.ui;

import android.net.Uri;
import android.widget.Button;

import butterknife.BindView;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.base.BaseFragmentActivity;
import zkch.com.exerdemo.widget.CustomVideoView;

public class WelcomeActivity extends BaseFragmentActivity {

    @BindView(R.id.videoview)
    CustomVideoView Videoview;
    @BindView(R.id.welcBtn)
    Button WelcBtn;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init() {
        setStatusBar();
        Videoview.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.kr36));
        Videoview.start();
        Videoview.setOnCompletionListener(mp -> Videoview.start());

        WelcBtn.setOnClickListener(v -> FinishSelf(SimpleActivity.class));
    }

}
