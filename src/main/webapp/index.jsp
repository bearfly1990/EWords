<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Ewords</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================
    <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>-->  
<!--===============================================================================================
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">-->
<!--===============================================================================================
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">-->
<!--===============================================================================================
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">-->
<!--===============================================================================================
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">-->
<!--===============================================================================================
    <link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">-->
<!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css" >
<!--    <link rel="stylesheet" type="text/css" href="css/jquery-confirm.min.css"/>-->  
<!--===============================================================================================-->
<!--===============================================================================================-->  
    <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================
    <script src="vendor/bootstrap/js/popper.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>-->
<!--===============================================================================================
    <script src="vendor/select2/select2.min.js"></script>-->
<!--===============================================================================================-->
    <script src="js/main.js"></script>
    <script src="js/jquery-ui.min.js"></script>
 <!--   <script type="text/javascript" src="js/jquery-confirm.min.js"></script> -->
<script type="text/javascript">
$(document).ready(function(){ 
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
	                                   	   var listr = '<div class="row detail"><div class="cell eword">{0}</div><div class="cell cword hidden">{1}</div><div class="cell oper"><a class="del" id={2} >delete</a></div></div>'
	                                   	   console.log(data);
	                                   	   listr = listr.format($('#taeword').val(), $('#tacword').val(), data.id);
	                                       $('.table').append(listr);
	                                       toggleLastRow();
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
	    	var listr = '<div class="row detail add"><div class="cell eword"><a>++添加++</a></div><div class="cell cword hidden"></div><div class="cell oper"></div></div>';
            $('.table').append(listr);
	    	$(data).each(function (index, word) {
	    		 var listr = '<div class="row detail"><div class="cell eword">{0}</div><div class="cell cword hidden">{1}</div><div class="cell oper"><a class="del" id={2} >delete</a></div></div>'
	    	     listr = listr.format(word.eword, word.cword, word.id);
	    		 $('.table').append(listr); 
	    	});
	    	toggleHidden();
	        
	        $(".add").click(function(event){
	        	  $( "#dialog" ).dialog( "open" );
	        	   // Link to open the dialog
	              event.preventDefault();
	        });

	        $(".del").each(function (index, row) {
                //console.log(row);
                $(row).click(function(){
                	var flag = confirm("Delete it?");//window.confirm("Press a button");  
                	if (flag==true)  
                	{  
                		$.ajax({
                            url:'delword',
                            type:'POST', //POST
                            async:true,    //或false,是否异步
                            data:{id:$(row).attr("id")
                            },
                            timeout:10000,    //超时时间
                            dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                            complete:function(data){
                            	   if(data.responseText=="success") {
                            		   $(row).parent().parent().remove();
                            		   //toggleLastRow();
                                   }else{
                                       alert("delete error!")
                                   }
                            	   //toggleHidden();
                            }
                        }); 
                	}  
                	
                });
            });
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
	

});

</script>

</head>
<body>
    
    <div class="limiter">
        <div class="container-table100">
            <div class="wrap-table100">
                    <div class="table">

                        <div class="row header">
                            <div class="cell">
                                Eword
                            </div>
                            <div class="cell">
                                Cword
                            </div>
                            <div class="cell">
                                Operation
                            </div>
                            
                        </div>
                    </div>
            </div>
        </div>
    </div>


<!-- ui-dialog -->
<div id="dialog" title="Add word">
    <div>eword:<textarea id="taeword" name="taeword" cols ="40" rows = "1"></textarea></div>
    <div>cword:<textarea id="tacword" name="tacword" cols ="40" rows = "2"></textarea></div>
</div>

    



</body>
</html>