package ua.kpi.its.lab.rest.svc

import ua.kpi.its.lab.rest.entity.SoftwareModule

interface SoftwareModuleService {
    fun create(module: SoftwareModule): SoftwareModule
    fun getById(id: Long): SoftwareModule?
    fun update(module: SoftwareModule): SoftwareModule
    fun deleteById(id: Long)
    fun getAll(): List<SoftwareModule>
}
