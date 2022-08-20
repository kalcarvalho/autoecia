// JavaScript Document

/**************************************
* http://vertical-scroller.vbarsan.com/
*    This notice may not be removed 
**************************************/

//-- Begin Scroller's Parameters and messages -->
//scroller's width
var swidth = 180;

//scroller's height
var sheight = 0;


//scroller's speed 
var sspeed  = 3;
var restart = sspeed;
var rspeed  = sspeed;

//scroller's pause
var spause = 0;

//scroller's background
var sbcolor = "#ffffff";

//Messages: set your own; use as many as you'd like; set up Hyperlinks to
//URLs as you normally do: <a target=... href="... URL ...">..message..</a>;
//Preloading images is recommended:
preload1 = new Image();
preload1.src = "imagens/fundomergulho.png";
preload2 = new Image();
preload2.src = "imagens/fundoescalador.png";
preload3 = new Image();
preload3.src = "imagens/fundomergulho.png";
preload4 = new Image();
preload4.src = "http://news-ticker.vbarsan.com/news-ticker.vbarsan.com.jpg";
preload5 = new Image();
preload5.src = "http://javascripts.vbarsan.com/javascripts.vbarsan.com.jpg";
//When no preloading, use below the actual image path instead of '+preload...+';
var singletext = new Array();
singletext[0] = '<table cellspacing="0" cellpadding="0" align="center">' +
					'<tr>' +
						'<td height="180" valign="middle">' +
							'<img border="0" src=' + preload1.src + ' width=200 alt="">' + 
						'</td>' + 
					'</tr>' + 
				'</table>';

//singletext[...]='...';
//-- end Parameters and message -->

//-- begin: Scroller's Algorithm -->
var ii = 0;

function goup(){
	if(sspeed != rspeed * 16 ) {
		sspeed = sspeed * 2;
		restart = sspeed;
	}
}

function godown(){
	if(sspeed != rspeed * -16 ) {
		sspeed = sspeed * -2;
		restart = sspeed;
	}
}

function start() {
	if(document.getElementById) {
		ns6div = document.getElementById('iens6div');
		ns6div.style.top=sheight;
		ns6div.innerHTML = singletext[0];
		sizeup = ns6div.offsetHeight;
		ns6scroll();
	}
	else if(document.layers) {
		ns4layer = document.ns4div.document.ns4div1;
		ns4layer.top = sheight;
		ns4layer.document.write(singletext[0]);
		ns4layer.document.close();
		sizeup = ns4layer.document.height;
		ns4scroll();
	}
	else if(document.all) {
		iediv = iens6div;
		iediv.style.pixelTop = sheight;
		iediv.innerHTML = singletext[0];
		sizeup = iediv.offsetHeight;
		iescroll();
	}
}

function iescroll() { 
	if(iediv.style.pixelTop > 0 && iediv.style.pixelTop <= sspeed) {
		iediv.style.pixelTop = 0;
		setTimeout("iescroll()",spause);
	} 
	else if(iediv.style.pixelTop >= sizeup * -1) {
		iediv.style.pixelTop -= sspeed;
		setTimeout("iescroll()", 100);
	}
	else {
		
		if(ii == singletext.length - 1) ii = 0;
		else ii++;
		iediv.style.pixelTop = sheight;
		iediv.innerHTML = singletext[ii];
		sizeup = iediv.offsetHeight;
		iescroll();
	}
}

function ns4scroll() {
	if(ns4layer.top > 0 && ns4layer.top <= sspeed) {
		ns4layer.top = 0;
		setTimeout("ns4scroll()",spause);
	} 
	else if(ns4layer.top >= sizeup * -1) { 
		ns4layer.top -= sspeed;
		setTimeout("ns4scroll()",100);
	}
	else { 
		if(ii == singletext.length-1) ii = 0;
		else ii++;
		ns4layer.top = sheight;
		ns4layer.document.write(singletext[ii]);
		ns4layer.document.close();
		sizeup = ns4layer.document.height;
		ns4scroll();
	}
}

function ns6scroll() {
		if(parseInt(ns6div.style.top) > 0 && parseInt(ns6div.style.top) <= sspeed) { 
			ns6div.style.top=0;
			setTimeout("ns6scroll()",spause);
		} 
		else if(parseInt(ns6div.style.top)>=sizeup*-1) { 
			ns6div.style.top = parseInt(ns6div.style.top) - sspeed;
			setTimeout("ns6scroll()",100);
		}
		else { 
			if(ii == singletext.length - 1) ii=0;
			else ii++;
			ns6div.style.top = sheight;
			ns6div.innerHTML = singletext[ii];
			sizeup = ns6div.offsetHeight;
			ns6scroll();
		}
}

function executar() {
	if (document.layers) { 
		document.write('<ilayer id="ns4div" width='+ swidth +' height=' + sheight + ' bgcolor=' + sbcolor + '><layer id="ns4div1" width=' + swidth + ' height=' + sheight + ' onmouseover="sspeed=0;" onmouseout="sspeed=rspeed;"></layer></ilayer>')
	}
	
	if(document.getElementById || document.all) {
		document.write('<div style="position:relative; overflow:hidden; width:' + swidth + '; height:' + sheight + '; clip:rect(0 ' + swidth + ' ' + sheight + ' 0); background-color:' + sbcolor + '; " onmouseover="sspeed=0; " onmouseout="sspeed=rspeed"><div id="iens6div" style="position:relative; width:' + swidth + ';"></div></div>');
	}
}


//-- end Algorithm -->