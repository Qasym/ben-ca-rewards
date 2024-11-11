package com.kasymzhan.rewards.controller

import com.kasymzhan.rewards.data.Reward
import com.kasymzhan.rewards.data.RewardItem
import com.kasymzhan.rewards.data.RewardRequest
import com.kasymzhan.rewards.data.RewardResponse
import com.kasymzhan.rewards.repository.RewardRepository
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/rewards")
class RewardsController(val rewardsRepository: RewardRepository) {
    @PostMapping("/add")
    fun addReward(@RequestBody rewardReq: RewardRequest): ResponseEntity<String> {
        return try {
            val reward = rewardReq.toReward()
            rewardsRepository.save(reward)
            ResponseEntity("Reward added successfully", HttpStatus.CREATED)
        } catch (e: Exception) {
            println("wrong item ${rewardReq.item}")
            ResponseEntity("Invalid item: ${rewardReq.item}", HttpStatus.NOT_ACCEPTABLE)
        }
    }

    @GetMapping
    @ResponseBody
    fun getAllRewards(): List<RewardResponse> {
        val list = rewardsRepository.findAll()
        return if (list.isEmpty()) emptyList()
        else list.map { it.toRewardResponse() }
    }

    private fun RewardRequest.toReward() =
        Reward(
            id = ObjectId(Date()),
            name = this.name,
            quantity = this.quantity,
            item = RewardItem.valueOf(this.item.uppercase())
        )

    private fun Reward.toRewardResponse() =
        RewardResponse(
            name = this.name,
            quantity = this.quantity,
            item = this.item.getName(),
            id = this.id.toString()
        )
}