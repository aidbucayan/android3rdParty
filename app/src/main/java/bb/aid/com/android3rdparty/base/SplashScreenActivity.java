package bb.aid.com.android3rdparty.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import bb.aid.com.android3rdparty.MainActivity;
import bb.aid.com.android3rdparty.R;


public class SplashScreenActivity extends BaseActivity {

    private final String TAG = SplashScreenActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_);

        if (!isTaskRoot()) {
            final Intent intent = getIntent();
            final String intentAction = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intentAction != null
                    && intentAction.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this,
                        MainActivity.class));
                finish();
            }
        }, 1000);
    }


}
