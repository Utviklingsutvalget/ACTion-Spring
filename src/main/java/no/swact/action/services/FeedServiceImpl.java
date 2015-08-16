package no.swact.action.services;

import no.swact.action.models.Feed;
import no.swact.action.repositories.FeedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedServiceImpl implements FeedService {

    private static final Logger LOG = LoggerFactory.getLogger(FeedServiceImpl.class);

    @Autowired
    private FeedRepository repository;

    @Override
    public List<Feed> all() {
        return repository.findAll();
    }

    @Override
    public Feed getForId(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Feed save(Feed feed) {
        return repository.saveAndFlush(feed);
    }

    @Override
    public Feed update(Feed feed) {
        return repository.saveAndFlush(feed);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public void delete(Feed feed) {
        repository.delete(feed);
    }
}
