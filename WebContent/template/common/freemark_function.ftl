<#function left str len>
	<#if (len <= 0 || len >= str?length)>
  		<#return str>
	</#if>
	<#local leftStr=str?substring(0,len)>	
  	<#return leftStr>
</#function>
<#function right str len>
	<#local allLength=str?length>
	<#if (allLength <= len)>
  		<#return str>
	</#if>
	<#local rightStr=str?substring(allLength-len,allLength)>	
  	<#return rightStr>
</#function>
<#--
	路径的回退
-->
<#function pathBack path num>
	<#local newPath = "">
	<#local i=0>
	<#list path?split("/") as name>
		<#local i=i+1>
	</#list>
	<#local size=i-num>
	<#if (size > 0)>
		<#local i=0>
		<#list path?split("/") as name>
			<#local i=i+1>
			<#if (i <= size)>
				<#if (i > 1)>
					<#local newPath = newPath + "/">
				</#if>
				<#local newPath = newPath + name>
			</#if>
		</#list>	
	</#if>
  	<#return newPath>
</#function>