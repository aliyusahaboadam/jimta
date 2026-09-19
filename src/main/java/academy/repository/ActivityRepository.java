package academy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import academy.model.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findAllByOrderByActivityDateDescIdDesc();

    List<Activity> findAllByTeam_IdOrderByActivityDateDescIdDesc(Long teamId);

}