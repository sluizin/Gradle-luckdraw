<#assign ACC_sj_dbPath=sj_sourcePath+"/db">
<#function getSjFreemarkerActiveList type sourcefile autochange>
        <#assign list="">
		<@sjAction type=type sourcefile=sourcefile autochange=autochange;listAA>
			<#assign list=listAA/>
		</@sjAction>
  		<#return list>
</#function>
<#--
	以element0Arrays数组的里下标为标准进行输出
	把BeanLine的一个单位里的所有属性以{@XXX}替换
-->
<#macro showSjFMBeanLineListHtml dblist BeanLineHtml element0Arrays>
	<#list dblist as bean>
		<#if checkArray(element0Arrays,bean.element0)>
			${getSjFMBeanLineHtml(BeanLineHtml,bean)}		
		</#if>
	</#list>
</#macro>
<#--
	判断数组中是否含此关键字
-->
<#function checkArray Arrays key>
	<#list Arrays as e>
		<#if e==key>
  			<#return true>
		</#if>
	</#list>
  	<#return false>
</#function>

<#--
	把BeanLine的一个单位里的所有属性以{@XXX}替换
-->
<#function getSjFMBeanLineHtml htmlStrSource BeanLine>
	<#local htmlStr=htmlStrSource>
	<#local htmlStr=htmlStr?replace("{@dbPath}",ACC_sj_dbPath?default(''))>
	<#local htmlStr=htmlStr?replace("{@element0}",BeanLine.element0?default(''))>
	<#local htmlStr=htmlStr?replace("{@element1}",BeanLine.element1?default(''))>
	<#local htmlStr=htmlStr?replace("{@element2}",BeanLine.element2?default(''))>
	<#local htmlStr=htmlStr?replace("{@element3}",BeanLine.element3?default(''))>
	<#local htmlStr=htmlStr?replace("{@element4}",BeanLine.element4?default(''))>
	<#local htmlStr=htmlStr?replace("{@element5}",BeanLine.element5?default(''))>
	<#local htmlStr=htmlStr?replace("{@element6}",BeanLine.element6?default(''))>
	<#local htmlStr=htmlStr?replace("{@element7}",BeanLine.element7?default(''))>
	<#local htmlStr=htmlStr?replace("{@element8}",BeanLine.element8?default(''))>
	<#local htmlStr=htmlStr?replace("{@element9}",BeanLine.element9?default(''))>
	<#local htmlStr=htmlStr?replace("{@element10}",BeanLine.element10?default(''))>
  	<#return htmlStr>
</#function>