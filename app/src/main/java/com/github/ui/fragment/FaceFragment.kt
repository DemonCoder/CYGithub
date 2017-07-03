package com.github.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import client.yalantis.com.githubclient.model.Repository
import com.flying.xiaopo.poishuhui_kotlin.toast
import com.github.R
import com.github.base.BaseFragment
import com.github.log.LogUtil
import com.github.presenter.contract.RepositoriesContract
import com.github.presenter.impl.RepositoriesPresenter
import com.github.ui.activity.RepositoryDetailActivity
import com.github.ui.adapter.GitHubListAdapter
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlinx.android.synthetic.main.fragment_face.*
import java.util.*

/**
 * Created by cuiyue on 2017/6/26.
 */
class FaceFragment : BaseFragment<RepositoriesContract.View, RepositoriesPresenter>(), RepositoriesContract.View {
    override var mPresenter: RepositoriesPresenter = RepositoriesPresenter()

    private var mAdapter: GitHubListAdapter? = null
    private var mRepositorys: MutableList<Repository>? = null

    override fun getLayout(): Int = R.layout.fragment_face

    override fun initView() {
        initXRecyclerview()
    }

    override fun initData() {
        mPresenter.loadRepositories()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun showError() {
    }

    override fun showErrorMsg(msg: String) {
    }

    override fun showEmpty() {
    }

    /**
     * 初始化XRecyclerview
     */
    private fun initXRecyclerview() {
        mAdapter = GitHubListAdapter(ArrayList<Repository>(), {
            mContext?.toast(it.name)
            startActivity(RepositoryDetailActivity.newIntent(activity, it.html_url))
        })
        xrecyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        xrecyclerview.adapter = mAdapter
        xrecyclerview.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onRefresh() {
                mPresenter.loadRefreshRepositories()
            }

            override fun onLoadMore() {
                mPresenter.loadMoreRepositories()
            }
        })
    }

    /**
     * 加载数据回调
     */
    override fun showRepositories(repositories: MutableList<Repository>) {
        mRepositorys = repositories
        mAdapter?.setData(mRepositorys!!)
        mAdapter?.notifyDataSetChanged()
    }

    /**
     * 下拉刷新数据回调
     */
    override fun showRefreshRepositories(repositories: MutableList<Repository>) {
        mRepositorys?.clear()
        mRepositorys = repositories
        mAdapter?.setData(mRepositorys!!)
        mAdapter?.notifyDataSetChanged()
        xrecyclerview.refreshComplete()
    }

    /**
     * 上拉加载更多数据回调
     */
    override fun showMoreRepositories(repositories: MutableList<Repository>) {

        if (repositories.isEmpty()) {
            Toast.makeText(mContext, "亲,没有更多数据了", Toast.LENGTH_SHORT).show()
            xrecyclerview.loadMoreComplete()
            return
        }

        mRepositorys?.addAll(repositories)
        mAdapter?.setData(mRepositorys!!)
        mAdapter?.notifyDataSetChanged()
        xrecyclerview.loadMoreComplete()
    }

}