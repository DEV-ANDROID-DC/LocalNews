package com.debin.localnews.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.doOnAttach
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.debin.localnews.R
import com.debin.localnews.model.Category
import com.debin.localnews.ui.adapter.NewsAdapter
import com.debin.localnews.util.CustomItemTouchHelper
import com.debin.localnews.viewmodel.NewsDataViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_news_list.*
import kotlinx.android.synthetic.main.activity_news_list.recyclerView
import kotlinx.android.synthetic.main.activity_news_list.view.*

class NewsListActivity : AppCompatActivity(),  BottomNavigationView.OnNavigationItemSelectedListener{

    private lateinit var newsDataViewModel : NewsDataViewModel
    private lateinit var customItemTouchHelper: CustomItemTouchHelper
    private var newsAdapter: NewsAdapter = NewsAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        newsDataViewModel = ViewModelProvider(this).get(NewsDataViewModel::class.java)
        bottomNavigation.setOnNavigationItemSelectedListener(this)

        recyclerView.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
            customItemTouchHelper = CustomItemTouchHelper(newsAdapter)
            val touchHelper = ItemTouchHelper(customItemTouchHelper)
            touchHelper.attachToRecyclerView(recyclerView)
        }


        populateItemList(Category.TOP_NEWS)





    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_topNews -> populateItemList(Category.TOP_NEWS)
            R.id.nav_cityNews -> populateItemList(Category.CITY_NEWS)
            R.id.nav_countyNews -> populateItemList(Category.COUNTY_NEWS)
            else -> return false
        }
        return true
    }

    private fun populateItemList(category: Category) {
         when (category) {
            Category.TOP_NEWS -> {
                newsDataViewModel.topNews.observe(this, Observer {
                    newsAdapter.updateItems(it)
                    newsAdapter.notifyDataSetChanged()
                })
            }
            Category.CITY_NEWS -> {
                newsDataViewModel.cityNews.observe(this, Observer {
                    newsAdapter.updateItems(it)
                    newsAdapter.notifyDataSetChanged()
                })
            }
            Category.COUNTY_NEWS -> {
                newsDataViewModel.countyNews.observe(this, Observer {
                    newsAdapter.updateItems(it)
                    newsAdapter.notifyDataSetChanged()
                })
            }
        }
    }

    fun onClickAddFab(view: View) {
        val intent = Intent(this, AddNewsActivity::class.java)
        val activityOptions = ActivityOptionsCompat
            .makeSceneTransitionAnimation(this@NewsListActivity)
        startActivity(intent, activityOptions.toBundle())
    }


}