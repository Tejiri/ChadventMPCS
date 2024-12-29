package com.xita.chadventmpcs.dataSource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xita.chadventmpcs.dataSource.local.daos.AccountDao
import com.xita.chadventmpcs.dataSource.local.daos.MemberDao
import com.xita.chadventmpcs.dataSource.local.daos.TransactionDao
import com.xita.chadventmpcs.models.entities.AccountEntity
import com.xita.chadventmpcs.models.entities.MemberEntity
import com.xita.chadventmpcs.models.entities.TransactionEntity

@Database(
    entities = [MemberEntity::class, TransactionEntity::class, AccountEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao
    abstract fun transactionDao(): TransactionDao
    abstract fun accountDao(): AccountDao

}