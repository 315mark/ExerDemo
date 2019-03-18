package zkch.com.exerdemo.cniaow.model;

import io.reactivex.Observable;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.bean.BaseBean;
import zkch.com.exerdemo.cniaow.bean.IndexBean;
import zkch.com.exerdemo.cniaow.bean.PageBean;

/**
 * 推荐页面的 Model  负责提供接口  Presenter 负责处理
 */

public class AppInfoModel {

    private ApiService apiService;

    public AppInfoModel(ApiService apiService) {
        this.apiService = apiService;
    }

    /**
     * 通过Observable 回调Api请求接口返回信息
     * apiService  接口参数
     */
    public Observable<BaseBean<PageBean<AppInfo>>> getApp() {
        return apiService.getApps("{'page':0}");
    }

    /*首页*/
    public Observable<BaseBean<IndexBean>> getIndex() {
        return apiService.index();
    }

    /*排行榜*/
    public Observable<BaseBean<PageBean<AppInfo>>> toList(int page) {
        return apiService.topList(page);
    }

    /*游戏*/
    public Observable<BaseBean<PageBean<AppInfo>>> getGames(int page) {
        return apiService.game(page);
    }

    // 获取种类app 精品
    public Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory(int categoryId, int page) {
        return apiService.getFeaturedAppsByCategory(categoryId, page);
    }

    //指定类别排行
    public Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory(int categoryId, int page) {
        return apiService.getTopListAppsByCategory(categoryId, page);
    }

    /*指定类别新品*/
    public Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory(int categoryId, int page) {
        return apiService.getNewListAppsByCategory(categoryId, page);
    }

    /*app详情*/
    public Observable<BaseBean<AppInfo>> getAppDetail(int id) {
        return apiService.getAppDetail(id);
    }


}
