<h1>布尔类型</h1>
${flag?c}<br>
${flag?string}<br>
${flag?string("yes", "no")}<br>
<#--
    true
    true
    yes
-->

<h1>日期类型</h1>
${createDate?date} <br>
${createDate?time} <br>
${createDate?datetime} <br>
${createDate?string("yyyy年MM月dd日 HH:mm:ss")} <br>
<#--
    2023年5月9日
    下午9:58:30
    2023年5月9日 下午9:58:30
    2023年05月09日 21:58:30
-->

<#--数值类型-->
<h1>数值类型</h1>
<#-- 直接输出数值型 -->
${age} <br>
${salary} <br>
<#-- 将数值转换成字符串输出 -->
${salary?c} <br>
<#-- 将数值转换成货币类型的字符串输出 -->
${salary?string.currency} <br>
<#-- 将数值转换成百分比类型的字符串输出 -->
${avg?string.percent} <br>
<#-- 将浮点型数值保留指定小数位输出 （##表示保留两位小数） -->
${avg?string["0.##"]} <br>
<#--
    18
    10,000
    10000
    ¥10,000.00
    55%
    0.55
-->

<h1>sequence类型</h1>
<#list books as book>
    ${book.id}&nbsp;
    ${book.name}&nbsp;
    ${book.author}<br>
</#list>

<h1>hash类型</h1>
<#list map?keys as key>
    <#if key == "first">
        ${key} -- ${map[key]}
    </#if>
    <br>
</#list>
