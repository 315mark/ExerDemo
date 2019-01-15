package zkch.com.exerdemo.mvp.module;

import dagger.Module;
import io.reactivex.Observable;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.bean.BaseBean;
import zkch.com.exerdemo.cniaow.bean.IndexBean;
import zkch.com.exerdemo.cniaow.bean.PageBean;

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
   public Observable<BaseBean<PageBean<AppInfo>>> getApp(){
       return apiService.getApps("{'page':0}");
   }

   public Observable<BaseBean<IndexBean>> getIndex() {
       return apiService.index();
   }

  public Observable<BaseBean<PageBean<AppInfo>>> toList(int page){
       return apiService.topList(page);
   }


}
