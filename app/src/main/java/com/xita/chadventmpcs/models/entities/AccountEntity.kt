package com.xita.chadventmpcs.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class AccountEntity(
    @PrimaryKey(autoGenerate = false)
    val _id: String,
    val username: String,
    val sharecapital: String,
    val thriftsavings: String,
    val specialdeposit: String,
    val commoditytrading: String,
    val fine: String,
    val loan: String,
    val projectfinancing: String,
)

