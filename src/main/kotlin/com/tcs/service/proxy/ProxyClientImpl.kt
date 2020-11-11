package com.tcs.service.proxy
import com.tcs.service.model.Deliveryschedulemodel
import com.tcs.service.utility.Utility
import org.springframework.stereotype.Service
import org.apache.logging.log4j.kotlin.logger
import java.util.*
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.json.JSONObject

@Service
class DeliveryClientService : Deliveryschedule<Deliveryschedulemodel> {
    val logger = logger()
    private val basePath = "http://localhost:8097/api/v1/service-template"
    override fun getdeliveryscheduleall(): List<Deliveryschedulemodel>? {

       return Utility.convert("$basePath/model", Deliveryschedulemodel())?.toList()
    }
    companion object {

        fun convertList(jsonObject: JSONObject): List<Deliveryschedulemodel> {
            return when {
                jsonObject.has("response") -> {
                    val mapper = ObjectMapper().registerKotlinModule()
                    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                    mapper.readValue<List<Deliveryschedulemodel>>(jsonObject["response"].toString(),
                            object : TypeReference<List<Deliveryschedulemodel>>() {})
                }
                else -> {
                    listOf()
                }
            }
        }
    }

}