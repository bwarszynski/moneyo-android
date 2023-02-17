package com.loevenhaupt.moneyo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int, //automatyczne generowanie ID
    val label: String,
    val amount: Double,
    val description: String): Serializable {
}