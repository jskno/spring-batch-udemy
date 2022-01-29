package com.jskno.spring_batch_udemy.jdbcOrientedJob

import org.springframework.batch.item.database.JdbcCursorItemReader
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.BeanPropertyRowMapper
import javax.sql.DataSource

@Configuration
class JdbcInputReaderConfig {

//    @Autowired
//    private lateinit var dataSource: DataSource

    @Bean
    fun jdbcCursorItemReader(): JdbcCursorItemReader<JdbcStudent> {
        val jdbcCursorItemReader = JdbcCursorItemReader<JdbcStudent>()
//        jdbcCursorItemReader.dataSource = dataSource
        jdbcCursorItemReader.dataSource = universityDataSource()
        jdbcCursorItemReader.sql = """
            select id, first_name as firstName, last_name as lastName, email
            from student
        """.trimIndent()

        val rowMapper = BeanPropertyRowMapper<JdbcStudent>()
        rowMapper.setMappedClass(JdbcStudent::class.java)
        jdbcCursorItemReader.setRowMapper(rowMapper)
//        jdbcCursorItemReader.setCurrentItemCount(2)
//        jdbcCursorItemReader.setMaxItemCount(8)
        return jdbcCursorItemReader
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    fun springBatchDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.universitydatasource")
    fun universityDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

}