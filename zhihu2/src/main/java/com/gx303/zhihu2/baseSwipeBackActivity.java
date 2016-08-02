package com.gx303.zhihu2;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Administrator on 2015/3/16.
 */
public class baseSwipeBackActivity extends SwipeBackActivity {
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
        overridePendingTransition(0,R.anim.slide_right_out);

    }
}
