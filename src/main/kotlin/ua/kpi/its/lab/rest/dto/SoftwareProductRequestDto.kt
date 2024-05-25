package ua.kpi.its.lab.rest.dto

data class SoftwareProductRequestDto(
    val name: String,
    val developer: String,
    val version: String,
    val releaseDate: String,
    val size: Double,
    val bitness: String,
    val crossPlatform: Boolean
)
