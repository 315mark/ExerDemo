package zkch.com.exerdemo.cniaow.mvp.presenter;

import com.hwangjr.rxbus.RxBus;

import javax.inject.Inject;

import zkch.com.exerdemo.cniaow.bean.LoginBean;
import zkch.com.exerdemo.cniaow.mvp.contract.LoginContract;
import zkch.com.exerdemo.common.ACache;
import zkch.com.exerdemo.common.constant.Constant;
import zkch.com.exerdemo.common.rx.RxHttpReponseCompat;
import zkch.com.exerdemo.common.subscriber.ErrorHandlerObserver;
import zkch.com.exerdemo.util.PhoneUtils;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel, LoginContract.LoginView> {

    @Inject
    public LoginPresenter(LoginContract.ILoginModel mModel, LoginContract.LoginView mView) {
        super(mModel, mView);
    }

    public void login(String phone, String pwd) {
        if (!PhoneUtils.matcherPhoneNum(phone)) {
            mView.checkPhoneError();
        } else {
            mView.checkPhoneSuccess();
        }

        mModel.login(phone, pwd).compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ErrorHandlerObserver<LoginBean>(mContext) {
                    @Override
                    public void onNext(LoginBean bean) {
                        mView.loginSuccess(bean);
                        saveUser(bean);
                        RxBus.get().post(bean.getUser());
                    }

                    @Override
                    public void onComplete() {
                        mView.dismissLoading();
                    }
                });

    }

    private void saveUser(LoginBean bean) {
        ACache aCache = ACache.get(mContext);
        aCache.put(Constant.TOKEN, bean.getToken());
        aCache.put(Constant.USER, bean.getUser());
    }


}
