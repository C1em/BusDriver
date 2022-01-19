package com.yavin.busdriver.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "transaction")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val amount: Int
)