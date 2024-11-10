package com.kasymzhan.rewards.data

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "rewards")
data class Reward(
    @Id
    val id: ObjectId,
    var name: String,
    var item: RewardItem,
    var quantity: Int,
)

enum class RewardItem(private val type: String) {
    GOLD("GOLD"),
    DIAMOND("DIAMOND");

    fun getName(): String = type
}
