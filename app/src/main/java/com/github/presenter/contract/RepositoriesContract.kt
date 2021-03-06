package com.github.presenter.contract

import client.yalantis.com.githubclient.model.Repository
import client.yalantis.com.githubclient.model.TDog
import com.github.base.BasePresenter
import com.github.base.BaseView

/**
 * Created by cuiyue on 2017/6/21.
 */
object RepositoriesContract {

    interface View : BaseView {
        fun showRepositories(repositories: MutableList<Repository>)
        fun showRefreshRepositories(repositories: MutableList<Repository>)
        fun showMoreRepositories(repositories: MutableList<Repository>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadRepositories()
        fun loadRefreshRepositories()
        fun loadMoreRepositories()
    }
}