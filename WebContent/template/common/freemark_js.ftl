
<#macro FMEshow_Inc_js_autobabear picname imgid time>
<#list 1..4 as t>
			<img id="${imgid!}${t}" src="${sj_sourcePath!}/images/${picname}${t}.png" alt=""  style="display:none"/>
</#list>
<script type="text/javascript">    
$(function (){
	var number=0;
	var wkNimgiii;
	setInterval(
		function (){
			wkNimgiii=(number%4+1);
			for(i=1;i<5;i++){
					$("#${imgid!}"+i).hide();

			}
					$("#${imgid!}"+wkNimgiii).show();
			number++;
		},${time?default('0')});
})    
</script> 

</#macro>