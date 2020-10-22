package ru.lemaitre.record

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.lemaitre.record.database.RecordDataBase
import ru.lemaitre.record.database.RecordDatabaseDao
import ru.lemaitre.record.database.RecordingItem
import java.io.IOException
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class RecordDatabaseTest {

    private lateinit var recordDatabaseDao:RecordDatabaseDao
    private lateinit var database:RecordDataBase

    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, RecordDataBase::class.java)
            .allowMainThreadQueries()
            .build()

        recordDatabaseDao = database.recordDatabase
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun testDatabase(){
        recordDatabaseDao.insert(RecordingItem())
        val getCount = recordDatabaseDao.getCount()
        assertEquals(getCount, 1)
    }

}