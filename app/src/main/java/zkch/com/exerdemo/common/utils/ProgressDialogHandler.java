package zkch.com.exerdemo.common.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;

import cn.pedant.SweetAlert.SweetAlertDialog;
import zkch.com.exerdemo.R;

public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 0;

    private SweetAlertDialog mDialog;

    private Context context;

    private boolean cancelable;  //撤销
    private OnProgressCanCealableListener mListener;

    public ProgressDialogHandler(Context context) {
        this(context, false, null);
    }


    public ProgressDialogHandler(Context context, boolean cancelable, OnProgressCanCealableListener mListener) {
        super();
        this.context = context;
        this.cancelable = cancelable;
        this.mListener = mListener;

        initProgressDialog();
    }

    private void initProgressDialog() {
        if (mDialog == null) {
            mDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            mDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            mDialog.setTitle(context.getResources().getString(R.string.loading));

            if (cancelable) {
                mDialog.setCancelText(context.getResources().getString(R.string.close));
                mDialog.setCancelClickListener(sweetAlertDialog -> {
                    sweetAlertDialog.cancel();
                    if (mListener != null) {
                        mListener.onCanCelProgress();
                    }
                });
            }
        }
    }

    public void showProgressDialog() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public void dismissProgressDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                showProgressDialog();
                break;

            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }

    public interface OnProgressCanCealableListener {
        void onCanCelProgress();
    }
}
