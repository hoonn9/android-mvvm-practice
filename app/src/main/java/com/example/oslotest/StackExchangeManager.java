package com.example.oslotest;

import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class StackExchangeManager {
    private final StackExchangeService mStackExchangeService;

    public StackExchangeManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mStackExchangeService = retrofit.create(StackExchangeService.class);
    }

    public Flowable<List<User>> getMostPopularSQysers(int howmany){
        return mStackExchangeService
                .getMostPopularSOusers(howmany)
                .map(new Function<UserResponse, List<User>>() {
                    @Override
                    public List<User> apply(@NonNull UserResponse usersResponse) throws Exception {
                        Log.d("debug","map" + usersResponse.getUsers());
                        Log.d("debug","map" + usersResponse.toString());
                        return usersResponse.getUsers();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

