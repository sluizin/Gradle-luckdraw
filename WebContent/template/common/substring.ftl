<#macro substring theString MAXLength>
<#if theString?? && theString?length gt MAXLength>
     ${theString?substring(0,MAXLength)}…
<#else>
     ${theString}
</#if>
</#macro>