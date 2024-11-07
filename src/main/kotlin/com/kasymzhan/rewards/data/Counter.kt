package com.kasymzhan.rewards.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "rewards_counter")
data class Counter(
    @Id
    val id: String,
    var value: Long,
)
