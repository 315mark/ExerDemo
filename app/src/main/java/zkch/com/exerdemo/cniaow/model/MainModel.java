package zkch.com.exerdemo.cniaow.model;

import java.util.List;

import io.reactivex.Observable;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.bean.AppsUpdateBean;
import zkch.com.exerdemo.cniaow.bean.BaseBean;
import zkch.com.exerdemo.cniaow.mvp.contract.MainContract;

public class MainModel implements MainContract.IMainModel {
    private ApiService apiService;

    public MainModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<AppInfo>>> getUpdateApps(AppsUpdateBean appsUpdate) {
        return apiService.getAppsUpdateinfo(appsUpdate.getPackageName(), appsUpdate.getVersionCode());
    }
}
