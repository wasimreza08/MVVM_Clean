package com.codeartist.trivagochallenge.detail.presentation.view.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeartist.trivagochallenge.R
import com.codeartist.trivagochallenge.common.Constants
import com.codeartist.trivagochallenge.databinding.ActivityDetailBinding
import com.codeartist.trivagochallenge.detail.presentation.view.adapter.FilmAdapter
import com.codeartist.trivagochallenge.detail.presentation.view.adapter.HeaderAdapter
import com.codeartist.trivagochallenge.detail.presentation.view.adapter.SpeciesAdapter
import com.codeartist.trivagochallenge.detail.presentation.viewmodel.DetailViewModel
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    @Inject
    lateinit var filmAdapter: FilmAdapter

    @Inject
    lateinit var filmHeaderAdapter: HeaderAdapter

    @Inject
    lateinit var speciesAdapter: SpeciesAdapter

    @Inject
    lateinit var speciesHeaderAdapter: HeaderAdapter
    val viewModel: DetailViewModel by viewModels()
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        val info = intent.getParcelableExtra<CharacterModel>(Constants.EXTRA_DATA)
        binding.character = info
        binding.lifecycleOwner = this
        info?.let { viewModel.collectionCharacterDetail(info) }
        Log.e("species", info?.species.toString())
        viewModel.population.observe(this) {
            it?.let {
                binding.populationInfo = it
            }
        }
        viewModel.filmList.observe(this) {
            filmAdapter.setFilms(it)
        }
        viewModel.speciseList.observe(this) {
            speciesAdapter.setSpeciesInfo(it)
        }
        binding.detailList.setHasFixedSize(true)
        binding.detailList.layoutManager = LinearLayoutManager(this)
        filmHeaderAdapter.setHeader("Films")
        speciesHeaderAdapter.setHeader("Species")
        val adapter =
            ConcatAdapter(speciesHeaderAdapter, speciesAdapter, filmHeaderAdapter, filmAdapter)
        binding.detailList.adapter = adapter
       //println("instances of adapter " + filmHeaderAdapter + "  " + speciesHeaderAdapter)
    }
}