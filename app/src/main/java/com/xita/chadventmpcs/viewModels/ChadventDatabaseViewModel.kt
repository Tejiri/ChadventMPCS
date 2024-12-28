package com.xita.chadventmpcs.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xita.chadventmpcs.dataSource.local.MemberDao
import com.xita.chadventmpcs.models.Member
import com.xita.chadventmpcs.models.entities.MemberEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ChadventDatabaseViewModel(private val memberDao: MemberDao) : ViewModel() {

    val allMembers: Flow<List<MemberEntity>> = memberDao.getAllMembers()

    fun addMember(member: Member) {
        viewModelScope.launch {
            memberDao.insert(
                MemberEntity(
                    _id = member._id,
                    username = member.username,
                    title = member.title,
                    firstname = member.firstname,
                    lastname = member.lastname,
                    middlename = member.middlename,
                    position = member.position,
                    membershipstatus = member.membershipstatus,
                    loanapplicationstatus = member.loanapplicationstatus,
                    phonenumber = member.phonenumber,
                    email = member.email,
                    address = member.address,
                    gender = member.gender,
                    occupation = member.occupation,
                    nextofkin = member.nextofkin,
                    nextofkinaddress = member.nextofkinaddress,
                    bank = member.bank,
                    accountnumber = member.accountnumber,
                    __v = member.__v
                )
            )
        }
    }
}