package ua.kpi.its.lab.rest.dto

data class SoftwareModuleRequestDto(
    val description: String,
    val author: String,
    val language: String,
    val lastEditDate: String,
    val size: Double,
    val linesOfCode: Int,
    val crossPlatform: Boolean,
    val softwareProductId: Long
)
