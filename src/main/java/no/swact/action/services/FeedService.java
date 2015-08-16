package no.swact.action.services;

import no.swact.action.models.Feed;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedService {

    List<Feed> all();

    Feed getForId(Long id);

    Feed save(Feed feed);

    Feed update(Feed feed);

    void delete(Long id);

    void delete(Feed feed);
}
