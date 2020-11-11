package com.tcs.service.service

import com.tcs.service.error.customexception.DataNotFoundException
import com.tcs.service.model.BaseModel
import com.tcs.service.model.TemplateContainer
import com.tcs.service.repository.TemplateContainerRepository
import org.springframework.stereotype.Service

@Service
class BaseService(private val templateContainerRepository: TemplateContainerRepository) {

    val ndf = "No Data Found"
    fun getBaseModel(): BaseModel {
        val result: TemplateContainer = templateContainerRepository.findById(101).block()
                ?: throw DataNotFoundException(ndf)
        return BaseModel().copy(modId = result.id.toString(), modDesc = result.name);
    }

    fun saveModel(model: BaseModel) {
        templateContainerRepository.save(TemplateContainer(id = model.modId.toInt(), name = model.modDesc))

    }


}