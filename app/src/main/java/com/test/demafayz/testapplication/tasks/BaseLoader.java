package com.test.demafayz.testapplication.tasks;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by demafayz on 25.08.16.
 */
public class BaseLoader extends AsyncTaskLoader<Object> {

    private DoInBackground doInBackground;

    public BaseLoader(Context context) {
        super(context);
    }

    @Override
    public Object loadInBackground() {
        if (doInBackground != null) {
            doInBackground.onBackground(getContext());
        }
        return null;
    }

    public void setDoInBackground(DoInBackground doInBackground) {
        this.doInBackground = doInBackground;
    }

    public interface DoInBackground {
        public void onBackground(Context context);
    }
}
