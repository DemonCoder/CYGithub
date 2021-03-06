package com.github.model.http

import client.yalantis.com.githubclient.model.Repository
import client.yalantis.com.githubclient.model.RepositoryDetail
import client.yalantis.com.githubclient.model.TDog
import com.github.model.bean.TDogResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by cuiyue on 2017/6/21.
 */
interface ApiService {

    @GET(ApiSettings.ORGANIZATION_REPOS)
    fun getOrganizationRepos(@Path(ApiSettings.PATH_ORGANIZATION) organizationName: String,
                             @Query(ApiSettings.PARAM_REPOS_TYPE) reposType: String, @Query(ApiSettings.PAGE) page: String, @Query(ApiSettings.PER_PAGE) per_page: String): Flowable<MutableList<Repository>>

    @GET(ApiSettings.REPOSITORY)
    fun getRepository(@Path(ApiSettings.PATH_OWNER) owner: String,
                      @Path(ApiSettings.PATH_REPO) name: String): Flowable<RepositoryDetail>


    @GET("top/classify")
    fun getTDog(): Flowable<TDogResponse<MutableList<TDog>>>
}