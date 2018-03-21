package scalikejdbc

import java.sql.Connection
import javax.sql.DataSource

import com.zaxxer.hikari.HikariDataSource

class HikariCPConnectionPool(
  override val url: String,
  override val user: String,
  password: String,
  override val settings: ConnectionPoolSettings = ConnectionPoolSettings()
) extends ConnectionPool(url, user, password, settings) {

  private val dataSourceClassName = "com.mysql.cj.jdbc.MysqlDataSource"

  private[this] val _dataSource = {
    val ds = new HikariDataSource()
    ds.setDataSourceClassName(dataSourceClassName)
    ds.setMinimumIdle(settings.initialSize)
    ds.setMaximumPoolSize(settings.maxSize)
    ds.addDataSourceProperty("url", url)
    ds.addDataSourceProperty("user", user)
    ds.addDataSourceProperty("password", password)
    ds
  }

  override def borrow(): Connection = dataSource.getConnection()

  override def dataSource: DataSource = _dataSource

  override def numActive: Int = _dataSource.getHikariPoolMXBean.getActiveConnections

  override def numIdle: Int = _dataSource.getHikariPoolMXBean.getIdleConnections

  override def maxActive: Int = _dataSource.getMaximumPoolSize

  override def maxIdle: Int = _dataSource.getMinimumIdle

  override def close(): Unit = _dataSource.close()
}
