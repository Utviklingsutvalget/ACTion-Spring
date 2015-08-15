package no.swact.action.services;

import no.swact.action.models.Feed;
import no.swact.action.repositories.FeedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
}
