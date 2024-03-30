package com.ifs21048.duplicateapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chat (
    var contactName : String,
    var contactProfile : Int,
    var contactChat: String,
) : Parcelable