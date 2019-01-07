package zkch.com.exerdemo.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import zkch.com.exerdemo.R;


/**
 * 正在加载的对话框
 *
 * Created by zew on 17/4/24.
 */
public class CustomProgressDialog extends ProgressDialog {
    private Context mContent;
    private String progressMessage;
    // 提示框标题
    private String title;
    // 提示框样式默认圆形
    private int progressDialogStyle = ProgressDialog.STYLE_SPINNER;
    // 提示框标题view
    private TextView titleView = null;
    // 提示框提示信息view
    private TextView messageView = null;
    // 提示框 提示百分比view(%)
    private TextView progressBarPercent;
    // 提示框 提示百分比view(x/100)
    private TextView progressPercentRightMsgView;
    // 提示框水平进度条
    private ProgressBar progressBarHorizontal;
    // 提示框视图
    private View viewLayout;
    // 提示框进度
    private int progress;
    //当前window
    private Window win ;



    public CustomProgressDialog(Context context) {
        super(context);
//        mContent = context;
//        LayoutInflater inflater = LayoutInflater.from(mContent);
//        viewLayout = inflater.inflate(R.layout.common_loading_gif, null);
//        win = ((Activity)context).getWindow();
//        WindowManager.LayoutParams localLayoutParams = win.getAttributes();
//        localLayoutParams.dimAmount = 0.7F;
//        win.setBackgroundDrawableResource(R.color.transparent);
//        ImageView progressView = (ImageView) viewLayout.findViewById(R.id.progressbar);
//        ImageLoadUtils.getInstance().loadImage(viewLayout.getContext(), GlideImageConfig.builder().load(R.drawable.loading_gif).asGif().into(progressView).build());
//        this.setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                // TODO Auto-generated method stub
//                backgroundAlpha(1.0f);
//            }
//        });
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
        mContent = context;
    }

    /**
     * 使用gif
     * @param progressDialog
     */
    public static void ProgressDialogSetting(ProgressDialog progressDialog) {
        ProgressDialogSettingPng(progressDialog);
    }

    /**
     * 使用png图片组
     * @param progressDialog
     */
    public static void ProgressDialogSettingPng(ProgressDialog progressDialog) {
        WindowManager.LayoutParams localLayoutParams = progressDialog.getWindow().getAttributes();
        localLayoutParams.dimAmount = 0F;
        localLayoutParams.alpha = 1f;
        localLayoutParams.height = Dp2Px(progressDialog.getContext(), 150);
        localLayoutParams.width = Dp2Px(progressDialog.getContext(), 150);
        progressDialog.getWindow().setAttributes(localLayoutParams);
//        progressDialog.getWindow().setBackgroundDrawableResource(R.drawable.shape_corner8_white2);
        progressDialog.getWindow().setContentView(R.layout.dialog_loading);
    }

    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    private static int Dp2Px(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * 设置提示框提示信息
     *
     * @param message
     */
    public void setMessage(String message) {
        this.progressMessage = message;
        if (messageView != null) {
            messageView.setText(message);
        }
    }

    /**
     * 设置提示框提示信息
     *
     * @param message
     */
    public void setMessage(int message) {
        this.progressMessage = (String) mContent.getText(message);
        if (messageView != null) {
            messageView.setText(mContent.getText(message));
        }
    }

    /**
     * 设置提示框标题
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
        if (titleView != null) {
            titleView.setText(title);
        }
    }

    /**
     * 设置提示框标题
     *
     * @param title
     */
    public void setTitle(int title) {
        this.title = (String) mContent.getText(title);
        if (titleView != null) {
            titleView.setText(mContent.getText(title));
        }
    }

    /**
     * 设置提示框样式
     *
     * @param progressStyle
     */
    public void setProgressStyle(int progressStyle) {
        this.progressDialogStyle = progressStyle;
    }

//    /**
//     * 设置提示框进度条进度
//     */
//    public void setProgress(int progress) {
//        super.setProgress(progress);
//        this.progress = progress;
//        if (progressBarPercent != null && progressPercentRightMsgView != null) {
//            progressBarPercent.setText(progress + "%");
//            progressPercentRightMsgView.setText(progress + "/100");
//        }
//
//        ProgressBar pp = (ProgressBar) viewLayout
//                .findViewById(R.id.progressbar_horizontal);
//        pp.setProgress(progress);
//    }

    /**
     * 显示提示框
     */
//    public void show() {
//        super.show();
////        viewLayout = getView();
//        setContentView(viewLayout);
//        backgroundAlpha(0.8f);
//    }


//    /**
//     * 获取提示框视图
//     * @return
//     */
//    public View getView() {
//        ImageView spaceshipImage = (ImageView) viewLayout
//                .findViewById(R.id.loading_img);
//        titleView = (TextView) viewLayout.findViewById(R.id.custom_progress_dialog_title);
//        messageView = (TextView) viewLayout.findViewById(R.id.custom_progress_tipTextView);// 提示文字
//
//        if (progressDialogStyle == ProgressDialog.STYLE_HORIZONTAL) {
//            // 设置水平进度条的进度Message
//            progressBarPercent = (TextView) viewLayout
//                    .findViewById(R.id.progressbar_percent);
//            progressBarPercent.setText(progress + "%");
//            progressPercentRightMsgView = (TextView) viewLayout
//                    .findViewById(R.id.progressbar_percent_right);
//            progressPercentRightMsgView.setText(progress + "/100");
//            // 隐藏圆形loading
//            spaceshipImage.setVisibility(View.GONE);
//        } else {
//            // 隐藏水平进度条
//            progressBarHorizontal = (ProgressBar) viewLayout
//                    .findViewById(R.id.progressbar_horizontal);
//            progressBarHorizontal.setVisibility(View.GONE);
//            LinearLayout progressBarMessageLayout = (LinearLayout) viewLayout
//                    .findViewById(R.id.progressbar_percent_message);
//            progressBarMessageLayout.setVisibility(View.GONE);
//            // 使用ImageView显示动画
//            Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
//                    mContent, R.anim.loading_animation);
//            spaceshipImage.startAnimation(hyperspaceJumpAnimation);
//        }
//        if (title != null) {
//            titleView.setText(title);
//        }
//        if (progressMessage != null) {
//            messageView.setText(progressMessage);
//        }
//        return viewLayout;
//    }
//
//
//    public static CustomProgressDialog show(Context context, int title,
//                                            String message, boolean indeterminate, boolean cancelable) {
//        CustomProgressDialog customDialog = new CustomProgressDialog(context);
//        customDialog.setCancelable(cancelable);
//        customDialog.setTitle(title);
//        customDialog.setIndeterminate(indeterminate);
//        customDialog.setMessage(message);
//        customDialog.setCanceledOnTouchOutside(false);
//        customDialog.show();
//        return customDialog;
//    }

//    /**
//     * 显示自定义进度条
//     * @param context 上下文
//     * @param title 标题
//     * @param message 内容
//     * @param indeterminate indeterminate
//     * @param cancelable 是否可取消
//     * @return CustomProgressDialog
//     */
//    public static CustomProgressDialog show(Context context, int title,
//                                            int message, boolean indeterminate, boolean cancelable) {
//
//        return show(context,context.getString(title),context.getString(message),indeterminate,cancelable);
//
//    }

    public static CustomProgressDialog show(Context context) {
        CustomProgressDialog customDialog = new CustomProgressDialog(context);
//        customDialog.setCancelable(cancelable);
//        customDialog.setTitle(title);
//        customDialog.setIndeterminate(indeterminate);
//        customDialog.setMessage(message);
        customDialog.setCancelable(true);
        customDialog.setCanceledOnTouchOutside(false);
        customDialog.show();
        ProgressDialogSetting(customDialog);
        return customDialog;
    }


    public static void show(Context context, String title, String message) {
        CustomProgressDialog customDialog = new CustomProgressDialog(context);
        customDialog.setTitle(title);
        customDialog.setMessage(message);
        customDialog.show();
    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.alpha = bgAlpha; // 透明度值域0.0-1.0
        win.setAttributes(lp);
    }
}
