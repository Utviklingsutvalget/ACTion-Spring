package no.swact.action.services;

import no.swact.action.models.Feed;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedService {

    List<Feed> all();

    Feed getForId(Long id);
}
