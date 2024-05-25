package ua.kpi.its.lab.rest.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.kpi.its.lab.rest.dto.SoftwareModuleRequestDto
import ua.kpi.its.lab.rest.dto.SoftwareModuleResponseDtos
import ua.kpi.its.lab.rest.svc.SoftwareModuleService
import ua.kpi.its.lab.rest.svc.SoftwareProductService
import ua.kpi.its.lab.rest.entity.SoftwareModule

@RestController
@RequestMapping("/api/modules")
class SoftwareModuleController(
    private val moduleService: SoftwareModuleService,
    private val productService: SoftwareProductService
) {

    @PostMapping
    fun createModule(@RequestBody requestDto: SoftwareModuleRequestDto): ResponseEntity<SoftwareModuleResponseDtos> {
        val softwareProduct = productService.getById(requestDto.softwareProductId)
        if (softwareProduct != null) {
            val module = moduleService.create(
                SoftwareModule(
                    description = requestDto.description,
                    author = requestDto.author,
                    language = requestDto.language,
                    lastEditDate = requestDto.lastEditDate,
                    size = requestDto.size,
                    linesOfCode = requestDto.linesOfCode,
                    crossPlatform = requestDto.crossPlatform,
                    softwareProduct = softwareProduct
                )
            )
            val responseDto = SoftwareModuleResponseDtos(
                id = module.id,
                description = module.description,
                author = module.author,
                language = module.language,
                lastEditDate = module.lastEditDate,
                size = module.size,
                linesOfCode = module.linesOfCode,
                crossPlatform = module.crossPlatform,
                softwareProductId = module.softwareProduct.id
            )
            return ResponseEntity.ok(responseDto)
        } else {
            return ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/{id}")
    fun getModuleById(@PathVariable id: Long): ResponseEntity<SoftwareModuleResponseDtos> {
        val module = moduleService.getById(id)
        return if (module != null) {
            val responseDto = SoftwareModuleResponseDtos(
                id = module.id,
                description = module.description,
                author = module.author,
                language = module.language,
                lastEditDate = module.lastEditDate,
                size = module.size,
                linesOfCode = module.linesOfCode,
                crossPlatform = module.crossPlatform,
                softwareProductId = module.softwareProduct.id
            )
            ResponseEntity.ok(responseDto)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{id}")
    fun updateModule(@PathVariable id: Long, @RequestBody requestDto: SoftwareModuleRequestDto): ResponseEntity<SoftwareModuleResponseDtos> {
        val softwareProduct = productService.getById(requestDto.softwareProductId)
        if (softwareProduct != null) {
            val module = moduleService.update(
                SoftwareModule(
                    id = id,
                    description = requestDto.description,
                    author = requestDto.author,
                    language = requestDto.language,
                    lastEditDate = requestDto.lastEditDate,
                    size = requestDto.size,
                    linesOfCode = requestDto.linesOfCode,
                    crossPlatform = requestDto.crossPlatform,
                    softwareProduct = softwareProduct
                )
            )
            val responseDto = SoftwareModuleResponseDtos(
                id = module.id,
                description = module.description,
                author = module.author,
                language = module.language,
                lastEditDate = module.lastEditDate,
                size = module.size,
                linesOfCode = module.linesOfCode,
                crossPlatform = module.crossPlatform,
                softwareProductId = module.softwareProduct.id
            )
            return ResponseEntity.ok(responseDto)
        } else {
            return ResponseEntity.badRequest().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteModule(@PathVariable id: Long): ResponseEntity<Void> {
        moduleService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping
    fun getAllModules(): ResponseEntity<List<SoftwareModuleResponseDtos>> {
        val modules = moduleService.getAll().map { module ->
            SoftwareModuleResponseDtos(
                id = module.id,
                description = module.description,
                author = module.author,
                language = module.language,
                lastEditDate = module.lastEditDate,
                size = module.size,
                linesOfCode = module.linesOfCode,
                crossPlatform = module.crossPlatform,
                softwareProductId = module.softwareProduct.id
            )
        }
        return ResponseEntity.ok(modules)
    }
}
