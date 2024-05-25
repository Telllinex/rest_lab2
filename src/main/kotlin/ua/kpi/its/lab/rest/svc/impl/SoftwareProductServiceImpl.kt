package ua.kpi.its.lab.rest.svc.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ua.kpi.its.lab.rest.entity.SoftwareProduct
import ua.kpi.its.lab.rest.repo.SoftwareProductRepository
import ua.kpi.its.lab.rest.svc.SoftwareProductService

@Service
class SoftwareProductServiceImpl @Autowired constructor(
    private val productRepository: SoftwareProductRepository
) : SoftwareProductService {
    override fun create(product: SoftwareProduct): SoftwareProduct {
        return productRepository.save(product)
    }

    override fun getById(id: Long): SoftwareProduct? {
        return productRepository.findById(id).orElse(null)
    }

    override fun update(product: SoftwareProduct): SoftwareProduct {
        return productRepository.save(product)
    }

    override fun deleteById(id: Long) {
        productRepository.deleteById(id)
    }

    override fun getAll(): List<SoftwareProduct> {
        return productRepository.findAll()
    }
}
