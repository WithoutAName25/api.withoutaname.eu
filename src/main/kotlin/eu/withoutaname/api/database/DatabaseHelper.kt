package eu.withoutaname.api.database

import eu.withoutaname.api.database.dao.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseHelper {
    fun setup() {
        Database.connect("jdbc:mysql://localhost:3306/withoutaname_api", driver = "com.mysql.cj.jdbc.Driver", user = "root", password = "test")
        transaction {
            SchemaUtils.createMissingTablesAndColumns(Users)
        }
    }
}