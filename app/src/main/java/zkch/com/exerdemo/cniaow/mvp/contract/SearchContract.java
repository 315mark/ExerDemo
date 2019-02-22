package zkch.com.exerdemo.cniaow.mvp.contract;

import java.util.List;

import io.reactivex.Observable;
import zkch.com.exerdemo.cniaow.bean.BaseBean;
import zkch.com.exerdemo.cniaow.bean.SearchResult;
import zkch.com.exerdemo.cniaow.ui.BaseView;

public class SearchContract {

    public interface SearchView extends BaseView {
        void showSearchHistory(List<String> list);

        void showSuggestions(List<String> list);

        void showSearchResult(SearchResult result);

    }

    public interface ISearchModel {

        Observable<BaseBean<List<String>>> getSuggestions(String keywork);

        Observable<BaseBean<SearchResult>> getSearchResult(String keywork);

    }


}
