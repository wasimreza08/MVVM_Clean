package com.codeartist.trivagochallenge.search.presentation.view.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeartist.trivagochallenge.R
import com.codeartist.trivagochallenge.common.Status
import com.codeartist.trivagochallenge.databinding.ActivitySearchBinding
import com.codeartist.trivagochallenge.search.presentation.view.adapter.OnItemClickListener
import com.codeartist.trivagochallenge.search.presentation.view.adapter.SearchListAdapter
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharaterModel
import com.codeartist.trivagochallenge.search.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), OnItemClickListener {
    @Inject
    lateinit var mAdapter: SearchListAdapter
    val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.editTextTextPersonName.doAfterTextChanged {
            val searchString = binding.editTextTextPersonName.text.toString()
            viewModel.setSearchString(searchString)
            setCrossVisibility(searchString)

        }
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.addClickListener(this)
        binding.recyclerView.adapter = mAdapter
        viewModel.searchResult.observe(this) {
            if (it.status == Status.LOADING) {
                binding.progressVisibility = true
            } else if (it.status == Status.SUCCESS) {
                it.data?.let {
                    mAdapter.submitList(it)
                    binding.progressVisibility = false
                }
            } else {

            }

        }
    }

    fun resetSearchText(view: View) {
        binding.editTextTextPersonName.setText("")
        setCrossVisibility("")
    }

    private fun setCrossVisibility(searchString: String) {
        binding.crossVisibility = searchString.isNotEmpty()
    }

    override fun onItemClicked(view: View, item: CharaterModel) {

    }
}