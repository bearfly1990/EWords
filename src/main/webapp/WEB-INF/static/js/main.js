
(function ($) {
    "use strict";

})(jQuery);

String.prototype.format = function(args) {
    var result = this;
    if (arguments.length > 0) {    
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                if(args[key]!=undefined){
                    var reg = new RegExp("({" + key + "})", "g");
                    result = result.replace(reg, args[key]);
                }
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] != undefined) {
                var reg = new RegExp("({)" + i + "(})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
    }
    return result;
}
function toggleHidden(){
	$(".detail").each(function (index, row) {
	    //console.log(row);
	    $(row).hover(function(){
	    	$(row).children(".cword").toggleClass('hidden');
	    	$(row).children(".oper").children(".del").toggle();
	    });
	});
}
function toggleLastRow(){
	 $('.table>.row:last').hover(function(){
     	$('.table>.row:last').children(".cword").toggleClass('hidden');
         $('.table>.row:last').children(".oper").children(".del").toggle();
     });
}
