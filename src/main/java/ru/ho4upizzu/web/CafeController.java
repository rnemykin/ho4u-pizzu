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

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(method = RequestMethod.GET)
public class CafeController {
    private static final List<String> AVAILABLE_SORT = Arrays.asList("name", "deliveryPrice");
    private static final Sort NAME_ASC_SORT = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    private CafeRepository cafeRepository;


    @RequestMapping("/")
    public ModelAndView findAll(Sort sort) {
        sort = getSortOrMakeDefault(sort);
        Iterable<Cafe> found = cafeRepository.findAll(sort);
        return buildView(found, sort);
    }

    @RequestMapping("/with-delivery")
    public ModelAndView findWithDelivery(Sort sort) {
        sort = getSortOrMakeDefault(sort);
        List<Cafe> cafesWithDelivery = cafeRepository.findByHasDeliveryTrue(sort);
        return buildView(cafesWithDelivery, sort);
    }

    @RequestMapping("/deliver-now")
    public ModelAndView findDeliverNow(Sort sort) {
        sort = getSortOrMakeDefault(sort);
        List<Cafe> deliverNow = cafeRepository.findDeliverNow(LocalTime.now(), sort);
        return buildView(deliverNow, sort);
    }

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

    private Sort getSortOrMakeDefault(Sort sort) {
        if(sort == null) {
            return NAME_ASC_SORT;
        }

        for (Sort.Order order : sort) {
            if(!AVAILABLE_SORT.contains(order.getProperty())) {
                return NAME_ASC_SORT;
            }
        }

        return sort;
    }

    private ModelAndView buildView(Iterable<Cafe> cafesWithDelivery, Sort sort) {
        ModelAndView modelAndView = new ModelAndView("cafeListView");
        modelAndView.addObject("cafes",  StreamSupport.stream(cafesWithDelivery.spliterator(), false).map(CafeDto::new).collect(Collectors.toList()));
        modelAndView.addObject("sort", getOrderProperty(sort));
        return modelAndView;
    }

    private String getOrderProperty(Sort sort) {
        for (Sort.Order order : sort) {
            return order.getProperty();
        }
        return "";
    }
}
