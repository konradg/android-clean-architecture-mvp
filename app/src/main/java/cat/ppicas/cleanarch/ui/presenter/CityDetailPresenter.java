package cat.ppicas.cleanarch.ui.presenter;

import java.util.ArrayList;
import java.util.List;

import cat.ppicas.cleanarch.R;
import cat.ppicas.cleanarch.domain.City;
import cat.ppicas.cleanarch.repository.CityRepository;
import cat.ppicas.cleanarch.task.GetCityTask;
import cat.ppicas.cleanarch.ui.view.CityDetailView;
import cat.ppicas.cleanarch.util.ShowErrorTaskCallback;
import cat.ppicas.cleanarch.util.TaskExecutor;

public class CityDetailPresenter extends Presenter<CityDetailView> {

    private TaskExecutor mTaskExecutor;
    private CityRepository mCityRepository;
    private String mCityId;

    private GetCityTask mGetCityTask;
    private City mCity;
    private final List<GetCityCallback> mGetCityCallbacks = new ArrayList<GetCityCallback>();

    public CityDetailPresenter(TaskExecutor taskExecutor, CityRepository cityRepository,
            String cityId) {
        mTaskExecutor = taskExecutor;
        mCityRepository = cityRepository;
        mCityId = cityId;
    }

    @Override
    public void bindView(CityDetailView view) {
        super.bindView(view);

        view.setTitle(R.string.city_details__title_loading);

        if (mCity != null) {
            view.setTitle(R.string.city_details__title, mCity.getName());
        }

        if (mTaskExecutor.isRunning(mGetCityTask)) {
            return;
        }

        view.showProgress(true);
        mGetCityTask = new GetCityTask(mCityRepository, mCityId);
        mTaskExecutor.execute(mGetCityTask, new ShowErrorTaskCallback<City>(this) {
            @Override
            public void onSuccess(City city) {
                mCity = city;

                for (GetCityCallback callback : mGetCityCallbacks) {
                    callback.onGetCityResult(city);
                }
                mGetCityCallbacks.clear();

                if (getView() != null) {
                    getView().showProgress(false);
                    getView().setTitle(R.string.city_details__title, city.getName());
                }
            }
        });
    }

    protected void getCity(GetCityCallback callback) {
        if (mCity != null) {
            callback.onGetCityResult(mCity);
        } else {
            mGetCityCallbacks.add(callback);
        }
    }

    protected interface GetCityCallback {

        public void onGetCityResult(City city);

    }
}
