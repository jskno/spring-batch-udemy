package com.jskno.spring_batch_udemy.genericJobs

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class InMemoryDatabaseConfig: DefaultBatchConfigurer() {

    override fun setDataSource(dataSource: DataSource) {
        // The batch must use memory repository to store the flow
    }
}