package zkch.com.exerdemo.ui;

public interface BaseView {
    /**
     * 显示进度对话框
     */
    void showLoading();

    /**
     * 关闭进度对话框
     */
    void dismissLoading();

    /**
     * 显示错误信息
     *
     * @param displayMessage
     */
    void showError(String displayMessage);
}
