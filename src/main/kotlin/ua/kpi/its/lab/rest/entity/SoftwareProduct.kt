package ua.kpi.its.lab.rest.entity

import jakarta.persistence.*


@Entity
data class SoftwareProduct(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,
    val developer: String,
    val version: String,
    val releaseDate: String,
    val size: Double,
    val bitness: String,
    val crossPlatform: Boolean,

    @OneToMany(mappedBy = "softwareProduct", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val modules: List<SoftwareModule> = emptyList()
) : Comparable<SoftwareProduct> {
    override fun compareTo(other: SoftwareProduct): Int {
        val nameComparison = name.compareTo(other.name)
        return if (nameComparison != 0) {
            nameComparison
        } else {
            id.compareTo(other.id)
        }
    }

    override fun toString(): String {
        return "SoftwareProduct(id=$id, name='$name', developer='$developer', version='$version', releaseDate='$releaseDate', size=$size, bitness='$bitness', crossPlatform=$crossPlatform)"
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
