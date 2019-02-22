package zkch.com.exerdemo.cniaow.model;

import io.reactivex.Observable;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.bean.BaseBean;
import zkch.com.exerdemo.cniaow.bean.LoginBean;
import zkch.com.exerdemo.cniaow.bean.LoginRequestBean;
import zkch.com.exerdemo.cniaow.mvp.contract.LoginContract;

public class LoginModel implements LoginContract.ILoginModel {
    private ApiService apiService;

    public LoginModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseBean<LoginBean>> login(String phone, String paswad) {
        LoginRequestBean bean = new LoginRequestBean();
        bean.setEmail(phone);
        bean.setPassword(paswad);
        return apiService.login(bean);
    }
}
