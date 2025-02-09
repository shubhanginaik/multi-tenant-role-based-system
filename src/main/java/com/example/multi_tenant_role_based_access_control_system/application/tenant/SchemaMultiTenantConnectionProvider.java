package com.example.multi_tenant_role_based_access_control_system.application.tenant;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.Stoppable;

public class SchemaMultiTenantConnectionProvider implements MultiTenantConnectionProvider, Stoppable {

  private final DataSource dataSource;

  public SchemaMultiTenantConnectionProvider(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public Connection getConnection(Object tenantIdentifier) throws SQLException {
    if (tenantIdentifier == null) {
      throw new SQLException("Tenant identifier cannot be null");
    }
    final Connection connection = dataSource.getConnection();
    connection.setSchema(tenantIdentifier.toString()); // Convert to string & set schema
    return connection;
  }

  @Override
  public Connection getAnyConnection() throws SQLException {
    return dataSource.getConnection();
  }

  @Override
  public void releaseAnyConnection(Connection connection) throws SQLException {
    connection.close();
  }

  @Override
  public void releaseConnection(Object tenantIdentifier, Connection connection) throws SQLException {
    connection.setSchema("public"); // Reset schema after usage
    connection.close();
  }

  @Override
  public boolean supportsAggressiveRelease() {
    return false;
  }

  @Override
  public boolean isUnwrappableAs(Class<?> unwrapType) {
    return MultiTenantConnectionProvider.class.isAssignableFrom(unwrapType);
  }

  @Override
  public <T> T unwrap(Class<T> unwrapType) {
    if (isUnwrappableAs(unwrapType)) {
      return (T) this;
    }
    throw new UnsupportedOperationException("Cannot unwrap to " + unwrapType);
  }

  @Override
  public void stop() {
    // Cleanup logic if needed
  }
}
