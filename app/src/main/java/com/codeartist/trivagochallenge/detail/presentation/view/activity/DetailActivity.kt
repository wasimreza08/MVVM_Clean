package com.codeartist.trivagochallenge.detail.presentation.view.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeartist.trivagochallenge.R
import com.codeartist.trivagochallenge.common.activity.BaseActivity
import com.codeartist.trivagochallenge.common.utils.Constants
import com.codeartist.trivagochallenge.common.utils.Status
import com.codeartist.trivagochallenge.databinding.ActivityDetailBinding
import com.codeartist.trivagochallenge.detail.presentation.uimodel.FilmModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.HomeWorldModel
import com.codeartist.trivagochallenge.detail.presentation.uimodel.SpeciesModel
import com.codeartist.trivagochallenge.detail.presentation.view.adapter.*
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
    lateinit var homeWorldHeaderAdapter: SingleLineAdapter

    @Inject
    lateinit var homeWorldAdapter: HomeWorldAdapter

    lateinit var detailAdapter: ConcatAdapter
    val viewModel: DetailViewModel by viewModels()
    private val TAG = "DetailActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG + "progressbar", "onCreate called")
        val info = intent.getParcelableExtra<CharacterModel>(Constants.EXTRA_DATA)
        info?.let {
            dataBinding.character = it
            dataBinding.toolbar.title = it.name
            viewModel.setCharacterInfo(it)
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

        characterInfoAdapter.add(info)
        detailAdapter =
            ConcatAdapter(
                characterInfoAdapter
            )
        dataBinding.includeLayout.detailList.adapter = detailAdapter
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

        viewModel.detailInfo.observe(this) {
            it?.let {
                if (it.status == Status.LOADING) {
                    dataBinding.progressVisibility = true
                } else if (it.status == Status.SUCCESS) {
                    it.data?.let {
                        setHomeWorldAdapter(it.homeWorldModel)
                        setSpeciesAdapter(it.speciesList)
                        setFilmAdapter(it.filmList)
                        dataBinding.includeLayout.detailList.adapter = detailAdapter
                        dataBinding.progressVisibility = false
                    }
                } else {
                    dataBinding.progressVisibility = false
                    showAlertDialog(this)
                }
            }
        }
        viewModel.isError.observe(this) {
            it?.let {
                // Log.e(TAG+"error:", it.toString())
                if (it) showAlertDialog(this)
            }
        }
    }

    private fun setFilmAdapter(list: MutableList<FilmModel>) {
        if (list.isNotEmpty()) {
            filmHeaderAdapter.add(getString(R.string.films))
            filmAdapter.addAll(list)
            detailAdapter.addAdapter(filmHeaderAdapter)
            detailAdapter.addAdapter(filmAdapter)
            Log.e(DetailActivity::class.java.simpleName, "setFilmAdapter added")
        }

    }

    private fun setSpeciesAdapter(list: MutableList<SpeciesModel>) {
        if (list.isNotEmpty()) {
            speciesHeaderAdapter.add(getString(R.string.species))
            speciesAdapter.addAll(list)
            detailAdapter.addAdapter(speciesHeaderAdapter)
            detailAdapter.addAdapter(speciesAdapter)
            Log.e(DetailActivity::class.java.simpleName, "setSpeciesAdapter added")
        }
    }

    private fun setHomeWorldAdapter(planet: HomeWorldModel) {
        if (planet.name.isNotEmpty() && planet.population.isNotEmpty()) {
            homeWorldHeaderAdapter.add(getString(R.string.home_world))
            homeWorldAdapter.add(planet)
            detailAdapter.addAdapter(homeWorldHeaderAdapter)
            detailAdapter.addAdapter(homeWorldAdapter)
            Log.e(DetailActivity::class.java.simpleName, "setHomeWorldAdapter added")
        }
    }
}