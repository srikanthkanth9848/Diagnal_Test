package com.example.diagntest

import android.annotation.SuppressLint
import android.content.res.Configuration.*
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diagntest.adapter.RecyclerViewAdapter
import com.example.diagntest.databinding.ActivityMainBinding
import com.example.diagntest.models.ContentItem
import com.example.diagntest.models.ResponseContPage
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.util.*


@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RecyclerViewAdapter.ClickListener {
    private lateinit var recyclerView: RecyclerView

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    private lateinit var activityMainBinding: ActivityMainBinding

    private lateinit var mainList: MutableList<ContentItem>

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar: ActionBar? = supportActionBar
        // showing the back button in action bar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeButtonEnabled(true)
        supportActionBar?.elevation = 10f;
        // Customize the back button
       // actionBar?.setHomeAsUpIndicator(R.drawable.back_arrow_button)

        val responseContent = loadJSONFromAssetContPageOne()
        actionBar?.title = responseContent.page?.title

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //main list to add all sublist data...
        mainList = mutableListOf()

        //first list object response...
        val contentItemList1: MutableList<ContentItem> =
            loadJSONFromAssetContPageOne().page?.contentItems?.content as MutableList<ContentItem>
        Log.e("MainActivity", "subList_1 $contentItemList1")

        //second list object response...
        val contentItemList2: MutableList<ContentItem> =
            loadJSONFromAssetContPageTwo().page?.contentItems?.content as MutableList<ContentItem>
        Log.e("MainActivity", "subList_2 $contentItemList2")

        //third list object response...
        val contentItemList3: MutableList<ContentItem> =
            loadJSONFromAssetContPageThree().page?.contentItems?.content as MutableList<ContentItem>
        Log.e("MainActivity", "subList_3 $contentItemList3")

        mainList.addAll(contentItemList1)
        mainList.addAll(contentItemList2)
        mainList.addAll(contentItemList3)
        Log.e("MainActivity", "mainList ${Gson().toJson(mainList)}")

        //recyclerview reference
        recyclerView = activityMainBinding.recyclerview

        activityMainBinding.shadowView.visibility = View.VISIBLE
        recyclerView.setOnScrollChangeListener(object :RecyclerView.OnScrollListener(),
            View.OnScrollChangeListener {
            override fun onScrollChange(p0: View?, p1: Int, p2: Int, p3: Int, p4: Int) {
                Log.e("MainActivity1", "onScrollChange...")
                activityMainBinding.shadowView.visibility = View.GONE
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.e("MainActivity1", "onScrolled...")
                activityMainBinding.shadowView.visibility = View.VISIBLE
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                Log.e("MainActivity1", "onScrollStateChanged...")
                activityMainBinding.shadowView.visibility = View.GONE
            }
        })

        //as per orientation screen is showing items depending on span count...
        if (resources.configuration.orientation == ORIENTATION_PORTRAIT) {
            recyclerView.layoutManager = GridLayoutManager(this, 3)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 5)
        }

        //initialized recyclerview adapter..
        recyclerViewAdapter = RecyclerViewAdapter(this)

        //adding mainList data to adapter
        recyclerViewAdapter.setData(mainList)
        recyclerView.adapter = recyclerViewAdapter
    }

    // calling on create option menu, layout to inflate our menu file.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // inside inflater we are inflating our menu file.
        menuInflater.inflate(R.menu.menu, menu)

        // below line is to get our menu item.
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        // getting search view of our item.
        val searchView: SearchView = searchItem.actionView as SearchView

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                // inside on query text change method we are calling a method to filter our recycler view.
                filterData(msg)
                return false
            }
        })
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filterData(text: String) {
        // creating a new array list to filter our data.
        val filteredList = ArrayList<ContentItem>()

        // running a for loop to compare elements.
        for (item in mainList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.name?.lowercase(Locale.ROOT)?.contains(text.lowercase(Locale.ROOT)) == true) {
                // if the item is matched we are adding it to our filtered list.
                filteredList.add(item)
                activityMainBinding.noDataFound.visibility = View.GONE
            } else if (filteredList.isEmpty()) {
                // if no item is added in filtered list we are displaying message as no data found.
                activityMainBinding.noDataFound.visibility = View.VISIBLE
            }
            // at last we are passing that filtered list to our adapter class.
            recyclerViewAdapter.filterList(filteredList)
            recyclerViewAdapter.notifyDataSetChanged()
        }
    }

    private fun loadJSONFromAssetContPageOne(): ResponseContPage {
        lateinit var jsonString: String
        try {
            jsonString =
                assets.open("CONTENTLISTINGPAGE-PAGE1.json").bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        //val listCountryType = object : TypeToken<List<ResponseContPageOne>>() {}.type
        //here Gson() is basically providing fromJson methods which actually //deserializing json. It basically parse json string to //list<Country> object
        // this loadJSONFromAssetContPageOne(ctx: Context) will return a list of Country data class.
        return Gson().fromJson(jsonString, ResponseContPage::class.java)
    }

    private fun loadJSONFromAssetContPageTwo(): ResponseContPage {

        lateinit var jsonString: String
        try {
            jsonString =
                assets.open("CONTENTLISTINGPAGE-PAGE2.json").bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        //val listCountryType = object : TypeToken<List<ResponseContPageOne>>() {}.type
        //here Gson() is basically providing fromJson methods which actually //deserializing json. It basically parse json string to //list<Country> object
        // this loadJSONFromAssetContPageOne(ctx: Context) will return a list of Country data class.
        return Gson().fromJson(jsonString, ResponseContPage::class.java)
    }


    private fun loadJSONFromAssetContPageThree(): ResponseContPage {

        lateinit var jsonString: String
        try {
            jsonString =
                assets.open("CONTENTLISTINGPAGE-PAGE3.json").bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        //val listCountryType = object : TypeToken<List<ResponseContPageOne>>() {}.type
        //here Gson() is basically providing fromJson methods which actually //deserializing json. It basically parse json string to //list<Country> object
        // this loadJSONFromAssetContPageOne(ctx: Context) will return a list of Country data class.
        return Gson().fromJson(jsonString, ResponseContPage::class.java)
    }

    //if you have any requirement which is belongs sending data to another activity
    //then we can use interface between adapter to activity
    override fun launchIntent(title: String?) {
        Toast.makeText(this@MainActivity, "clicked $title", Toast.LENGTH_SHORT).show()
    }
}