package cat.ppicas.cleanarch.presenter;

public class FruitListPresenter /*extends Presenter<FruitListView>*/ {

    /*private final TaskExecutor mExecutor = App.getServiceContainer().getTaskExecutor();

    private List<Fruit> mFruits;
    private ListFruitsTask mTask;

    public void onShowFruits() {
        if (mFruits != null) {
            getView().showFruits(mFruits);
        } else if (mTask == null || !mExecutor.isRunning(mTask)) {
            mTask = new ListFruitsTask(App.getServiceContainer().getFruitRepository());
            mExecutor.execute(mTask, new ListFruitsCallback());
        }
    }

    @Override
    protected Class<FruitListView> getViewClassToken() {
        return FruitListView.class;
    }

    private class ListFruitsCallback implements TaskCallback<List<Fruit>> {

        @Override
        public void onSuccess(List<Fruit> fruits) {
            mFruits = fruits;
            getView().showFruits(fruits);
        }

        @Override
        public void onError(Exception error) {
            getView().showError(R.string.error__connection);
        }
    }*/
}
