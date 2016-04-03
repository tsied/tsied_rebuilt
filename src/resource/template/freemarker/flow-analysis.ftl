<#setting locale="zh_CN">
<#assign names=[]/>



<#list aggregations['2'].buckets as b>
	     <#if !names?seq_contains(b.key)>
	     	<#assign names+=[b]/>
	     </#if>
 
</#list>


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
					"name":"pv",
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
					"name":"uv",
					"data":[
						 <#list aggregations['2'].buckets as b>
								<#if flag1>,<#else>
								<#assign flag1=true/></#if>
								${b['3'].value}
						 </#list>
					 ]
				},
				<#assign flag1=false/>
				{
						"name":"ipStats",
						"data":[
								 <#list aggregations['2'].buckets as b>
										<#if flag1>,<#else>
										<#assign flag1=true/></#if>
										${b['4'].value}
								</#list>
							]
				},
				<#assign flag1=false/>
				{
						"name":"sessionStat",
						"data":[
								 <#list aggregations['2'].buckets as b>
										<#if flag1>,<#else>
										<#assign flag1=true/></#if>
										${b['5'].value}
								</#list>
							]
				},
				<#assign flag1=false/>
				{
						"name":"exitSessionCount",
						"data":[
								 <#list aggregations['2'].buckets as b>
										<#if flag1>,<#else>
										<#assign flag1=true/></#if>
										<#if b['9'].value==0 ||  b['5'].value ==0>0<#else>${b['9'].value/b['5'].value}</#if>
								</#list>
							]
				},
				<#assign flag1=false/>
				{
						"name":"bounceSessionCount",
						"data":[
								 <#list aggregations['2'].buckets as b>
										<#if flag1>,<#else>
										<#assign flag1=true/></#if>
										<#if b['10'].value == 0 || b['5'].value == 0 >0<#else>${b['10'].value/b['5'].value}</#if>
								</#list>
							]
				}

		]
}
