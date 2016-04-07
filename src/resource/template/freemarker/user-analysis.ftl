<#setting locale="zh_CN">
<#setting number_format="#"> 

{
	
		"series":[
				<#assign flag=false/>
				{
					"name":"regusercnt",
					"data":[
						 <#list aggregations['2'].buckets as b>
							<#if flag>,<#else>
							<#assign flag=true/></#if>
							<#if b['1'].value??>${b['1'].value}<#else>0</#if>
						</#list>
					]
				},
				
				<#assign flag1=false/>
				{
					"name":"loginusercnt",
					"data":[
						<#list aggregations['2'].buckets as b>
							<#if flag1>,<#else>
							<#assign flag1=true/></#if>
							<#if b['3'].value??>${b['3'].value}<#else>0</#if>
						</#list>
					 ]
				},
				<#assign flag2=false/>
				{
						"name":"payusercnt",
						"data":[
								 <#list aggregations['2'].buckets as b>
									<#if flag2>,<#else>
									<#assign flag2=true/></#if>
									<#if b['4'].value??>${b['4'].value}<#else>0</#if>
								</#list>
							]
				},
				<#assign flag3=false/>
				{
						"name":"paycnt",
						"data":[
								 <#list aggregations['2'].buckets as b>
									<#if flag3>,<#else>
									<#assign flag3=true/></#if>
									<#if b['5'].value??>${b['5'].value}<#else>0</#if>
								 </#list>
							]
				},
				<#assign flag4=false/>
				{
					"name":"ydrate",
					"data":[
							<#list aggregations['2'].buckets as b>
								<#if flag4>,<#else>
								<#assign flag4=true/></#if>
								<#if b['6'].value??>${b['6'].value}<#else>0</#if>
							</#list>
						]
				},
				<#assign flag5=false/>
				{
					"name":"tdrate",
					"data":[
							 <#list aggregations['2'].buckets as b>
								<#if flag5>,<#else>
								<#assign flag5=true/></#if>
								<#if b['7'].value??>${b['7'].value}<#else>0</#if>
							</#list>
						]
				},
				<#assign flag6=false/>
				{
					"name":"sdrate",
					"data":[
							 <#list aggregations['2'].buckets as b>
								<#if flag6>,<#else>
								<#assign flag6=true/></#if>
								<#if b['8'].value??>${b['8'].value}<#else>0</#if>
							</#list>
						]
				}
		]
}
