function panelOneObjectCreator(img,linktoweb,panelName,timeInsec, evtDate, evtNotes)
{
this.img=img;
this.linktoweb=linktoweb;
this.panelName=panelName;
this.timeInsec=timeInsec*1000;
this.evtDate = evtDate;
this.evtNotes = evtNotes;
}

function panelTwoObjectCreator(photo,linktoweb1,panelName2,timeInsec2,evtDate2, evtNotes2)
{
this.photo=photo;
this.linktoweb1=linktoweb1;
this.panelName2=panelName2;
this.timeInsec2=timeInsec2*1000;
this.evtDate2 = evtDate2;
this.evtNotes2 = evtNotes2;
}

function advertisementBean(path, advLinkToWeb, advPName, timeInsecondsToDis)
{
this.path = path;
this.altw = advLinkToWeb;
this.adpn = advPName;
this.disTime = timeInsecondsToDis*1000;
}


//this is for auto logout function..
function autologout()
{
var events = ['click', 'mousemove', 'keydown'],
i = events.length,
timer,
delay = 120000,
logout = function () {
	window.location = "logout.action"
},
reset = function () {
    clearTimeout(timer);
    timer = setTimeout(logout, 120000);
};

while (i) {
i -= 1;
document.addEventListener(events[i], reset, false);
}
reset();
}


//this is to close the pop up window automatically after specified time..
function PopWin(urlName) {
	
	var popup = window.open(urlName, "","fullscreen=1");
	if (window.focus) {popup.focus()}
	setTimeout(function(){popup.close()}, 300000);
}
/*	
	var doc = popup.document;
	var events1 = ['click', 'mousemove', 'keydown'],
	i = events1.length,
	timer1,
	delay1 = 5000,
	logout1 = function () {
		//window.location = "logout.action"
		popup.alert("popup close")
		popup.close();
	},
	reset1 = function () {
		
	    clearTimeout(timer1);
		
	    timer1 = setTimeout(function(){popup.close()}, 5000);
		
	};

	while (i) {
	i -= 1;
	
	popup.addEventListener(events1[i], reset1, false);
	
	}
	
	reset1();
	
	//setTimeout(function(){popup.close()}, 5000)*/

// Pop Up Window for Advertizement.	
function PopUpAdv(urlName) {
	
	var popup1 = window.open(urlName, "","fullscreen=1");
	if (window.focus) {popup1.focus()}
	setTimeout(function(){popup1.close()}, 300000);
}

//Pop up WIndow for News Letter. Pdf wil open in new Window and automatically closes after specified time
function callBrowseHierarchy(){
	var po = window.open("news.action","Browse","fullscreen=1,toolbar=0,resizable=0");
	setTimeout(function(){po.close()}, 5000);
	}


var panelOneObjList = new Array();
var panelTwoObjList = new Array();
var panelThreeObjList = new Array();



var imgNumber = 0

var img = new Array();
var link = new Array();
var panelOneLabel= new Array();
var panelOneTimeInterval = new Array();
var panelTwoLabel = new Array();
var photos = new Array();
var link1 = new Array();
var intervalaaaa = setInterval(function(){NextImage()}, 10000);
var Photo_intervalaaaa = setInterval(function(){NextPhoto()}, 1000);
var adv_interval1 = setInterval(function(){NextAdv1()}, 1000);
var adv_interval2 = setInterval(function(){NextAdv2()}, 1000);


function windowOnloadFunction(obj1, obj2, obj3)
{
	var bImage = document.getElementById("body");
	 bImage.style.background = "url(resources/BG6.jpg)";
	
	panelOneObjList = obj1;
	panelTwoObjList = obj2;
	panelThreeObjList= obj3;
	
	
	if(panelOneObjList.length>0)
		{
		clearInterval(intervalaaaa)
		document.getElementById("events").src = 'upcoming/'+panelOneObjList[0].img	
		document.getElementById("panelOneLabel").innerHTML = panelOneObjList[0].panelName;
		document.getElementById("panelOneDate").innerHTML = panelOneObjList[0].evtDate
		document.getElementById("panelOneNotes").innerHTML =panelOneObjList[0].evtNotes
		
		intervalaaaa = setInterval(function(){NextImage()}, panelOneObjList[0].timeInsec)
		}
	else
		{
		document.getElementById("events").src = 'upcoming/Alt_Panel1.jpg'	
		}
	
	if(panelTwoObjList.length>0)
		{
		
		clearInterval(Photo_intervalaaaa)
		document.getElementById("photos").src = 'photos/'+panelTwoObjList[0].photo	
		document.getElementById("panelTwoLabel").innerHTML = panelTwoObjList[0].panelName2
		document.getElementById("panelTwoDate").innerHTML = panelTwoObjList[0].evtDate2
		document.getElementById("panelTwoNotes").innerHTML =panelTwoObjList[0].evtNotes2
		Photo_intervalaaaa = setInterval(function(){NextPhoto()}, panelTwoObjList[0].timeInsec2)
		
		}
	else
		{
		document.getElementById("photos").src = 'photos/Alt_Panel2.jpg'
		}
	
	if(panelThreeObjList.length>0)
	{
	
	clearInterval(adv_interval1)
	document.getElementById("adv1").src = 'adver/'+panelThreeObjList[0].path	
	document.getElementById("panelThreeLabel").innerHTML = panelThreeObjList[0].adpn
	adv_interval1 = setInterval(function(){NextAdvt1()}, panelThreeObjList[0].disTime)
	document.getElementById("linkToWebAd1").value = panelThreeObjList[0].altw
	
	if(panelThreeObjList.length>1)
		{
		clearInterval(adv_interval2)
		document.getElementById("adv2").src = 'adver/'+panelThreeObjList[1].path	
		document.getElementById("panelThreeLabel").innerHTML = panelThreeObjList[1].adpn
		adv_interval2 = setInterval(function(){NextAdvt2()}, panelThreeObjList[1].disTime)
		document.getElementById("linkToWebAd2").value = panelThreeObjList[1].altw
		
		}
	else
		{
		document.getElementById("adv2").src = 'adver/Alt_Advertise3.jpg'
		}
	
	}
else
	{
	document.getElementById("adv1").src = 'adver/Alt_Advertise1.jpg'	
	document.getElementById("adv2").src = 'adver/Alt_Advertise3.jpg'	
	
	}
	
	showOnError()
}

function NextImage()
   {
	if(panelOneObjList.length>0)
       {
		imgNumber++
		
       if (imgNumber == panelOneObjList.length){
           imgNumber = 0
       } 
           clearInterval(intervalaaaa)
     intervalaaaa = setInterval(function(){NextImage()}, panelOneObjList[imgNumber].timeInsec);
       document.getElementById("events").src = 'upcoming/'+panelOneObjList[imgNumber].img;
       document.getElementById("panelOneLabel").innerHTML = panelOneObjList[imgNumber].panelName;
       document.getElementById("panelOneDate").innerHTML = panelOneObjList[imgNumber].evtDate
		document.getElementById("panelOneNotes").innerHTML =panelOneObjList[imgNumber].evtNotes
   }
	else
	{
	
	
	}
   }

   function PreviousImage()
   {
	   if(panelOneObjList.length>0)
       {
	   imgNumber--
       if (imgNumber < 0){
           imgNumber = panelOneObjList.length - 1
       }
	   clearInterval(intervalaaaa) 
	   intervalaaaa = setInterval(function(){NextImage()}, panelOneObjList[imgNumber].timeInsec);
       document.getElementById("events").src = 'upcoming/'+panelOneObjList[imgNumber].img;
	   document.getElementById("panelOneLabel").innerHTML = panelOneObjList[imgNumber].panelName;
	   document.getElementById("panelOneDate").innerHTML = panelOneObjList[imgNumber].evtDate
		document.getElementById("panelOneNotes").innerHTML =panelOneObjList[imgNumber].evtNotes
   }
	   else
		   {
		   
		   }
		   }
   
   function linkToWeb()
   {
	   
   	if(panelOneObjList[imgNumber].linktoweb==null || panelOneObjList[imgNumber].linktoweb=="")
   		{
   		document.getElementById("linkToWeb").href = ""
   		}
   	else
   		{
   		
   		PopWin(panelOneObjList[imgNumber].linktoweb);
   		//document.getElementById("linkToWeb").href = panelOneObjList[imgNumber].linktoWeb
   		}
   }
   
   
   
   var photoNumber = 0
  

   function NextPhoto()
   {
   		if(panelTwoObjList.length>0)
   			{
       photoNumber++
       if (photoNumber == panelTwoObjList.length)
    	   {
    	   photoNumber = 0
    	   }
   		clearInterval(Photo_intervalaaaa)
   		Photo_intervalaaaa = setInterval(function(){NextPhoto()}, panelTwoObjList[photoNumber].timeInsec2);
       document.getElementById("photos").src = 'photos/'+panelTwoObjList[photoNumber].photo;
       document.getElementById("panelTwoDate").innerHTML = panelTwoObjList[photoNumber].evtDate2
		document.getElementById("panelTwoNotes").innerHTML =panelTwoObjList[photoNumber].evtNotes2
       
       document.getElementById("panelTwoLabel").innerHTML= panelTwoObjList[imgNumber].panelName2;
   }
   }

   function PreviousPhoto()
   {
	   if(panelTwoObjList.length>0)
   			{
       photoNumber--
       if (photoNumber < 0)
    	   {photoNumber = panelTwoObjList.length - 1}
       clearInterval(Photo_intervalaaaa)
       Photo_intervalaaaa = setInterval(function(){NextPhoto()}, panelTwoObjList[photoNumber].timeInsec2);
       document.getElementById("photos").src = 'photos/'+panelTwoObjList[photoNumber].photo;
       document.getElementById("panelTwoLabel").innerHTML= panelTwoObjList[photoNumber].panelName2;
       document.getElementById("panelTwoDate").innerHTML = panelTwoObjList[photoNumber].evtDate2
		document.getElementById("panelTwoNotes").innerHTML =panelTwoObjList[photoNumber].evtNotes2
   }}

   function linkToWebForPhotos()
   {
   	if(panelTwoObjList[photoNumber].linktoweb2==null || panelTwoObjList[photoNumber].linktoweb2=="")
   		{
   		document.getElementById("linkToWeb2").href = ""
   		}
   	else
   		{
   		PopWin(panelTwoObjList[photoNumber].linktoweb2)
   		document.getElementById("linkToWeb2").href = panelTwoObjList[photoNumber].linktoweb2
   		}
   }
   
   
   
   
   var advtNumber = 1;
   
   function NextAdvt1()
   {
   		if(panelThreeObjList.length>0)
   			{
   			advtNumber++
       if (advtNumber == panelThreeObjList.length)
    	   {
    	  
    	   advtNumber = 0
    	   }
   		clearInterval(adv_interval1)
   		adv_interval1 = setInterval(function(){NextAdvt1()}, panelThreeObjList[advtNumber].disTime);
       document.getElementById("adv1").src = 'adver/'+panelThreeObjList[advtNumber].path;
       document.getElementById("linkToWebAd1").value = panelThreeObjList[advtNumber].altw
       
       document.getElementById("panelThreeLabel").innerHTML= panelThreeObjList[advtNumber].adpn;
   }
   }
   
   function NextAdvt2()
   {
   		if(panelThreeObjList.length>0)
   			{
   			advtNumber++
       if (advtNumber >= panelThreeObjList.length)
    	   {
    	   advtNumber = 0
    	   }
   		clearInterval(adv_interval2)
   		adv_interval2 = setInterval(function(){NextAdvt2()}, panelThreeObjList[advtNumber].disTime);
       document.getElementById("adv2").src = 'adver/'+panelThreeObjList[advtNumber].path;
       document.getElementById("linkToWebAd2").value = panelThreeObjList[advtNumber].altw
       document.getElementById("panelThreeLabel").innerHTML= panelThreeObjList[advtNumber].adpn;
   }
   }
   
   
  
   
 // onError show alternative Image
   
   function showOnError()
   {
	   document.getElementById('events').onerror = function() { 
		    
		    document.getElementById('events').src = "upcoming/Alt_Panel1.jpg"; 
		  }
	   
	   document.getElementById('photos').onerror = function() { 
		    document.getElementById('photos').src = "photos/Alt_Panel2.jpg"; 
		  }
	   
	   document.getElementById('adv1').onerror = function() { 
		   document.getElementById("adv1").src = 'adver/Alt_Advertise1.jpg'	
		  }
	   
	   document.getElementById('adv2').onerror = function() { 
		   document.getElementById("adv2").src = 'adver/Alt_Advertise3.jpg'
		  }
	   
   }
   
   
   
   function mouse()
   {
     
      window.status ="Go To website...!!! "
   }
   
   var cSpeed=9;
	var cWidth=150;
	var cHeight=150;
	var cTotalFrames=23;
	var cFrameWidth=128;
	var cImageSrc='resources/sprites.gif';
	
	var cImageTimeout=false;
	
	function startAnimation(){
		
		document.getElementById('loaderImage').innerHTML='<canvas id="canvas" width="'+cWidth+'" height="'+cHeight+'"><p>Your browser does not support the canvas element.</p></canvas>';
		document.getElementById("swipecardtext").innerHTML = "<b>Swipe Your Card <br /> on Card Reader.</b>"
		//FPS = Math.round(100/(maxSpeed+2-speed));
		FPS = Math.round(100/cSpeed);
		SECONDS_BETWEEN_FRAMES = 1 / FPS;
		g_GameObjectManager = null;
		g_run=genImage;

		g_run.width=cTotalFrames*cFrameWidth;
		genImage.onload=function (){cImageTimeout=setTimeout(fun, 0)};
		initCanvas();
	}
	
	
	function imageLoader(s, fun)//Pre-loads the sprites image
	{
		clearTimeout(cImageTimeout);
		cImageTimeout=0;
		genImage = new Image();
		genImage.onload=function (){cImageTimeout=setTimeout(fun, 0)};
		genImage.onerror=new Function('alert(\'Could not load the image\')');
		genImage.src=s;
	}
	
	function startAnimatingFromJS()
	{
		
		new imageLoader(cImageSrc, 'startAnimation()');
	}