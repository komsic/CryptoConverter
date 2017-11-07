package com.example.komsic.cryptoconverter.service;

import com.example.komsic.cryptoconverter.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * This is where the end point is declared.
 */

public interface RestApiService {
    @GET("https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=ETH,USD,CAD,EUR,GBP,CNY,CHF,AUD,JPY,SEK,MXN,NZD,SGD,HKD,NOK,TRY,RUB,ZAR,BRL,MYR,NGN")
    Call<ItemResponse> getItemResponse();
}