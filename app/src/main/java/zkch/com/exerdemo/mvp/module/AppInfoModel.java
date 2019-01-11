package zkch.com.exerdemo.mvp.module;

import dagger.Module;
import rx.Observable;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.bean.BaseBean;
import zkch.com.exerdemo.cniaow.bean.IndexBean;
import zkch.com.exerdemo.cniaow.bean.PageBean;
import zkch.com.exerdemo.mvp.scope.ScopeFragment;

/**
 *  推荐页面的 Model  负责提供接口  Presenter 负责处理
 */
@Module
public class AppInfoModel {

    private ApiService apiService;

    public AppInfoModel(ApiService apiService) {
        this.apiService = apiService;
    }

    /**
     *  通过Observable 回调Api请求接口返回信息
     *   apiService  接口参数
     * @return
     */

    Observable<BaseBean<PageBean<AppInfo>>> getApp(){
        return apiService.getApps("{'page':0}");
    }

    Observable<BaseBean<IndexBean>> getIndex(){
        return apiService.index();
    }

    Observable<BaseBean<PageBean<AppInfo>>> topList(int page){
        return apiService.topList(page);
    }


}
