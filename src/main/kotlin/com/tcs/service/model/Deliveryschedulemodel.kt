package com.tcs.service.model

import com.microsoft.azure.spring.data.cosmosdb.core.mapping.PartitionKey
import org.springframework.data.annotation.Id

data class Deliveryschedulemodel(
        @Id
        @PartitionKey
        val id: String="",
        val storeNumber: String="",
        val deliveryStreamNumber: Double?=0.0,
        val deliveryStreamName: String?="",
        val schemaName: String?="",
        val deliverySchemaType: Int?=0,
        val startDate: String?="",
        val endDate: String?="",
        val notes: String?="",
        val _rid: String?="",
        var timeTableList:List<Timetable>?= listOf()
) {

}
