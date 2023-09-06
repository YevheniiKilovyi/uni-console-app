package yevhkil.uniconsoleapp.repository.lector;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yevhkil.uniconsoleapp.model.Lector;

public interface LectorRepository extends JpaRepository<Lector, Long> {
    @EntityGraph(attributePaths = "departments")
    @Query("SELECT l FROM Lector l WHERE LOWER(l.firstName)"
            + " LIKE %:template% OR LOWER(l.lastName) LIKE %:template%")
    List<Lector> findLectorsByTemplate(@Param("template") String template);
}
