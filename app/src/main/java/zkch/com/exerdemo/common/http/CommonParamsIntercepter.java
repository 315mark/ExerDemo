package zkch.com.exerdemo.common.http;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import zkch.com.exerdemo.common.ACache;
import zkch.com.exerdemo.common.constant.Constant;
import zkch.com.exerdemo.util.DeviceUtils;

/**
 * 公共参数请求拦截
 */
public class CommonParamsIntercepter implements Interceptor {

    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private Context mContext;
    private Gson mGson;

    public CommonParamsIntercepter(Context context, Gson gson) {
        this.mContext = context;
        this.mGson = gson;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        try {
            String method = request.method();

            HashMap<String, Object> paramsMap = new HashMap<>();
            paramsMap.put(Constant.IMEI, "5456773132487564567");//DeviceUtils.getIMEI(mContext)
            paramsMap.put(Constant.MODEL, DeviceUtils.getModel());
            paramsMap.put(Constant.LANGUAGE, DeviceUtils.getLanguage());
            paramsMap.put(Constant.RESOLUTION, DeviceUtils.getScreenW(mContext) + "*" + DeviceUtils.getScreenH(mContext));
            paramsMap.put(Constant.os, DeviceUtils.getBuildVersionIncremental());
            paramsMap.put(Constant.SDK, DeviceUtils.getSDKVersion() + "");
            paramsMap.put(Constant.DENSITY_SCALE_FACTOR, mContext.getResources().getDisplayMetrics().density + "");
            //发送token到server
            String token = ACache.get(mContext).getAsString(Constant.TOKEN);
            paramsMap.put(Constant.TOKEN, TextUtils.isEmpty(token) ? "" : token);

            //TODO 这个get请求封装还需要看视频回顾  完全不懂
            if (method.equals("GET")) {
                HttpUrl url = request.url();
                //添加公共参数到hashmap
                HashMap<String, Object> map = new HashMap<>();
                //get All query Parameter 获取所有参数
                Set<String> pramaeNames = url.queryParameterNames();
                for (String key : pramaeNames) {
                    // TODO: select destin PARAM
                    // TODO: 如果包含指定的PARAM 则插入到map中
                    if (Constant.PARAM.equals(key)) {
                        // TODO: oldParamJson
                        String oldParamJson = url.queryParameter(Constant.PARAM);
                        if (oldParamJson != null) {
                            HashMap<String, Object> hashMap = mGson.fromJson(oldParamJson, HashMap.class);
                            if (hashMap != null) {
                                for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                                    map.put(entry.getKey(), entry.getValue());
                                }
                            }
                        }
                    } else {
                        map.put(key, url.queryParameter(key));
                    }
                }
                //添加公共参数
                map.put("publicParams", paramsMap);
                //TODO: cover to jsonString
                String jsonParam = mGson.toJson(map);
                String Url = url.toString();
                // TODO: change Url
                int index = Url.indexOf("?");
                if (index > 0) {
                    Url = Url.substring(0, index);
                }
                Url = Url + "?" + Constant.PARAM + "=" + jsonParam;
                // TODO: Build new Request
                request = request.newBuilder().url(Url).build();
            } else if (method.equals("POST")) {
                RequestBody body = request.body();
                HashMap<String, Object> rootMap = new HashMap<>();
                //form表单
                if (body instanceof FormBody) {
                    for (int i = 0; i < ((FormBody) body).size(); i++) {
                        rootMap.put(((FormBody) body).encodedName(i), ((FormBody) body).encodedValue(i));
                    }
                } else {
                    Buffer buffer = new Buffer();
                    body.writeTo(buffer);
                    String oldJsonParams = buffer.readUtf8();
                    if (!TextUtils.isEmpty(oldJsonParams)) {
                        rootMap = mGson.fromJson(oldJsonParams, HashMap.class);
                        // 原始参数  // 重新组装
                        if (rootMap != null) {
                            rootMap.put("publicParams", paramsMap);
                            // {"page":0,"publicParams":{"imei":'xxxxx',"sdk":14,.....}}
                            String newJsonParams = mGson.toJson(rootMap);
                            request = request.newBuilder().post(RequestBody.create(JSON, newJsonParams)).build();
                        }
                    }
                }
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return chain.proceed(request);
    }
}
