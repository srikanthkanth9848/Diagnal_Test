package com.example.diagntest.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseContPage(@field:SerializedName("page") val page: Page? = null) : Parcelable {
    //nothing here...
}

@Parcelize
data class Page(

    @field:SerializedName("page-num") val pageNum: String? = null,

    @field:SerializedName("page-size") val pageSize: String? = null,

    @field:SerializedName("content-items") val contentItems: ContentItems? = null,

    @field:SerializedName("total-content-items") val totalContentItems: String? = null,

    @field:SerializedName("title") val title: String? = null
) : Parcelable {
    //nothing here...
}

@Parcelize
data class ContentItems(

    @field:SerializedName("content") val content: List<ContentItem?>? = null
) : Parcelable {
    //nothing here...
}

@Parcelize
data class ContentItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("poster-image")
    val posterImage: String? = null,

    var selectedTextColor: Int? = null
) : Parcelable {
    //nothing here...
}
