package com.github.danilogmoura.algafood.core.data;

import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public class PageableTranslator {


    private PageableTranslator() {
    }

    public static Pageable translate(Pageable pageable, Map<String, String> fieldsMapping) {
        var orders = pageable.getSort().stream()
            .filter(order -> fieldsMapping.containsKey(order.getProperty()))
            .map(order -> new Order(order.getDirection(), fieldsMapping.get(order.getProperty())))
            .toList();

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
    }

}
