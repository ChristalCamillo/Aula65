package br.com.zup.loginfirebase.data.datasource.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Text(@SerializedName("file")
val text: String = ""): Parcelable
