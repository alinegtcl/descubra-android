package com.ifsp.descubra

import com.google.gson.annotations.SerializedName

data class WikipediaResponse(
    @SerializedName("type")
    val type: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("displaytitle")
    val displayTitle: String? = null,

    @SerializedName("namespace")
    val namespace: Namespace? = null,

    @SerializedName("wikibase_item")
    val wikibaseItem: String? = null,

    @SerializedName("titles")
    val titles: Titles? = null,

    @SerializedName("pageid")
    val pageId: Long? = null,

    @SerializedName("thumbnail")
    val thumbnail: ImageInfo? = null,

    @SerializedName("originalimage")
    val originalImage: ImageInfo? = null,

    @SerializedName("lang")
    val lang: String? = null,

    @SerializedName("dir")
    val dir: String? = null,

    @SerializedName("revision")
    val revision: String? = null,

    @SerializedName("tid")
    val tid: String? = null,

    @SerializedName("timestamp")
    val timestamp: String? = null,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("description_source")
    val descriptionSource: String? = null,

    @SerializedName("coordinates")
    val coordinates: Coordinates? = null,

    @SerializedName("content_urls")
    val contentUrls: ContentUrls? = null,

    @SerializedName("extract")
    val extract: String? = null,

    @SerializedName("extract_html")
    val extractHtml: String? = null
)

data class Namespace(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("text")
    val text: String? = null
)

data class Titles(
    @SerializedName("canonical")
    val canonical: String? = null,

    @SerializedName("normalized")
    val normalized: String? = null,

    @SerializedName("display")
    val display: String? = null
)

data class ImageInfo(
    @SerializedName("source")
    val source: String? = null,

    @SerializedName("width")
    val width: Int? = null,

    @SerializedName("height")
    val height: Int? = null
)

data class Coordinates(
    @SerializedName("lat")
    val lat: Double? = null,

    @SerializedName("lon")
    val lon: Double? = null
)

data class ContentUrls(
    @SerializedName("desktop")
    val desktop: UrlGroup? = null,

    @SerializedName("mobile")
    val mobile: UrlGroup? = null
)

data class UrlGroup(
    @SerializedName("page")
    val page: String? = null,

    @SerializedName("revisions")
    val revisions: String? = null,

    @SerializedName("edit")
    val edit: String? = null,

    @SerializedName("talk")
    val talk: String? = null
)
