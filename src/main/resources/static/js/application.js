"use strict";

jQuery(document).ready(function () {
    $(".collapse").click(function () {
        var div = $(this).parents(".address");
        var subAddress = div.children(".sub-address");
        subAddress.toggle();
    });

    $(window).scroll(function () {
        if ($(this).scrollTop() > 500) {
            $("#go-top").fadeIn();
        } else {
            $("#go-top").fadeOut();
        }
    });

    $("#go-top").click(function () {
        $("html, body").animate({ scrollTop: 0 }, 600);
        return false;
    });

    var onClick = function(event){
        event.preventDefault();

        var deliverNow = $('#deliver-now').is(':checked'),
            withDelivery = $('#with-delivery').is(':checked');

        if(!deliverNow && !withDelivery){
            window.location.href = '/';
            return;
        }

        if(deliverNow){
           window.location.href = '/deliver-now?sort=deliveryPrice';
            return;
        }

        if(withDelivery){
            window.location.href  = '/with-delivery?sort=deliveryPrice';
            return;
        }

    };

    $('#deliver-now').click(function(event){
        var checked = $(this).is(':checked');
        $("#with-delivery").prop('checked', checked);

        onClick(event);

    });

    $('#with-delivery').click(function(event){
         var checked = $(this).is(':checked');
        if(!checked){
            $("#deliver-now").prop('checked', false);
        }

        onClick(event);
    });


    $('.sorting').click(function(event){
        event.preventDefault();

        var url = window.location.href;
        url = url.substr(0, url.indexOf('?'));
        window.location.href = url + '?sort=' + $(this).data('sort');
    });

});