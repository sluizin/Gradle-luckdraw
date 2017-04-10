<#--
1:赤
2:橙
3:黄
4:绿
5:蓝
6:靛
7:紫
8:金
9:蓝绿
-->
<#macro FMEshow_rightfloat style email emailtitle qq tel >
<link rel="stylesheet" type="text/css" href="${sj_staticPath!}/work/freemark_block_right_float/style${style?default('1')}/css/css.css"/>
<@FMEshow_rightfloat_Inc_email email=email emailtitle=emailtitle sourcepath="" />
<@FMEshow_rightfloat_Inc_tel tel=tel  sourcepath="${sj_sourcePath!}"/>
<@FMEshow_rightfloat_Inc_QQ_1 qq=qq  style=style />
</#macro>
<#macro FMEshow_rightfloat_free style email emailtitle qq tel emailsourcepath telsourcepath>
<link rel="stylesheet" type="text/css" href="${sj_staticPath!}/work/freemark_block_right_float/style${style?default('1')}/css/css.css"/>
<@FMEshow_rightfloat_Inc_email email=email emailtitle=emailtitle sourcepath=emailsourcepath />
<@FMEshow_rightfloat_Inc_tel tel=tel  sourcepath=telsourcepath/>
<@FMEshow_rightfloat_Inc_QQ_1 qq=qq  style=style />
</#macro>
<#--
1:赤
-->
<#macro FMEshow_rightfloat2 style email emailtitle qq1name,qq1,qq2name,qq2 tel >
<link rel="stylesheet" type="text/css" href="${sj_staticPath!}/work/freemark_block_right_float/doublestyle${style?default('1')}/css/css.css"/>
<@FMEshow_rightfloat_Inc_email email=email emailtitle=emailtitle sourcepath="" />
<@FMEshow_rightfloat_Inc_tel tel=tel  sourcepath="${sj_sourcePath!}"/>
<@FMEshow_rightfloat_Inc_QQ_2 style=style qq1name=qq1name qq1=qq1 qq2name=qq2name qq2=qq2 />
</#macro>

<#function getSplit splitString num i>
<#local  numi=0/>
<#list splitString?split(",") as name>
<#if numi==num>
</#if>
</#list>
</#function>

<#--
---------------------------------------------------------------------
---------------------------------------------------------------------
---------------------------------------------------------------------
------------------------------------代码块---------------------------
---------------------------------------------------------------------
---------------------------------------------------------------------
---------------------------------------------------------------------
-->
<#--	电话	热线	-->
<#macro FMEshow_rightfloat_Inc_tel tel sourcepath>
	<#if tel?length gt 0>
		<#if sourcepath?length == 0>
			<#assign sourcepath="..">
		</#if>
		<!-- 电话热线 -->
		<style type="text/css">
		.after_phone_right { width: 80px; height: 176px; background: url(${sourcepath!}/images/after_phone.png) no-repeat top left; position: fixed; right: 0; top: 50%; margin-top: -105px; z-index: 9999999; }
		.M.after_phone_right { width: 140px; height: 308px; background: url(${sourcepath!}/images/after_phone_w.png) no-repeat top left; position: fixed; right: 0; top: 50%; margin-top: -180px; z-index: 9999999; }
		</style>
		<a href="tel:${tel!}"><div class="after_phone_right J-target"></div></a>
	</#if>
</#macro>
<#--						-->
<#--	邮件	提交表单	-->
<#--						-->
<#macro FMEshow_rightfloat_Inc_email email emailtitle sourcepath>
<#if email?length gt 0>
		<#if sourcepath?length gt 0>
		<!-- 报名 -->
		<style type="text/css">
			.buy_phone_right { width: 80px; height: 176px; background: url(${sourcepath!}/images/buy_phone.png) no-repeat top left; position: fixed; right: 0; top: 50%; margin-top: -290px; z-index: 9999999; }
			.M.buy_phone_right { width: 140px; height: 308px; background: url(${sourcepath!}/images/buy_phone_w.png) no-repeat top left; position: fixed; right: 0; top: 50%; margin-top: -500px; z-index: 9999999; }
		</style>
		</#if>
	<div class="buy_phone_right J-target J-wf-btn"></div>
	<#include "/common/freemark_popup.ftl">
	<@FMEshowpopup inttype="29" left="" top="" title="提交信息" inputsplit="company:2|name:2|mobile:2|qq:0|email:1|account:0|tel:0|address:0|product_name:0|industry:0|identity:0|jobposition:0|remarks:1" issendemail=true sendEmailTitle=emailtitle sendEmailAddress=email isVerificationCode=false/>
</#if>
</#macro>
<#--						-->
<#--	QQ	1个QQ			-->
<#--						-->
<#macro FMEshow_rightfloat_Inc_QQ_1 qq style>
	<#if qq?length gt 0>
		<!-- 咨询 -->
		<style type="text/css">		
			/*咨询*/
			.aqbg_right { width: 80px; height: 176px; background: url(${sj_staticPath!}/work/freemark_block_right_float/style${style?default('1')}/images/aq.png) no-repeat top left; position: fixed; right: 0; top: 50%; margin-top: 80px; z-index: 9999999; }
			.aqbg_right a { display: block; width: 67px; height: 90px; padding-top: 86px; padding-left: 13px; }
			.bIren2_right { overflow: hidden; position: relative; width: 59px; height: 77px; }
			#wk-Nimg_right { left: 0; position: absolute; top: 0; }

			.M.aqbg_right { width: 140px; height: 308px; background: url(${sj_staticPath!}/work/freemark_block_right_float/style${style?default('1')}/images/aq_w.png) no-repeat top left; position: fixed; right: 0; top: 50%; margin-top: 140px; z-index: 9999999; }
			.M.aqbg_right a { display: block; width: 100px; height: 158px; padding-top: 150px; padding-left: 40px; }
			.M.bIren2_right { overflow: hidden; position: relative; width: 100px; height: 144px; }
			.M #wk-Nimg_right { left: -14px; position: absolute; top: 6px; width: 100px; height: 130px; }		
		</style>		
		<div class="aqbg_right J-target">
		    <a href="http://wpa.qq.com/msgrd?v=3&amp;uin=${qq!}&amp;site=qq&amp;menu=yes" target="_blank">
		        <div class="bIren2_right J-target">
		            <img alt="" src="${sj_staticPath!}/work/freemark_block_right_float/style${style?default('1')}/images/wk-Nimg01.png" id="wk-Nimg_right">
		        </div>
		    </a>
		</div>
		<script type="text/javascript">
		$(function() {
		  	//右侧小牛转动 2016.08.08 
			$(function (){
				var J_wkN = document.getElementById('wk-Nimg_right');
				var number=0;
				setInterval(
					function (){
						J_wkN.src='${sj_staticPath!}/work/freemark_block_right_float/style${style?default('1')}/images/wk-Nimg0'+(number%4+1)+'.png';
						number++;
					},400);
			})
			//兼容手机
			var ua = navigator.userAgent;
			var isIphone = ua.match(/(iPhone\sOS)\s([\d_]+)/);
			var isAndroid = ua.match(/(Android)\s+([\d.]+)/);
			var isMobile = isIphone || isAndroid;
				if(isMobile) {
					$(".J-target").addClass('M');
				} else {
				};
		});
		</script>
	</#if>
</#macro>
<#--						-->
<#--	QQ	2个QQ			-->
<#--						-->
<#macro FMEshow_rightfloat_Inc_QQ_2 style,qq1name,qq1,qq2name,qq2>
	<!-- 咨询 -->
        <div class="aqbg2_right J-target">
            <a href="http://wpa.qq.com/msgrd?v=3&amp;uin=${qq1!}&amp;site=qq&amp;menu=yes" target="_blank"><!--${qq1!}-->
                <div class="bIren_right J-target">
                    <img src="${sj_staticPath!}/work/freemark_block_right_float/doublestyle${style?default('1')}/images/wk-Nimg01.png" id="wk-Nimg_right">
                </div>
                <p>${qq1name!}</p>
            </a>
            <a href="http://wpa.qq.com/msgrd?v=3&amp;uin=${qq2!}&amp;site=qq&amp;menu=yes" target="_blank"><!--${qq2!}-->
                <div class="bIren_right J-target">
                    <img src="${sj_staticPath!}/work/freemark_block_right_float/doublestyle${style?default('1')}/images/wk-Nimg01.png" id="wk-Nimg2_right">
                </div>
                <p>${qq2name!}</p>
            </a>
        </div>
	<script type="text/javascript">
		$(function (){
			var J_wkN = document.getElementById('wk-Nimg_right');
			var number=0;
			setInterval(
				function (){
					J_wkN.src='${sj_staticPath!}/work/freemark_block_right_float/doublestyle${style?default('1')}/images/wk-Nimg0'+(number%4+1)+'.png';
					number++;
				},400);
			
			//兼容手机
			var ua = navigator.userAgent;
			var isIphone = ua.match(/(iPhone\sOS)\s([\d_]+)/);
			var isAndroid = ua.match(/(Android)\s+([\d.]+)/);
			var isMobile = isIphone || isAndroid;
				if(isMobile) {
					$(".J-target").addClass('M');
				} else {
				};
		})
		$(function (){
			var J_wkN2 = document.getElementById('wk-Nimg2_right');
			var number=0;
			setInterval(
				function (){
					J_wkN2.src='${sj_staticPath!}/work/freemark_block_right_float/doublestyle${style?default('1')}/images/wk-Nimg0'+(number%4+1)+'.png';
					number++;
				},400);
		})
	</script>
</#macro>
<#macro FMEshow_rightfloat_Inc_toprightstyle top right>
	<#if top?length gt 0 || right?length gt 0> style="<#if top?length gt 0>top:${top!};</#if><#if right?length gt 0>right:${right!};</#if>"
	</#if>
</#macro>
