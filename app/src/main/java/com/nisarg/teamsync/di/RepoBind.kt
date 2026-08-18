package com.nisarg.teamsync.di

import com.nisarg.teamsync.data.repository.AuthRepoImpl
import com.nisarg.teamsync.data.repository.CreateTeamRepoImpl
import com.nisarg.teamsync.data.repository.GetTeamByIdRepoImpl
import com.nisarg.teamsync.data.repository.GetTeamRepoImpl
import com.nisarg.teamsync.data.repository.JoinTeamRepoImpl
import com.nisarg.teamsync.data.repository.TaskRepoImpl
import com.nisarg.teamsync.domain.repository.AuthRepository
import com.nisarg.teamsync.domain.repository.CreateTeamRepository
import com.nisarg.teamsync.domain.repository.GetTeamByIdRepository
import com.nisarg.teamsync.domain.repository.GetTeamRepository
import com.nisarg.teamsync.domain.repository.JoinTeamRepository
import com.nisarg.teamsync.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoBind {
    @Binds
    abstract fun bindRepo(authRepoImpl: AuthRepoImpl) : AuthRepository

    @Binds
    abstract fun bindCreate(createTeamRepoImpl: CreateTeamRepoImpl) : CreateTeamRepository

    @Binds
    abstract fun bindJoin(joinTeamRepoImpl: JoinTeamRepoImpl) : JoinTeamRepository

    @Binds
    abstract fun bindGet(getTeamRepoImpl: GetTeamRepoImpl) : GetTeamRepository

    @Binds
    abstract fun bindGetId(getTeamByIdRepoImpl: GetTeamByIdRepoImpl) : GetTeamByIdRepository

    @Binds
    abstract fun bindTask(taskRepoImpl: TaskRepoImpl) : TaskRepository
}