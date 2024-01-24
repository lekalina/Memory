package di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized
import okio.Path.Companion.toPath

private lateinit var _dataStore: DataStore<Preferences>

@OptIn(InternalCoroutinesApi::class)
private val lock = SynchronizedObject()

/**
 * Gets the singleton DataStore instance, creating it if necessary.
 */
@OptIn(InternalCoroutinesApi::class)
fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    synchronized(lock) {
        if (::_dataStore.isInitialized) {
            _dataStore
        } else {
            PreferenceDataStoreFactory.createWithPath(produceFile = { producePath().toPath() })
                .also { _dataStore = it }
        }
    }

internal const val dataStoreFileName = "kmm_playground.preferences_pb"

/*fun createDataStore(
    producePath: () -> String,
): DataStore<Preferences> = PreferenceDataStoreFactory.createWithPath(
    corruptionHandler = null,
    migrations = emptyList(),
    produceFile = { producePath().toPath() },
)*/