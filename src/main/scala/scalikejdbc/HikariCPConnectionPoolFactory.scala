package scalikejdbc

object HikariCPConnectionPoolFactory extends ConnectionPoolFactory {

  override def apply(url: String, user: String, password: String, settings: ConnectionPoolSettings): ConnectionPool = {
    new HikariCPConnectionPool(url, user, password, settings)
  }
}
