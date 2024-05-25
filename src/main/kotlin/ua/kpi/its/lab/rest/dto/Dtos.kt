package ua.kpi.its.lab.rest.dto

data class SoftwareProductRequestDtos(
    val name: String,
    val developer: String,
    val version: String,
    val releaseDate: String,
    val size: Double,
    val bitness: String,
    val crossPlatform: Boolean
)

data class SoftwareProductResponseDtos(
    val id: Long,
    val name: String,
    val developer: String,
    val version: String,
    val releaseDate: String,
    val size: Double,
    val bitness: String,
    val crossPlatform: Boolean
)

data class SoftwareModuleRequestDtos(
    val description: String,
    val author: String,
    val language: String,
    val lastEditDate: String,
    val size: Double,
    val linesOfCode: Int,
    val crossPlatform: Boolean,
    val softwareProductId: Long
)

data class SoftwareModuleResponseDtos(
    val id: Long,
    val description: String,
    val author: String,
    val language: String,
    val lastEditDate: String,
    val size: Double,
    val linesOfCode: Int,
    val crossPlatform: Boolean,
    val softwareProductId: Long
)
