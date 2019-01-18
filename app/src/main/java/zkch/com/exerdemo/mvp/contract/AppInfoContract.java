package zkch.com.exerdemo.mvp.contract;

import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.bean.IndexBean;
import zkch.com.exerdemo.cniaow.bean.PageBean;
import zkch.com.exerdemo.cniaow.ui.BaseView;

/**
 *  契约层  IView IPersenter 都定义到Contract层
 *  IView 每次都要声明 showLoading等公共方法
    Presenter中每次请求时都要调用mView的showLoading等公共方法
 */
public interface AppInfoContract {

    /**
     *  推荐页面 View
     */
    interface RecyView extends BaseView{
        /**
         * 显示结果  判断显示哪种类型item
         */
        void showResult(IndexBean indexBean);

        /**
         * 数据为空
         */
        void showNodata();

        /**
         * 数据加载错误 view
         *
         * @param msg
         */
        void showError(String msg);
    }

    interface AppInfoView extends BaseView {

        void showResult(PageBean<AppInfo> pageBean);

        void onLoadMoreComplete();
    }

    /**
     * App 详细
     */
    interface  AppDetailView extends BaseView{

        void showAppDetail(AppInfo appInfo);

    }


}
