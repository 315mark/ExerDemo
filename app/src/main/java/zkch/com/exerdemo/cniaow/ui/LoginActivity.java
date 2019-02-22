package zkch.com.exerdemo.cniaow.ui;

import android.annotation.SuppressLint;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;
import io.reactivex.Observable;
import zkch.com.exerdemo.R;
import zkch.com.exerdemo.base.BaseActivity;
import zkch.com.exerdemo.cniaow.bean.LoginBean;
import zkch.com.exerdemo.cniaow.mvp.component.AppComponent;
import zkch.com.exerdemo.cniaow.mvp.component.DaggerLoginComponent;
import zkch.com.exerdemo.cniaow.mvp.contract.LoginContract;
import zkch.com.exerdemo.cniaow.mvp.module.LoginModule;
import zkch.com.exerdemo.cniaow.mvp.presenter.LoginPresenter;
import zkch.com.exerdemo.util.ToastUtils;
import zkch.com.exerdemo.widget.LoadingButton;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView {

    @BindView(R.id.Toolbar)
    Toolbar Toolbar;
    @BindView(R.id.edit_mobi)
    EditText editMobi;
    @BindView(R.id.view_mobi)
    TextInputLayout viewMobi;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.view_pwd)
    TextInputLayout viewPwd;
    @BindView(R.id.btn_login)
    LoadingButton btnLogin;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        initView();
    }

    @SuppressLint("CheckResult")  //RxJava 会造成内存泄漏  RxLifeCycle框架能解决问题
    private void initView() {
        Observable<CharSequence> mobiObservable = RxTextView.textChanges(editMobi);
        Observable<CharSequence> pwdObservable = RxTextView.textChanges(editPwd);

        //合并  直译:结合最新的
        Observable.combineLatest(mobiObservable, pwdObservable, (mobi, pwd) ->
                isPasswordValid(pwd.toString()) && isPhoneValid(mobi.toString()))
                .subscribe(aBoolean -> RxView.enabled(btnLogin).accept(aBoolean));

        Toolbar.setNavigationIcon(new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_arrow_back).sizeDp(16).color(getResources().getColor(R.color.md_white_1000)));

        RxView.clicks(btnLogin).subscribe(o -> mPresenter.login(editMobi.getText().toString().trim(), editPwd.getText().toString().trim()));

    }

    @Override
    public void checkPhoneError() {
        viewMobi.setError("手机号格式不正确");
    }

    @Override
    public void checkPhoneSuccess() {
        viewMobi.setError("");
        viewMobi.setErrorEnabled(false);
    }

    @Override
    public void loginSuccess(LoginBean bean) {
        this.finish();
        ToastUtils.showShort("登录成功");
    }


    private boolean isPhoneValid(String phone) {
        return phone.length() == 11;
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    /**
     * 设置Activity Component
     *
     * @param appComponent
     */
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder().appComponent(appComponent)
                .loginModule(new LoginModule(this)).build().inject(this);
    }

}
