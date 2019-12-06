package com.example.oslotest;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StackExchangeService {
    @GET("/2.2/users?order=desc&sort=reputation&site=stackoverflow")
    Flowable<UserResponse> getMostPopularSOusers(@Query("pagesize") int howmany);
}
