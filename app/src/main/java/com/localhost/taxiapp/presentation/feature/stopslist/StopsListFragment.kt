package com.localhost.taxiapp.presentation.feature.stopslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.localhost.taxiapp.R
import com.localhost.taxiapp.TaxiApplication
import com.localhost.taxiapp.data.stop.Stop
import kotlinx.android.synthetic.main.fragment_stops_list.*
import javax.inject.Inject

class StopsListFragment : MvpAppCompatFragment(),StopsView {
    private var adapter: StopsAdapter =
        StopsAdapter()

    @Inject
    @InjectPresenter
    lateinit var presenter: StopsListPresenter

    @ProvidePresenter
    fun initPresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        TaxiApplication.appComponent.stopsComponent().build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        activity?.title = getString(R.string.stops_list)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_stops_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_stops_list.adapter = adapter
    }

    override fun setList(list: List<Stop>) {
        adapter.rides = list
        adapter.notifyDataSetChanged()
    }
}
