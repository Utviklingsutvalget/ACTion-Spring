package no.swact.action.controllers.api;

import no.swact.action.models.Feed;
import no.swact.action.services.FeedService;
import no.swact.action.services.FeedServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/feed")
public class FeedRestController {

    private static final Logger LOG = LoggerFactory.getLogger(FeedRestController.class);

    @Autowired
    private FeedService service;

    @RequestMapping("/")
    public List<Feed> all(){
        return service.all().stream().sorted(((e1, e2) -> {
            if(e1.getDateTime().isAfter(e2.getDateTime())){
                return -1;
            }else if(e1.getDateTime().isBefore(e2.getDateTime())){
                return 1;
            }
            return 0;
        })).collect(Collectors.toList());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void save(@RequestBody Feed feed){
        service.save(feed);
    }

    @RequestMapping("/{id}")
    public Feed feed(@PathVariable Long id){
        return service.getForId(id);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Feed update(@RequestBody Feed feed){
        return service.update(feed);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void delete(@RequestBody Feed feed){
        service.delete(feed);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String errorHandler(RuntimeException e){
        LOG.error(e.getMessage());
        return e.getMessage();
    }
}
