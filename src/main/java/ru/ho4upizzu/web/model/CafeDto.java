package ru.ho4upizzu.web.model;

import org.springframework.util.StringUtils;
import ru.ho4upizzu.model.Address;
import ru.ho4upizzu.model.Cafe;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CafeDto {
    private static final String NO_INFO = "Нет информации";
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public String name;
    public String type;
    public String description;
    public Boolean hasDelivery;
    public BigDecimal deliveryPrice;
    public Boolean deliveryPriceFrom;
    public Boolean minimalOrderPrice;
    public Integer deliveryTime;
    public LocalTime deliveryWorkStart;
    public LocalTime deliveryWorkEnd;
    public LocalTime workStart;
    public LocalTime workEnd;
    public String siteUrl;
    public Integer rank;
    public String phoneNumbers;
    public String viewLink;
    public List<Address> addresses = new ArrayList<>();

    public CafeDto(Cafe cafe) {
        this.name = cafe.getName();
        this.type = cafe.getType();
        this.description = cafe.getDescription();
        this.hasDelivery = cafe.getHasDelivery();
        this.deliveryPrice = cafe.getDeliveryPrice();
        this.deliveryPriceFrom = cafe.getDeliveryPriceFrom();
        this.minimalOrderPrice = cafe.getMinimalOrderPrice();
        this.deliveryTime = cafe.getDeliveryTime();
        this.deliveryWorkStart = cafe.getDeliveryWorkStart();
        this.deliveryWorkEnd = cafe.getDeliveryWorkEnd();
        this.workStart = cafe.getWorkStart();
        this.workEnd = cafe.getWorkEnd();
        this.siteUrl = cafe.getSiteUrl();
        this.rank = cafe.getRank();
        this.phoneNumbers = cafe.getPhoneNumbers();
        this.viewLink = cafe.getViewLink();
        this.addresses = cafe.getAddresses();
    }

    public String getDeliveryPriceDescription() {
        if (deliveryPriceFrom != null)
            return "Бесплатная при заказе от " + deliveryPrice + " руб.";

        if (minimalOrderPrice != null)
            return "Бесплатная. Минимальный заказ от " + deliveryPrice + " руб.";

        if (BigDecimal.ZERO.compareTo(deliveryPrice) == 0)
            return "Бесплатная";

        return deliveryPrice + " руб.";
    }

    public String getPhone() {
        return StringUtils.hasText(phoneNumbers) ? phoneNumbers : NO_INFO;
    }

    public String getSiteUrl() {
        if(StringUtils.endsWithIgnoreCase(siteUrl, "/")) {
            return siteUrl.replaceFirst(".$", "");
        }

        return StringUtils.hasText(siteUrl) ? siteUrl : "";
    }

    public String getWorkTime() {
        if(deliveryWorkStart == null || deliveryWorkEnd == null) {
            return NO_INFO;
        }

        if(deliveryWorkStart.equals(deliveryWorkEnd)) {
            return "Круглосуточно";
        }

        return MessageFormat.format("с {0} до {1}",
                deliveryWorkStart.format(TIME_FORMATTER), deliveryWorkEnd.format(TIME_FORMATTER));
    }

    public List<String> getAddresses() {
        return addresses.stream().map(a -> {
            StringBuilder sb = new StringBuilder();
            if(a.getCity().contains("пос.")) {
                sb.append("{0}");
            } else {
                sb.append("г. {0}");
            }

            if(a.getStreet().matches("просп\\.|пер\\.|бр\\.")) {
                sb.append(", {1}, {2}");
            } else {
                sb.append(", ул. {1}, {2}");
            }
            return MessageFormat.format(sb.toString(), a.getCity(), a.getStreet(), a.getHouse());
        }).collect(Collectors.toList());
    }




}
