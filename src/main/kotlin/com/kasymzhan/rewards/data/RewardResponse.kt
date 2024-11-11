package com.kasymzhan.rewards.data

import org.bson.types.ObjectId

data class RewardResponse(
    val id: ObjectId,
    val name: String,
    val item: String,
    val quantity: Int
)
