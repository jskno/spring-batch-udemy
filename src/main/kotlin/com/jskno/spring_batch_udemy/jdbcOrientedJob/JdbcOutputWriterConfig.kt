package com.jskno.spring_batch_udemy.jdbcOrientedJob

import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class JdbcOutputWriterConfig {

    @Bean(name = ["jdbcOutputWriter"])
    fun jdbcOutputWriter(@Qualifier("outputDatasource") outputDataSource: DataSource):
            JdbcBatchItemWriter<JdbcStudent> {
        val jdbcOutputWriter = JdbcBatchItemWriter<JdbcStudent>()
        jdbcOutputWriter.setDataSource(outputDataSource)
        jdbcOutputWriter.setSql("""
            insert into student(id, first_name, last_name, email)
            values (:id, :firstName, :lastName, :email)
        """.trimIndent())
        jdbcOutputWriter.setItemSqlParameterSourceProvider { BeanPropertySqlParameterSource(it) }
        return jdbcOutputWriter
    }

    @Bean(name = ["outputDatasource"])
    @ConfigurationProperties(prefix = "spring.outputdatasource")
    fun outputDatasource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean(name = ["jdbcOutputWriterPrepareStatement"])
    fun jdbcOutputWriterPrepareStatement(@Qualifier("outputDatasource") outputDataSource: DataSource):
            JdbcBatchItemWriter<JdbcStudent> {
        val jdbcOutputWriter = JdbcBatchItemWriter<JdbcStudent>()
        jdbcOutputWriter.setDataSource(outputDataSource)
        jdbcOutputWriter.setSql("""
            insert into student(id, first_name, last_name, email)
            values (?, ?, ?, ?)
        """.trimIndent())
        jdbcOutputWriter.setItemPreparedStatementSetter { item, ps ->
            ps.setLong(1, item.id)
            ps.setString(2, item.firstName)
            ps.setString(3, item.lastName)
            ps.setString(4, item.email)
        }
        return jdbcOutputWriter
    }
}