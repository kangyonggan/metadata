(function ($) {
    $.fn.ace = {
        skin: function(skin_class) {

            var skins = {'no-skin':'#438EB9', 'skin-1':'#222A2D', 'skin-2':'#C6487E', 'skin-3':'#D0D0D0'};

            $(".dropdown-caret li a").removeClass("selected");
            $(".dropdown-caret li a[data-color='" + skins[skin_class] + "']").addClass("selected");

            $(".btn-colorpicker").css("background-color", skins[skin_class]);

            //skin cookie tip
            var body = $(document.body);
            body.removeClass('no-skin skin-1 skin-2 skin-3');
            //if(skin_class != 'skin-0') {
            body.addClass(skin_class);
            ace.data.set('skin', skin_class);
            //save the selected skin to cookies
            //which can later be used by your server side app to set the skin
            //for example: <body class="<?php echo $_COOKIE['ace_skin']; ?>"
            //} else ace.data.remove('skin');

            var skin3_colors = ['red', 'blue', 'green', ''];

            //undo skin-1
            $('.ace-nav > li.grey').removeClass('dark');

            //undo skin-2
            $('.ace-nav > li').removeClass('no-border margin-1');
            $('.ace-nav > li:not(:last-child)').removeClass('light-pink').find('> a > '+ace.vars['.icon']).removeClass('pink').end().eq(0).find('.badge').removeClass('badge-warning');
            $('.sidebar-shortcuts .btn')
                .removeClass('btn-pink btn-white')
                .find(ace.vars['.icon']).removeClass('white');

            //undo skin-3
            $('.ace-nav > li.grey').removeClass('red').find('.badge').removeClass('badge-yellow');
            $('.sidebar-shortcuts .btn').removeClass('btn-primary btn-white')
            var i = 0;
            $('.sidebar-shortcuts .btn').each(function() {
                $(this).find(ace.vars['.icon']).removeClass(skin3_colors[i++]);
            });

            var skin0_buttons = ['btn-success', 'btn-info', 'btn-warning', 'btn-pink'];
            if(skin_class == 'no-skin') {
                $(".widget-box").removeClass("widget-color-pink widget-color-blue widget-color-dark");
                $(".widget-box").addClass("widget-color-blue");
                $(".widget-box .widget-title").removeClass("dark white");
                $(".widget-box .widget-title").addClass("dark");
                $(".skin-inverse").removeClass("dark white");
                $(".skin-inverse").addClass("dark");

                $(".skin-btn").removeClass("btn-inverse btn-primary btn-pink");
                $(".skin-btn").addClass("btn-primary");

                $(".skin-color").removeClass("color-skin-1 color-skin-2 color-skin-3");
                $(".skin-color").addClass("color-skin-1");

                var i = 0;
                $('.sidebar-shortcuts .btn').each(function() {
                    $(this).attr('class', 'btn ' + skin0_buttons[i++%4]);
                })

                $('.sidebar[data-sidebar-scroll=true]').ace_sidebar_scroll('updateStyle', '');
                $('.sidebar[data-sidebar-hover=true]').ace_sidebar_hover('updateStyle', 'no-track scroll-thin');
            }

            else if(skin_class == 'skin-1') {
                $(".widget-box").removeClass("widget-color-pink widget-color-blue widget-color-dark");
                $(".widget-box").addClass("widget-color-blue");
                $(".widget-title").removeClass("dark white");
                $(".widget-title").addClass("dark");

                $(".skin-inverse").removeClass("dark white");
                $(".skin-inverse").addClass("dark");

                $(".skin-btn").removeClass("btn-inverse btn-primary btn-pink");
                $(".skin-btn").addClass("btn-primary");

                $(".skin-color").removeClass("color-skin-1 color-skin-2 color-skin-3");
                $(".skin-color").addClass("color-skin-1");

                $('.ace-nav > li.grey').addClass('dark');
                var i = 0;
                $('.sidebar-shortcuts')
                    .find('.btn').each(function() {
                    $(this).attr('class', 'btn ' + skin0_buttons[i++%4]);
                })

                $('.sidebar[data-sidebar-scroll=true]').ace_sidebar_scroll('updateStyle', 'scroll-white no-track');
                $('.sidebar[data-sidebar-hover=true]').ace_sidebar_hover('updateStyle', 'no-track scroll-thin scroll-white');
            }

            else if(skin_class == 'skin-2') {
                $(".widget-box").removeClass("widget-color-pink widget-color-blue widget-color-dark");
                $(".widget-box").addClass("widget-color-pink");
                $(".widget-title").removeClass("dark white");
                $(".widget-title").addClass("dark");

                $(".skin-inverse").removeClass("dark white");
                $(".skin-inverse").addClass("dark");

                $(".skin-btn").removeClass("btn-inverse btn-primary btn-pink");
                $(".skin-btn").addClass("btn-pink");

                $(".skin-color").removeClass("color-skin-1 color-skin-2 color-skin-3");
                $(".skin-color").addClass("color-skin-2");

                $('.ace-nav > li').addClass('no-border margin-1');
                $('.ace-nav > li:not(:last-child)').addClass('light-pink').find('> a > '+ace.vars['.icon']).addClass('pink').end().eq(0).find('.badge').addClass('badge-warning');

                $('.sidebar-shortcuts .btn').attr('class', 'btn btn-white btn-pink')
                    .find(ace.vars['.icon']).addClass('white');

                $('.sidebar[data-sidebar-scroll=true]').ace_sidebar_scroll('updateStyle', 'scroll-white no-track');
                $('.sidebar[data-sidebar-hover=true]').ace_sidebar_hover('updateStyle', 'no-track scroll-thin scroll-white');
            }

            //skin-3
            //change shortcut buttons classes, this should be hard-coded if you want to choose this skin
            else if(skin_class == 'skin-3') {

                $(".widget-box").removeClass("widget-color-pink widget-color-blue widget-color-dark");
                $(".widget-box").addClass("widget-color-dark");
                $(".widget-title").removeClass("dark white");
                $(".widget-title").addClass("white");

                $(".skin-inverse").removeClass("dark white");
                $(".skin-inverse").addClass("white");

                $(".skin-btn").removeClass("btn-inverse btn-primary btn-pink");
                $(".skin-btn").addClass("btn-inverse");

                $(".skin-color").removeClass("color-skin-1 color-skin-2 color-skin-3");
                $(".skin-color").addClass("color-skin-3");

                body.addClass('no-skin');//because skin-3 has many parts of no-skin as well

                $('.ace-nav > li.grey').addClass('red').find('.badge').addClass('badge-yellow');

                var i = 0;
                $('.sidebar-shortcuts .btn').each(function() {
                    $(this).attr('class', 'btn btn-primary btn-white');
                    $(this).find(ace.vars['.icon']).addClass(skin3_colors[i++]);
                })

                $('.sidebar[data-sidebar-scroll=true]').ace_sidebar_scroll('updateStyle', 'scroll-dark no-track');
                $('.sidebar[data-sidebar-hover=true]').ace_sidebar_hover('updateStyle', 'no-track scroll-thin');
            }

            //some sizing differences may be there in skins, so reset scrollbar size
            $('.sidebar[data-sidebar-scroll=true]').ace_sidebar_scroll('reset')
            //$('.sidebar[data-sidebar-hover=true]').ace_sidebar_hover('reset')

            if(ace.vars['old_ie']) ace.helper.redraw(document.body, true);
        }
    };
})(jQuery);

