package zkch.com.exerdemo.common.seivice;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class InstallAccessibilityService extends AccessibilityService {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        AccessibilityNodeInfo nodeInfo = event.getSource();
        if (nodeInfo == null) {
            return;
        }
        int eventType = event.getEventType();
        if (eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED || eventType == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            click("安装");
            click("下一步");
            click("确定");
            click("完成");
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void click(String txt) {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo == null) {
            return;
        }
        //如何获取UI元素
        List<AccessibilityNodeInfo> infoList = nodeInfo.findAccessibilityNodeInfosByText(txt);
        if (nodeInfo == null) {
            return;
        }
        for (AccessibilityNodeInfo info : infoList) {
            if (info.getClassName().equals("android.widget.Button") && info.isClickable()) {
                info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }


    @Override
    public void onInterrupt() {

    }
}
