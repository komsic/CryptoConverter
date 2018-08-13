package com.example.komsic.cryptoconverter.di.application;

import com.example.komsic.cryptoconverter.data.service.RestApiService;
import com.example.komsic.cryptoconverter.di.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @ApplicationScope
    public RestApiService restApiService(Retrofit retrofit) {
        return retrofit.create(RestApiService.class);
    }

    @Provides
    @ApplicationScope
    public Retrofit retrofit(String baseUrl, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    @ApplicationScope
    public GsonConverterFactory gsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @ApplicationScope
    public String baseUrl() {
        return "https://min-api.cryptocompare.com";
    }
}
