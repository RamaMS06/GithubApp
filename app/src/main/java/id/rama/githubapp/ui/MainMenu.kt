package id.rama.githubapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.rama.githubapp.R
import id.rama.githubapp.adapter.AdapterUsers
import id.rama.githubapp.data.SearchData
import id.rama.githubapp.data.SearchViewModel
import id.rama.githubapp.factory.FactoryUsers
import id.rama.githubapp.model.ModelUsers
import id.rama.githubapp.repository.RepoUsers
import id.rama.githubapp.utils.Utilities
import id.rama.githubapp.viewmodel.ViewModelUsers
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenu : AppCompatActivity(), AdapterUsers.UsersClickListener {
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var viewModelUsers: ViewModelUsers
    private var page = 1
    private var per_page = 10
    private var isLoading = false

    private val adapterUsers by lazy { AdapterUsers(this, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        setupRecyclerView()
        getDataUsers()
        actionSearch()
        onScrollUser()

    }

    private fun setupRecyclerView() {
        rv_list_users_github.apply {
            adapter = adapterUsers
            layoutManager = LinearLayoutManager(this@MainMenu)
        }

    }

    private fun getDataUsers() {
        isLoading = true
        val repo = RepoUsers()
        val factoryUsers = FactoryUsers(repo)
        viewModelUsers =
            ViewModelProvider(this, factoryUsers).get(ViewModelUsers::class.java)
        viewModelUsers.searchUsers(Utilities.DEFAULT_SEARCH, per_page, page)
        viewModelUsers.searchResponseUsers.observe(this, { response ->
            if (response.isSuccessful) {
                response.body()?.let { adapterUsers.setData(it.items) }
                isLoading = false
            }
        })

    }

    private fun searchUser() {
        val search = edt_search_users.text.toString()
        if (search.isEmpty()) {
            getDataUsers()
        } else {
            val repo = RepoUsers()
            val factoryUsers = FactoryUsers(repo)
            viewModelUsers =
                ViewModelProvider(
                    this,
                    factoryUsers
                ).get(ViewModelUsers::class.java)
            viewModelUsers.searchUsers(search, per_page, page)
            viewModelUsers.searchResponseUsers.observe(this, { response ->
                if (response.isSuccessful) {
                    response.body()?.let { adapterUsers.setData(it.items) }
                }
            })
        }
    }

    private fun onScrollUser() {
        val layoutManager = LinearLayoutManager(this)
        rv_list_users_github.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstVisibleItemPosition()
                val total = adapterUsers.itemCount
                if (!isLoading && page < per_page) {
                    if (visibleItemCount + pastVisibleItem >= total) {
                        page++
                        getDataUsers()
                    }
                }
            }

        })
    }


    private fun actionSearch() {
        btn_search.setOnClickListener {
            searchUser()
            addHistorySearch()
        }

        //Masih terdapat error (sebagai pengganti menggunakan list querySearch)
        val querySearch = listOf("Rama", "Hayu", "test")
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        searchViewModel.readAllData.observe(this, { _ ->
            val adapter =
                ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    querySearch
                )
            edt_search_users.setAdapter(adapter)
        })

        edt_search_users.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchUser()
                addHistorySearch()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

    }

    private fun addHistorySearch() {
        val searchQuery = edt_search_users.text.toString()
        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val search = SearchData(0, searchQuery)
        searchViewModel.addSearchQuery(search)

    }

    override fun onItemClickListener(item: ModelUsers, position: Int) {
        viewModelUsers.searchResponseUsers.observe(this, { response ->
            if (response.isSuccessful) {
                val intent = Intent(this, DetailUsers::class.java)
                intent.putExtra(
                    Utilities.USERNAME_GITHUB,
                    response.body()?.items?.get(position)?.login
                )
                startActivity(intent)
            }
        })
    }

}