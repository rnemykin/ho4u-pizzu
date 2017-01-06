package ru.ho4upizzu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.ho4upizzu.exception.InvalidParameterException;
import ru.ho4upizzu.exception.ObjectNotFoundException;
import ru.ho4upizzu.model.Cafe;
import ru.ho4upizzu.repository.CafeRepository;
import ru.ho4upizzu.web.model.CafeDto;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(method = RequestMethod.GET)
public class CafeController {
    @Autowired
    private CafeRepository cafeRepository;



    @RequestMapping("/")
    public ModelAndView findAll(Sort sort) {
        Iterable<Cafe> found = cafeRepository.findAll(getSortOrMakeDefault(sort));

        ModelAndView modelAndView = new ModelAndView("findCafeAll");
        modelAndView.addObject("cafes",  StreamSupport.stream(found.spliterator(), false).map(CafeDto::new).collect(Collectors.toList()));
        return modelAndView;
    }

    private Sort getSortOrMakeDefault(Sort sort) {
        return sort == null ? new Sort(Sort.Direction.ASC, "name") : sort;
    }

//    @RequestMapping("/with-delivery")
//    public ModelAndView findWithDelivery(Sort sort) {
//        sort = getSortOrMakeDefault(sort);
//        List<Cafe> cafesWithDelivery = cafeRepository.findByHasDeliveryTrue(sort);
//        ModelAndView modelAndView = new ModelAndView("findAll");
//        modelAndView.addObject("cafes", found);
//        return modelAndView;
//    }
//
//    @RequestMapping("/deliver-now")
//    public ModelAndView findDeliverNow(Sort sort) {
//        sort = getSortOrMakeDefault(sort);
//        List<Cafe> deliverNow = cafeRepository.findDeliverNow(LocalTime.now(), sort);
//        ModelAndView modelAndView = new ModelAndView("findAll");
//        modelAndView.addObject("cafes", found);
//        return modelAndView;
//    }

    @RequestMapping("/cafe/{viewLink}")
    public ModelAndView getByViewLink(@PathVariable String viewLink) {
        if(!StringUtils.hasText(viewLink)) {
            throw new InvalidParameterException("viewLink");
        }
        Cafe cafe = cafeRepository.findByViewLink(viewLink);
        if(cafe == null) {
            throw new ObjectNotFoundException("cafe.vieLink = " + viewLink);
        }

        ModelAndView modelAndView = new ModelAndView("cafeByViewLink");
        modelAndView.addObject("cafe", new CafeDto(cafe));
        return modelAndView;
    }
}
