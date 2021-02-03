package com.codeartist.trivagochallenge.detail.data.remoteentity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SpeciesEntity( @SerializedName("name")
                          @Expose
                          var name: String,
                          @SerializedName("language")
                          @Expose
                          var language: String)
