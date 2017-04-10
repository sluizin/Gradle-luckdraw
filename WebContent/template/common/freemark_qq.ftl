<#macro FMEshowqq qq num right top>
<!--QQ-->
<link rel="stylesheet" type="text/css" href="${sj_staticPath!}/work/freemark_qq/style${num?default('1')}/css/css.css"/>
<div class="aqbgQQ" style="<#if right?length gt 0>right:${right!};</#if><#if top?length gt 0>top:${top!};</#if>">
	<a href="http://wpa.qq.com/msgrd?v=3&amp;uin=${qq!}&amp;site=qq&amp;menu=yes" target="_blank">
		<div class="bIrenQQ">
			<img id="animImgQQ" src="${sj_staticPath!}/work/freemark_qq/style${num?default('1')}/images/aqimg.png" />
		</div>
	</a>
</div>
<script type="text/javascript">
//图片动画效果
function playAnimQQ(speed) {
	var max = 2;
	var index = 1;
	var elem = document.getElementById("animImgQQ");
	var elemStyle = elem.style;
	var timer = window.setInterval(function() {
		if (index < max) {
		elemStyle.left = -(index * 66) + "px";
		index++;
		} else {
			index = 0;
		}
	}, speed);
}
playAnimQQ(200);
</script>
</#macro>
<#macro FMEshowqq2 qq num right top>
<!-- QQ -->
<link rel="stylesheet" type="text/css" href="${sj_staticPath!}/work/freemark_qq/commonstyle${num?default('1')}/css/css.css"/>
<a href="http://wpa.qq.com/msgrd?v=3&amp;uin=${qq!}&amp;site=qq&amp;menu=yes" target="blank"><div class="freemaker-apple-qq2" style="<#if right?length gt 0>right:${right!};</#if><#if top?length gt 0>top:${top!};</#if>"></div></a>
</#macro>
<#macro FMEshowqq3 qq num right top>
<link rel="stylesheet" type="text/css" href="${sj_staticPath!}/work/freemark_qq/style3_${num?default('1')}/css/css.css"/>
    <a target="blank" href="http://wpa.qq.com/msgrd?v=3&amp;uin=${qq!}&amp;site=qq&amp;menu=yes">
        <div class="H-qq-QQ" style="<#if right?length gt 0>right:${right!};</#if><#if top?length gt 0>top:${top!};</#if>">
            <div class="H-qq-text-QQ"></div>
        </div>
    </a>
</#macro>