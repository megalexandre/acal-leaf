package acal.com.acal_left.core.repository;

import acal.com.acal_left.core.model.Link;
import acal.com.acal_left.core.model.filter.LinkFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository {
    List<Link> findByFilter(LinkFilter filter);
    Link save(Link link);
}
