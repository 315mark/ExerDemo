package zkch.com.exerdemo.common.rx;

import android.accounts.NetworkErrorException;
import android.content.Context;

import com.google.gson.JsonParseException;

import java.net.SocketException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;
import zkch.com.exerdemo.common.exception.ApiException;
import zkch.com.exerdemo.common.exception.BaseException;
import zkch.com.exerdemo.common.exception.ErrorMessageFactory;
import zkch.com.exerdemo.util.ToastUtils;

/**
 * 封装网络请求异常
 */
public class RxErrorHandler {

    private Context mContext;

    public RxErrorHandler(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 异常处理方法
     */
    public BaseException handlerError(Throwable e) {
        BaseException exception = new BaseException();
        if (e instanceof ApiException) {
            exception.setCode(((ApiException) e).getCode());
        } else if (e instanceof SocketException) {
            exception.setCode(BaseException.SOCKET_ERROR);
        } else if (e instanceof SocketTimeoutException) {
            exception.setCode(BaseException.SOCKET_TIMEOUT_ERROR);
        } else if (e instanceof JsonParseException) {
            exception.setCode(BaseException.JSON_ERROR);
        } else if (e instanceof HttpException) {
            exception.setCode(BaseException.HTTP_ERROR);
        } else if (e instanceof NetworkErrorException) {
            exception.setCode(BaseException.NETWORD_ERROR);
        } else {
            exception.setCode(BaseException.UNKNOWN_ERROR);
        }
        exception.setDisplayMsg(ErrorMessageFactory.create(mContext, exception.getCode()));
        return exception;
    }

    /**
     * 显示错误信息
     */
    public void showErrorMsg(BaseException e) {
        ToastUtils.showShort(e.getDisplayMsg());
    }

}
