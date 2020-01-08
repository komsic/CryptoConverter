package com.example.komsic.cryptoconverter.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.komsic.cryptoconverter.data.db.CurrencyCard;
import com.example.komsic.cryptoconverter.data.db.CurrencyCardDao;
import com.example.komsic.cryptoconverter.data.db.CurrencyCardSubset;
import com.example.komsic.cryptoconverter.data.service.CurrencyRemote;
import com.example.komsic.cryptoconverter.data.service.model.ItemResponse;
import com.example.komsic.cryptoconverter.di.ApplicationScope;

import java.util.List;

import javax.inject.Inject;

@ApplicationScope
public class CurrencyRepository {
    private static final String TAG = "CurrencyRepository";

    private CurrencyCardDao mDao;
    private LiveData<List<CurrencyCard>> mSelectedCard;
    private LiveData<List<CurrencyCardSubset>> mUnselectedCard;
    private CurrencyRemote mRemote;
    private static MutableLiveData<Boolean> mRemoteDataFetchingStatus;

    CurrencyConverter currencyConverter;

    @Inject
    CurrencyRepository(CurrencyConverter currencyConverter, CurrencyCardDao dao,
                       CurrencyRemote currencyRemote) {
        this.currencyConverter = currencyConverter;
        mDao = dao;
        mSelectedCard = mDao.getSelectedCards();
        mUnselectedCard = mDao.getUnselectedCards();
        mRemote = currencyRemote;
        new updateRatesFromRemoteAsyncTask(mDao, mRemote).execute();
        mRemoteDataFetchingStatus = new MutableLiveData<>();
    }

    public double[] getConvertedAmount(CurrencyCard card, String selectedCurrencyToBeConverted,
                                       double amountTobeConverted) {
        currencyConverter.setCard(card);
        return currencyConverter.convert(selectedCurrencyToBeConverted, amountTobeConverted);
    }

    public LiveData<CurrencyCard> getCurrencyCard(String currencyType) {
        return mDao.getCardByType(currencyType);
    }

    public LiveData<List<CurrencyCard>> getSelectedCard() {
        return mSelectedCard;
    }

    public LiveData<List<CurrencyCardSubset>> getUnselectedCard() {
        return mUnselectedCard;
    }

    public static MutableLiveData<Boolean> getRemoteDataFetchingStatus() {
        return mRemoteDataFetchingStatus;
    }

    public void updateRates() {
        new updateRatesFromRemoteAsyncTask(mDao, mRemote).execute();
    }

    public void updateSelectedStatus(String currencyType, boolean selectedStatus) {
        new updateSelectedStatusAsyncTask(mDao, selectedStatus).execute(currencyType);
    }

    private static class updateRatesFromRemoteAsyncTask extends AsyncTask<Void, Void, Void> implements CurrencyRemote.OnDataLoaded{
        private CurrencyCardDao mAsyncTaskDao;
        private CurrencyRemote mRemote;
        private List<CurrencyCard> cards;

        updateRatesFromRemoteAsyncTask(CurrencyCardDao dao, CurrencyRemote remote) {
            mAsyncTaskDao = dao;
            mRemote = remote;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cards = mAsyncTaskDao.getAllCards();
            mRemote.setDataLoaded(this);
            mRemote.fetchData();

            return null;
        }

        @Override
        public void onDataLoadedSuccess(ItemResponse itemResponse) {
            if (itemResponse != null && cards != null) {
                for (CurrencyCard card : cards) {
                    card.btcRate = itemResponse.getBTC(card.currencyType);
                    card.ethRate = itemResponse.getETH(card.currencyType);

                    new updateRatesAsyncTask(mAsyncTaskDao).execute(card);
                }
            }
        }

        @Override
        public void onDataLoadedFailure(boolean status) {
            mRemoteDataFetchingStatus.postValue(status);
        }
    }

    private static class updateRatesAsyncTask extends AsyncTask<CurrencyCard, Void, Void> {
        private CurrencyCardDao mAsyncTaskDao;

        updateRatesAsyncTask(CurrencyCardDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(CurrencyCard... cards) {
            CurrencyCard card = cards[0];
            mAsyncTaskDao.updateRates(card.currencyType, card.btcRate, card.ethRate);
            mRemoteDataFetchingStatus.postValue(true);
            return null;
        }
    }

    private static class updateSelectedStatusAsyncTask extends AsyncTask<String, Void, Void> {
        private CurrencyCardDao mAsyncTaskDao;
        private boolean mSelectedStatus;

        updateSelectedStatusAsyncTask(CurrencyCardDao dao, boolean selectedStatus) {
            mAsyncTaskDao = dao;
            mSelectedStatus = selectedStatus;
        }

        @Override
        protected Void doInBackground(String... currencyTypes) {
            String currencyType = currencyTypes[0];
            mAsyncTaskDao.updateSelectedStatus(currencyType, mSelectedStatus);
            return null;
        }
    }
}
