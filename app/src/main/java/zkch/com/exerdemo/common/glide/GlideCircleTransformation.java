package zkch.com.exerdemo.common.glide;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.security.MessageDigest;

/**
 *   Glide对网络图片进行处理 自定义圆形/圆角 模糊处理等
 *   图像变换最重要的方法transform()
 *   1)pool，这个是Glide中的BitmapPool缓存池，用于对Bitmap对象进行重用
 *   2)toTransform，这个是原始图片的Bitmap对象，就是要对它来进行图片变换。
 *   3)图片变换后的宽度   4)图片变换后的高度
 *   https://github.com/wasabeef/glide-transformations 这个库实现很多通用的变换效果
 *
 *   glide4.x和3.x使用的方式很不一样，导致centerCrop()和transform并不能共存
 *
 *
 */
public class GlideCircleTransformation extends CenterCrop {

    public GlideCircleTransformation() {

    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }

    private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if(source ==null) return null;
        int size=Math.min(source.getWidth(),source.getHeight());
        int x= (source.getWidth() - size)/2;
        int y= (source.getHeight() - size)/2;

        Bitmap bitmap = Bitmap.createBitmap(source, x, y, size, size);
        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        return result;
    }


    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }

}
