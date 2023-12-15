package com.example.l3.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class WishlistResponse(
    val success: Boolean,
    val id: String,
    val contents: List<WishlistItem>
)

@Parcelize
data class WishlistItem(
    val id: String,
    val price: Int,
    val title: String,
    val rating: Int,
    val date: String,
    val nights: Int,
    val stars: Int
) : Parcelable