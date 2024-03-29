import java.sql.*
import java.util.Properties

/**
 * Program to list databases in MySQL using Kotlin
 */
object MySQLDatabaseExampleKotlin {

    internal var conn: Connection? = null
    internal var recipename = "recipename" // provide the username


    @JvmStatic fun main(args: Array<String>) {
        // make a connection to MySQL Server
        getConnection()
        // execute the query via connection object
        executeMySQLQuery()
    }

    fun executeMySQLQuery() {
        var stmt: Statement? = null
        var resultset: ResultSet? = null

        try {
            stmt = conn!!.createStatement()

            resultset = stmt!!.executeQuery("SELECT NAME FROM Wut2CookRecipes WHERE NAME = RecipeSearchBar.text;")

            if (stmt.execute("SELECT NAME FROM Wut2CookRecipes WHERE NAME = RecipeSearchBar.text;")) {
                resultset = stmt.resultSet
            }

            while (resultset!!.next()) {
                println(resultset.getString("Database"))
            }
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } finally {
            // release resources
            if (resultset != null) {
                try {
                    resultset.close()
                } catch (sqlEx: SQLException) {
                }

                resultset = null
            }

            if (stmt != null) {
                try {
                    stmt.close()
                } catch (sqlEx: SQLException) {
                }

                stmt = null
            }

            if (conn != null) {
                try {
                    conn!!.close()
                } catch (sqlEx: SQLException) {
                }

                conn = null
            }
        }
    }

    /**
     * This method makes a connection to MySQL Server
     * In this example, MySQL Server is running in the local host (so 127.0.0.1)
     * at the standard port 3306
     */
    fun getConnection() {
        val connectionProps = Properties()
        connectionProps.put("recipename", recipename)

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection(
                "connectionString=\"server=localhost;port=3306;database=Wut2CookRecipes;uid=cook;password=Cooking@123\"",
                connectionProps)
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } catch (ex: Exception) {
            // handle any errors
            ex.printStackTrace()
        }
    }
}