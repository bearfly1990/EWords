
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
function toggleDisplay(){
	$(".row").on("mouseover mouseout",function(event){
		//if(event.type == "mouseover"){}
    	$(this).children(".cword").children(".cspan").toggle();
    	$(this).children(".oper").children(".del").toggle();
    });
}

function lastRowDisplay(){
	$(".row").last().on("mouseover mouseout",function(event){
		//if(event.type == "mouseover"){}
    	$(this).children(".cword").children(".cspan").toggle();
    	$(this).children(".oper").children(".del").toggle();
    });
}

function bindDeleteLinks(){
	$(".del").on("click", function(event){
		var thisRow = $(this);
    	var flag = confirm("Delete it?");//window.confirm("Press a button");  
    	if (flag==true)
    	{  
    		$.ajax({
                url:'delword',
                type:'POST', //POST
                async:true,    //或false,是否异步
                data:{id:$(this).attr("id")
                },
                timeout:10000,    //超时时间
                dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                complete:function(data){
                	   if(data.responseText=="success") {
                		   thisRow.parent().parent().remove();
                       }else{
                           alert("delete error!")
                       }
                }
            }); 
    	}  
	});
	
	
 /*   $(".del").each(function (index, row) {
        //console.log(row);
        $(row).click(function(){

        });
    });*/
}

function lastBindDeleteLinks(){
	$(".del").last().on("click", function(event){
		var thisRow = $(this);
    	var flag = confirm("Delete it?");//window.confirm("Press a button");  
    	if (flag==true)  
    	{  
    		$.ajax({
                url:'delword',
                type:'POST', //POST
                async:true,    //或false,是否异步
                data:{id:$(this).attr("id")
                },
                timeout:10000,    //超时时间
                dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                complete:function(data){
                	   if(data.responseText=="success") {
                		   thisRow.parent().parent().remove();
                       }else{
                           alert("delete error!")
                       }
                }
            }); 
    	}  
	});
	
	
 /*   $(".del").each(function (index, row) {
        //console.log(row);
        $(row).click(function(){

        });
    });*/
}
