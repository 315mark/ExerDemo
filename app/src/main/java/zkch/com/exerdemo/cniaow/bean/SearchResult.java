package zkch.com.exerdemo.cniaow.bean;

import java.util.List;

public class SearchResult extends BaseEntity {
    private boolean hasMore;

    private List<AppInfo> listApp;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<AppInfo> getListApp() {
        return listApp;
    }

    public void setListApp(List<AppInfo> listApp) {
        this.listApp = listApp;
    }

}
