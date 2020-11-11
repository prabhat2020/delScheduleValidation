package com.tcs.service.utility

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.tcs.service.model.Deliveryschedulemodel
import java.util.Arrays

import khttp.get

object Utility {

    fun convert(url: String, objectType: Any): Array<Deliveryschedulemodel>? {

        val jsonObject = get(url = url).jsonObject
        print(jsonObject)
        return when {
            jsonObject!= null -> {
                val mapper = ObjectMapper().registerKotlinModule()
                mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                print(objectType)
                when (objectType) {
                    is Deliveryschedulemodel -> {
                        print(mapper)

                        mapper.readValue(jsonObject["response"].toString(),Array<Deliveryschedulemodel>::class.java)

                    }
//                    is  -> {
//                        mapper.readValue<StoreCluster>(jsonObject["response"].toString(), StoreCluster::class.java)
//                    }
                    else -> {
                        null
                    }
                }
            }
            else -> {
                 null
            }
        }
    }




}