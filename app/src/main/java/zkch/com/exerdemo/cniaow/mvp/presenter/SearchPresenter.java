package zkch.com.exerdemo.cniaow.mvp.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import zkch.com.exerdemo.cniaow.bean.SearchResult;
import zkch.com.exerdemo.cniaow.mvp.contract.SearchContract;
import zkch.com.exerdemo.common.rx.RxHttpReponseCompat;
import zkch.com.exerdemo.common.subscriber.ProgressObserver;

public class SearchPresenter extends BasePresenter<SearchContract.ISearchModel, SearchContract.SearchView> {

    @Inject
    public SearchPresenter(SearchContract.ISearchModel mModel, SearchContract.SearchView mView) {
        super(mModel, mView);
    }

    public void showHistory() {
        List<String> list = new ArrayList<>();
        list.add("地图");
        list.add("KK");
        list.add("爱奇艺");
        list.add("播放器");
        list.add("支付宝");
        list.add("微信");
        list.add("QQ");
        list.add("TV");
        list.add("直播");
        list.add("妹子");
        list.add("美女");
        mView.showSearchHistory(list);
    }

    //获取提醒列表
    public void getSuggestions(String keyword) {
        mModel.getSuggestions(keyword).compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressObserver<List<String>>(mContext, mView) {
                    @Override
                    public void onNext(List<String> strings) {
                        mView.showSuggestions(strings);
                    }
                });
    }

    //查找关键词
    public void search(String keyword) {
        saveSearchHistory(keyword);
        mModel.getSearchResult(keyword).compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressObserver<SearchResult>(mContext, mView) {
                    @Override
                    public void onNext(SearchResult result) {
                        mView.showSearchResult(result);
                    }
                });
    }

    //点击关键词
    public void click(String keyword) {
        saveSearchHistory(keyword);
        mModel.getSearchResult(keyword).compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressObserver<SearchResult>(mContext, mView) {
                    @Override
                    public void onNext(SearchResult result) {
                        mView.showSearchResult(result);
                    }
                });
    }

    //保存到数据库
    private void saveSearchHistory(String keyword) {

    }

}
