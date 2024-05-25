package ua.kpi.its.lab.rest.handler

import org.springframework.stereotype.Component
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import ua.kpi.its.lab.rest.dto.*
import ua.kpi.its.lab.rest.svc.SoftwareModuleService
import ua.kpi.its.lab.rest.svc.SoftwareProductService
import ua.kpi.its.lab.rest.entity.SoftwareModule
import ua.kpi.its.lab.rest.entity.SoftwareProduct

@Component
class SoftwareProductHandler(private val productService: SoftwareProductService) {

    fun createProductHandler(request: ServerRequest): ServerResponse {
        val requestDto = request.body(SoftwareProductRequestDto::class.java)
        val product = productService.create(
            SoftwareProduct(
                name = requestDto.name,
                developer = requestDto.developer,
                version = requestDto.version,
                releaseDate = requestDto.releaseDate,
                size = requestDto.size,
                bitness = requestDto.bitness,
                crossPlatform = requestDto.crossPlatform
            )
        )
        val responseDto = SoftwareProductResponseDto(
            id = product.id,
            name = product.name,
            developer = product.developer,
            version = product.version,
            releaseDate = product.releaseDate,
            size = product.size,
            bitness = product.bitness,
            crossPlatform = product.crossPlatform
        )
        return ServerResponse.ok().body(responseDto)
    }

    fun getProductByIdHandler(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val product = productService.getById(id)
        return if (product != null) {
            val responseDto = SoftwareProductResponseDto(
                id = product.id,
                name = product.name,
                developer = product.developer,
                version = product.version,
                releaseDate = product.releaseDate,
                size = product.size,
                bitness = product.bitness,
                crossPlatform = product.crossPlatform
            )
            ServerResponse.ok().body(responseDto)
        } else {
            ServerResponse.notFound().build()
        }
    }

    fun updateProductHandler(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val requestDto = request.body(SoftwareProductRequestDto::class.java)
        val product = productService.update(
            SoftwareProduct(
                id = id,
                name = requestDto.name,
                developer = requestDto.developer,
                version = requestDto.version,
                releaseDate = requestDto.releaseDate,
                size = requestDto.size,
                bitness = requestDto.bitness,
                crossPlatform = requestDto.crossPlatform
            )
        )
        val responseDto = SoftwareProductResponseDto(
            id = product.id,
            name = product.name,
            developer = product.developer,
            version = product.version,
            releaseDate = product.releaseDate,
            size = product.size,
            bitness = product.bitness,
            crossPlatform = product.crossPlatform
        )
        return ServerResponse.ok().body(responseDto)
    }

    fun deleteProductHandler(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        productService.deleteById(id)
        return ServerResponse.noContent().build()
    }

    fun getAllProductsHandler(request: ServerRequest): ServerResponse {
        val products = productService.getAll().map { product ->
            SoftwareProductResponseDto(
                id = product.id,
                name = product.name,
                developer = product.developer,
                version = product.version,
                releaseDate = product.releaseDate,
                size = product.size,
                bitness = product.bitness,
                crossPlatform = product.crossPlatform
            )
        }
        return ServerResponse.ok().body(products)
    }
}

@Component
class SoftwareModuleHandler(
    private val moduleService: SoftwareModuleService,
    private val productService: SoftwareProductService
) {

    fun createModuleHandler(request: ServerRequest): ServerResponse {
        val requestDto = request.body(SoftwareModuleRequestDto::class.java)
        val softwareProduct = productService.getById(requestDto.softwareProductId)
        return if (softwareProduct != null) {
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
            val responseDto = SoftwareModuleResponseDto(
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
            ServerResponse.ok().body(responseDto)
        } else {
            ServerResponse.badRequest().build()
        }
    }

    fun getModuleByIdHandler(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val module = moduleService.getById(id)
        return if (module != null) {
            val responseDto = SoftwareModuleResponseDto(
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
            ServerResponse.ok().body(responseDto)
        } else {
            ServerResponse.notFound().build()
        }
    }

    fun updateModuleHandler(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val requestDto = request.body(SoftwareModuleRequestDto::class.java)
        val softwareProduct = productService.getById(requestDto.softwareProductId)
        return if (softwareProduct != null) {
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
            val responseDto = SoftwareModuleResponseDto(
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
            ServerResponse.ok().body(responseDto)
        } else {
            ServerResponse.badRequest().build()
        }
    }

    fun deleteModuleHandler(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        moduleService.deleteById(id)
        return ServerResponse.noContent().build()
    }

    fun getAllModulesHandler(request: ServerRequest): ServerResponse {
        val modules = moduleService.getAll().map { module ->
            SoftwareModuleResponseDto(
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
        return ServerResponse.ok().body(modules)
    }
}
