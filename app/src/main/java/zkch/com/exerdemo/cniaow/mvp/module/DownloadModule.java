package zkch.com.exerdemo.cniaow.mvp.module;

import android.app.Application;
import android.os.Environment;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import zkch.com.exerdemo.common.ACache;
import zkch.com.exerdemo.common.constant.Constant;
import zlc.season.rxdownload2.RxDownload;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

@Module
public class DownloadModule {

    @Provides
    @Singleton
    public RxDownload provideRxDownload(Application application, Retrofit retrofit, File path) {
        ACache.get(application).put(Constant.APK_DOWNLOAD_DIR, path.getPath());

        return RxDownload.getInstance(application)
                .defaultSavePath(path.getPath())
                .retrofit(retrofit)
                .maxDownloadNumber(10)
                .maxThread(10);
    }

    @Provides
    @Singleton
    File provideDownPath() {
        return Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS);
    }
}
