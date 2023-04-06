package com.hakemy.marketsamer.ui.profile.notification.model

import com.squareup.moshi.Json

data class NotificationsResponse(

    @Json(name = "first_page_url") val firstPageUrl: String? = null,

    @Json(name = "path") val path: String? = null,

    @Json(name = "per_page") val perPage: Int? = null,

    @Json(name = "data") val data: MutableList<NotificationItem>? = null,

    @Json(name = "next_page_url") val nextPageUrl: String? = null,

    @Json(name = "from") val from: Int? = null,

    @Json(name = "to") val to: Int? = null,

    @Json(name = "prev_page_url") val prevPageUrl: String? = null,

    @Json(name = "current_page") val currentPage: Int? = null
)
data class NotificationItem(

    @Json(name = "date") val date: String? = null,

    @Json(name = "image") val image: String? = null,

    @Json(name = "id") val id: Int? = null,

    @Json(name = "time") val time: String? = null,

    @Json(name = "title") val title: String? = null,

    @Json(name = "message") val message: String? = null
)