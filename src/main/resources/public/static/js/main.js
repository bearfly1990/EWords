
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

function confirmDel(text, callback) {
	 $( "#delConfirm" ).dialog({
		 modal: true,
         resizable: false,
         buttons: [
             {
                 text: "OK",
                 click: function() {
                	 $( this ).dialog( "close" );
                	 console.log("ok");
                	 callback.call();
                 }
             },
             {
                 text: "Cancel",
                 click: function() {
                     $( this ).dialog( "close" );
                 }
             }
         ]
     });
}

function bindDeleteLinks(){
	$(".del").on("click", function(event){
		var thisRow = $(this);
		confirmDel("confirmDel", function(){
			$.ajax({
                url:'delword',
                type:'POST', //POST
                async:true,    //或false,是否异步
                data:{id:thisRow.attr("id")
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
		}); 
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
    	//var flag = confirm("Delete it?");//window.confirm("Press a button");  
		//var flag =  $( "#delConfirm" ).dialog( "open" );
		confirmDel("confirmDel", function(){
			$.ajax({
                url:'delword',
                type:'POST', //POST
                async:true,    //或false,是否异步
                data:{id:thisRow.attr("id")
                },
                timeout:5000,    //超时时间
                dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                complete:function(data){
                	   if(data.responseText=="success") {
                		   thisRow.parent().parent().remove();
                       }else{
                           alert("delete error!")
                       }
                }
            });
		});
	});
	
	
 /*   $(".del").each(function (index, row) {
        //console.log(row);
        $(row).click(function(){

        });
    });*/
}

function initDelConfirm(){
	 $("#delConfirm").hide();
}
function initAddEword(){
	   $( "#dialog" ).dialog({
	        autoOpen: false,
	        width: 400,
	        buttons: [
	            {
	                text: "OK",
	                click: function() {
	                    $( this ).dialog( "close" );
	                    $.ajax({
	                        url:'addword',
	                        type:'POST', //POST
	                        async:true,    //或false,是否异步
	                        data:{
	                            eword:$('#taeword').val(),
	                        	cword:$('#tacword').val()
	                        },
	                        timeout:5000,    //超时时间
	                        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	                        complete:function(data){
	                            if(data.responseText=="success") {
	                            	   $.ajax({
	                                       url:'eword',
	                                       type:'GET', //POST
	                                       async:true,    //或false,是否异步
	                                       data:{
	                                    	   eword:$('#taeword').val(),
	                                    	   cword:$('#tacword').val()
	                                       },
	                                       timeout:10000,    //超时时间
	                                       dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	                                       success:function(data){
		                                   	   var listr = '<div class="row detail"><div class="cell eword">{0}</div><div class="cell cword"><span class="cspan">{1}</cspan></div><div class="cell oper"><a class="del" id={2} >delete</a></div></div>'
		                                   	   listr = listr.format($('#taeword').val(), $('#tacword').val(), data.id);
		                                       $('.table').append(listr);
		                                       lastRowDisplay();
		                                       lastBindDeleteLinks();
	                                       }
	                                   });
	                            	
	                            	//alert("add successfully!")
	                            }else{
	                            	alert("add error!")
	                            }
	                           
	                        }
	                    }); 
	                }
	            },
	            {
	                text: "Cancel",
	                click: function() {
	                    $( this ).dialog( "close" );
	                }
	            }
	        ]
	    });
}

function initQueryEword(){
	//Get the Eword Information
	$.ajax({
	    url:'ewords',
	    type:'GET', //POST
	    async:true,    //或false,是否异步
	    data:{
	    },
	    timeout:5000,    //超时时间
	    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
	    beforeSend:function(xhr){
	        //console.log(xhr)
	        //console.log('before send')
	    },
	    success:function(data,textStatus,jqXHR){
	    	var listr = '<div class="row detail add"><div class="cell eword"><a>++添加++</a></div><div class="cell cword"></div><div class="cell oper"></div></div>';
            $('.table').append(listr);
	    	$(data).each(function (index, word) {
	    		 var listr = '<div class="row detail"><div class="cell eword">{0}</div><div class="cell cword"><span class="cspan">{1}</span></div><div class="cell oper"><a class="del" id={2} >delete</a></div></div>'
	    	     listr = listr.format(word.eword, word.cword, word.id);
	    		 $('.table').append(listr); 
	    		 //toggleLastRow();
	    	});
	    	toggleDisplay();
	        
	        $(".add").click(function(event){
	        	  $( "#dialog" ).dialog( "open" );
	        	   // Link to open the dialog
	              event.preventDefault();
	        });
	        // Delete 
            bindDeleteLinks();
	        //console.log(textStatus)
	        //console.log(jqXHR)
	    },
	    error:function(xhr,textStatus){
	        //console.log('error')
	        //console.log(xhr)
	        //console.log(textStatus)
	    },
	    complete:function(){
	        //console.log('end request')
	    }
	});
}
