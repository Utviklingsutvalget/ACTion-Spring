package no.swact.action.controllers.api;

import no.swact.action.models.Feed;
import no.swact.action.services.FeedService;
import no.swact.action.services.FeedServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
public class FeedRestController {

    @Autowired
    private FeedService service;


}
