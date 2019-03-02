package com.stylingandroid.muselee.topartists.di

import android.app.Application
import androidx.room.Room
import com.stylingandroid.muselee.providers.DataMapper
import com.stylingandroid.muselee.providers.DataPersister
import com.stylingandroid.muselee.topartists.database.DatabaseTopArtistsMapper
import com.stylingandroid.muselee.topartists.database.DatabaseTopArtistsPersister
import com.stylingandroid.muselee.topartists.database.DbArtist
import com.stylingandroid.muselee.topartists.database.DbImage
import com.stylingandroid.muselee.topartists.database.TopArtistsDao
import com.stylingandroid.muselee.topartists.database.TopArtistsDatabase
import com.stylingandroid.muselee.topartists.entities.Artist
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @Provides
    @JvmStatic
    internal fun providesDatabase(context: Application): TopArtistsDatabase =
        Room.databaseBuilder(context, TopArtistsDatabase::class.java, "top-artists").build()

    @Provides
    @JvmStatic
    internal fun providesTopArtistsDao(database: TopArtistsDatabase): TopArtistsDao =
        database.topArtistsDao()

    @Provides
    @JvmStatic
    internal fun providesTopArtistsMapper():
            DataMapper<Triple<Int, Artist, Long>, Pair<DbArtist, Collection<DbImage>>> =
        DatabaseTopArtistsMapper()

    @Provides
    @JvmStatic
    internal fun providesDatabasePersister(
        dao: TopArtistsDao,
        mapper: DataMapper<Triple<Int, Artist, Long>, Pair<DbArtist, Collection<DbImage>>>
    ): DataPersister<List<Artist>> =
        DatabaseTopArtistsPersister(dao, mapper)
}
