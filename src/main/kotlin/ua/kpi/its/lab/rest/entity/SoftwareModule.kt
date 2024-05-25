package ua.kpi.its.lab.rest.entity

import jakarta.persistence.*

@Entity
data class SoftwareModule(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val description: String,
    val author: String,
    val language: String,
    val lastEditDate: String,
    val size: Double,
    val linesOfCode: Int,
    val crossPlatform: Boolean,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "software_product_id")
    val softwareProduct: SoftwareProduct
)
