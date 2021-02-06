package com.codeartist.trivagochallenge.detail.presentation.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeartist.trivagochallenge.R
import com.codeartist.trivagochallenge.common.activity.BaseActivity
import com.codeartist.trivagochallenge.common.Constants
import com.codeartist.trivagochallenge.databinding.ActivityDetailBinding
import com.codeartist.trivagochallenge.detail.presentation.view.adapter.CharacterInfoAdapter
import com.codeartist.trivagochallenge.detail.presentation.view.adapter.FilmAdapter
import com.codeartist.trivagochallenge.detail.presentation.view.adapter.SingleLineAdapter
import com.codeartist.trivagochallenge.detail.presentation.view.adapter.SpeciesAdapter
import com.codeartist.trivagochallenge.detail.presentation.viewmodel.DetailViewModel
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DetailActivity(override val layoutResourceId: Int = R.layout.activity_detail) :
    BaseActivity<ActivityDetailBinding>() {
    @Inject
    lateinit var filmAdapter: FilmAdapter

    @Inject
    lateinit var filmHeaderAdapter: SingleLineAdapter

    @Inject
    lateinit var speciesAdapter: SpeciesAdapter

    @Inject
    lateinit var speciesHeaderAdapter: SingleLineAdapter

    @Inject
    lateinit var characterInfoAdapter: CharacterInfoAdapter

    @Inject
    lateinit var populationAdapter: SingleLineAdapter
    val viewModel: DetailViewModel by viewModels()
    private val TAG = "DetailActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val info = intent.getParcelableExtra<CharacterModel>(Constants.EXTRA_DATA)
        info?.let {
            dataBinding.character = it
            dataBinding.toolbar.title = it.name
            viewModel.collectionCharacterDetail(it)
            setRecyclerViewData(it)
        }
        setSupportActionBar(dataBinding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(true)
        }
        observingData()
    }

    private fun setRecyclerViewData(info: CharacterModel) {
        dataBinding.includeLayout.detailList.setHasFixedSize(true)
        dataBinding.includeLayout.detailList.layoutManager = LinearLayoutManager(this)
        speciesHeaderAdapter.setText(getString(R.string.species))
        filmHeaderAdapter.setText(getString(R.string.films))
        characterInfoAdapter.setInfo(info)
        val adapter =
            ConcatAdapter(
                characterInfoAdapter,
                populationAdapter,
                speciesHeaderAdapter,
                speciesAdapter,
                filmHeaderAdapter,
                filmAdapter
            )
        dataBinding.includeLayout.detailList.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observingData() {
        viewModel.population.observe(this) {
            it?.let {
                populationAdapter.setText(getString(R.string.population) + " " + it.population)
            }
        }
        viewModel.filmList.observe(this) {
            filmAdapter.setFilms(it)
        }
        viewModel.speciseList.observe(this) {
            speciesAdapter.setSpeciesInfo(it)
        }
        viewModel.isLoading.observe(this) {
            it?.let {
                dataBinding.progressVisibility = it
                // Log.e(TAG+"progressbar", it.toString())
            }

        }
        viewModel.isError.observe(this) {
            it?.let {
                // Log.e(TAG+"error:", it.toString())
                if (it) showAlertDialog(this)
            }
        }
    }
}