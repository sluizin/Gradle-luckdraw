<#assign acc_fm_right_i=0>
<#assign acc_fm_right_email=false>
<#assign acc_fm_right_tel=false>
<#assign acc_fm_right_qq=false>
<#--
数量:1
['-76px','-154px']
数量:2
1:['-135px','-312px']
2:['0px','0px']
-->
<#assign acc_final_fm_right_magintop_1_1 = ['-76px','-154px'] >
<#assign acc_final_fm_right_magintop_2_1 = ['-135px','-312px'] >
<#assign acc_final_fm_right_magintop_2_2 = ['0px','0px'] >





<#assign acc_fm_right_magintop_email = ['-210px','-500px'] >
<#assign acc_fm_right_magintop_tel = ['-75px','-180px'] >
<#assign acc_fm_right_magintop_qq = ['60px','140px'] >

<#macro FMEshow_right_common style email emailtitle tel qq>
<#if email?length gt 0>
	<@FMEshow_right_Inc_email style=style type=1 email=email emailtitle=emailtitle />
</#if>
<#if tel?length gt 0>
	<@FMEshow_right_Inc_tel tel=tel sourcepath="${sj_sourcePath!}"/>
</#if>
<#if qq?length gt 0>
	<@FMEshow_right_Inc_QQ_1 style=style qq=qq/>
</#if>
</#macro>


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
<#--
---------------------------------------------------------------------
---------------------------------------------------------------------
---------------------------------------------------------------------
------------------------------------代码块---------------------------
---------------------------------------------------------------------
---------------------------------------------------------------------
---------------------------------------------------------------------
-->
<#--						-->
<#--	邮件	提交表单	-->
<#--						-->
<#--
type:
0:我要报名[提交报名表单]
1:批发采购通道[提交报名表单]
-->
<#macro FMEshow_right_Inc_email style type email emailtitle>
<#assign acc_fm_right_email=true>
<#assign acc_fm_right_i=acc_fm_right_i+1>
<#if email?length gt 0>
<#local email=email?trim>
	<#local typefile="buy_phone"/>
	<#if type==0>
		<#local typefile="signup"/>
	</#if><!-- 报名 -->
		<style type="text/css">
			.buy_phone_right { width: 60px; height: 132px; background: url(${sj_staticPath!}/work/freemark_block_right/style${style?default('1')}/${typefile}.png) no-repeat top left; position: fixed; right: 0; top: 50%; margin-top: ${acc_fm_right_magintop_email[0]?default('-210px')}; z-index: 9999999; }
			.M.buy_phone_right { width: 140px; height: 308px; background: url(${sj_staticPath!}/work/freemark_block_right/style${style?default('1')}/${typefile}_w.png) no-repeat top left; position: fixed; right: 0; top: 50%; margin-top: ${acc_fm_right_magintop_email[1]?default('-500px')}; z-index: 9999999; }
		</style>
	<div class="buy_phone_right J-target J-wf-btn"></div>
	<#include "/common/freemark_popup.ftl">
	<@FMEshowpopup inttype="29" left="" top="" title="提交信息" inputsplit="company:2|name:2|mobile:2|qq:0|email:1|account:0|tel:0|address:0|product_name:0|industry:0|identity:0|jobposition:0|remarks:1" issendemail=true sendEmailTitle=emailtitle sendEmailAddress=email isVerificationCode=false/>
</#if>
</#macro>
<#--						-->
<#--	电话	热线	    -->
<#--						-->
<#macro FMEshow_right_Inc_tel tel sourcepath>
<#assign acc_fm_right_tel=true>
<#assign acc_fm_right_i=acc_fm_right_i+1>
	<#if tel?length gt 0>
		<#local tel=tel?trim>
		<!-- 电话热线 -->
		<style type="text/css">
			.after_phone_right { width: 60px; height: 132px; background: url(${sourcepath!}/images/after_phone.png) no-repeat top left; position: fixed; right: 0; top: 50%; margin-top: ${acc_fm_right_magintop_tel[0]?default('-75px')}; z-index: 9999999; }
			.M.after_phone_right { width: 140px; height: 308px; background: url(${sourcepath!}/images/after_phone_w.png) no-repeat top left; position: fixed; right: 0; top: 50%; margin-top: ${acc_fm_right_magintop_tel[1]?default('-180px')}; z-index: 9999999; }
		</style>
		<a href="tel:${tel!}"><div class="after_phone_right J-target"></div></a>
	</#if>
</#macro>
<#--						-->
<#--	QQ	1个QQ			-->
<#--						-->
<#macro FMEshow_right_Inc_QQ_1 style qq>
<#assign acc_fm_right_qq=true>
<#assign acc_fm_right_i=acc_fm_right_i+1>
	<#if qq?length gt 0>
		<#local qq=qq?trim>
		<!-- 咨询 -->
		<style type="text/css">		
			/*咨询*/
			.aqbg_right { width: 60px; height: 132px; background: url(${sj_staticPath!}/work/freemark_block_right/style${style?default('1')}/aq.png) no-repeat top left; position: fixed; right: 0; top: 50%; margin-top: ${acc_fm_right_magintop_qq[0]?default('60px')}; z-index: 9999999; }
			.aqbg_right a { display: block; width: 67px; height: 90px; padding-top: 66px; padding-left: 13px; }
			.bIren2_right { overflow: hidden; position: relative; width: 44px; height: 57px; }
			#wk-Nimg_right { left: 0; position: absolute; top: 0; }

			.M.aqbg_right { width: 140px; height: 308px; background: url(${sj_staticPath!}/work/freemark_block_right/style${style?default('1')}/aq_w.png) no-repeat top left; position: fixed; right: 0; top: 50%; margin-top: ${acc_fm_right_magintop_qq[1]?default('140px')}; z-index: 9999999; }
			.M.aqbg_right a { display: block; width: 100px; height: 158px; padding-top: 150px; padding-left: 40px; }
			.M.bIren2_right { overflow: hidden; position: relative; width: 100px; height: 144px; }
			.M #wk-Nimg_right { left: -14px; position: absolute; top: 6px; width: 100px; height: 130px; }		
		</style>		
		<div class="aqbg_right J-target">
		    <a href="http://wpa.qq.com/msgrd?v=3&amp;uin=${qq!}&amp;site=qq&amp;menu=yes" target="_blank">
		        <div class="bIren2_right J-target">

<#list 1..4 as t>
		            <img alt="" src="${sj_staticPath!}/work/freemark_block_right/static/wk-Nimg0${t}.png" id="wk-Nimg_right_${t}" style="display:none">
</#list>
<#list 1..4 as t>
		            <img alt="" src="${sj_staticPath!}/work/freemark_block_right/static/wk-Nimg0${t}-w.png" id="wk-Nimg_right_${t}-w" width="100" height="130" style="display:none">
</#list>
		        </div>
		    </a>
		</div>
		<script type="text/javascript">
		$(function() {
		  	//右侧小牛转动 2016.08.08 
			$(function (){
				//var J_wkN = document.getElementById('wk-Nimg_right');
				var number=0;
				var wkNimgiii;
				var ismobileext=isMobileext();
				setInterval(
					function (){
						//J_wkN.src='${sj_staticPath!}/work/freemark_block_right/static/wk-Nimg0'+(number%4+1)+'.png';
						wkNimgiii=(number%4+1);
						for(i=1;i<5;i++){
							if(i!=wkNimgiii){
								$("#wk-Nimg_right_"+i+ismobileext).hide();
							}else{
								$("#wk-Nimg_right_"+i+ismobileext).show();
							}
						}
						number++;
					},400);
			})
		});
		</script>
	</#if>
</#macro>
<#--						-->
<#--	QQ	2个QQ			-->
<#--						-->
<#macro FMEshow_right_Inc_QQ_2 style qq1name qq1 qq2name qq2>
	<!-- 咨询 -->
		<style type="text/css">
			.aqbg2_right { width: 60px; height:133px; background: url(${sj_staticPath!}/work/freemark_block_right/style${style?default('1')}/aq2.png) no-repeat top left; position: fixed; right: 0; top: 50%; margin-top: 60px; z-index: 9999999; padding-top: 62px;  }
			.aqbg2_right a { display: block; width: 54px; height: 62px;padding-left: 6px; font-size:14px; color:#FFF; }
			.aqbg2_right a:hover{ text-decoration:none; font-family:"Microsoft Yahei","Hiragino Sans GB"; }
			.aqbg2_right a p{font-family: "Microsoft Yahei",'Hiragino Sans GB';}
			.aqbg2_right a img{width: 34px; height: 44px;}	
			.bIren_right { overflow: hidden; position: relative; width: 34px; height: 44px; margin-left:10px; }
			
			.M.aqbg2_right { width: 140px; height:308px; background: url(${sj_staticPath!}/work/freemark_block_right/style${style?default('1')}/aq2_w.png) no-repeat top left; position: fixed; right: 0; top: 50%; margin-top:140px; z-index: 9999999; padding-top: 148px;  }
			.M.aqbg2_right a {display: block;width: 120px;height: 144px;padding-left: 20px;font-size: 14px;color: #FFF;margin-bottom: 5px;font-family:"Microsoft Yahei","Hiragino Sans GB";font-weight:normal;}
			.M.aqbg2_right a:hover{ text-decoration:none;font-family:"Microsoft Yahei","Hiragino Sans GB";font-weight:normal;}
			.M.aqbg2_right a img{ width: 80px; height: 108px;}
			.M.aqbg2_right a p{ padding-left: 10px;font-size: 24px;line-height: 36px;font-family:"Microsoft Yahei","Hiragino Sans GB";font-weight:normal;}	
			.M.bIren_right {overflow: hidden;position: relative;width: 80px;height: 108px;margin-left: 18px;}			
						
			#wk-Nimg_right,#wk-Nimg2_right { left: 0; position: absolute; top: 0; }			
			
		</style>
        <div class="aqbg2_right J-target">
            <a href="http://wpa.qq.com/msgrd?v=3&amp;uin=${qq1!}&amp;site=qq&amp;menu=yes" target="_blank"><!--${qq1!}-->
                <div class="bIren_right J-target">
                    <img src="${sj_staticPath!}/work/freemark_block_right/static//wk-Nimg01.png" id="wk-Nimg_right1_1">
                    <img src="${sj_staticPath!}/work/freemark_block_right/static//wk-Nimg02.png" id="wk-Nimg_right1_2" style="display:none">
                    <img src="${sj_staticPath!}/work/freemark_block_right/static//wk-Nimg03.png" id="wk-Nimg_right1_3" style="display:none">
                    <img src="${sj_staticPath!}/work/freemark_block_right/static//wk-Nimg04.png" id="wk-Nimg_right1_4" style="display:none">
                </div>
                <p>${qq1name!}</p>
            </a>
            <a href="http://wpa.qq.com/msgrd?v=3&amp;uin=${qq2!}&amp;site=qq&amp;menu=yes" target="_blank"><!--${qq2!}-->
                <div class="bIren_right J-target">
                    <img src="${sj_staticPath!}/work/freemark_block_right/static/wk-Nimg01.png" id="wk-Nimg_right2_1">
                    <img src="${sj_staticPath!}/work/freemark_block_right/static/wk-Nimg02.png" id="wk-Nimg_right2_2" style="display:none">
                    <img src="${sj_staticPath!}/work/freemark_block_right/static/wk-Nimg03.png" id="wk-Nimg_right2_3" style="display:none">
                    <img src="${sj_staticPath!}/work/freemark_block_right/static/wk-Nimg04.png" id="wk-Nimg_right2_4" style="display:none">
                </div>
                <p>${qq2name!}</p>
            </a>
        </div>
	<script type="text/javascript">
		$(function (){
			//var J_wkN = document.getElementById('wk-Nimg_right');
			//var J_wkN2 = document.getElementById('wk-Nimg2_right');
			var number=0;
			var wkNimg2iii;
			setInterval(
				function (){
					//J_wkN.src='${sj_staticPath!}/work/freemark_block_right/static/wk-Nimg0'+(number%4+1)+'.png'
					//J_wkN2.src='${sj_staticPath!}/work/freemark_block_right/static/wk-Nimg0'+(number%4+1)+'.png';
					wkNimg2iii=(number%4+1);
						for(i=1;i<5;i++){
							if(i!=wkNimg2iii){
								$("#wk-Nimg_right1_"+i).hide();
								$("#wk-Nimg_right2_"+i).hide();
							}else{
								$("#wk-Nimg_right1_"+i).show();
								$("#wk-Nimg_right2_"+i).show();
							}
						}					
					number++;
				},400);
		})
	</script>
</#macro>
	<script type="text/javascript">
		$(function() {
			//兼容手机
				if(isMobile()) {
					$(".J-target").addClass('M');
				};
		});
		function isMobile(){
			//兼容手机
			var ua = navigator.userAgent;
			var isIphone = ua.match(/(iPhone\sOS)\s([\d_]+)/);
			var isAndroid = ua.match(/(Android)\s+([\d.]+)/);
			var isMobile = isIphone || isAndroid;
			return isMobile;
		}
		function isMobileext(){
			//兼容手机
			if(isMobile()){
				return '-w';
			}
			return '';
		}
	</script>
<#if !(acc_fm_right_email && acc_fm_right_tel && acc_fm_right_qq)>
	<script type="text/javascript">
		$(function() {
			//兼容手机
				if(isMobile()) {
					//$(".J-target").addClass('Mam');
				}else{
					//$(".J-target").addClass('Ma');
				};
		});
	</script>
</#if>

