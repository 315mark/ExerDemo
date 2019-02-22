package zkch.com.exerdemo.cniaow.mvp.contract;

import java.util.List;

import io.reactivex.Observable;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.bean.AppsUpdateBean;
import zkch.com.exerdemo.cniaow.bean.BaseBean;
import zkch.com.exerdemo.cniaow.ui.BaseView;

public interface MainContract {

    interface MainView extends BaseView {
        void requestPermisSuccess();

        void requestPermisFail();

        void changeAppUpdateCount(int count);
    }

    interface IMainModel {
        Observable<BaseBean<List<AppInfo>>> getUpdateApps(AppsUpdateBean appsUpdate);
    }

}
