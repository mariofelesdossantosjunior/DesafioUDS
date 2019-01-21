package com.mario.desafiouds.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "schedule")
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "detail")
    val detail: String,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "status")
    var status: StatusSchedule,

    @ColumnInfo(name = "expanded")
    var expanded: Boolean = false
)