package com.localhost.taxiapp.presentation.feature.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.localhost.taxiapp.R
import com.localhost.taxiapp.TaxiApplication
import com.localhost.taxiapp.data.ride.Ride
import kotlinx.android.synthetic.main.fragment_rides_list.*
import javax.inject.Inject
import androidx.recyclerview.widget.DividerItemDecoration

class HistoryFragment : MvpAppCompatFragment(), HistoryView, SwipeRefreshLayout.OnRefreshListener {
    private var adapter: StoryAdapter =
            StoryAdapter()

    @Inject
    @InjectPresenter
    lateinit var presenter: HistoryPresenter

    @ProvidePresenter
    fun initPresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        TaxiApplication.rideComponent?.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        activity?.title = getString(R.string.history)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_history, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_rides_list.adapter = adapter
        rv_rides_list
            .addItemDecoration(DividerItemDecoration(rv_rides_list.getContext(), DividerItemDecoration.VERTICAL))
        swipe_refresh.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        swipe_refresh.isRefreshing = true
        presenter.refresh()
    }

    override fun setList(list: List<Ride>) {
        swipe_refresh.isRefreshing = false
        adapter.rides = list
        adapter.notifyDataSetChanged()
    }
}
