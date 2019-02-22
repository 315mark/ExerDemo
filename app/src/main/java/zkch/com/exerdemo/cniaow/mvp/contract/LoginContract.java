package zkch.com.exerdemo.cniaow.mvp.contract;

import io.reactivex.Observable;
import zkch.com.exerdemo.cniaow.bean.BaseBean;
import zkch.com.exerdemo.cniaow.bean.LoginBean;
import zkch.com.exerdemo.cniaow.ui.BaseView;

public interface LoginContract {

    interface ILoginModel {
        Observable<BaseBean<LoginBean>> login(String phone, String paswad);
    }

    interface LoginView extends BaseView {
        void checkPhoneError();

        void checkPhoneSuccess();

        void loginSuccess(LoginBean bean);
    }

}
