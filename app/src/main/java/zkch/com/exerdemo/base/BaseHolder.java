package zkch.com.exerdemo.base;

import android.view.View;

import butterknife.ButterKnife;

public class BaseHolder {

    public BaseHolder(View itemView) {
        ButterKnife.bind(this,itemView);
    }
}
