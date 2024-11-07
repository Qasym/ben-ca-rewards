package com.kasymzhan.rewards.service

import com.kasymzhan.rewards.data.Counter
import com.kasymzhan.rewards.utils.DbConstants
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service

@Service
class CounterService(val mongoOperations: MongoOperations) {
    fun getNextId(): Long {
        // search for counter
        // if counter doesn't exist, create one
        val query = Query(Criteria.where("_id").`is`(DbConstants.COUNTER_ID))
        val update = Update().inc("value", 1)
        val options = FindAndModifyOptions.options().returnNew(true).upsert(true)
        val counter = mongoOperations.findAndModify(query, update, options, Counter::class.java)
        return counter?.value ?: 1
    }
}