package ua.kpi.its.lab.rest.repo

import org.springframework.data.jpa.repository.JpaRepository
import ua.kpi.its.lab.rest.entity.SoftwareProduct

interface SoftwareProductRepository : JpaRepository<SoftwareProduct, Long>
