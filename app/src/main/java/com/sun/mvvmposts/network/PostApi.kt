package com.sun.mvvmposts.network

import com.sun.mvvmposts.model.Post
import io.reactivex.Single
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/posts")
    fun getPosts(): Single<List<Post>>
}