package kr.hs.dgsw.SOPO_server_v2.domain.contest.repository;

import kr.hs.dgsw.SOPO_server_v2.domain.contest.entity.ContestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;

@Repository
public interface ContestRepository extends JpaRepository<ContestEntity, Long> {
}
