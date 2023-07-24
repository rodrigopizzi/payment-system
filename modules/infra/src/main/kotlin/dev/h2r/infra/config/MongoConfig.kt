package dev.h2r.infra.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["dev.h2r.infra.database.repository"])
class MongoConfig {
}