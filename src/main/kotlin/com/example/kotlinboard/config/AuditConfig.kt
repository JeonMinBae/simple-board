package com.example.kotlinboard.config

import com.example.kotlinboard.authentication.CurrentUser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*


@Configuration
@EnableJpaAuditing
internal class AuditConfig {
    @Bean
    fun auditorProvider(): AuditorAware<String> {
        return AuditorAware { Optional.ofNullable(CurrentUser.userId) }
    }
}