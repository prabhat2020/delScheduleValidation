package com.tcs.service.repository

import org.springframework.stereotype.Repository
import com.microsoft.azure.spring.data.cosmosdb.repository.ReactiveCosmosRepository
import com.tcs.service.model.TemplateContainer

/**
 * This file will contain all the repositories interfaces.
 */

@Repository
interface TemplateContainerRepository : ReactiveCosmosRepository<TemplateContainer, Int>