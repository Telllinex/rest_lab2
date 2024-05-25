package ua.kpi.its.lab.rest.svc

import ua.kpi.its.lab.rest.entity.SoftwareProduct

interface SoftwareProductService {
    fun create(product: SoftwareProduct): SoftwareProduct
    fun getById(id: Long): SoftwareProduct?
    fun update(product: SoftwareProduct): SoftwareProduct
    fun deleteById(id: Long)
    fun getAll(): List<SoftwareProduct>
}
