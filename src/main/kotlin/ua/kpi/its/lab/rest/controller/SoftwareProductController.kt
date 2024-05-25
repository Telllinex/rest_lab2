package ua.kpi.its.lab.rest.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ua.kpi.its.lab.rest.dto.*
import ua.kpi.its.lab.rest.svc.SoftwareProductService
import ua.kpi.its.lab.rest.entity.SoftwareProduct

@RestController
@RequestMapping("/api/products")
class SoftwareProductController(private val productService: SoftwareProductService) {

    @PostMapping
    fun createProduct(@RequestBody requestDto: SoftwareProductRequestDtos): ResponseEntity<SoftwareProductResponseDtos> {
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
        val responseDto = SoftwareProductResponseDtos(
            id = product.id,
            name = product.name,
            developer = product.developer,
            version = product.version,
            releaseDate = product.releaseDate,
            size = product.size,
            bitness = product.bitness,
            crossPlatform = product.crossPlatform
        )
        return ResponseEntity.ok(responseDto)
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<SoftwareProductResponseDtos> {
        val product = productService.getById(id)
        return if (product != null) {
            val responseDto = SoftwareProductResponseDtos(
                id = product.id,
                name = product.name,
                developer = product.developer,
                version = product.version,
                releaseDate = product.releaseDate,
                size = product.size,
                bitness = product.bitness,
                crossPlatform = product.crossPlatform
            )
            ResponseEntity.ok(responseDto)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody requestDto: SoftwareProductRequestDtos): ResponseEntity<SoftwareProductResponseDtos> {
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
        val responseDto = SoftwareProductResponseDtos(
            id = product.id,
            name = product.name,
            developer = product.developer,
            version = product.version,
            releaseDate = product.releaseDate,
            size = product.size,
            bitness = product.bitness,
            crossPlatform = product.crossPlatform
        )
        return ResponseEntity.ok(responseDto)
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        productService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<SoftwareProductResponseDtos>> {
        val products = productService.getAll().map { product ->
            SoftwareProductResponseDtos(
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
        return ResponseEntity.ok(products)
    }
}
