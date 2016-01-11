// JavaScript Document


//导航菜单
function navList(id) {
    var $obj = $("#nav_dot"), $item = $("#J_nav_" + id);
    $item.addClass("on").parent().removeClass("none").parent().addClass("selected");
    $obj.find("h4").hover(function () {
        $(this).addClass("hover");
    }, function () {
        $(this).removeClass("hover");
    });
    $obj.find("p").hover(function () {
        if ($(this).hasClass("on")) { return; }
        $(this).addClass("hover");
    }, function () {
        if ($(this).hasClass("on")) { return; }
        $(this).removeClass("hover");
    });
    $obj.find("h4").click(function () {
        var $div = $(this).siblings(".list-item");
        if ($(this).parent().hasClass("selected")) {
            $div.slideUp(600);
            $(this).parent().removeClass("selected");
        }
        if ($div.is(":hidden")) {
            $("#nav_dot li").find(".list-item").slideUp(600);
            $("#nav_dot li").removeClass("selected");
            $(this).parent().addClass("selected");
            $div.slideDown(600);

        } else {
            $div.slideUp(600);
        }
    });
}
//显示系统当前时间
function checktime(i)
{
	if(i<10)
	{
		i="0"+i;
	}
	return i;
}

/****表格隔行高亮显示*****/
window.onload=function(){
	oTable=document.getElementById("tab");//找表格
	aTr=document.getElementsByTagName("tr");//找所有的行
	for(i=0;i<aTr.length;i++){
		if(i%2==0){
			aTr[i].style.background="#fff";	
		}else{
			aTr[i].style.background="#ccc";	
		};
	};
	var show = document.getElementById("showtime");
	var weekday=new Array(7)
	weekday[0]="星期天"
	weekday[1]="星期一"
	weekday[2]="星期二"
	weekday[3]="星期三"
	weekday[4]="星期四"
	weekday[5]="星期五"
	weekday[6]="星期六"

	setInterval
		(
		function() 
		{
		var time = new Date();    // 程序计时的月从0开始取值后+1
		var t = time.getFullYear()+"-"+
			(time.getMonth()+1)+"-"+
			time.getDate()+" "+
			weekday[time.getDay()]+" "+
			time.getHours()+":"+
			checktime(time.getMinutes())+":"+
			checktime(time.getSeconds());
			show.innerHTML = t;
		},1000
		);
}
