package zkch.com.exerdemo.mvp.module;

import dagger.Provides;
import dagger.Module;
import zkch.com.exerdemo.mvp.contract.AppInfoContract;
import zkch.com.exerdemo.api.ApiService;

@Module
public class RecommendModule{

   private AppInfoContract.RecyView mView;

   public RecommendModule(AppInfoContract.RecyView mView){
       this.mView=mView;
    }

   @Provides
   public AppInfoContract.RecyView provideView(){

       return mView;
   }

    @Provides
    public AppInfoModel provideRecommendModel(ApiService apiService) {
        return new AppInfoModel(apiService);
    }

   /* @Provides
    public android.app.ProgressDialog provideProgressDialog(AppInfoContract.ReView reView) {
        return new ProgressDialog(((RecommendFragment) reView).getActivity());
    }
*/

}
