package com.xita.chadventmpcs.dataSource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xita.chadventmpcs.models.entities.MemberEntity

@Database(entities = [MemberEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase:RoomDatabase() {
    abstract fun memberDao():MemberDao

}