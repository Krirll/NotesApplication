package ru.krirll.notes.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.krirll.notes.data.notes_db.NotesDbHelper
import ru.krirll.notes.data.repository.NotesRepositoryImpl
import ru.krirll.notes.domain.repository.NotesRepository
import ru.krirll.notes.domain.use_case.DeleteNoteUseCase
import ru.krirll.notes.domain.use_case.GetNoteByIdUseCase
import ru.krirll.notes.domain.use_case.GetNotesUseCase
import ru.krirll.notes.domain.use_case.InsertOrUpdateNoteUseCase
import ru.krirll.notes.domain.use_case.NotesUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNotesDataBase(app: Application): NotesDbHelper {
        return NotesDbHelper(app.applicationContext)
    }

    @Provides
    @Singleton
    fun provideNotesRepository(db: NotesDbHelper): NotesRepository {
        return NotesRepositoryImpl(db)
    }

    @Provides
    @Singleton
    fun provideNotesUseCases(repository: NotesRepository): NotesUseCases {
        return NotesUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            insertOrUpdateNoteUseCase = InsertOrUpdateNoteUseCase(repository),
            getNoteById = GetNoteByIdUseCase(repository)
        )
    }
}