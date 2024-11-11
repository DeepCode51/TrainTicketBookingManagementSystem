$(function(){
	    var $this = $(".renav");
	    var scrollTimer;
	    $this.hover(function(){
	          clearInterval(scrollTimer);
	     },function(){
	       scrollTimer = setInterval(function(){
	                     scrollNews($this);
	                }, 3000 );
	    }).trigger("mouseout");
	});
	function scrollNews(obj){
	   var $self = obj.find("h4:first");
	   var lineHeight = $self.find("p:first").height();  
	   $self.animate({ "margin-top":-lineHeight +"px" },1000,function(){
	         $self.css({"margin-top":"0px"}).find("p:first").appendTo($self);
	   })
}
	