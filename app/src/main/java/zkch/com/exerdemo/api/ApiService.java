package zkch.com.exerdemo.api;


import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import io.reactivex.Observable;
import zkch.com.exerdemo.cniaow.bean.AppInfo;
import zkch.com.exerdemo.cniaow.bean.BaseBean;
import zkch.com.exerdemo.cniaow.bean.IndexBean;
import zkch.com.exerdemo.cniaow.bean.PageBean;

public interface ApiService {

    // TODO: BASE_URL
    public static final String BASE_URL = "http://112.124.22.238:8081/course_api/cniaoplay/";
    // TODO: BASE_IMGURL
//    public static final String BASE_IMG_URL = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";
    String BASE_IMG_URL = "http://file.market.xiaomi.com/mfc/thumbnail/png/w150q80/";

//    public Call<PageBean<AppInfo>> getApps(@Query("p") String jsonParam);

    @GET("featured2")
    Observable<BaseBean<PageBean<AppInfo>>> getApps(@Query("p") String jsonParam);

    @GET("index")
    public Observable<BaseBean<IndexBean>> index();

    // TODO: topList
    @GET("toplist")
    public Observable<BaseBean<PageBean<AppInfo>>> topList(@Query("page") int page);

    @GET("game")
    Observable<BaseBean<PageBean<AppInfo>>> game(@Query("page") int page);


//    @POST("login")
//    Observable<BaseBean<LoginBean>> login(@Body LoginRequestBean param);
//
//    @GET("category")
//    Observable<BaseBean<List<Category>>> getCategories();


    /**
     * Category
     */
    @GET("category/featured/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getFeaturedAppsByCategory(@Path("categoryid") int categoryid, @Query("page") int page);

    @GET("category/toplist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getTopListAppsByCategory(@Path("categoryid") int categoryid, @Query("page") int page);

    @GET("category/newlist/{categoryid}")
    Observable<BaseBean<PageBean<AppInfo>>> getNewListAppsByCategory(@Path("categoryid") int categoryid, @Query("page") int page);
    @GET("app/{id}")
    Observable<BaseBean<AppInfo>> getAppDetail(@Path("id") int id);

}
