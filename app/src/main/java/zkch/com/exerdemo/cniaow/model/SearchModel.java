package zkch.com.exerdemo.cniaow.model;

import java.util.List;

import io.reactivex.Observable;
import zkch.com.exerdemo.api.ApiService;
import zkch.com.exerdemo.cniaow.bean.BaseBean;
import zkch.com.exerdemo.cniaow.bean.SearchResult;
import zkch.com.exerdemo.cniaow.mvp.contract.SearchContract;

public class SearchModel implements SearchContract.ISearchModel {

    private ApiService mApiService;

    public SearchModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<String>>> getSuggestions(String keywork) {
        return mApiService.searchSuggest(keywork);
    }

    @Override
    public Observable<BaseBean<SearchResult>> getSearchResult(String keywork) {
        return mApiService.search(keywork);
    }
}
