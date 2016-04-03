<#setting locale="zh_CN">
<#setting number_format="#"> 
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
						 		 <#list b['8'].buckets as c>
										<#if flag>,<#else>
										<#assign flag=true/></#if>
										${c['1'].value}
						 		 </#list>
						</#list>
					]
				},
				
				<#assign flag1=false/>
				{
					"name":"uv",
					"data":[
						<#list aggregations['2'].buckets as b>
						 		 <#list b['8'].buckets as c>
										<#if flag1>,<#else>
										<#assign flag1=true/></#if>
										${c['3'].value}
						 		 </#list>
						</#list>
					 ]
				},
				<#assign flag2=false/>
				{
						"name":"ipStats",
						"data":[
								 <#list aggregations['2'].buckets as b>
							 		 <#list b['8'].buckets as c>
											<#if flag2>,<#else>
											<#assign flag2=true/></#if>
											${c['4'].value}
							 		 </#list>
								</#list>
							]
				},
				<#assign flag3=false/>
				{
						"name":"sessionStat",
						"data":[
								 <#list aggregations['2'].buckets as b>
							 		 <#list b['8'].buckets as c>
											<#if flag3>,<#else>
											<#assign flag3=true/></#if>
											${c['5'].value}
							 		 </#list>
								 </#list>
							]
				},
				<#assign flag4=false/>
				{
					"name":"exitSessionCount",
					"data":[
							<#list aggregations['2'].buckets as b>
						 		 <#list b['8'].buckets as c>
										<#if flag4>,<#else>
										<#assign flag4=true/></#if>
										<#if c['6'].value==0.0 ||  c['5'].value ==0.0>0<#else>${c['6'].value/c['5'].value}</#if>
						 		 </#list>
							</#list>
						]
				},
				<#assign flag5=false/>
				{
					"name":"bounceSessionCount",
					"data":[
							 <#list aggregations['2'].buckets as b>
						 		 <#list b['8'].buckets as c>
										<#if flag5>,<#else>
										<#assign flag5=true/></#if>
										<#if c['7'].value==0.0 ||  c['5'].value ==0.0>0<#else>${c['7'].value/c['5'].value}</#if>
						 		 </#list>
							</#list>
						]
				},
				<#assign flag6=false/>
				{
					"name":"sessionTime",
					"data":[
							 <#list aggregations['2'].buckets as b>
						 		 <#list b['8'].buckets as c>
										<#if flag6>,<#else>
										<#assign flag6=true/></#if>
										<#if c['9'].value??>${c['9'].value}<#else>0</#if>
						 		 </#list>
							</#list>
						]
				},
				<#assign flag7=false/>
				{
					"name":"reqPages",
					"data":[
							 <#list aggregations['2'].buckets as b>
						 		 <#list b['8'].buckets as c>
										<#if flag7>,<#else>
										<#assign flag7=true/></#if>
										<#if c['10'].value??>${c['10'].value}<#else>0</#if>
						 		 </#list>
							</#list>
						]
				}

		]
}
