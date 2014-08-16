package cat.ppicas.cleanarch.app;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cat.ppicas.cleanarch.repository.CityRepository;
import cat.ppicas.cleanarch.rest.RestCityRepository;
import cat.ppicas.cleanarch.util.AsyncTaskExecutor;
import cat.ppicas.cleanarch.util.TaskExecutor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

class DefaultServiceContainer implements ServiceContainer {

    private Context mContext;

    private RestAdapter mRestAdapter;
    private OkHttpClient mOkClient;
    private AsyncTaskExecutor mTaskExecutor;

    public DefaultServiceContainer(Context context) {
        mContext = context;
    }

    @Override
    public CityRepository getCityRepository() {
        return new RestCityRepository(getRestAdapter());
    }

    @Override
    public TaskExecutor getTaskExecutor() {
        if (mTaskExecutor == null) {
            mTaskExecutor = new AsyncTaskExecutor();
        }
        return mTaskExecutor;
    }

    private RestAdapter getRestAdapter() {
        if (mRestAdapter == null) {
            mRestAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://api.openweathermap.org")
                    .setClient(new OkClient(getOkHttpClient()))
                    .build();
        }

        return mRestAdapter;
    }

    private OkHttpClient getOkHttpClient() {
        if (mOkClient == null) {
            mOkClient = new OkHttpClient();
            mOkClient.setConnectTimeout(10, TimeUnit.SECONDS);
            mOkClient.setReadTimeout(5, TimeUnit.SECONDS);
            mOkClient.setWriteTimeout(5, TimeUnit.SECONDS);
            mOkClient.setCache(getOkHttpCache());
        }

        return mOkClient;
    }

    private Cache getOkHttpCache() {
        try {
            File directory = new File(mContext.getCacheDir(), "ok-http");
            return new Cache(directory, 3000000);
        } catch (IOException e) {
            return null;
        }
    }
}