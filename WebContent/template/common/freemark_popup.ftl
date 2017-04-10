<#-- FMEshowpopup 
'' --- 23
'' ---div:left --  20px
'' ---div:top  --  100px
'报名信息'
'' ---"company:2|name:2|mobile:2|qq:0|email:2|account:0|tel:2|address:0|product_name:0|industry:0|identity:0|jobposition:2"
false isemail
'' 味丰半成品原料招商报名信息
'' baolubo@99114.com
true 是否需要验证码
-->
<#macro FMEshowpopup inttype left top title inputsplit issendemail sendEmailTitle sendEmailAddress isVerificationCode>
<#assign inputArray=inputsplit>
<#assign actionurl="">
<#if issendemail>
	<#assign actionurl=request.contextPath+"/sendemail/send">
<#else>
	<#if isVerificationCode>
		<#assign actionurl=request.contextPath+"/saveform/saveVeriCode">
	<#else>
		<#assign actionurl=request.contextPath+"/saveform/save">
	</#if>
</#if>
<#assign bool="">
	<!--  公共弹出框 -->
	<link rel="stylesheet" href="${sj_staticPath!}/work/freemark_popup/css/css.css" />
	<!--  公共弹出框 -->
	<div class="M-popup-wrap J-M-popup-wrap J-target" style="<#if left?length gt 0>left:${left!};</#if><#if top?length gt 0>top:${top!};</#if>">
		<form id="Formsend" name="Formsend"  action="${actionurl!}" method="post">
		<#if !issendemail>
				<input id="inttype" name="inttype" type="hidden" value="${inttype!"0"}" />		
		<#else>		
				<input id="sendEmailTitle" name="sendEmailTitle" type="hidden" value="${sendEmailTitle!}" />
				<input id="sendEmailAddress" name="sendEmailAddress" type="hidden" value="${sendEmailAddress!}" />		
		</#if>
		<div class="M-popup-down J-M-popup-down J-target"></div>
		<div class="M-popup-up J-M-popup-up J-target">
			<div class="M-popup-title">${title!"提交信息"}</div>
			<#assign bool= value("company")>
			<#if (bool>0)>
				<div class="M-popup-line J-target">
					<span><#if (bool=2)><em>*</em></#if>公司全称:</span>
					<div class="M-pupup-inputWrap J-target">
						<input type="text" id="company" name="company" maxlength="25" value="" />
					</div>
				</div>
				<div class="M-popup-error-text J-target"></div>
			</#if>
			
			<#assign bool= value("address")>
			<#if (bool>0)>
				<div class="M-popup-line J-target">
					<span><#if (bool=2)><em>*</em></#if>公司地址:</span>
					<div class="M-pupup-inputWrap J-target">
						<input type="text"  id="address" name="address" maxlength="50" value="" />
					</div>
				</div>
				<div class="M-popup-error-text J-target"></div>
			</#if>

			<#assign bool= value("industry")>
			<#if (bool>0)>
				<div class="M-popup-line J-target">
					<span><#if (bool=2)><em>*</em></#if>所属行业:</span>
					<div class="M-pupup-inputWrap J-target">
						<input type="text" id="industry" name="industry" maxlength="25" value="" />
					</div>
				</div>
				<div class="M-popup-error-text J-target"></div>
			</#if>
			
			<#assign bool= value("name")>
			<#if (bool>0)>
				<div class="M-popup-line J-target">
					<span><#if (bool=2)><em>*</em></#if>联系人:</span>
					<div class="M-pupup-inputWrap J-target">
						<input type="text" id="name" name="name" maxlength="25" value="" />
					</div>
				</div>
				<div class="M-popup-error-text J-target"></div>
			</#if>
			
			<#assign bool= value("jobposition")>
			<#if (bool>0)>
				<div class="M-popup-line J-target">
					<span><#if (bool=2)><em>*</em></#if>职位:</span>
					<div class="M-pupup-inputWrap J-target">
						<input type="text" id="jobposition" name="jobposition" maxlength="25" value="" />
					</div>
				</div>
				<div class="M-popup-error-text J-target"></div>
			</#if>
			
			<#assign bool= value("mobile")>
			<#if (bool>0)>
				<div class="M-popup-line J-target">
					<span><#if (bool=2)><em>*</em></#if>手机:</span>
					<div class="M-pupup-inputWrap J-target">
						<input type="text" id="mobile" name="mobile" maxlength="25" value="" />
					</div>
				</div>
				<div class="M-popup-error-text J-target"></div>
			</#if>

			<#assign bool= value("tel")>
			<#if (bool>0)>
				<div class="M-popup-line J-target">
					<span><#if (bool=2)><em>*</em></#if>座机:</span>
					<div class="M-pupup-inputWrap J-target">
						<input type="text" id="tel" name="tel" maxlength="25" value="" />
					</div>
				</div>
				<div class="M-popup-error-text J-target"></div>
			</#if>
			
			<#assign bool= value("qq")>
			<#if (bool>0)>
				<div class="M-popup-line J-target">
					<span><#if (bool=2)><em>*</em></#if>QQ:</span>
					<div class="M-pupup-inputWrap J-target">
						<input type="text" id="qq" name="qq" maxlength="25" value="" />
					</div>
				</div>
				<div class="M-popup-error-text J-target"></div>			
			</#if>
			
			<#assign bool= value("email")>
			<#if (bool>0)>
				<div class="M-popup-line J-target">
					<span><#if (bool=2)><em>*</em></#if>邮箱:</span>
					<div class="M-pupup-inputWrap J-target">
						<input type="text" id="email" name="email" maxlength="25" value="" />
					</div>
				</div>
				<div class="M-popup-error-text J-target"></div>
			</#if>
			
			<#assign bool= value("identity")>
			<#if (bool>0)>
				<div class="M-popup-line J-target">
					<span><#if (bool=2)><em>*</em></#if>身份:</span>
					<div class="M-pupup-inputWrap J-target">
						<input type="text" id="identity" name="identity" maxlength="25" value="" />
					</div>
				</div>
				<div class="M-popup-error-text J-target"></div>
			</#if>
			
			<#assign bool= value("account")>
			<#if (bool>0)>
				<div class="M-popup-line J-target">
					<span><#if (bool=2)><em>*</em></#if>会员账号:</span>
					<div class="M-pupup-inputWrap J-target">
						<input type="text" id="account" name="account" maxlength="25" value="" />
					</div>
				</div>
				<div class="M-popup-error-text J-target"></div>
			</#if>
			
			<#assign bool= value("remarks")>
			<#if (bool>0)>
				<div class="M-popup-more-line J-target">
					<span><#if (bool=2)><em>*</em></#if>备注:</span>
					<textarea id="remarks" name="remarks" cols="10" rows="5"></textarea>
				</div><!-- 
				<div class="M-popup-error-text J-target"></div>-->
			</#if>
			<#if isVerificationCode>
			<div class="M-popup-line J-target">
				<span><em>*</em>验证码:</span>
				<div class="M-pupup-inputWrapverification">
					<input type="text" id="verificationcode" name="verificationcode" maxlength="6" size="55" value="" />
					<span style="float: right;text-align: left;width: 60px;">${verificationcode!}</span>
				</div>
			</div>
			<div class="M-popup-error-text J-target"></div>
			</#if>
			<input class="M-popup-btn J-target" type="button" value="确认提交" />
		</div>
		<i></i>
		</form>	
	<script src="${sj_staticPath!}/work/freemark_popup/js/public-popup.js"></script>
        <script type="text/javascript">
			$(function(){	
				/*弹框*/
				$(".M-popup-btn").click( function(){
					formcommonsub();
					return;
				})
			});
		</script>
	</div>
	<script type="text/javascript">
	function formcommonsub() {
		var formthis = document.getElementById("Formsend");
		<#--        company        										-->
		<@showcheckFormInputElement "company","公司名称必须填写！"/>
		<#--        address        										-->
		<@showcheckFormInputElement "address","公司地址必须填写！"/>
		<#--        industry        										-->
		<@showcheckFormInputElement "industry","所属行业必须填写！"/>
		<#--        name        										-->
		<@showcheckFormInputElement "name","联系人必须填写！"/>
		<#--        jobposition        										-->
		<@showcheckFormInputElement "jobposition","职位必须填写！"/>		
		<#--        mobile        										-->
		<@showcheckFormInputElement "mobile","手机必须填写！"/>
		var myreg = /^13[0-9]{9}$|14[0-9]{9}$|15[0-9]{9}$|17[0-9]{9}$|18[0-9]{9}$|^[0-9]{3,4}-[0-9]{7,16}$/;
		<@showcheckFormInputElementRegular "mobile","输入有效的手机号码！！"/>
		<#--        tel        										-->
		<@showcheckFormInputElement "tel","座机号码必须填写！"/>
		var myreg = /(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
		<@showcheckFormInputElementRegular "tel","输入有效的座机号码！！"/>
		<#--        email        										-->
		<@showcheckFormInputElement "email","E_mail必须填写！"/>		
		var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		<@showcheckFormInputElementRegular "email","输入有效的E_mail！！"/>
		<#--        remarks        										-->
		<@showcheckFormInputText "remarks",300,"留言信息必须填写！"/>
		<#if isVerificationCode>
			var vericode="${verificationcode!}";
			var obj =document.getElementById("verificationcode");
			var vericodeinputvalue=obj.value.trim();
			if(vericode!=vericodeinputvalue){			
				changeError(obj,"请输入正确的验证码！");
				obj.focus();
				changecss(obj, true);
				return false;
			}else{
				changeError(obj,'');
				changecss(obj, false);
			}
		</#if>
		$.ajax({
			cache : true,
			type : "POST",
			url : "${actionurl!}",
			data : $('#Formsend').serialize(),// 你的formid
			async : false,
			error : function(request) {
				alert("请求失败!");
			},
			success : function(data) {
				//if (data) {
					clearInputAll();
					alert("发送成功!");
					//$(".J-tk").hide();
					$(".J-M-popup-wrap").hide();
				//}
			}
		});
		return;
	}
	</script>
</#macro>

<#function value key>
	<#if inputArray?index_of(key+":1")!=-1>
		<#return 1>
	</#if>
	<#if inputArray?index_of(key+":2")!=-1>
		<#return 2>
	</#if>
<#return 0>
</#function>
<#macro showcheckFormInputElement key,isSpaceAlert>
		<#assign bool= value(key)>
		<#if (bool=1 || bool=2)>
		<#assign too=true >
		<#if bool=2><#assign too=false></#if>
			if (checkFormInputElement(formthis.${key!}, "${isSpaceAlert!}",${too?string('true','false')})) { return; };
		</#if>
</#macro>
<#macro showcheckFormInputElementRegular key,isSpaceAlert>
		<#assign bool= value(key)>
		<#if (bool=1 || bool=2)>
		<#assign too=true >
		<#if bool=2><#assign too=false></#if>
			if (checkFormInputElementRegular(formthis.${key!}, myreg, "${isSpaceAlert!}", ${too?string('true','false')})) { return; };
		</#if>
</#macro>
<#macro showcheckFormInputText key,maxnum,isSpaceAlert>
		<#assign bool= value(key)>
		<#if (bool=1 || bool=2)>
		<#assign too=true >
		<#if bool=2><#assign too=false></#if>
			if (checkFormTextareaElement(formthis.${key!},${maxnum?default("0")}, "${isSpaceAlert!}",${too?string('true','false')})) { return; };
		</#if>
</#macro>