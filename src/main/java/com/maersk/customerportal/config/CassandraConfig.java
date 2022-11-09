package com.maersk.customerportal.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

@Configuration
@EnableReactiveCassandraRepositories(basePackageClasses = { CassandraConfig.class })
public class CassandraConfig extends AbstractReactiveCassandraConfiguration {

	@Value("${spring.data.cassandra.keyspace-name}")
	String keySpace;

	@Value("${spring.data.cassandra.contact-points}")
	String contactPoints;

	@Override
	protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {

		CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(keySpace).ifNotExists()
				.with(KeyspaceOption.DURABLE_WRITES, true);

		return Arrays.asList(specification);
	}

	@Override
	protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
		return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(keySpace));
	}

	@Override
	protected String getKeyspaceName() {
		return keySpace;
	}

	@Override
	protected String getContactPoints() {
		return contactPoints;
	}

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.RECREATE;
	}

}
