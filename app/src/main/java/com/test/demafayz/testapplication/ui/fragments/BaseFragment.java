package com.test.demafayz.testapplication.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;

import com.test.demafayz.testapplication.tasks.BaseLoader;

/**
 * Created by demafayz on 25.08.16.
 */
public abstract class BaseFragment extends Fragment implements LoaderManager.LoaderCallbacks<Object>, BaseLoader.DoInBackground {

    private Loader<Object> loader;
    private LoaderManager manager;
    private static int BASE_LOADER_ID = 100;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populateBackground();
    }

    protected abstract void onPreExecute();

    protected abstract void doInBackground(Context context);

    protected abstract void onPostExecute();

    private void populateBackground() {
        manager = getLoaderManager();
        loader = manager.restartLoader(BASE_LOADER_ID, null, this);
        loader.forceLoad();
    }

    @Override
    public void onBackground(Context context) {
        doInBackground(context);
    }

    @Override
    public Loader<Object> onCreateLoader(int id, Bundle args) {
        BaseLoader baseLoader = new BaseLoader(getContext());
        baseLoader.setDoInBackground(this);
        onPreExecute();
        return baseLoader;
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {
        onPostExecute();
    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {

    }
}
