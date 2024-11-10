package com.kasymzhan.rewards.repository

import com.kasymzhan.rewards.data.Reward
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface RewardRepository : MongoRepository<Reward, ObjectId> {}