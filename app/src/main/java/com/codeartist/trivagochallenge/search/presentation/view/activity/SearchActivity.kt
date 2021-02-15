package com.codeartist.trivagochallenge.search.presentation.view.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeartist.trivagochallenge.R
import com.codeartist.trivagochallenge.common.base.activity.BaseActivity
import com.codeartist.trivagochallenge.common.utils.Constants
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.databinding.ActivitySearchBinding
import com.codeartist.trivagochallenge.detail.presentation.view.activity.DetailActivity
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import com.codeartist.trivagochallenge.search.presentation.view.adapter.OnItemClickListener
import com.codeartist.trivagochallenge.search.presentation.view.adapter.SearchListAdapter
import com.codeartist.trivagochallenge.search.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchActivity(override val layoutResourceId: Int = R.layout.activity_search) :
    BaseActivity<ActivitySearchBinding>(), OnItemClickListener {
    @Inject
    lateinit var searchListAdapter: SearchListAdapter
    val viewModel: SearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.editTextCharacterName.doAfterTextChanged {
            val searchString = dataBinding.editTextCharacterName.text.toString()
            viewModel.setSearchString(searchString)
            setCrossVisibility(searchString)
        }
        dataBinding.recyclerView.setHasFixedSize(true)
        dataBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        searchListAdapter.addClickListener(this)
        dataBinding.recyclerView.adapter = searchListAdapter
        viewModel.searchResult.observe(this) {
            if (it.status == Status.LOADING) {
                dataBinding.progressVisibility = true
            } else if (it.status == Status.SUCCESS) {
                it.data?.let {
                    searchListAdapter.submitList(it)
                    dataBinding.progressVisibility = false
                }
            } else {
                dataBinding.progressVisibility = false
                showAlertDialog(this)
            }

        }
    }

    fun resetSearchText(view: View) {
        dataBinding.editTextCharacterName.setText(Constants.EMPTY_STRING)
        setCrossVisibility(Constants.EMPTY_STRING)
    }

    private fun setCrossVisibility(searchString: String) {
        dataBinding.crossVisibility = searchString.isNotEmpty()
    }

    override fun onItemClicked(view: View, item: CharacterModel) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(Constants.EXTRA_DATA, item)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, )
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                view, getString(R.string.transition_text)
            )
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }


    }
}