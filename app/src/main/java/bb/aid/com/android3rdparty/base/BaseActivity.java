package bb.aid.com.android3rdparty.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

public class BaseActivity extends ActionBarActivity {

    public Toolbar actionbarToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*public void setUpToolBar() {
        actionbarToolBar = (Toolbar) findViewById(R.id.toolbar);
        //actionbarToolBar.setTitleTextColor(getResources().getColor(R.color.primary_text));
        setSupportActionBar(actionbarToolBar);
    }*/

}
