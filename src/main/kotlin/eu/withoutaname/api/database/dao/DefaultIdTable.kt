package eu.withoutaname.api.database.dao

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

@Suppress("unused")
object DefaultIdGenerator {
    private const val UNUSED_BITS = 1
    private const val EPOCH_BITS = 41
    private const val NODE_ID_BITS = 10
    private const val SEQUENCE_BITS = 12
    private const val maxNodeId: Long = (1L shl NODE_ID_BITS) - 1
    private const val maxSequence: Long = (1L shl SEQUENCE_BITS) - 1

    private const val nodeId: Long = 0
    private const val customEpoch: Long = 1640995200000

    @Volatile
    private var lastTimestamp: Long = -1

    @Volatile
    private var sequence: Long = 0

    @Synchronized
    fun nextId(): Long {
        var currentTimestamp = current()
        check(currentTimestamp >= lastTimestamp) { "Invalid System Clock!" }
        if (currentTimestamp == lastTimestamp) {
            if (++sequence > maxSequence) {
                currentTimestamp = waitOneMillisecond(currentTimestamp)
                sequence = 0
            }
        } else {
            // reset sequence to start with zero for the next millisecond
            sequence = 0
        }
        lastTimestamp = currentTimestamp
        return (currentTimestamp shl NODE_ID_BITS + SEQUENCE_BITS) or (nodeId shl SEQUENCE_BITS) or sequence
    }

    private fun current(): Long {
        return System.currentTimeMillis() - customEpoch
    }

    private fun waitOneMillisecond(currentTimestamp: Long): Long {
        var timestamp = currentTimestamp
        while (timestamp == lastTimestamp) {
            timestamp = current()
        }
        return timestamp
    }
}

open class DefaultIdTable(name: String = "", columnName: String = "id") : IdTable<Long>(name) {
    final override val id: Column<EntityID<Long>> = long(columnName).clientDefault { DefaultIdGenerator.nextId() }.entityId()
    final override val primaryKey = PrimaryKey(id)
}