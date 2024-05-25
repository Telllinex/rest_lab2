package ua.kpi.its.lab.rest.svc.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ua.kpi.its.lab.rest.entity.SoftwareModule
import ua.kpi.its.lab.rest.repo.SoftwareModuleRepository
import ua.kpi.its.lab.rest.svc.SoftwareModuleService

@Service
class SoftwareModuleServiceImpl @Autowired constructor(
    private val moduleRepository: SoftwareModuleRepository
) : SoftwareModuleService {
    override fun create(module: SoftwareModule): SoftwareModule {
        return moduleRepository.save(module)
    }

    override fun getById(id: Long): SoftwareModule? {
        return moduleRepository.findById(id).orElse(null)
    }

    override fun update(module: SoftwareModule): SoftwareModule {
        return moduleRepository.save(module)
    }

    override fun deleteById(id: Long) {
        moduleRepository.deleteById(id)
    }

    override fun getAll(): List<SoftwareModule> {
        return moduleRepository.findAll()
    }
}
