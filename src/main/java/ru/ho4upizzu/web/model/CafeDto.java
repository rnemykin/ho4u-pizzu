package ru.ho4upizzu.web.model;

import lombok.Getter;
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

@Getter
public class CafeDto {
    private static final String NO_INFO = "Нет информации";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private String name;
    private String type;
    private String description;
    private Boolean hasDelivery;
    private BigDecimal deliveryPrice;
    private Boolean deliveryPriceFrom;
    private Boolean minimalOrderPrice;
    private Integer deliveryTime;
    private LocalTime deliveryWorkStart;
    private LocalTime deliveryWorkEnd;
    private LocalTime workStart;
    private LocalTime workEnd;
    private String siteUrl;
    private Integer rank;
    private String phoneNumbers;
    private String viewLink;
    private List<Address> addresses = new ArrayList<>();

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
        if (Boolean.TRUE.equals(deliveryPriceFrom)) {
            return "Бесплатная при заказе от " + deliveryPrice + " руб.";
        }

        if (Boolean.TRUE.equals(minimalOrderPrice)) {
            return "Бесплатная. Минимальный заказ от " + deliveryPrice + " руб.";
        }

        if (BigDecimal.ZERO.compareTo(deliveryPrice) == 0) {
            return "Бесплатная";
        }

        return deliveryPrice + " руб.";
    }

    public String getPhone() {
        return StringUtils.hasText(phoneNumbers) ? phoneNumbers : NO_INFO;
    }

    public String getSiteUrl() {
        if (StringUtils.endsWithIgnoreCase(siteUrl, "/")) {
            return siteUrl.replaceFirst(".$", "");
        }

        return StringUtils.hasText(siteUrl) ? siteUrl : "";
    }

    public String getWorkTime() {
        if (deliveryWorkStart == null || deliveryWorkEnd == null) {
            return NO_INFO;
        }

        if (deliveryWorkStart.equals(deliveryWorkEnd)) {
            return "Круглосуточно";
        }

        return MessageFormat.format("с {0} до {1}",
                deliveryWorkStart.format(TIME_FORMATTER), deliveryWorkEnd.format(TIME_FORMATTER));
    }

    public List<String> getAddressesAsStrings() {
        return addresses.stream().map(a -> {
            StringBuilder sb = new StringBuilder();
            if (a.getCity().contains("пос.")) {
                sb.append("{0}");
            } else {
                sb.append("г. {0}");
            }

            if (a.getStreet().matches("просп\\.|пер\\.|бр\\.")) {
                sb.append(", {1}, {2}");
            } else {
                sb.append(", ул. {1}, {2}");
            }
            return MessageFormat.format(sb.toString(), a.getCity(), a.getStreet(), a.getHouse());
        }).collect(Collectors.toList());
    }

}
