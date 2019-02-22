package zkch.com.exerdemo.cniaow.mvp.module;

import dagger.Module;
import dagger.Provides;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.model.LoginModel;
import zkch.com.exerdemo.cniaow.mvp.contract.LoginContract;

@Module
public class LoginModule {
    private LoginContract.LoginView mLoginView;

    public LoginModule(LoginContract.LoginView mLoginView) {
        this.mLoginView = mLoginView;
    }

    @Provides
    public LoginContract.LoginView provideLoginView() {
        return mLoginView;
    }

    @Provides
    public LoginContract.ILoginModel provideLoginModel(ApiService apiService) {
        return new LoginModel(apiService);
    }

}
