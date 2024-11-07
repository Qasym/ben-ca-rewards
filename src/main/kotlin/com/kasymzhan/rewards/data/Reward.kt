package com.kasymzhan.rewards.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collation = "rewards")
data class Reward(
    @Id
    val id: Long,
    var name: String,
    var item: String,
    var quantity: String,
)
