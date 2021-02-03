package com.codeartist.trivagochallenge.detail.presentation.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.codeartist.trivagochallenge.R
import com.codeartist.trivagochallenge.common.Constants
import com.codeartist.trivagochallenge.databinding.ActivityDetailBinding
import com.codeartist.trivagochallenge.detail.presentation.viewmodel.DetailViewModel
import com.codeartist.trivagochallenge.search.presentation.uimodel.CharacterModel

class DetailActivity : AppCompatActivity() {
    val viewModel: DetailViewModel by viewModels()
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        val info = intent.getParcelableExtra<CharacterModel>(Constants.EXTRA_DATA)
        binding.character = info
        binding.lifecycleOwner = this
        val film = info?.films?.isNotEmpty().let {
            info?.films?.get(0)
        }
        viewModel.getFilms(film)



    }
}