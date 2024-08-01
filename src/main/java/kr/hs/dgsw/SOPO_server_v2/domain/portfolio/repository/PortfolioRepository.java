package kr.hs.dgsw.SOPO_server_v2.domain.portfolio.repository;

import kr.hs.dgsw.SOPO_server_v2.domain.portfolio.entity.PortfolioEntity;
import kr.hs.dgsw.SOPO_server_v2.domain.portfolio.enums.PortfolioState;
import kr.hs.dgsw.SOPO_server_v2.global.page.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Long> {

    @Query("SELECT p FROM PortfolioEntity p WHERE p.portfolioState = :state ORDER BY p.createdAt DESC")
    List<PortfolioEntity> findPortfoliosByState(@Param("state") PortfolioState state);
}
