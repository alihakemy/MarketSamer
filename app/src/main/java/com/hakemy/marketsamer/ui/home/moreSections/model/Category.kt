package com.hakemy.marketsamer.ui.home.moreSections.model

data class Category(
    val id: Int,
    val imagePath: String,
    val name: String,
    val parent_id: String,
    val sub_cat: List<Any>
)