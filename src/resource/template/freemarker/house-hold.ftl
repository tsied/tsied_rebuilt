<#setting locale="zh_CN">
{
	"date":
		[
			<#list aggregations['2'].buckets as b>
				
				"${b.key_as_string?date("yyyy-MM-dd")}"
				<#if b?has_next>,</#if>
			</#list>
			
		],
		"series":[
		
					<#assign flag=false/>
					{
						"name":"PV",
						"data":[
							 <#list aggregations['2'].buckets as b>
									<#if flag>,<#else>
									<#assign flag=true/></#if>
									${b['1'].value}
							 </#list>
						]
					},
					<#assign flag1=false/>
					{
						"name":"UV",
						"data":[
							 <#list aggregations['2'].buckets as b>
									<#if flag1>,<#else>
									<#assign flag1=true/></#if>
									${b['3'].value}
							 </#list>
						]
					}
	
				]
}
