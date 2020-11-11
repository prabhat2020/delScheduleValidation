package com.tcs.service.controller

import com.microsoft.applicationinsights.TelemetryClient
import com.tcs.service.model.BaseModel
import com.tcs.service.model.ServiceResponse
import com.tcs.service.model.TemplateContainer
import com.tcs.service.proxy.DeliveryClientService
import com.tcs.service.repository.TemplateContainerRepository
import com.tcs.service.service.BaseService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.logging.log4j.kotlin.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/service-template")
@Tag(name = "BaseAPI", description = "The Base API")
class BaseController(private val service: DeliveryClientService) {

    val logger = logger()

    /**
     * TelemetryClient is responsible for sending events to App Insights
     */
    @Autowired
    lateinit var telemetryClient: TelemetryClient

    /**
     * This is a sample of the GET Endpoint
     */
    @Operation(summary = "Get Model", description = "Get Model", tags = ["BaseAPI"])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found Model", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = BaseModel::class)))))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "Did not find any BaseModel", content = [Content()])]
    )
    @RequestMapping(value = ["/model"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getBaseModel(): ResponseEntity<ServiceResponse> {
        logger.info("Inside Base Controller")
        telemetryClient.trackEvent("URI /model is triggered");
        return ResponseEntity.ok(ServiceResponse("200",
                "SUCCESS", service.getdeliveryscheduleall()))
    }

    /**
     * This is a sample of the POST Endpoint
     */
    @PostMapping("/modelcreate")
    fun saveModel(@RequestBody baseModel: BaseModel): ResponseEntity<ServiceResponse>? {

       // service.saveModel(baseModel)
        return ResponseEntity.ok(ServiceResponse("200",
                "SUCCESS", "Data Successfully Inserted"))

    }
}